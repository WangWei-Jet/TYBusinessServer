/**
 * 
 */
package com.whty.example.design.pattern.reponsibilitychain;

/**
 * <p>
 * Title:ResponsibilityChainDemo
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月14日 上午10:55:24
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ResponsibilityChainDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AbstructHandler handerA = new HandlerA(EventType.TYPEA);
		AbstructHandler handerA2 = new HandlerA(EventType.TYPEA);
		AbstructHandler handerB = new HandlerB(EventType.TYPEB);
		AbstructHandler handerC = new HandlerC(EventType.TYPEC);

		// handerA.setNextHandler(handerB);
		// handerB.setNextHandler(handerC);
		// handerC.setNextHandler(handerA2);
		//
		// handerA.handleEvent(EventType.TYPEA, "message");

		ChainManager.getSingletonChainManager().addHander(handerA);
		ChainManager.getSingletonChainManager().addHander(handerB);
		ChainManager.getSingletonChainManager().addHander(handerC);
		ChainManager.getSingletonChainManager().addHander(handerA2);
		ChainManager.getSingletonChainManager().startChain(EventType.TYPEA, "message");
	}

}
