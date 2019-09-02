package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

/**
 * 脚本文件下载
 * 
 * @author wangwei01
 *
 */
public class TMSScriptFileDownloadRequest extends TMSBaseRequest {

	// 脚本解析器版本号
	String scriptParserVersion;
	// 脚本下载索引
	String scriptDownloadIndex;
	// 下载应用标识号
	String downloadAppIdentityNo;
	// 硬件标识信息
	String hardwareIdentityInfo;
	// 当前终端各脚本类型版本号
	String currentTerminalTypeVersionForEachScript;

	public String getScriptParserVersion() {
		return scriptParserVersion;
	}

	public void setScriptParserVersion(String scriptParserVersion) {
		this.scriptParserVersion = scriptParserVersion;
	}

	public String getScriptDownloadIndex() {
		return scriptDownloadIndex;
	}

	public void setScriptDownloadIndex(String scriptDownloadIndex) {
		this.scriptDownloadIndex = scriptDownloadIndex;
	}

	public String getDownloadAppIdentityNo() {
		return downloadAppIdentityNo;
	}

	public void setDownloadAppIdentityNo(String downloadAppIdentityNo) {
		this.downloadAppIdentityNo = downloadAppIdentityNo;
	}

	public String getHardwareIdentityInfo() {
		return hardwareIdentityInfo;
	}

	public void setHardwareIdentityInfo(String hardwareIdentityInfo) {
		this.hardwareIdentityInfo = hardwareIdentityInfo;
	}

	public String getCurrentTerminalTypeVersionForEachScript() {
		return currentTerminalTypeVersionForEachScript;
	}

	public void setCurrentTerminalTypeVersionForEachScript(String currentTerminalTypeVersionForEachScript) {
		this.currentTerminalTypeVersionForEachScript = currentTerminalTypeVersionForEachScript;
	}

	public TMSScriptFileDownloadRequest(String completeRequestMessage) {
		super(completeRequestMessage);
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
		if (getUnHandledRequestMessage().length() < (2 + 10 + 5) * 2) {
			logger.debug("request message length error.length:" + getCompleteRequestMessage().length());
			validateResult.setValidated(false);
			return validateResult;
		}
		setScriptParserVersion(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 2 * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(2 * 2));
		logger.debug("script parser version:" + getScriptParserVersion());

		setScriptDownloadIndex(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 10 * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(10 * 2));
		logger.debug("script download index:" + getScriptDownloadIndex());

		setDownloadAppIdentityNo(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 5 * 2))));
		setUnHandledRequestMessage(getUnHandledRequestMessage().substring(5 * 2));
		logger.debug("download app identity number:" + getDownloadAppIdentityNo());

		if (getUnHandledRequestMessage().length() > 8 * 2) {
			setHardwareIdentityInfo(new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, 8 * 2))));
			setUnHandledRequestMessage(getUnHandledRequestMessage().substring(8 * 2));
			logger.debug("hardware identity information:" + getHardwareIdentityInfo());
			if (getHardwareIdentityInfo().startsWith("1")) {
				logger.debug("针打");
			} else if (getHardwareIdentityInfo().startsWith("2")) {
				logger.debug("热敏");
			} else {
				logger.debug("value error!!!");
				validateResult.setValidated(false);
				return validateResult;
			}
		}
		if (getUnHandledRequestMessage().length() > 2) {
			String typeVersionLen = getUnHandledRequestMessage().substring(0, 2);
			setUnHandledRequestMessage(getUnHandledRequestMessage().substring(2));
			int dataLen = Integer.valueOf(typeVersionLen, 16);
			if (getUnHandledRequestMessage().length() <= 0 || getUnHandledRequestMessage().length() > 70
					|| getUnHandledRequestMessage().length() % (8 * 2) != 0
					|| getUnHandledRequestMessage().length() != dataLen * 2) {
				logger.debug("request message length error!!!");
				validateResult.setValidated(false);
				return validateResult;
			}
			setCurrentTerminalTypeVersionForEachScript(
					new String(GPMethods.str2bytes(getUnHandledRequestMessage().substring(0, dataLen * 2))));
			setUnHandledRequestMessage(getUnHandledRequestMessage().substring(dataLen * 2));
			logger.debug(
					"current terminal type version for each script:" + getCurrentTerminalTypeVersionForEachScript());
			int tempIndex = 0;
			while (tempIndex < getCurrentTerminalTypeVersionForEachScript().length() - 1) {
				String scriptType = getCurrentTerminalTypeVersionForEachScript().substring(tempIndex, tempIndex++);
				String appType = getCurrentTerminalTypeVersionForEachScript().substring(tempIndex, tempIndex + 2);
				tempIndex += 2;
				// 版本号
				String version = getCurrentTerminalTypeVersionForEachScript().substring(tempIndex, tempIndex + 4);
				tempIndex += 4;
				logger.debug("script type:" + scriptType + "\napp type:" + appType + "\nversion:" + version);
				try {
					if (Integer.valueOf(scriptType) < 1 || Integer.valueOf(scriptType) > 4) {
						logger.debug("illegal script type");
						validateResult.setValidated(false);
						return validateResult;
					}
					switch (Integer.valueOf(scriptType)) {
					case 1:
					case 2:
					case 3:
						if (!appType.equals("00")) {
							logger.debug("app type can not match with script type");
							validateResult.setValidated(false);
							return validateResult;
						}
						break;

					case 4:
						if (Integer.valueOf(appType) < 1 || Integer.valueOf(appType) > 9) {
							logger.debug("illegal request app type");
							validateResult.setValidated(false);
							return validateResult;
						}
						break;

					default:
						break;
					}
				} catch (Exception e) {
					logger.debug("data error");
					validateResult.setValidated(false);
					return validateResult;
				}
			}
		}

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
