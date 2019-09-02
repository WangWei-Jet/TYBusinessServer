package com.whty.example.server.tms;

import com.whty.example.utils.GPMethods;

public class TestMain {

	public static void main(String[] args) {
		// Rule apiRule = new TYRule();
		// try {
		// String appName = apiRule.getAppName("D:/a.bin");
		// String appVersion = apiRule.getAppVer("D:/a.bin");
		// System.out.println("appName:" + appName + "\nappVersion:" +
		// appVersion);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		System.out.println(GPMethods.doLrc(
				"6f600003000030303730303631313131313131313131313120202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020"));
	}

}
