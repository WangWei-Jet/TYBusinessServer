package com.whty.example.server.tms;

public class TMSTerminalAPPInfo {
	// 应用名称
	String app_name;
	// 应用版本信息
	String app_edition;
	// 应用程序更新方式,0x0000——不更新， 0x0001——更新， 0x0002——新增，0x0004——删除
	String app_update;
	// 应用参数更新方式, 0x0000——不更新， 0x0001——更新， 0x0002——新增，0x0004——删除
	String app_para_update;
	// 保留数据，此字段置为全 0x00,目前不用
	String app_down_time;
	// 保 留 数 据 ， 目 前 不 用 。 应 用 更 新 时 间 (YYYYMMDDHHMMSS)，立即更新时，此字段置为全 0x00
	String app_update_time;
	// 保留
	String app_enable;
	// 保留数据（0X01—强制更新,0x00—结算更新）
	String other;

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_edition() {
		return app_edition;
	}

	public void setApp_edition(String app_edition) {
		this.app_edition = app_edition;
	}

	public String getApp_update() {
		return app_update;
	}

	public void setApp_update(String app_update) {
		this.app_update = app_update;
	}

	public String getApp_para_update() {
		return app_para_update;
	}

	public void setApp_para_update(String app_para_update) {
		this.app_para_update = app_para_update;
	}

	public String getApp_down_time() {
		return app_down_time;
	}

	public void setApp_down_time(String app_down_time) {
		this.app_down_time = app_down_time;
	}

	public String getApp_update_time() {
		return app_update_time;
	}

	public void setApp_update_time(String app_update_time) {
		this.app_update_time = app_update_time;
	}

	public String getApp_enable() {
		return app_enable;
	}

	public void setApp_enable(String app_enable) {
		this.app_enable = app_enable;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
}
