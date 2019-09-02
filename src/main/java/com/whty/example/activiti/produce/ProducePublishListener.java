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
 * Title:ProducePublishListener
 * </p>
 * <p>
 * Description:生产发布任务listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午2:58:42
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ProducePublishListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ProducePublishListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.debug("produce publish notify:" + delegateTask.getId());

		// TODO
		// 结合业务从数据库查询可执行生产发布任务的人员（用户账号）作为任务的可操作员
		delegateTask.addCandidateUser("luyijie");
		delegateTask.addCandidateUser("yuxunchao");

		Set<IdentityLink> identityLinks = delegateTask.getCandidates();
		if (identityLinks == null || identityLinks.size() == 0) {
			logger.debug("no candidate found for task");
			return;
		}
		logger.debug("produce publish task candidate user assigned over.They are:");
		for (IdentityLink identityLink : identityLinks) {
			logger.debug(identityLink.getUserId());
		}

	}

}
