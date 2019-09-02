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
 * Title:DistributeSnNotificationListener
 * </p>
 * <p>
 * Description:分配sn通知listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:27:09
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class DistributeSnNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DistributeSnNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		logger.debug("***********************");
		logger.debug("distribute sn notify:" + execution.getId());

		// 查询业务数据库动态分配邮件接收者（sn分配）
		execution.setVariable("snMailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("snMailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("snMailSubject", "动态指定发件人收件人");
		execution.setVariable("snMailContent", "请分配sn");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
