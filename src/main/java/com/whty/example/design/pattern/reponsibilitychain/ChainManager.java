/**
 * 
 */
package com.whty.example.design.pattern.reponsibilitychain;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:ChainManager
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月14日 上午11:10:23
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ChainManager extends HanderObserver {

	private static ChainManager singletonChianManager;

	private List<AbstructHandler> handerList = new ArrayList<>();

	// chain running state
	private boolean chainRunning = false;

	public static ChainManager getSingletonChainManager() {
		if (singletonChianManager == null) {
			singletonChianManager = new ChainManager();
		}
		return singletonChianManager;
	}

	public static ChainManager createNewChainManager() {
		return new ChainManager();
	}

	/**
	 * 责任链添加handler
	 * 
	 * @param hander
	 */
	public void addHander(AbstructHandler hander) {
		if (handerList.isEmpty()) {
			handerList.add(hander);
			return;
		}
		hander.setHanderObserver(this);
		handerList.get(handerList.size() - 1).setNextHandler(hander);
		handerList.add(hander);
		return;
	}

	/**
	 * 启动责任链
	 * 
	 * @param eventType
	 * @param message
	 * @return
	 */
	public boolean startChain(EventType eventType, String message) {
		if (handerList.isEmpty()) {
			System.out.println("chain is empty");
			return false;
		}
		if (chainRunning) {
			System.out.println("chain is already in running state");
			return false;
		}
		chainRunning = true;
		handerList.get(0).handleEvent(eventType, message);
		return true;
	}

	/**
	 * 清空责任链
	 * 
	 * @return
	 */
	public boolean resetChain() {
		if (chainRunning) {
			System.out.println("chain is already in running state");
			return false;
		}
		handerList.clear();
		return true;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.design.pattern.reponsibilitychain.HanderObserver#notified()
	 */
	@Override
	void finishNotified() {
		// chain run over
		chainRunning = false;
		System.out.println("chain runs over");
	}
}
