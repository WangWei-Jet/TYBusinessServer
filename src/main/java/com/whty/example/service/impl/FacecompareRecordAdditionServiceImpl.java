package com.whty.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whty.example.mapper.FacecompareRecordAdditionMapper;
import com.whty.example.pojo.FacecompareRecordAddition;
import com.whty.example.pojo.FacecompareRecordAdditionExample;
import com.whty.example.pojo.FacecompareRecordAdditionWithBLOBs;
import com.whty.example.service.FacecompareRecordAdditionService;

@Service
@Transactional
public class FacecompareRecordAdditionServiceImpl implements FacecompareRecordAdditionService {

	@Autowired
	FacecompareRecordAdditionMapper facecompareRecordAdditionMapper;

	@Override
	public long countByExample(FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return facecompareRecordAdditionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FacecompareRecordAdditionWithBLOBs record) {
		return facecompareRecordAdditionMapper.insert(record);
	}

	@Override
	public int insertSelective(FacecompareRecordAdditionWithBLOBs record) {
		return facecompareRecordAdditionMapper.insertSelective(record);
	}

	@Override
	public List<FacecompareRecordAdditionWithBLOBs> selectByExampleWithBLOBs(FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<FacecompareRecordAddition> selectByExample(FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.selectByExample(example);
	}

	@Override
	public FacecompareRecordAdditionWithBLOBs selectByPrimaryKey(Integer id) {
		return facecompareRecordAdditionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(FacecompareRecordAdditionWithBLOBs record,
			FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExampleWithBLOBs(FacecompareRecordAdditionWithBLOBs record,
			FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.updateByExampleWithBLOBs(record, example);
	}

	@Override
	public int updateByExample(FacecompareRecordAddition record, FacecompareRecordAdditionExample example) {
		return facecompareRecordAdditionMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(FacecompareRecordAdditionWithBLOBs record) {
		return facecompareRecordAdditionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(FacecompareRecordAdditionWithBLOBs record) {
		return facecompareRecordAdditionMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(FacecompareRecordAddition record) {
		return facecompareRecordAdditionMapper.updateByPrimaryKey(record);
	}
}
