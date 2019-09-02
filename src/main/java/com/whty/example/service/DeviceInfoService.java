package com.whty.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whty.example.pojo.DeviceInfo;
import com.whty.example.pojo.DeviceInfoExample;

public interface DeviceInfoService {

	// 查询身份证信息列表
	List<DeviceInfo> selectByExample(DeviceInfoExample example);

	// 添加身份证信息
	int insert(DeviceInfo record);

	// 添加身份证信息
	int insertSelective(DeviceInfo record);

	// 更新身份证信息
	int updateByExampleSelective(@Param("record") DeviceInfo record, @Param("example") DeviceInfoExample example);
}
