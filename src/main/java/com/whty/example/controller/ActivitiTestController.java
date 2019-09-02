/**
 * 
 */
package com.whty.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.example.entity.WorkFlowDetail;
import com.whty.example.entity.WorkFlowProcess;
import com.whty.example.entity.WorkFlowTask;
import com.whty.example.service.ActivitiWorkFlowService;

/**
 * <p>
 * Title:ActivitiTestClass
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2017年11月24日 下午3:33:25
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiTestController extends BaseController {

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

	@Autowired
	ActivitiWorkFlowService produceProcessService;

	// private static final Logger logger =
	// LoggerFactory.getLogger(ActivitiTestController.class);

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public void showCurrentTask(HttpServletRequest request, HttpServletResponse response, String processInstanceId,
			String userAccount) {
		List<WorkFlowTask> tasks = produceProcessService.getUserCurrentTaskList(userAccount, processInstanceId);
		if (tasks == null || tasks.size() == 0) {
			writeTextResult("当前用户无等待执行的任务", response);
			return;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("<body>");
		for (WorkFlowTask task : tasks) {
			sb.append("</br>");
			sb.append("task name:" + task.getTaskName() + "</br>" + "process instance id:" + task.getProcessInstanceId()
					+ "</br>" + "assignee:" + task.getAssignee());
		}
		sb.append("</body>");

		writeTextResult(sb.toString(), response);

		/*logger.debug("查看process当前任务");
		// 查询的是act_ru_task?
		List<Task> task = taskService.createTaskQuery().processInstanceId(processID).list();
		logger.debug("task list size:" + task.size());
		
		Task task1 = task.get(task.size() - 1);
		StringBuffer sb = new StringBuffer();
		sb.append("当前环节：\nassignee:" + task1.getAssignee() + "\nexecution id:" + task1.getExecutionId() + "\nid:"
				+ task1.getId() + "\nname:" + task1.getName() + "\nparent task id:" + task1.getParentTaskId()
				+ "\nprocess instance id:" + task1.getProcessInstanceId());
		// sb.append("推动流程到下一环节：" + task1 + "<br/>");
		// taskService.complete(task1.getId());
		// task1 =
		// taskService.createTaskQuery().executionId(task1.getExecutionId()).singleResult();
		// sb.append("第二环节：" + task1 + "<br/>");
		// logger.debug("测试完成");
		logger.debug(sb.toString());
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("remarks", "测试完成");
		result.put("data", sb.toString());
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;*/
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/done", method = RequestMethod.GET)
	public void finishCurrentTask(HttpServletRequest request, HttpServletResponse response, String userAccount,
			String processInstanceId, String taskId) {

		boolean result = produceProcessService.finishTask(userAccount, processInstanceId, taskId);
		writeTextResult("完成任务执行结果:" + result, response);
		return;

		/*logger.debug("查看process当前任务");
		// 查询的是act_ru_task?
		List<Task> task = taskService.createTaskQuery().processInstanceId(processID).list();
		if (task == null || task.size() == 0) {
			logger.debug("当前工作流已经无待处理的相关任务");
			writeTextResult("当前工作流已经无待处理的相关任务", response);
			return;
		}
		logger.debug("task list size:" + task.size());
		
		Task task1 = task.get(task.size() - 1);
		StringBuffer sb = new StringBuffer();
		sb.append("当前环节：\nassignee:" + task1.getAssignee() + "\nexecution id:" + task1.getExecutionId() + "\nid:"
				+ task1.getId() + "\nname:" + task1.getName() + "\nparent task id:" + task1.getParentTaskId()
				+ "\nprocess instance id:" + task1.getProcessInstanceId());
		// sb.append("推动流程到下一环节：" + task1 + "<br/>");
		// taskService.complete(task1.getId());
		// task1 =
		// taskService.createTaskQuery().executionId(task1.getExecutionId()).singleResult();
		// sb.append("第二环节：" + task1 + "<br/>");
		// logger.debug("测试完成");
		logger.debug(sb.toString());
		if (reason != null && reason.trim().length() > 0) {
			Map<String, Object> variables = new HashMap<>();
			variables.put("reason", reason);
			taskService.complete(task1.getId(), variables);
		} else {
			taskService.complete(task1.getId());
		}
		logger.debug("完成当前环节任务");
		task = taskService.createTaskQuery().processInstanceId(processID).list();
		if (task == null || task.size() == 0) {
			logger.debug("当前工作流任务已经完成");
			writeTextResult("当前工作流任务已完成", response);
			return;
		}
		logger.debug("task list size:" + task.size());
		
		task1 = task.get(task.size() - 1);
		// sb = new StringBuffer();
		sb.append("当前环节：\nassignee:" + task1.getAssignee() + "\nexecution id:" + task1.getExecutionId() + "\nid:"
				+ task1.getId() + "\nname:" + task1.getName() + "\nparent task id:" + task1.getParentTaskId()
				+ "\nprocess instance id:" + task1.getProcessInstanceId());
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("remarks", "测试完成");
		result.put("data", sb.toString());
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;*/
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public void showProcessHistory(HttpServletRequest request, HttpServletResponse response, String processInstanceId) {

		WorkFlowDetail workFlowDetail = produceProcessService.getWorkFlowDetail(processInstanceId);
		if (workFlowDetail == null) {
			writeTextResult("无任务历史", response);
			return;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<body>");
		sb.append("任务是否完成:" + workFlowDetail.isFinished());
		List<WorkFlowTask> tasks = workFlowDetail.getTaskList();
		if (tasks == null || tasks.size() == 0) {
			writeTextResult("无历史任务", response);
			return;
		}

		for (WorkFlowTask task : tasks) {
			sb.append("</br>");
			sb.append("task name:" + task.getTaskName() + "</br>" + "process instance id:" + task.getProcessInstanceId()
					+ "</br>" + "assignee:" + task.getAssignee() + "</br>" + "start time:" + task.getStartTime()
					+ "</br>" + "finish time:" + task.getFinishTime());
		}
		sb.append("</body>");

		writeTextResult(sb.toString(), response);
		return;

		/*logger.debug("查看process任务进度");
		List<HistoricTaskInstance> historicTaskList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processID).orderByTaskCreateTime().asc().list();
		if (historicTaskList == null || historicTaskList.size() == 0) {
			logger.debug("无工作流历史");
			writeTextResult("无工作流历史", response);
			return;
		}
		logger.debug("工作流历史记录:" + historicTaskList.size());
		StringBuffer sb = new StringBuffer();
		for (HistoricTaskInstance historicTask : historicTaskList) {
			logger.debug("name:" + historicTask.getName() + "\tasignee:" + historicTask.getAssignee()
					+ "\tprocess instance id:" + historicTask.getProcessInstanceId() + "\tstartTime"
					+ historicTask.getStartTime());
			sb.append("task_name:" + historicTask.getName() + "\n");
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("remarks", "测试完成");
		result.put("data", sb.toString());
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;*/
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/launch", method = RequestMethod.GET)
	public void launchProcess(HttpServletRequest request, HttpServletResponse response) {

		String workFlowId = produceProcessService.startWorkFlowProcess(WorkFlowProcess.PRODUCE_PROCESS);
		writeTextResult("工作流启动完成,流程id:" + workFlowId, response);
		return;

		/*logger.debug("测试activiti process启动");
		// 部署流程
		repositoryService.createDeployment().addClasspathResource("activiti/Repair.bpmn").deploy();
		// 开启流程，myprocess是流程的ID
		// 查询的表,act_hi_procinst还是act_ru_execution?
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("repairProcess");
		// String processInstanceId = processInstance.getId();
		// String processInstanceDeploymentId = processInstance.getDeploymentId();
		// logger.debug("process id:" + processInstanceId + "\tprocess deployment id:" +
		// processInstanceDeploymentId);
		logger.debug("process info:\nactivity id:" + processInstance.getActivityId() + "\nbusiness key:"
				+ processInstance.getBusinessKey() + "\ndeployment id:" + processInstance.getDeploymentId()
				+ "\ndescription:" + processInstance.getDescription() + "\nid:" + processInstance.getId() + "\nname:"
				+ processInstance.getName() + "\ndefinition id:" + processInstance.getProcessDefinitionId());
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("remarks", "repairProcess启动成功");
		result.put("data", "流程id:" + processInstance.getProcessInstanceId());
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;*/
	}

}
