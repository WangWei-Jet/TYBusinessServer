package com.whty.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whty.example.mapper.IdCardInfoMapper;
import com.whty.example.pojo.IdCardInfo;
import com.whty.example.pojo.IdCardInfoExample;
import com.whty.example.service.IdCardInfoService;

@Service
@Transactional
public class IdCardInfoServiceImpl implements IdCardInfoService {

	@Autowired
	IdCardInfoMapper idCardInfoMapper;

	@Override
	public List<IdCardInfo> selectByExample(IdCardInfoExample example) {
		return idCardInfoMapper.selectByExample(example);
	}

	@Override
	public int insert(IdCardInfo record) {
		return idCardInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(IdCardInfo record) {
		return idCardInfoMapper.insertSelective(record);
	}

	@Override
	public int updateByExampleSelective(IdCardInfo record, IdCardInfoExample example) {
		return idCardInfoMapper.updateByExampleSelective(record, example);
	}

}
