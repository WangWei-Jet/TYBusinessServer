package com.whty.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.whty.example.entity.WorkFlowDetail;
import com.whty.example.entity.WorkFlowProcess;
import com.whty.example.entity.WorkFlowTask;
import com.whty.example.service.ActivitiWorkFlowService;

/*import com.whty.example.entity.WorkFlowDetail;
import com.whty.example.entity.WorkFlowProcess;
import com.whty.example.entity.WorkFlowTask;
import com.whty.example.service.ActivitiWorkFlowService;*/

/**
 * 
 * <p>
 * Title:ProduceProcessServiceImpl
 * </p>
 * <p>
 * Description:生产流程工作流service实现
 * </p>
 * <p>
 * Date:2017年12月7日 上午10:30:39
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
@Service
public class ActivitiWorkFlowServiceImpl implements ActivitiWorkFlowService {

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	HistoryService historyService;

	@Autowired
	ManagementService managementService;

	@Value("${ProduceProcessBpmnLocation}")
	private String produceProcessBpmnLocation;

	@Value("${ProduceProcessKey}")
	private String produceProcessKey;

	@Value("${RepairProcessBpmnLocation}")
	private String repairProcessBpmnLocation;

	@Value("${RepairProcessKey}")
	private String repairProcessKey;

	private static final Logger logger = LoggerFactory.getLogger(ActivitiWorkFlowServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiBaseService#startWorkFlowProcess()
	 */
	@Override
	public String startWorkFlowProcess(WorkFlowProcess workFlowProcess) {
		logger.debug("********************");
		logger.debug("startWorkFlowProcess");
		try {
			ProcessInstance processInstance = null;
			if (workFlowProcess == null) {
				return null;
			} else if (workFlowProcess == WorkFlowProcess.PRODUCE_PROCESS) {
				// 部署流程
				repositoryService.createDeployment().addClasspathResource(produceProcessBpmnLocation).deploy();
				// 开启流程，myprocess是流程的ID
				processInstance = runtimeService.startProcessInstanceByKey(produceProcessKey);
			} else if (workFlowProcess == WorkFlowProcess.REPAIR_PROCESS) {
				// 部署流程
				repositoryService.createDeployment().addClasspathResource(repairProcessBpmnLocation).deploy();
				// 开启流程，myprocess是流程的ID
				processInstance = runtimeService.startProcessInstanceByKey(repairProcessKey);
			} else {
				return null;
			}

			logger.debug("process info:\nactivity id:" + processInstance.getActivityId() + "\nbusiness key:"
					+ processInstance.getBusinessKey() + "\ndeployment id:" + processInstance.getDeploymentId()
					+ "\nid:" + processInstance.getId() + "\nname:" + processInstance.getName() + "\ndefinition id:"
					+ processInstance.getProcessDefinitionId() + "\nprocess instance id:"
					+ processInstance.getProcessInstanceId());
			return processInstance.getProcessInstanceId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiBaseService#getUserCurrentTaskList(java.lang.String)
	 */
	@Override
	public List<WorkFlowTask> getUserCurrentTaskList(String userAccount, String processInstanceId) {
		logger.debug("**********************");
		logger.debug("getUserCurrentTaskList");
		try {
			if (userAccount == null || userAccount.trim().length() == 0) {
				return null;
			}
			TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userAccount);
			if (processInstanceId != null) {
				taskQuery = taskQuery.processInstanceId(processInstanceId);
			}
			List<Task> task = taskQuery.list();
			if (task == null || task.size() == 0) {
				return null;
			}
			logger.debug("task list size:" + task.size());
			List<WorkFlowTask> taskList = new ArrayList<>();
			for (Task tempTask : task) {
				WorkFlowTask workFlowTask = new WorkFlowTask();
				workFlowTask.setProcessInstanceId(tempTask.getProcessInstanceId());
				workFlowTask.setTaskName(tempTask.getName());
				workFlowTask.setTaskId(tempTask.getId());
				workFlowTask.setStartTime(tempTask.getCreateTime());
				workFlowTask.setAssignee(tempTask.getAssignee());
				taskList.add(workFlowTask);
			}
			return taskList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiBaseService#finishTask(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean finishTask(String userAccount, String processInstanceId, String taskId) {
		logger.debug("**********");
		logger.debug("finishTask");
		try {
			if (userAccount == null || userAccount.trim().length() == 0) {
				logger.debug("传入的任务完成人为空");
				return false;
			}
			TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userAccount);
			if (processInstanceId != null) {
				taskQuery = taskQuery.processInstanceId(String.valueOf(processInstanceId));
			}
			if (taskId != null) {
				taskQuery = taskQuery.taskId(String.valueOf(taskId));
			}
			// 查询的是act_ru_task?
			List<Task> taskList = taskQuery.list();
			if (taskList == null || taskList.size() == 0) {
				logger.debug("未找到对应的正在进行中的任务，无法完成");
				return false;
			}
			logger.debug("task list size:" + taskList.size());
			if (taskList.size() > 1) {
				logger.debug("系统数据异常，找到两个相同任务");
				return false;
			}

			Task task = taskList.get(0);
			taskId = task.getId();
			logger.debug("当前待执行任务：\nassignee:" + task.getAssignee() + "\nexecution id:" + task.getExecutionId()
					+ "\nid:" + task.getId() + "\nname:" + task.getName() + "\nparent task id:" + task.getParentTaskId()
					+ "\nprocess instance id:" + task.getProcessInstanceId());
			try {
				// 申领任务
				taskService.claim(String.valueOf(taskId), userAccount);
			} catch (ActivitiTaskAlreadyClaimedException e) {
				logger.debug("任务已经被申领，无法再申领");
				return false;
			}
			// 完成任务
			taskService.complete(String.valueOf(taskId));
			logger.debug("完成当前环节任务");
			taskList = getTaskListByProcessInstance(taskService, String.valueOf(processInstanceId));
			if (taskList == null || taskList.size() == 0) {
				logger.debug("当前工作流任务已经完成");
				return true;
			}
			task = taskList.get(0);
			logger.debug("任务已完成,当前待执行任务：\nassignee:" + task.getAssignee() + "\nexecution id:" + task.getExecutionId()
					+ "\nid:" + task.getId() + "\nname:" + task.getName() + "\nparent task id:" + task.getParentTaskId()
					+ "\nprocess instance id:" + task.getProcessInstanceId());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiBaseService#getWorkFlowState(java.lang.Long)
	 */
	@Override
	public WorkFlowDetail getWorkFlowDetail(String processInstanceId) {
		logger.debug("*****************");
		logger.debug("getWorkFlowDetail");
		try {
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(String.valueOf(processInstanceId)).singleResult();
			if (historicProcessInstance == null) {
				logger.debug("未找到对应的工作流记录");
				return null;
			}
			WorkFlowDetail workFlowState = new WorkFlowDetail();
			if (historicProcessInstance.getEndTime() != null) {
				workFlowState.setFinished(true);
			} else {
				workFlowState.setFinished(false);
			}
			List<HistoricTaskInstance> historicTaskList = historyService.createHistoricTaskInstanceQuery()
					.processInstanceId(String.valueOf(processInstanceId)).orderByTaskCreateTime().asc().list();
			if (historicTaskList == null || historicTaskList.size() == 0) {
				logger.debug("无工作流历史");
				return null;
			}
			logger.debug("工作流历史记录:" + historicTaskList.size());
			List<WorkFlowTask> taskList = new ArrayList<>();
			for (HistoricTaskInstance historicTask : historicTaskList) {
				logger.debug("name:" + historicTask.getName() + "\tasignee:" + historicTask.getAssignee()
						+ "\tprocess instance id:" + historicTask.getProcessInstanceId() + "\tstartTime"
						+ historicTask.getStartTime() + "\nfinishTime:" + historicTask.getEndTime());
				WorkFlowTask workFlowTask = new WorkFlowTask();
				workFlowTask.setProcessInstanceId(historicTask.getProcessInstanceId());
				workFlowTask.setTaskName(historicTask.getName());
				workFlowTask.setTaskId(historicTask.getId());
				workFlowTask.setStartTime(historicTask.getCreateTime());
				workFlowTask.setFinishTime(historicTask.getEndTime());
				workFlowTask.setAssignee(historicTask.getAssignee());
				taskList.add(workFlowTask);
			}
			workFlowState.setTaskList(taskList);
			return workFlowState;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Task> getTaskListByProcessInstance(TaskService taskService, String processInstanceId) {
		return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
	}

}
