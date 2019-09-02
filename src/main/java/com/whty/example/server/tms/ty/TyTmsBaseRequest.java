package com.whty.example.server.tms.ty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.example.utils.GPMethods;

/**
 * TMS请求基础类
 * 
 * @author wangwei01
 *
 */
public class TyTmsBaseRequest {
	/*
	 * 通用变量
	 */
	// 请求码
	String requestCode;
	// 设备序列号
	String deviceSn;
	// 设备终端号
	String deviceTerminalNo;
	// 完整的TMS请求报文
	String completeRequestMessage;
	// 剩余未处理(validate)的请求报文，可以在子类中进行处理
	String unHandledRequestMessage;
	// 业务代理
	TyTmsBusinessProxy tyTmsBusinessProxy;

	final Logger logger = LoggerFactory.getLogger(getClass());

	public TyTmsBaseRequest(String completeRequestMessage, TyTmsBusinessProxy tyTmsBusinessProxy) {
		super();
		setCompleteRequestMessage(completeRequestMessage);
		setTyTmsBusinessProxy(tyTmsBusinessProxy);
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

	public String getDeviceTerminalNo() {
		return deviceTerminalNo;
	}

	public void setDeviceTerminalNo(String deviceTerminalNo) {
		this.deviceTerminalNo = deviceTerminalNo;
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

	public TyTmsBusinessProxy getTyTmsBusinessProxy() {
		return tyTmsBusinessProxy;
	}

	public void setTyTmsBusinessProxy(TyTmsBusinessProxy tyTmsBusinessProxy) {
		this.tyTmsBusinessProxy = tyTmsBusinessProxy;
	}

	/**
	 * 验证报文通用变量
	 * 
	 * @return 验证结果
	 */
	public ValidateResult validate() {
		ValidateResult validateResult = new ValidateResult();
		logger.info("准备验证请求报文通用部分");
		if (tyTmsBusinessProxy == null) {
			logger.info("业务代理为空");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.PARAM_ERROR);
			return validateResult;
		}
		if (getCompleteRequestMessage() == null || getCompleteRequestMessage().trim().length() == 0) {
			logger.info("请求报文为空");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		if (getCompleteRequestMessage().length() < (2 + 8 + 32) * 2) {
			logger.info("请求报文长度不足,length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.REQUEST_DATA_ERROR);
			return validateResult;
		}
		setRequestCode(new String(GPMethods.str2bytes(getCompleteRequestMessage().substring(0, 2 * 2))));
		logger.info("请求码:" + getRequestCode());
		setDeviceTerminalNo(new String(GPMethods.str2bytes(getCompleteRequestMessage().substring(2 * 2, (2 + 8) * 2))));
		logger.info("设备终端号:" + getDeviceTerminalNo());
		setDeviceSn(
				new String(GPMethods.str2bytes(getCompleteRequestMessage().substring((2 + 8) * 2, (2 + 8 + 32) * 2))));
		logger.info("设备SN:" + getDeviceSn());
		// 验证数据合法性
		// 1.查询是否有效设备
		if (!tyTmsBusinessProxy.doDeviceExist(getDeviceSn(), getDeviceTerminalNo())) {
			logger.info("设备不存在");
			validateResult.setValidated(false);
			validateResult.setResponseCode(ErrorCode.UNKOWN_TERMINAL);
			return validateResult;
		}
		logger.info("请求报文通用部分验证成功");
		setUnHandledRequestMessage(
				getCompleteRequestMessage().substring((2 + 8 + 32) * 2, getCompleteRequestMessage().length()));
		validateResult.setValidated(true);
		validateResult.setResponseCode("00");
		return validateResult;
	}

}
