package com.whty.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whty.example.mapper.DeviceInfoMapper;
import com.whty.example.pojo.DeviceInfo;
import com.whty.example.pojo.DeviceInfoExample;
import com.whty.example.service.DeviceInfoService;

@Service
@Transactional
public class DeviceInfoServiceImpl implements DeviceInfoService {

	@Autowired
	DeviceInfoMapper deviceInfoMapper;

	@Override
	public List<DeviceInfo> selectByExample(DeviceInfoExample example) {
		// TODO Auto-generated method stub
		return deviceInfoMapper.selectByExample(example);
	}

	@Override
	public int insert(DeviceInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DeviceInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExampleSelective(DeviceInfo record, DeviceInfoExample example) {
		// TODO Auto-generated method stub
		return 0;
	}
}
