/**
 * 
 */
package com.whty.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.whty.example.activiti.produce.MakeProduceToolNotificationListener;
import com.whty.example.service.ActivitiProduceBusinessProxy;

/**
 * <p>
 * Title:ActivitiProduceBusinessProxyImpl
 * </p>
 * <p>
 * Description:工作流生产流程代理实现类
 * </p>
 * <p>
 * Date:2017年12月8日 下午2:09:46
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
@Service
public class ActivitiProduceBusinessProxyImpl implements ActivitiProduceBusinessProxy {

	private static final Logger logger = LoggerFactory.getLogger(MakeProduceToolNotificationListener.class);

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiBusinessBaseProxy#getTaskCandidates(java.lang.String)
	 */
	@Override
	public List<String> getTaskCandidates(String taskName) {
		// TODO Auto-generated method stub
		logger.debug("getTaskCandidates");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiBusinessBaseProxy#getEmailReceivers(java.lang.String)
	 */
	@Override
	public List<String> getEmailReceivers(String emailTaskName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiProduceBusinessProxy#isOrderHasKeyfile(java.lang.String)
	 */
	@Override
	public boolean isOrderHasKeyfile(String processInstanceId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.whty.example.service.ActivitiProduceBusinessProxy#getTestResult(java.lang.String)
	 */
	@Override
	public boolean getTestResult(String processInstanceId) {
		// TODO Auto-generated method stub
		return false;
	}

}
