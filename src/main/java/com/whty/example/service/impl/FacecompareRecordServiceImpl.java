package com.whty.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whty.example.mapper.FacecompareRecordMapper;
import com.whty.example.pojo.FacecompareRecord;
import com.whty.example.pojo.FacecompareRecordExample;
import com.whty.example.service.FacecompareRecordService;

@Service
@Transactional
public class FacecompareRecordServiceImpl implements FacecompareRecordService {

	@Autowired
	FacecompareRecordMapper facecompareRecordMapper;

	@Override
	public long countByExample(FacecompareRecordExample example) {
		return facecompareRecordMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(FacecompareRecordExample example) {
		return facecompareRecordMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return facecompareRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FacecompareRecord record) {
		return facecompareRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(FacecompareRecord record) {
		return facecompareRecordMapper.insertSelective(record);
	}

	@Override
	public List<FacecompareRecord> selectByExample(FacecompareRecordExample example) {
		return facecompareRecordMapper.selectByExample(example);
	}

	@Override
	public FacecompareRecord selectByPrimaryKey(Integer id) {
		return facecompareRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(FacecompareRecord record, FacecompareRecordExample example) {
		return facecompareRecordMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(FacecompareRecord record, FacecompareRecordExample example) {
		return facecompareRecordMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(FacecompareRecord record) {
		return facecompareRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FacecompareRecord record) {
		return facecompareRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertSelectiveWithIdBack(FacecompareRecord record) {
		return facecompareRecordMapper.insertSelectiveWithIdBack(record);
	}

}
