/**
 * 
 */
package com.whty.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whty.example.pojo.FacecompareRecordAddition;
import com.whty.example.pojo.FacecompareRecordAdditionExample;
import com.whty.example.pojo.FacecompareRecordAdditionWithBLOBs;

/**
 * <p>
 * Title:FacecompareRecordAdditionService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年6月5日 下午7:04:55
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public interface FacecompareRecordAdditionService {

	long countByExample(FacecompareRecordAdditionExample example);

	int deleteByExample(FacecompareRecordAdditionExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(FacecompareRecordAdditionWithBLOBs record);

	int insertSelective(FacecompareRecordAdditionWithBLOBs record);

	List<FacecompareRecordAdditionWithBLOBs> selectByExampleWithBLOBs(FacecompareRecordAdditionExample example);

	List<FacecompareRecordAddition> selectByExample(FacecompareRecordAdditionExample example);

	FacecompareRecordAdditionWithBLOBs selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") FacecompareRecordAdditionWithBLOBs record,
			@Param("example") FacecompareRecordAdditionExample example);

	int updateByExampleWithBLOBs(@Param("record") FacecompareRecordAdditionWithBLOBs record,
			@Param("example") FacecompareRecordAdditionExample example);

	int updateByExample(@Param("record") FacecompareRecordAddition record,
			@Param("example") FacecompareRecordAdditionExample example);

	int updateByPrimaryKeySelective(FacecompareRecordAdditionWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(FacecompareRecordAdditionWithBLOBs record);

	int updateByPrimaryKey(FacecompareRecordAddition record);

}
