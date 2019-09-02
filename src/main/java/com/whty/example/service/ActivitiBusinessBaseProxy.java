/**
 * 
 */
package com.whty.example.service;

import java.util.List;

/**
 * <p>
 * Title:ActivitiWorkFlowBusinessProxy
 * </p>
 * <p>
 * Description:工作流业务代理（由具体的业务类实现接口）
 * </p>
 * <p>
 * Date:2017年12月7日 下午4:27:06
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public interface ActivitiBusinessBaseProxy {

	/**
	 * 获取任务执行候选人列表
	 * 
	 * @param taskName
	 *            任务名称
	 * @return 任务执行候选人列表(用户账号account)
	 */
	public List<String> getTaskCandidates(String taskName);

	/**
	 * 获取邮件接收人列表
	 * 
	 * @param emailTaskName
	 *            邮件任务名称
	 * @return 邮件接收人列表
	 */
	public List<String> getEmailReceivers(String emailTaskName);
}
