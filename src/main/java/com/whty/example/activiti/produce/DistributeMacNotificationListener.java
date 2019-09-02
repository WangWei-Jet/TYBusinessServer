/**
 * 
 */
package com.whty.example.activiti.produce;

import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title:DistributeMacNotificationListener
 * </p>
 * <p>
 * Description:分配mac邮件通知listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:21:22
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class DistributeMacNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DistributeMacNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		logger.debug("***********************");
		logger.debug("distribute mac notify:" + execution.getId());

		// 查询业务数据库动态分配邮件接收者（mac分配）
		execution.setVariable("macMailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("macMailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("macMailSubject", "动态指定发件人收件人");
		execution.setVariable("macMailContent", "请分配mac");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
