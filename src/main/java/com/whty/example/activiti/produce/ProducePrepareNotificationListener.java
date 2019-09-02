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
 * Title:ProducePrepareNotificationListener
 * </p>
 * <p>
 * Description:生产准备通知listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午3:00:32
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ProducePrepareNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProducePrepareNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		logger.debug("***********************");
		logger.debug("produce prepare notification notify:" + execution.getId());

		// 查询业务数据库动态分配邮件接收者（生产准备）
		execution.setVariable("producePrepareMailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("producePrepareMailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("producePrepareMailSubject", "动态指定发件人收件人");
		execution.setVariable("producePrepareMailContent", "请进行生产准备");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
