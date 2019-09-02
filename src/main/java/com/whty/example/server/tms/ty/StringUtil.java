/**
 * 
 */
package com.whty.example.server.tms.ty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title:StringUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月21日 下午3:17:25
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class StringUtil {
	public static boolean isNumeric(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static byte[] str2bytes(String src) {
		if (src == null || src.length() == 0 || src.length() % 2 != 0) {
			return null;
		}
		int nSrcLen = src.length();
		byte byteArrayResult[] = new byte[nSrcLen / 2];
		StringBuffer strBufTemp = new StringBuffer(src);
		String strTemp;
		int i = 0;
		while (i < strBufTemp.length() - 1) {
			strTemp = src.substring(i, i + 2);
			byteArrayResult[i / 2] = (byte) Integer.parseInt(strTemp, 16);
			i += 2;
		}
		return byteArrayResult;
	}

	public static String doLrc(String data) {
		byte[] m = str2bytes(data);
		int x = 0;
		int l = m.length;

		for (int i = 0; i < l; i++) {
			x = x + m[i];
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

	public static String generateZero(int count) {
		if (count <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	public static String generateSpace(int count) {
		if (count <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * 将byte数组转换成16进制组成的字符串 例如 一个byte数组 b[0]=0x07;b[1]=0x10;...b[5]=0xFB;
	 * byte2hex(b); 将返回一个字符串"0710BE8716FB"
	 * 
	 * @param bytes
	 *            待转换的byte数组
	 * @return
	 */
	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		StringBuffer buff = new StringBuffer();
		int len = bytes.length;
		for (int j = 0; j < len; j++) {
			if ((bytes[j] & 0xff) < 16) {
				buff.append('0');
			}
			buff.append(Integer.toHexString(bytes[j] & 0xff));
		}
		return buff.toString();
	}

	/**
	 * 将byte数组转换成16进制组成的字符串 例如 一个byte数组 b[0]=0x07;b[1]=0x10;...b[5]=0xFB;
	 * byte2hex(b); 将返回一个字符串"0710BE8716FB"
	 * 
	 * @param bytes
	 *            待转换的byte数组
	 * @return
	 */
	public static String bytesToHexString(byte[] bytes, int len) {
		if (bytes == null) {
			return "";
		}
		StringBuffer buff = new StringBuffer();
		for (int j = 0; j < len; j++) {
			if ((bytes[j] & 0xff) < 16) {
				buff.append('0');
			}
			buff.append(Integer.toHexString(bytes[j] & 0xff));
		}
		return buff.toString();
	}
}
