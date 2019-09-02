/**
 * 
 */
package com.whty.example.activiti.produce;

import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title:TestListener
 * </p>
 * <p>
 * Description:测试任务listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:49:24
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.debug("test task notify:" + delegateTask.getId());

		// TODO
		// 结合业务从数据库查询可执行测试任务的人员（用户账号）作为任务的可操作员
		delegateTask.addCandidateUser("chenlian");
		delegateTask.addCandidateUser("wujianqiang");
		delegateTask.addCandidateUser("wangwei");

		Set<IdentityLink> identityLinks = delegateTask.getCandidates();
		if (identityLinks == null || identityLinks.size() == 0) {
			logger.debug("no candidate found for task");
			return;
		}
		logger.debug("test task candidate user assigned over.They are:");
		for (IdentityLink identityLink : identityLinks) {
			logger.debug(identityLink.getUserId());
		}

	}

}
