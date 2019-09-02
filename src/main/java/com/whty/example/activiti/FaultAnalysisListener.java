/**
 * 
 */
package com.whty.example.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title:FaultAnalysisListener
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2017年12月4日 下午1:50:34
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FaultAnalysisListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(FaultAnalysisListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.debug("任务notify:" + delegateTask.getId());

		// delegateTask.setAssignee("故障分析员");
		delegateTask.addCandidateUser("故障分析员1");
		delegateTask.addCandidateUser("故障分析员2");
		delegateTask.addCandidateUser("故障分析员3");

	}

}
