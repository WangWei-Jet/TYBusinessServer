/**
 * 
 */
package com.whty.example.test;

import com.whty.example.utils.GPMethods;

/**
 * <p>
 * Title:TestUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月7日 下午2:12:17
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = new String("3130");
		String b = new String("3136433030303032");
		String c = new String("42354E4C30303030323132302020202020202020202020202020202020202020");
		String d = new String("5630303030303030303030202020202020202020");
		String e = new String("5630303030303030303030202020202020202020");
		// String m = "5632303137303731333031202020202020202020";
		// String n = "3030303031373730";
		String f = a + b + c + d + e;
		System.out.println("f:" + f);
		System.out.println("tmep:" + new String(GPMethods.str2bytes(f)));
		System.out.println("分割线");
		String lrc = GPMethods.doLrc(f);
		System.out.println(lrc);
	}

}
