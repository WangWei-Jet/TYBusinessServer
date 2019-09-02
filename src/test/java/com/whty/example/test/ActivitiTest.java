/**
 * 
 */
package com.whty.example.test;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.test.Deployment;

/**
 * <p>
 * Title:ActivitiTest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2017年11月28日 下午3:01:57
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ActivitiTest extends PluggableActivitiTestCase {

	@Deployment(resources = "activiti/ProduceProcess.bpmn")
	public void test() {
		// processEngine = ProcessEngineConfiguration
		// .createProcessEngineConfigurationFromResource("activiti/activiti.cfg.xml").buildProcessEngine();
		runtimeService.startProcessInstanceByKey("produceProcess");
	}
}
