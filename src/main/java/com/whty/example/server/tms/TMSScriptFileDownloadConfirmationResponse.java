package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

public class TMSScriptFileDownloadConfirmationResponse extends TMSBaseResponse {

	TMSScriptFileDownloadConfirmationRequest originalReq;

	public TMSScriptFileDownloadConfirmationResponse(TMSBaseRequest request, String responseCode) {
		super(request, responseCode);
		originalReq = (TMSScriptFileDownloadConfirmationRequest) request;
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
