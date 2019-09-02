package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

public class TMSScriptFileDownloadResponse extends TMSBaseResponse {

	TMSScriptFileDownloadRequest originalReq;

	public TMSScriptFileDownloadResponse(TMSBaseRequest request, String responseCode) {
		super(request, responseCode);
		originalReq = (TMSScriptFileDownloadRequest) request;
	}

	@Override
	public String packMessage() {
		logger.debug("prepare to pack response message");
		if (!validate()) {
			return null;
		}
		// 脚本下载索引;一次下载任务唯一，在第一次请求的应答时下发，此后整个会话使用同一索引
		String scriptDownloadIndex = "5555566666";
		// 下载应用标识号;如果为 FFFFF 表示下载结束，脚本参 数中为所有下载脚本的版本号。 否则表示还需要下载，下次请求上送该标识号
		String downloadAppIdentityNo = "FFFFF";
		// 脚本参数
		String scriptParams = "";
		String fileName = "testFile";
		fileName = GPMethods.bytesToHexString(new byte[] { (byte) fileName.length() }) + fileName.getBytes();
		String fileContent = "45441541546443";
		fileContent = GPMethods.getHexLen(fileContent, 8) + fileContent;
		scriptParams = fileName + fileContent;
		scriptParams = GPMethods.getHexLen(scriptParams, 4) + scriptParams;
		String responseData = GPMethods.bytesToHexString((getRequest().getRequestCode() + getRequest().getDeviceSn()
				+ getResponseCode() + scriptDownloadIndex + downloadAppIdentityNo).getBytes()) + scriptParams;
		logger.debug("pack response message success.");
		return responseData;
	}

}
