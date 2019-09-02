package com.whty.example.server.tms.ty;

import com.whty.example.utils.GPMethods;

/**
 * 通知请求
 * <p>
 * Title:TyTmsNotificationRequest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月18日 上午11:28:51
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyTmsNotificationRequest extends TyTmsBaseRequest {
	// 软件任务ID
	String softTaskId;
	// 软件版本号
	String softVersion;
	// 参数任务ID
	String paramTaskId;
	// 参数版本号
	String paramVersion;
	// 检验位
	String lrc;

	public TyTmsNotificationRequest(String completeRequestMessage, TyTmsBusinessProxy tyTmsBusinessProxy) {
		super(completeRequestMessage, tyTmsBusinessProxy);
	}

	public String getSoftTaskId() {
		return softTaskId;
	}

	public void setSoftTaskId(String softTaskId) {
		this.softTaskId = softTaskId;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public String getParamTaskId() {
		return paramTaskId;
	}

	public void setParamTaskId(String paramTaskId) {
		this.paramTaskId = paramTaskId;
	}

	public String getParamVersion() {
		return paramVersion;
	}

	public void setParamVersion(String paramVersion) {
		this.paramVersion = paramVersion;
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
			return validateResult;
		}
		if (getUnHandledRequestMessage().length() < (8 + 20 + 8 + 20 + 1) * 2) {
			logger.debug("请求数据长度不足.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		setSoftTaskId(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 8 * 2))));
		logger.info("软件任务id=>soft task id:" + getSoftTaskId());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(8 * 2));
		setSoftVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 20 * 2))));
		logger.info("软件版本=>soft version:" + getSoftVersion());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(20 * 2));
		setParamTaskId(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 8 * 2))));
		logger.info("参数任务id=>param task id:" + getParamTaskId());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(8 * 2));
		setParamVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 20 * 2))));
		logger.info("参数版本=>param version:" + getParamVersion());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(20 * 2));
		setLrc(getUnHandledRequestMessage().substring(0, 1 * 2));
		logger.info("lrc:" + getLrc());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(1 * 2));
		if (getUnHandledRequestMessage() != null && getUnHandledRequestMessage().length() > 0) {
			logger.debug("请求数据长度超长.EXTRA DATA FOUND");
			validateResult.setValidated(false);
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
		// 软件任务id，软件版本
		TyTmsTerminalInfo tyTmsTerminalInfo = getTyTmsBusinessProxy().getDeviceSoftwareInfo(getDeviceSn(),
				getDeviceTerminalNo(), getSoftTaskId(), null, getSoftVersion());
		if (tyTmsTerminalInfo == null) {
			logger.info("软件任务不存在,请求软件版本与任务id不匹配");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_VERSION_INCONSISTENT);
			return validateResult;
		}
		logger.debug("数据验证成功.");
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
