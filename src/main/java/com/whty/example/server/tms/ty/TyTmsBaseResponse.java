package com.whty.example.server.tms.ty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TMS响应基础类
 * 
 * @author wangwei01
 *
 */
public abstract class TyTmsBaseResponse {

	TyTmsBaseRequest request;

	final Logger logger = LoggerFactory.getLogger(getClass());

	public TyTmsBaseResponse(TyTmsBaseRequest request) {
		super();
		this.request = request;
	}

	public ValidateResult validate() {
		return request.validate();
	}

	public String packResponse() {
		ValidateResult validateResult = validate();
		if (!validateResult.isValidated()) {
			// 验证失败，组装失败报文
			return packValidateFailMessage(validateResult.getResponseCode());
		}
		// 验证成功
		return packValidateSuccessMessage();
	}

	public abstract String packValidateFailMessage(String responseCode);

	public abstract String packValidateSuccessMessage();

}
