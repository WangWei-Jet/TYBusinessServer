package com.whty.example.server.tms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.example.utils.GPMethods;

/**
 * 应用信息下发
 * 
 * @author wangwei01
 *
 */
public class TMSAPPInfoDownloadRequest extends TMSBaseRequest {

	// 应用版本号
	String appVersion;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	public TMSAPPInfoDownloadRequest(String completeRequestMessage) {
		super(completeRequestMessage);
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * 
	 * @return 验证结果
	 */
	public ValidateResult validate() {
		ValidateResult validateResult = new ValidateResult();
		logger.debug("prepare to validate request message tentatively");
		if (completeRequestMessage == null || completeRequestMessage.trim().length() == 0) {
			logger.debug("request message null");
			validateResult.setValidated(false);
			return validateResult;
		}
		if (completeRequestMessage.length() < 104 * 2) {
			logger.debug("request message length error,length:" + completeRequestMessage.length());
			validateResult.setValidated(false);
			return validateResult;
		}
		requestCode = getCompleteRequestMessage().substring(0, 4 * 2);
		requestCode = new String(GPMethods.str2bytes(requestCode));
		logger.debug("requestCode:" + requestCode);
		deviceSn = getCompleteRequestMessage().substring(8, 108);
		deviceSn = new String(GPMethods.str2bytes(deviceSn));
		logger.debug("deviceSn:" + deviceSn);
		appVersion = getCompleteRequestMessage().substring(108, 208);
		appVersion = new String(GPMethods.str2bytes(appVersion));
		logger.debug("appVersion:" + appVersion);
		logger.debug("validate common request part success");
		setUnHandledRequestMessage(getCompleteRequestMessage().substring(208, getCompleteRequestMessage().length()));
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
