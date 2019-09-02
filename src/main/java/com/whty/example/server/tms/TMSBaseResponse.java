package com.whty.example.server.tms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.example.utils.GPMethods;

/**
 * TMS报文响应基础类
 * 
 * @author wangwei01
 *
 */
public class TMSBaseResponse {
	// 请求报文实体类
	TMSBaseRequest request;
	// 响应码
	String responseCode;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	public TMSBaseRequest getRequest() {
		return request;
	}

	public void setRequest(TMSBaseRequest request) {
		this.request = request;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public TMSBaseResponse(TMSBaseRequest request, String responseCode) {
		super();
		this.request = request;
		this.responseCode = responseCode;
	}

	public String packMessage() {
		logger.debug("prepare to pack response message");
		if (!validate()) {
			return null;
		}
		String responseData = GPMethods.bytesToHexString(
				(getRequest().getRequestCode() + getRequest().getDeviceSn() + getResponseCode()).getBytes());
		logger.debug("pack response message success.");
		return responseData;
	}

	public boolean validate() {
		if (getRequest() == null) {
			logger.debug("corresponding request is null!");
			return false;
		}
		if (getResponseCode() == null || getResponseCode().trim().length() == 0) {
			logger.debug("response code null!");
			return false;
		}
		if (getResponseCode().length() != 2) {
			logger.debug("response code length error!");
			return false;
		}
		return true;
	}

}
