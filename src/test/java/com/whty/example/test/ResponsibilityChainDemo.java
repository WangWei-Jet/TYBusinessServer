/**
 * 
 */
package com.whty.example.test;

import com.whty.example.design.pattern.reponsibilitychain.AbstructHandler;
import com.whty.example.design.pattern.reponsibilitychain.ChainManager;
import com.whty.example.design.pattern.reponsibilitychain.EventType;
import com.whty.example.design.pattern.reponsibilitychain.HandlerA;
import com.whty.example.design.pattern.reponsibilitychain.HandlerB;
import com.whty.example.design.pattern.reponsibilitychain.HandlerC;

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

		ChainManager.getSingletonChainManager().addHander(handerA);
		ChainManager.getSingletonChainManager().addHander(handerB);
		ChainManager.getSingletonChainManager().addHander(handerC);
		ChainManager.getSingletonChainManager().addHander(handerA2);
		ChainManager.getSingletonChainManager().startChain(EventType.TYPEA, "message");
	}

}
