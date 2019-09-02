/**
 * 
 */
package com.whty.example.utils;

/**
 * <p>
 * Title:SingletonTestInstance
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月6日 上午9:36:36
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public enum SingletonTestInstance {

	INSTANCE;

	private String propertyA;
	private String propertyB;

	public String getPropertyA() {
		return this.propertyA;
	}

	public String getPropertyB() {
		return this.propertyB;
	}

	public void doReadOperation() {
		System.out.println("propertyA:" + propertyA);
		System.out.println("propertyB:" + propertyB);
	}

	public synchronized void doWriteOperation() {
		propertyA = "value A";
		propertyB = "value B";
	}

}
