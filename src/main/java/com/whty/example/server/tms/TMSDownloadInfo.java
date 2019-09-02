package com.whty.example.server.tms;

public class TMSDownloadInfo {
	// 日志级别
	byte logLvl;
	// 日志文件路径
	String logFile;
	// 多应用时不用
	String progFile;
	// 终端型号信息(20B)+硬件序列号(38B)
	String paraFile;
	// 下载进度信息
	String dataFile;
	// 全部应用保存的文件夹地址
	String otherFile;
	// 下载类型
	byte type;
	// 终端的应用个数
	String appSum;
	// 终端应用信息
	TMSTerminalAPPInfo[] appInfos;

	public byte getLogLvl() {
		return logLvl;
	}

	public void setLogLvl(byte logLvl) {
		this.logLvl = logLvl;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getProgFile() {
		return progFile;
	}

	public void setProgFile(String progFile) {
		this.progFile = progFile;
	}

	public String getParaFile() {
		return paraFile;
	}

	public void setParaFile(String paraFile) {
		this.paraFile = paraFile;
	}

	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getAppSum() {
		return appSum;
	}

	public void setAppSum(String appSum) {
		this.appSum = appSum;
	}

	public TMSTerminalAPPInfo[] getAppInfos() {
		return appInfos;
	}

	public void setAppInfos(TMSTerminalAPPInfo[] appInfos) {
		this.appInfos = appInfos;
	}

}
