package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

/**
 * 终端信息上送
 * 
 * @author wangwei01
 *
 */
public class TMSTerminalInfoUploadRequest extends TMSBaseRequest {
	String appInfo;
	String appVersion;
	String appParam;
	String hardwareInfo;

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppParam() {
		return appParam;
	}

	public void setAppParam(String appParam) {
		this.appParam = appParam;
	}

	public String getHardwareInfo() {
		return hardwareInfo;
	}

	public void setHardwareInfo(String hardwareInfo) {
		this.hardwareInfo = hardwareInfo;
	}

	public TMSTerminalInfoUploadRequest(String completeRequestMessage) {
		super(completeRequestMessage);
	}

	@Override
	public ValidateResult validate() {
		ValidateResult validateResult = super.validate();
		if (!validateResult.isValidated()) {
			return validateResult;
		}
		// logic coding
		if (getUnHandledRequestMessage() == null || getUnHandledRequestMessage().trim().length() == 0) {
			logger.debug("no app info found.");
			validateResult.setValidated(false);
			return validateResult;
		}
		if (getUnHandledRequestMessage().length() < (100 + 3) * 2) {
			logger.debug("request message length error.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		int appInfoDecimalLength = Integer
				.valueOf(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 6))));
		logger.debug("request appinfo length:" + appInfoDecimalLength);
		if (getUnHandledRequestMessage().length() < (appInfoDecimalLength + 3 + 100) * 2) {
			logger.debug("request message length error.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		String appInfoValidData = getUnHandledRequestMessage().substring(6, 6 + appInfoDecimalLength * 2);
		int appVersionDecimalLength = Integer
				.valueOf(new String(GPMethods.str2bytes(appInfoValidData.substring(0, 4))));
		String appVersionInfo = appInfoValidData.substring(4, 4 + appVersionDecimalLength * 2);
		setAppVersion(new String(GPMethods.str2bytes(appVersionInfo)));
		logger.debug("app version length:" + appVersionDecimalLength + "\tapp version info:" + getAppVersion());
		int appParamDecimalLength = Integer.valueOf(new String(GPMethods.str2bytes(
				appInfoValidData.substring(4 + appVersionDecimalLength * 2, 4 + appVersionDecimalLength * 2 + 6))));
		String appParamInfo = appInfoValidData.substring(4 + appVersionDecimalLength * 2 + 6,
				4 + appVersionDecimalLength * 2 + 6 + appParamDecimalLength * 2);
		setAppParam(new String(GPMethods.str2bytes(appParamInfo)));
		logger.debug("app param length:" + appParamDecimalLength + "\tapp param info:" + getAppParam());
		setAppInfo(new String(
				GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 6 + appInfoDecimalLength * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(6 + appInfoDecimalLength * 2));
		logger.debug("app info length:" + appVersionDecimalLength + "\tapp info:" + getAppInfo());
		if (getUnHandledRequestMessage().length() < 100 * 2) {
			logger.debug("hardware info length error.length:" + getUnHandledRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		setHardwareInfo(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 200))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(200));
		logger.debug("hardware info:" + getHardwareInfo());
		logger.debug("unhandled message:" + getUnHandledRequestMessage());
		if (getUnHandledRequestMessage() != null && getUnHandledRequestMessage().length() > 0) {
			logger.debug("request message length error.EXTRA DATA FOUND");
			validateResult.setValidated(false);
			return validateResult;
		}
		logger.debug("validate message success.");
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
