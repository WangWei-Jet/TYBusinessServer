package com.whty.example.server.tms;

import com.whty.example.server.tms.application.TYRule;
import com.whty.example.utils.GPMethods;

public class TMSAPPInfoDownloadResponse extends TMSBaseResponse {

	public TMSAPPInfoDownloadResponse(TMSBaseRequest request, String responseCode) {
		super(request, responseCode);
	}

	@Override
	public String packMessage() {
		logger.debug("prepare to pack response message");
		if (!validate()) {
			return null;
		}
		TYRule tyDevice = new TYRule();
		String appVersion = null;
		try {
			appVersion = tyDevice.getAppName("D:/a.bin") + tyDevice.getAppVer("D:/a.bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		appVersion = GPMethods.getDecimalLen(appVersion, 2) + appVersion;
		String appParams = "261818181827002870012920180531301000003110013215842665874         3313548669587         34192.168.28.169:32091          35192.168.28.168:32092          36CMNET|tms|tms|127.0.0.1|10001                               37tms@chinaunipay|tms|127.0.0.1|10001                         381234567812345678123456781234567839006040downloadingtmsparams          4160666600004216050100000043180531000000";
		appParams = GPMethods.getDecimalLen(appParams, 3) + appParams;
		String nextAppParamFlag = "0";
		String responseData = GPMethods.bytesToHexString((getRequest().getRequestCode() + getRequest().getDeviceSn()
				+ getResponseCode() + appVersion + appParams + nextAppParamFlag).getBytes());
		logger.debug("pack response message success.");
		return responseData;
	}

}
