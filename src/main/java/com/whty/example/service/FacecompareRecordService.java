/**
 * 
 */
package com.whty.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whty.example.pojo.FacecompareRecord;
import com.whty.example.pojo.FacecompareRecordExample;

/**
 * <p>
 * Title:FacecompareRecordService
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
public interface FacecompareRecordService {
	long countByExample(FacecompareRecordExample example);

	int deleteByExample(FacecompareRecordExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(FacecompareRecord record);

	int insertSelective(FacecompareRecord record);

	List<FacecompareRecord> selectByExample(FacecompareRecordExample example);

	FacecompareRecord selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") FacecompareRecord record,
			@Param("example") FacecompareRecordExample example);

	int updateByExample(@Param("record") FacecompareRecord record, @Param("example") FacecompareRecordExample example);

	int updateByPrimaryKeySelective(FacecompareRecord record);

	int updateByPrimaryKey(FacecompareRecord record);

	int insertSelectiveWithIdBack(FacecompareRecord record);
}
