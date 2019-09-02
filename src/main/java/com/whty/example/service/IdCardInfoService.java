package com.whty.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whty.example.pojo.IdCardInfo;
import com.whty.example.pojo.IdCardInfoExample;

public interface IdCardInfoService {

	// 查询身份证信息列表
	List<IdCardInfo> selectByExample(IdCardInfoExample example);

	// 添加身份证信息
	int insert(IdCardInfo record);

	// 添加身份证信息
	int insertSelective(IdCardInfo record);

	// 更新身份证信息
	int updateByExampleSelective(@Param("record") IdCardInfo record, @Param("example") IdCardInfoExample example);
}
