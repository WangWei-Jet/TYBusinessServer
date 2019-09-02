package com.whty.example.server.tms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.example.utils.GPMethods;

/**
 * TMS请求基础类(不适用的场景:应用信息下发)
 * 
 * @author wangwei01
 *
 */
public class TMSBaseRequest {
	/*
	 * 通用变量
	 */
	// 请求码
	String requestCode;
	// 设备序列号
	String deviceSn;
	// 商户编号
	String merchantNo;
	// 终端编号
	String terminalNo;

	// 完整的TMS请求报文
	String completeRequestMessage;
	// 剩余未处理(validate)的请求报文，可以在子类中进行处理
	String unHandledRequestMessage;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	public TMSBaseRequest(String completeRequestMessage) {
		super();
		setCompleteRequestMessage(completeRequestMessage);
	}

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getCompleteRequestMessage() {
		return completeRequestMessage;
	}

	public void setCompleteRequestMessage(String completeRequestMessage) {
		this.completeRequestMessage = completeRequestMessage;
	}

	public String getUnHandledRequestMessage() {
		return unHandledRequestMessage;
	}

	public void setUnHandledRequestMessage(String unHandledRequestMessage) {
		this.unHandledRequestMessage = unHandledRequestMessage;
	}

	/**
	 * 验证报文通用变量
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
		if (completeRequestMessage.length() < 77 * 2) {
			logger.debug("request message length error,length:" + completeRequestMessage.length());
			validateResult.setValidated(false);
			return validateResult;
		}
		requestCode = getCompleteRequestMessage().substring(0, 8);
		requestCode = new String(GPMethods.str2bytes(requestCode));
		logger.debug("requestCode:" + requestCode);
		deviceSn = getCompleteRequestMessage().substring(8, 108);
		deviceSn = new String(GPMethods.str2bytes(deviceSn));
		logger.debug("deviceSn:" + deviceSn);
		merchantNo = getCompleteRequestMessage().substring(108, 138);
		merchantNo = new String(GPMethods.str2bytes(merchantNo));
		logger.debug("merchantNo:" + merchantNo);
		terminalNo = getCompleteRequestMessage().substring(138, 154);
		terminalNo = new String(GPMethods.str2bytes(terminalNo));
		logger.debug("terminalNo:" + terminalNo);
		logger.debug("validate common request part success");
		setUnHandledRequestMessage(getCompleteRequestMessage().substring(154, getCompleteRequestMessage().length()));
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
