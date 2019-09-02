/**
 * 
 */
package com.whty.example.server.tms.ty;

/**
 * <p>
 * Title:ErrorCode
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月18日 下午4:49:59
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class ErrorCode {

	// 成功
	public static final String SUCCESS = "00";
	// 未知终端
	public static final String UNKOWN_TERMINAL = "01";
	// 软件为最新版本，不需要更新
	public static final String LATEST_SOFTWARE = "02";
	// 参数为最新版本，不需要更新
	public static final String LATEST_PARAM = "03";
	// 服务器拒绝更新
	public static final String SERVER_DENIED = "04";
	// 系统忙
	public static final String SERVER_BUSY = "05";
	// 找不到下载版本
	public static final String DOWNLOAD_VERSION_NOT_FOUND = "06";
	// 未知通知类型
	public static final String UNKOWN_NOTIFICATION_TYPE = "07";
	// Lrc校验位错误
	public static final String LRC_WRONG = "08";
	// 不需要更新
	public static final String UPDATE_NO_NEED = "09";
	// 请求版本不一致，重新下载
	public static final String REQUEST_VERSION_INCONSISTENT = "10";
	// 系统不支持
	public static final String NOT_SUPPORTED = "60";
	// 接口调用错误
	public static final String INVOKE_ERROR = "70";
	// 参数错误
	public static final String PARAM_ERROR = "71";
	// 请求数据错误(通用)
	public static final String REQUEST_DATA_ERROR = "99";

}
