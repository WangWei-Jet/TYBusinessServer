package com.cup.tms.thirdinterface;

public abstract class Rule {
	/**
	 * 获取厂商应用程序的版本号
	 * 
	 * @param filePath
	 *            厂商程序包所存放的绝对路径名
	 * @return 返回实际程序版本号
	 * @throws java.lang.Exception
	 */
	public abstract String getAppVer(String filePath) throws Exception;

	/**
	 * 获取厂商应用程序的名称
	 * 
	 * @param filePath
	 *            厂商程序包所存放的绝对路径名
	 * @return 返回实际程序名称
	 * @throws java.lang.Exception
	 */
	public abstract String getAppName(String filePath) throws Exception;
}
