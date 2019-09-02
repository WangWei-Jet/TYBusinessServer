package com.whty.example.server.tms.ty;

import com.whty.example.utils.GPMethods;

/**
 * 天喻终端固件/参数下载请求
 * <p>
 * Title:TyTmsDownloadRequest
 * </p>
 * <p>
 * Description:请求下载固件/参数，目前不支持参数的下载
 * </p>
 * <p>
 * Date:2018年5月18日 上午10:50:29
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyTmsDownloadRequest extends TyTmsBaseRequest {
	// 任务ID
	String taskId;
	// 旧版本号
	String oldVersion;
	// 新版本号
	String newVersion;
	// 下载类型
	String type;
	// 起始位
	String start;
	// 长度
	String length;
	// 检验位
	String lrc;

	public TyTmsDownloadRequest(String completeRequestMessage, TyTmsBusinessProxy tyTmsBusinessProxy) {
		super(completeRequestMessage, tyTmsBusinessProxy);
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOldVersion() {
		return oldVersion;
	}

	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}

	public String getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	@Override
	public ValidateResult validate() {
		ValidateResult validateResult = super.validate();
		if (!validateResult.isValidated()) {
			return validateResult;
		}
		// logic coding
		if (getUnHandledRequestMessage() == null || getUnHandledRequestMessage().trim().length() == 0) {
			logger.debug("请求信息为空.");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		if (getUnHandledRequestMessage().length() < (8 + 20 + 20 + 1 + 8 + 8 + 1) * 2) {
			logger.debug("请求数据长度不足.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		setTaskId(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 8 * 2))));
		logger.info("任务id=>task id:" + getTaskId());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(8 * 2));
		setOldVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 20 * 2))));
		logger.info("旧版本号=>old version:" + getOldVersion());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(20 * 2));
		setNewVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 20 * 2))));
		logger.info("新版本号=>new version:" + getNewVersion());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(20 * 2));
		setType(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 1 * 2))));
		logger.info("下载类型=>type:" + getType());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(1 * 2));
		setStart(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 8 * 2))));
		logger.info("起始下载点=>start:" + getStart());
		if (!StringUtil.isNumeric(getStart())) {
			logger.debug("起始下载点不是数字格式");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(8 * 2));
		setLength(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 8 * 2))));
		logger.info("下载的数据长度=>length:" + getLength());
		if (!StringUtil.isNumeric(getLength())) {
			logger.debug("请求的下载数据长度不是数字格式");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(8 * 2));
		setLrc(getUnHandledRequestMessage().substring(0, 1 * 2));
		logger.info("lrc:" + getLrc());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(1 * 2));
		if (getUnHandledRequestMessage() != null && getUnHandledRequestMessage().length() > 0) {
			logger.debug("请求数据长度超长.EXTRA DATA FOUND");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		// 验证lrc
		String calLrc = GPMethods
				.doLrc(getCompleteRequestMessage().substring(0, getCompleteRequestMessage().length() - 2));
		if (!calLrc.equalsIgnoreCase(lrc)) {
			logger.info("lrc校验错误");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.LRC_WRONG);
			return validateResult;
		}
		// 任务id
		// 旧版本号，新版本号
		TyTmsTerminalInfo tyTmsTerminalInfo = getTyTmsBusinessProxy().getDeviceSoftwareInfo(getDeviceSn(),
				getDeviceTerminalNo(), getTaskId(), getOldVersion(), getNewVersion());
		if (tyTmsTerminalInfo == null) {
			// 软件任务不存在
			logger.info("软件任务不存在,请求软件版本与任务id不匹配");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_VERSION_INCONSISTENT);
			return validateResult;
		}
		// 下载类型(目前仅支持固件下载)
		if (!getType().equals("0")) {
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.NOT_SUPPORTED);
			return validateResult;
		}
		// 起始下载点，下载的数据长度 (获取固件信息，判断传入的起始下载点，下载的数据长度是否有效)
		byte[] softContent = tyTmsTerminalInfo.getSoftContent();
		if (softContent.length <= Integer.valueOf(getStart())) {
			// 请求的下载起始点超出文件的长度
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		logger.debug("数据验证成功.");
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
