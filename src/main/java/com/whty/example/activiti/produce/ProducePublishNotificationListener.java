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
 * Title:ProducePublishNotificationListener
 * </p>
 * <p>
 * Description:生产发布通知listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:55:31
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ProducePublishNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProducePublishNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		logger.debug("***********************");
		logger.debug("produce publish notification notify:" + execution.getId());

		// 查询业务数据库动态分配邮件接收者（生产发布）
		execution.setVariable("producePublishMailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("producePublishMailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("producePublishMailSubject", "动态指定发件人收件人");
		execution.setVariable("producePublishMailContent", "请准备生产发布");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
