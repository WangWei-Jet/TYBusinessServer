/**
 * 
 */
package com.whty.example.utils;

/**
 * <p>
 * Title:AA
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年6月6日 下午7:49:57
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class AA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		FileTransfer fileTransfer = new FileTransfer();
		int result = fileTransfer.GetBmp("C:\\pic.wlt", 2);
		System.out.println("调用结束:" + result);

	}

}
