package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

/**
 * 远程应用（参数）下载
 * 
 * @author wangwei01
 *
 */
public class TMSRemoteAppDownloadRequest extends TMSBaseRequest {

	// 下载内容标志
	String downloadContentFlag;
	// 应用版本号
	String appVersionNo;
	// 下载任务校验码
	String downloadTaskVerificationCode;

	public String getDownloadContentFlag() {
		return downloadContentFlag;
	}

	public void setDownloadContentFlag(String downloadContentFlag) {
		this.downloadContentFlag = downloadContentFlag;
	}

	public String getAppVersionNo() {
		return appVersionNo;
	}

	public void setAppVersionNo(String appVersionNo) {
		this.appVersionNo = appVersionNo;
	}

	public String getDownloadTaskVerificationCode() {
		return downloadTaskVerificationCode;
	}

	public void setDownloadTaskVerificationCode(String downloadTaskVerificationCode) {
		this.downloadTaskVerificationCode = downloadTaskVerificationCode;
	}

	public TMSRemoteAppDownloadRequest(String completeRequestMessage) {
		super(completeRequestMessage);
		logger.info("remote app download");
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
		if (getUnHandledRequestMessage().length() < 68) {
			logger.debug("request message length error.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		setDownloadContentFlag(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 4))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(4));
		logger.debug("download content flag:" + getDownloadContentFlag());
		if (getDownloadContentFlag().equals("00")) {
			logger.debug("unionpay card app");
			setAppVersionNo(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 12))));
			setUnHandledRequestMessage(getUnHandledRequestMessage().substring(12));
		} else if (getDownloadContentFlag().equals("01")) {
			logger.debug("not unionpay card app");
			String appVersionNumberLen = new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 4)));
			setUnHandledRequestMessage(getUnHandledRequestMessage().substring(4));
			int appVersionLen = Integer.parseInt(appVersionNumberLen, 16);
			setAppVersionNo(
					new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, appVersionLen * 2))));
			setUnHandledRequestMessage(getUnHandledRequestMessage().substring(appVersionLen * 2));
		} else {
			logger.debug("unrecognized download content flag");
			validateResult.setValidated(false);
			return validateResult;
		}
		logger.debug("app version number:" + getAppVersionNo());
		setDownloadTaskVerificationCode(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 64))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(64));
		logger.debug("download task verification code:" + getDownloadTaskVerificationCode());
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
