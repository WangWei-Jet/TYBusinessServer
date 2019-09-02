/**
 * 
 */
package com.whty.example.activiti.produce;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title:TestEndListener
 * </p>
 * <p>
 * Description:测试结束任务listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午6:07:11
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestEndListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TestEndListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.debug("test task finished notify:" + delegateTask.getId());

		// TODO
		// 测试结束查询测试结果，决定下一工作流程
		delegateTask.setVariable("testPass", "0");

	}

}
