/**
 * 
 */
package com.whty.example.activiti;

import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
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
public class RepairNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RepairNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		// TODO Auto-generated method stub
		logger.debug("***********************");
		logger.debug("任务notify:" + execution.getId());
		execution.setVariable("mailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("mailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("mailSubject", "动态指定发件人收件人");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
