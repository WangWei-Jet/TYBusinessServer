/**
 * 
 */
package com.whty.example.activiti.produce;

import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.example.service.ActivitiProduceBusinessProxy;

/**
 * 
 * <p>
 * Title:MakeProduceToolNotificationListener
 * </p>
 * <p>
 * Description:制作生产工具通知 listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:36:55
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
@Service
public class MakeProduceToolNotificationListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ActivitiProduceBusinessProxy activitiProduceBusinessProxy;

	private static final Logger logger = LoggerFactory.getLogger(MakeProduceToolNotificationListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	@Override
	public void notify(DelegateExecution execution) {
		logger.debug("***********************");
		logger.debug("make produce tool notification notify:" + execution.getId());

		activitiProduceBusinessProxy.getTaskCandidates(execution.getEventName());

		// 查询业务数据库动态分配邮件接收者（制作生产工具）
		execution.setVariable("produceToolMailTo", "Wangwei01@whty.com.cn");
		execution.setVariable("produceToolMailFrom", "Wangwei01@whty.com.cn");
		execution.setVariable("produceToolMailSubject", "动态指定发件人收件人");
		execution.setVariable("produceToolMailContent", "请制作生产工具");
		Set<String> variableNames = execution.getVariableNames();
		for (String variableName : variableNames) {
			logger.debug("variable name:" + variableName);
		}
		logger.debug("***********************");

	}

}
