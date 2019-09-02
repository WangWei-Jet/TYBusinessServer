/**
 * 
 */
package com.whty.example.utils;

/**
 * <p>
 * Title:FileTransferNative
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年6月6日 下午8:32:30
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FileTransferNative {
	static {
		System.loadLibrary("/GetInfo");
	}

	public static native int GetBmp(String filename, int t);

}
