/**
 * 
 */
package com.whty.example.entity;

import java.util.List;

/**
 * <p>
 * Title:WorkFlowState
 * </p>
 * <p>
 * Description:工作流详细信息
 * </p>
 * <p>
 * Date:2017年12月5日 下午4:52:25
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class WorkFlowDetail {

	private boolean isFinished = false;

	private List<WorkFlowTask> taskList;

	/**
	 * 工作流是否已经结束
	 * 
	 * @return 工作流是否结束状态
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * 设置工作流结束状态
	 * 
	 * @param isFinished
	 *            结束状态
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * 获取工作流任务列表
	 * 
	 * @return 任务列表
	 */
	public List<WorkFlowTask> getTaskList() {
		return taskList;
	}

	/**
	 * 设置工作流任务列表
	 * 
	 * @param taskList
	 *            任务列表
	 */
	public void setTaskList(List<WorkFlowTask> taskList) {
		this.taskList = taskList;
	}

}
