package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

/**
 * 初始化
 * 
 * @author wangwei01
 *
 */
public class TMSInitializationRequest extends TMSBaseRequest {

	// 认证授权码
	String authenticationAuthorityCode;

	public String getAuthenticationAuthorityCode() {
		return authenticationAuthorityCode;
	}

	public void setAuthenticationAuthorityCode(String authenticationAuthorityCode) {
		this.authenticationAuthorityCode = authenticationAuthorityCode;
	}

	public TMSInitializationRequest(String completeRequestMessage) {
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
			logger.debug("no download content flag found.");
			validateResult.setValidated(false);
			return validateResult;
		}
		if (getUnHandledRequestMessage().length() < 24 * 2) {
			logger.debug("request message length error.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		setAuthenticationAuthorityCode(
				new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 24 * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(24 * 2));
		logger.debug("authentication authority code:" + getAuthenticationAuthorityCode());
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
