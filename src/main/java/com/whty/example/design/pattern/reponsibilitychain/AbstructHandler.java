/**
 * 
 */
package com.whty.example.design.pattern.reponsibilitychain;

/**
 * <p>
 * Title:AbstructHandler
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月14日 上午10:38:43
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public abstract class AbstructHandler {

	private EventType eventType;

	private AbstructHandler nextHandler;

	private HanderObserver handlerObserver;

	public AbstructHandler(EventType eventType) {
		this.eventType = eventType;
	}

	public void setNextHandler(AbstructHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public void setHanderObserver(HanderObserver handlerObserver) {
		this.handlerObserver = handlerObserver;
	}

	public void handleEvent(EventType eventType, String message) {
		if (eventType == this.eventType) {
			// match my event type
			doWork(message);
		}
		// go on,continue the responsibility chain
		if (nextHandler != null) {
			nextHandler.handleEvent(eventType, message);
		} else {
			if (handlerObserver != null) {
				handlerObserver.finishNotified();
			}
			System.out.println("hander chain finished");
		}
	}

	// do work according to event type
	abstract void doWork(String message);
}
