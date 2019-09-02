package com.whty.example.server.tms.ty;

import com.whty.example.utils.GPMethods;

public class TyTmsNotificationResponse extends TyTmsBaseResponse {

	/**
	 * @param request
	 */
	public TyTmsNotificationResponse(TyTmsBaseRequest request) {
		super(request);
	}

	@Override
	public String packValidateFailMessage(String responseCode) {
		String data = responseCode;
		data = GPMethods.bytesToHexString(data.getBytes());
		String lrc = GPMethods.doLrc(data);
		return data + lrc;
	}

	@Override
	public String packValidateSuccessMessage() {
		String data = "00";
		data = GPMethods.bytesToHexString(data.getBytes());
		String lrc = GPMethods.doLrc(data);
		return data + lrc;
	}
}
