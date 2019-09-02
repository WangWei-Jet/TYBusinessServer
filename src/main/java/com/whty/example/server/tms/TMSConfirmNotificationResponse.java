package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

public class TMSConfirmNotificationResponse extends TMSBaseResponse {

	TMSConfirmNotificationRequest originalReq;

	public TMSConfirmNotificationResponse(TMSBaseRequest request, String responseCode) {
		super(request, responseCode);
		originalReq = (TMSConfirmNotificationRequest) request;
	}

	@Override
	public String packMessage() {
		logger.debug("prepare to pack response message");
		if (!validate()) {
			return null;
		}
		String responseData = GPMethods.bytesToHexString((getRequest().getRequestCode() + getRequest().getDeviceSn()
				+ originalReq.getConfirmTradeFlag() + getResponseCode()).getBytes());
		logger.debug("pack response message success.");
		return responseData;
	}

}
