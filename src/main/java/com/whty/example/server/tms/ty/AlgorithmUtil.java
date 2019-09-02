/**
 * 
 */
package com.whty.example.server.tms.ty;

/**
 * <p>
 * Title:AlgorithmUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月21日 下午3:20:24
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class AlgorithmUtil {

	public static String doLrc(String data) {
		byte[] m = StringUtil.str2bytes(data);
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
}
