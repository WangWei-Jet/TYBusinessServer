/**
 * 
 */
package com.whty.example.service;

import java.util.List;

import com.whty.example.entity.WorkFlowDetail;
import com.whty.example.entity.WorkFlowProcess;
import com.whty.example.entity.WorkFlowTask;

/**
 * 
 * <p>
 * Title:ActivitiWorkFlowService
 * </p>
 * <p>
 * Description:工作流service
 * </p>
 * <p>
 * Date:2017年12月7日 上午10:59:58
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public interface ActivitiWorkFlowService {
	/**
	 * 启动工作流实例
	 * 
	 * @param workFlowProcess
	 *            工作流类型
	 * @return 工作流实例id(每个实例id唯一,需要与业务数据库数据记录关系)
	 */
	public String startWorkFlowProcess(WorkFlowProcess workFlowProcess);

	/**
	 * 获取用户当前任务列表
	 * 
	 * @param userAccount
	 *            用户账号(业务数据库查询获取),不可为空
	 * @param processInstanceId
	 *            流程实例id,可为空
	 * @return
	 */
	public List<WorkFlowTask> getUserCurrentTaskList(String userAccount, String processInstanceId);

	/**
	 * 完成任务
	 * 
	 * @param userAccount
	 *            用户账号(业务数据库查询获取)
	 * @param processInstanceId
	 *            工作流实例ID
	 * @param taskId
	 *            任务id
	 * @return 操作结果
	 */
	public boolean finishTask(String userAccount, String processInstanceId, String taskId);

	/**
	 * 查询工作流详细信息
	 * 
	 * @param processInstanceId
	 *            工作流实例id
	 * @return 工作流详细信息
	 */
	public WorkFlowDetail getWorkFlowDetail(String processInstanceId);

}
