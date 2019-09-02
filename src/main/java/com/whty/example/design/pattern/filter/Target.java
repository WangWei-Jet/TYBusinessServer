/**
 * 
 */
package com.whty.example.design.pattern.filter;

/**
 * <p>
 * Title:Target
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月15日 上午10:30:57
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class Target {

	public void doWork(Object request) {

		System.out.println(this.getClass().getSimpleName() + "=>request:" + request.toString());
		System.out.println(this.getClass().getSimpleName() + "=>target do work");
		System.out.println(this.getClass().getSimpleName() + "=>target finish work");
	}

}
