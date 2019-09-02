package com.whty.example.server.tms.application;

import com.cup.tms.thirdinterface.Rule;
import com.whty.example.utils.GPMethods;

public class TestMain {

	public static void main(String[] args) {
		Rule apiRule = new TYRule();
		try {
			String appName = apiRule.getAppName("D:/a.bin");
			String appVersion = apiRule.getAppVer("D:/a.bin");
			System.out.println("appName:" + appName + "\nappVersion:" + appVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String lrcResult = doLrc(
				"0201080000000000303037303034333039345358582d323931343536313720202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202003");
		System.out.println("lrc:" + lrcResult);

	}

	private static String doLrc(String data) {

		char[] m = new String(GPMethods.str2bytes(data)).toCharArray();
		int x = 0;
		int l = m.length;

		for (int i = 0; i < l; i++) {
			x = x + (byte) m[i];
		}
		x = ~x;
		int d = (x & (0xff));
		d += 1;
		String finaldata = Integer.toHexString(d).toUpperCase();
		while (finaldata.length() < 2) {
			finaldata = "0" + finaldata;
		}
		return finaldata;
	}

}
