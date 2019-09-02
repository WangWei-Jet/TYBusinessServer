/**
 * 
 */
package com.whty.example.test;

import com.whty.example.utils.SingletonTestInstance;

/**
 * <p>
 * Title:TestSingleton
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月6日 上午9:52:44
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestSingleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonTestInstance.INSTANCE.doWriteOperation();
		SingletonTestInstance.INSTANCE.doReadOperation();
		SingletonTestInstance.INSTANCE.getPropertyA();
	}

}
