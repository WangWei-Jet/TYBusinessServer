package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

/**
 * 脚本文件下载确认
 * 
 * @author wangwei01
 *
 */
public class TMSScriptFileDownloadConfirmationRequest extends TMSBaseRequest {

	// 确认交易标识
	String confirmTradeFlag;

	// 脚本下载索引
	String scriptDownloadIndex;

	public String getConfirmTradeFlag() {
		return confirmTradeFlag;
	}

	public void setConfirmTradeFlag(String confirmTradeFlag) {
		this.confirmTradeFlag = confirmTradeFlag;
	}

	public String getScriptDownloadIndex() {
		return scriptDownloadIndex;
	}

	public void setScriptDownloadIndex(String scriptDownloadIndex) {
		this.scriptDownloadIndex = scriptDownloadIndex;
	}

	public TMSScriptFileDownloadConfirmationRequest(String completeRequestMessage) {
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
		if (getUnHandledRequestMessage().length() < 12 * 2) {
			logger.debug("request message length error.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		setConfirmTradeFlag(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 2 * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(2 * 2));
		logger.debug("confirm trade flag:" + getConfirmTradeFlag());

		setScriptDownloadIndex(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 10 * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(10 * 2));
		logger.debug("script download index:" + getScriptDownloadIndex());

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
