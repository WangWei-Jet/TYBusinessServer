/**
 * 
 */
package com.whty.example.service;

/**
 * <p>
 * Title:ActivitiProduceBusinessProxy
 * </p>
 * <p>
 * Description:工作流生产业务代理
 * </p>
 * <p>
 * Date:2017年12月7日 下午6:06:29
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public interface ActivitiProduceBusinessProxy extends ActivitiBusinessBaseProxy {
	/**
	 * 生产订单是否包含秘钥文件
	 * 
	 * @param processInstanceId
	 *            工作流实例id，可以获取到对应的生产订单信息
	 * @return
	 */
	public boolean isOrderHasKeyfile(String processInstanceId);

	/**
	 * 获取测试任务结果
	 * 
	 * @param processInstanceId
	 *            工作流实例id，可以获取到对应的生产订单信息
	 * @return 测试任务结果,测试通过返回true，测试不通过返回false
	 */
	public boolean getTestResult(String processInstanceId);
}
