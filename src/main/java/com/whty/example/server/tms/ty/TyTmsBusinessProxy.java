/**
 * 
 */
package com.whty.example.server.tms.ty;

/**
 * <p>
 * Title:TyTmsBusinessProxy
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月21日 上午10:19:03
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public interface TyTmsBusinessProxy {

	/**
	 * 查询设备是否在系统中存在
	 * 
	 * @param deviceSn
	 *            设备SN
	 * @param deviceTerminalNo
	 *            设备终端号
	 * @return
	 */
	boolean doDeviceExist(String deviceSn, String deviceTerminalNo);

	/**
	 * 查询设备是否需要升级软件版本
	 * 
	 * @param deviceSn
	 *            设备SN
	 * @param deviceTerminalNo
	 *            设备终端号
	 * @param currentSoftwareVersion
	 *            设备当前软件版本
	 * @return
	 */
	boolean doDeviceNeedUpdate(String deviceSn, String deviceTerminalNo, String currentSoftwareVersion);

	/**
	 * 获取设备最新信息(软件/参数)
	 * 
	 * @param deviceSn
	 *            设备SN
	 * @param deviceTerminalNo
	 *            设备终端号
	 * @return
	 */
	TyTmsTerminalInfo getDeviceLatestInfo(String deviceSn, String deviceTerminalNo, String currentSoftwareVersion);

	/**
	 * 获取设备信息
	 * 
	 * @param deviceSn
	 *            设备SN
	 * @param deviceTerminalNo
	 *            设备终端号
	 * @param softTaskId
	 *            软件任务id
	 * @param softOldVersion
	 *            软件旧版本
	 * @param softNewVersion
	 *            软件新版本
	 * @return 软件详细信息（包含软件内容）
	 */
	TyTmsTerminalInfo getDeviceSoftwareInfo(String deviceSn, String deviceTerminalNo, String softTaskId,
			String softOldVersion, String softNewVersion);

}
