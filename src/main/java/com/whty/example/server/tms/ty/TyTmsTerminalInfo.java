/**
 * 
 */
package com.whty.example.server.tms.ty;

/**
 * 天喻终端信息
 * <p>
 * Title:TyTmsTerminalInfo
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月21日 下午2:14:39
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyTmsTerminalInfo {
	// 软件任务id
	String softTaskId;
	// 旧软件版本
	String oldSoftVersion;
	// 新软件版本号
	String softVersion;
	// 软件内容
	byte[] softContent;
	// 软件长度
	String softLength;
	// 参数任务id
	String paramTaskId;
	// 旧参数版本
	String oldParamVersion;
	// 新参数版本号
	String paramVersion;
	// 参数内容
	String paramContent;
	// 参数长度
	String paramLength;

	public String getSoftTaskId() {
		return softTaskId;
	}

	public void setSoftTaskId(String softTaskId) {
		this.softTaskId = softTaskId;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public String getSoftLength() {
		return softLength;
	}

	public void setSoftLength(String softLength) {
		this.softLength = softLength;
	}

	public String getParamTaskId() {
		return paramTaskId;
	}

	public void setParamTaskId(String paramTaskId) {
		this.paramTaskId = paramTaskId;
	}

	public String getParamVersion() {
		return paramVersion;
	}

	public void setParamVersion(String paramVersion) {
		this.paramVersion = paramVersion;
	}

	public String getParamLength() {
		return paramLength;
	}

	public void setParamLength(String paramLength) {
		this.paramLength = paramLength;
	}

	public byte[] getSoftContent() {
		return softContent;
	}

	public void setSoftContent(byte[] softContent) {
		this.softContent = softContent;
	}

	public String getParamContent() {
		return paramContent;
	}

	public void setParamContent(String paramContent) {
		this.paramContent = paramContent;
	}

	public String getOldSoftVersion() {
		return oldSoftVersion;
	}

	public void setOldSoftVersion(String oldSoftVersion) {
		this.oldSoftVersion = oldSoftVersion;
	}

	public String getOldParamVersion() {
		return oldParamVersion;
	}

	public void setOldParamVersion(String oldParamVersion) {
		this.oldParamVersion = oldParamVersion;
	}

}
