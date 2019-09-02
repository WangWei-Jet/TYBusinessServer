/**
 * 
 */
package com.whty.example.activiti;

import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title:RepairNotificationListener
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2017年12月4日 下午2:26:29
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ReturnFactoryNotificationListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReturnFactoryNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.debug("任务notify:" + delegateTask.getId());
		Set<String> variableNames = delegateTask.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
	}

}
