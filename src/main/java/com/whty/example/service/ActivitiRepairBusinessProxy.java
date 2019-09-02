/**
 * 
 */
package com.whty.example.service;

/**
 * <p>
 * Title:ActivitiRepairBusinessProxy
 * </p>
 * <p>
 * Description:工作流售后维修业务代理
 * </p>
 * <p>
 * Date:2017年12月7日 下午6:23:45
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public interface ActivitiRepairBusinessProxy extends ActivitiBusinessBaseProxy {

	/**
	 * 获取设备故障原因
	 * 
	 * @param processInstanceId
	 *            工作流实例id
	 * @return 设备故障原因
	 */
	public String getTroubleCause(String processInstanceId);

}
