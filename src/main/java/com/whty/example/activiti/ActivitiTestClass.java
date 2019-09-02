/**
 * 
 */
package com.whty.example.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class ActivitiTestClass {

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

}
