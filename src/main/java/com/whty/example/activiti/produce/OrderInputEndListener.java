/**
 * 
 */
package com.whty.example.activiti.produce;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title:OrderInputEndListener
 * </p>
 * <p>
 * Description:订单输入任务结束listener
 * </p>
 * <p>
 * Date:2017年12月6日 下午6:03:53
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class OrderInputEndListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(OrderInputEndListener.class);

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		logger.debug("OrderInput finished notify:" + delegateTask.getId());

		// TODO
		// 订单输入完成后查看时候存在秘钥文件，决定是否进行mac/SN的分配
		delegateTask.setVariable("hasKeyFile", "0");

	}

}
