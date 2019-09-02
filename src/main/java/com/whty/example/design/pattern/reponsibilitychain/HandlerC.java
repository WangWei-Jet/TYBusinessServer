/**
 * 
 */
package com.whty.example.design.pattern.reponsibilitychain;

/**
 * <p>
 * Title:HandlerA
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月14日 上午10:50:23
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class HandlerC extends AbstructHandler {

	/**
	 * @param eventType
	 */
	public HandlerC(EventType eventType) {
		super(eventType);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.whty.example.design.pattern.reponsibilitychain.AbstructHandler#doWork(java.lang.String)
	 */
	@Override
	public void doWork(String message) {
		System.out.println(this.getClass().getSimpleName() + " do work");
	}

}
