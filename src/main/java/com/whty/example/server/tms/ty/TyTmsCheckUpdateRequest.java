package com.whty.example.server.tms.ty;

import com.whty.example.utils.GPMethods;

/**
 * 检查更新接口
 * <p>
 * Title:TyTmsCheckUpdateRequest
 * </p>
 * <p>
 * Description:终端发送检查更新请求，请求服务器是否有固件/参数可供升级(目前不检查参数，只检查固件)
 * </p>
 * <p>
 * Date:2018年5月18日 上午10:34:30
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyTmsCheckUpdateRequest extends TyTmsBaseRequest {
	// 软件版本号
	String softwareVersion;
	// 参数版本号
	String paramVersion;
	// 检验位
	String lrc;

	public TyTmsCheckUpdateRequest(String completeRequestMessage, TyTmsBusinessProxy tyTmsBusinessProxy) {
		super(completeRequestMessage, tyTmsBusinessProxy);
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
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
			logger.debug("请求数据为空.");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		if (getUnHandledRequestMessage().length() < (20 + 20 + 1) * 2) {
			logger.debug("请求数据长度不足.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		setSoftwareVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 20 * 2))));
		logger.info("软件版本:" + getSoftwareVersion());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(20 * 2));
		setParamVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 20 * 2))));
		logger.info("参数版本:" + getParamVersion());
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(20 * 2));
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
		// 判断当前软件版本是否需要更新
		if (!tyTmsBusinessProxy.doDeviceNeedUpdate(getDeviceSn(), getDeviceTerminalNo(), getSoftwareVersion())) {
			logger.info("当前软件已是最新版本，无需更新");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.LATEST_SOFTWARE);
			return validateResult;
		}
		logger.debug("数据验证成功.");
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
