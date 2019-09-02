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
 * Title:TestNotificationListener
 * </p>
 * <p>
 * Description:测试通知listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:47:02
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TestNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		logger.debug("***********************");
		logger.debug("test notify:" + execution.getId());

		// 查询业务数据库动态分配邮件接收者（测试）
		execution.setVariable("testMailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("testMailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("testMailSubject", "动态指定发件人收件人");
		execution.setVariable("testMailContent", "请测试");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
