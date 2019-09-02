package com.whty.example.server.tms.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.cup.tms.thirdinterface.Rule;

public class TYRule extends Rule {

	private static final int unit = 10240;
	private static final int size = 1;

	@Override
	public String getAppVer(String filePath) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			System.out.println("target file not exists");
			return null;
		}
		InputStream is = new FileInputStream(targetFile);
		byte[] tempBuffer = new byte[10240];
		int tempLen = 0;
		int currentIndex = 0;
		String appVersion = null;
		while ((tempLen = is.read(tempBuffer, 0, unit)) != -1) {
			currentIndex += tempLen;
			// System.out.println("读取到"+tempLen+"字节数据");
			if (currentIndex > unit * size && currentIndex <= unit * (size + 1)) {
				byte[] tempData = new byte[tempLen];
				System.arraycopy(tempBuffer, 0, tempData, 0, tempLen);
				String valideData = Utils.bytesToHexString(tempData, 34 * 6);
				// System.out.println("data:"+valideData);
				// String tag02Data = valideData.substring(34*2*3, 34*2*4);
				// int tag02Len = Integer.parseInt(tag02Data.substring(2,
				// 4),16);
				// String data02 = tag02Data.substring(4, 4+tag02Len*2);
				String tag03Data = valideData.substring(34 * 2 * 4, 34 * 2 * 5);
				int tag03Len = Integer.parseInt(tag03Data.substring(2, 4), 16);
				String data03 = tag03Data.substring(4, 4 + tag03Len * 2);
				// System.out.println("tag 02:"+tag02Data+"\ndata
				// Len:"+tag02Len+"\ndata:"+data02);
				// System.out.println("tag 03:"+tag03Data+"\ndata
				// Len:"+tag03Len+"\ndata:"+data03);
				// String str02 = new String(Utils.hexString2Bytes(data02),
				// Charset.forName("GBK"));
				String str03 = new String(Utils.hexString2Bytes(data03), Charset.forName("GBK"));
				appVersion = str03;
				// System.out.println("str02:"+str02+"\nstr03:"+str03);
			}
		}
		is.close();
		return appVersion;
	}

	@Override
	public String getAppName(String filePath) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			System.out.println("target file not exists");
			return null;
		}
		InputStream is = new FileInputStream(targetFile);
		byte[] tempBuffer = new byte[10240];
		int tempLen = 0;
		int currentIndex = 0;
		String appName = null;
		while ((tempLen = is.read(tempBuffer, 0, unit)) != -1) {
			currentIndex += tempLen;
			// System.out.println("读取到"+tempLen+"字节数据");
			if (currentIndex > unit * size && currentIndex <= unit * (size + 1)) {
				byte[] tempData = new byte[tempLen];
				System.arraycopy(tempBuffer, 0, tempData, 0, tempLen);
				String valideData = Utils.bytesToHexString(tempData, 34 * 6);
				// System.out.println("data:"+valideData);
				String tag02Data = valideData.substring(34 * 2 * 3, 34 * 2 * 4);
				int tag02Len = Integer.parseInt(tag02Data.substring(2, 4), 16);
				String data02 = tag02Data.substring(4, 4 + tag02Len * 2);
				// String tag03Data = valideData.substring(34*2*4, 34*2*5);
				// int tag03Len = Integer.parseInt(tag03Data.substring(2, 4),
				// 16);
				// String data03 = tag03Data.substring(4, 4+tag03Len*2);
				// System.out.println("tag 02:"+tag02Data+"\ndata
				// Len:"+tag02Len+"\ndata:"+data02);
				// System.out.println("tag 03:"+tag03Data+"\ndata
				// Len:"+tag03Len+"\ndata:"+data03);
				String str02 = new String(Utils.hexString2Bytes(data02), Charset.forName("GBK"));
				// String str03 = new String(Utils.hexString2Bytes(data03),
				// Charset.forName("GBK"));
				appName = str02;
				// System.out.println("str02:"+str02+"\nstr03:"+str03);
			}
		}
		is.close();
		return appName;
	}

}
