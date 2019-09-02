/**
 * 
 */
package com.whty.example.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title:MyEventListener
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2017年12月6日 上午10:29:35
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class MyEventListener implements ActivitiEventListener {

	private static final Logger logger = LoggerFactory.getLogger(MyEventListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.activiti.engine.delegate.event.ActivitiEvent)
	 */
	@Override
	public void onEvent(ActivitiEvent event) {
		// TODO Auto-generated method stub
		logger.debug("\nevent type:" + event.getType() + "\nprocess instance id:" + event.getProcessInstanceId()
				+ "\nexecution id:" + event.getExecutionId());
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#isFailOnException()
	 */
	@Override
	public boolean isFailOnException() {
		// TODO Auto-generated method stub
		return false;
	}

}
