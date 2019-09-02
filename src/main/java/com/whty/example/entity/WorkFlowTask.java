/**
 * 
 */
package com.whty.example.entity;

import java.util.Date;

/**
 * <p>
 * Title:WorkFlowTask
 * </p>
 * <p>
 * Description:工作流任务描述
 * </p>
 * <p>
 * Date:2017年12月5日 下午4:53:09
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class WorkFlowTask {

	private String taskId;

	private String taskName;

	private String assignee;

	private String processInstanceId;

	private String processInstanceName;

	private Date startTime;

	private Date finishTime;

	/**
	 * 获取任务id
	 * 
	 * @return 任务id
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * 设置任务id
	 * 
	 * @param taskId
	 *            任务id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取任务对应的工作流流程实例id
	 * 
	 * @return 工作流流程实例id
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * 设置任务对应的工作流流程实例id
	 * 
	 * @param processInstanceId
	 *            工作流流程实例id
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * 获取流程实例名称
	 * 
	 * @return 流程实例名称
	 */
	public String getProcessInstanceName() {
		return processInstanceName;
	}

	/**
	 * 设置流程实例名称
	 * 
	 * @param processInstanceName
	 *            流程实例名称
	 */
	public void setProcessInstanceName(String processInstanceName) {
		this.processInstanceName = processInstanceName;
	}

	/**
	 * 获取任务名称
	 * 
	 * @return 任务名称
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * 设置任务名称
	 * 
	 * @param taskName
	 *            任务名称
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * 获取任务代理人
	 * 
	 * @return 任务代理人
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * 设置任务代理人
	 * 
	 * @param assignee
	 *            任务代理人
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * 获取任务开始时间
	 * 
	 * @return 任务开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置任务开始时间
	 * 
	 * @param startTime
	 *            任务开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取任务完成时间
	 * 
	 * @return 任务完成时间
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * 设置任务完成时间
	 * 
	 * @param finishTime
	 *            任务完成时间
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}
