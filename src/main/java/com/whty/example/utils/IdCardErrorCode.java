package com.whty.example.utils;

import java.util.HashMap;

public class IdCardErrorCode {

	private static HashMap<String, String> errorCodeMap = new HashMap<String, String>();

	static {
		errorCodeMap.put("0000000", "成功");
		errorCodeMap.put("0101001", "version 为空");
		errorCodeMap.put("0101002", "app_id 为空");
		errorCodeMap.put("0101003", "biz_type 为空");
		errorCodeMap.put("0101004", "biz_time 为空");
		errorCodeMap.put("0101005", "biz_sequence_id 为空");
		errorCodeMap.put("0101006", "security_factor 为空");
		errorCodeMap.put("0101007", "encrypt_type 为空");
		errorCodeMap.put("0101008", "sign_type 为空");
		errorCodeMap.put("0101009", "sign 为空");
		errorCodeMap.put("0101010", "sign_factor 为空");
		errorCodeMap.put("0101019", "encrypt_factor 为空");
		errorCodeMap.put("0199004", "reqid 为空");
		errorCodeMap.put("0102001", "参数 json 格式错误");
		errorCodeMap.put("0102006", "security_factor 格式不正确");
		errorCodeMap.put("0102011", "biz_time 格式不正确");
		errorCodeMap.put("0102012", "biz_sequence_id 大于 128 位");
		errorCodeMap.put("0102016", "extension 格式错误");
		errorCodeMap.put("0102017", "attach 长度过长");
		errorCodeMap.put("0198002", "reqid 格式不正确");
		errorCodeMap.put("0103001", "请求地址上的 app_id 与请求参数中的 app_id不一致");
		errorCodeMap.put("0103002", "biz_type 不合法");
		errorCodeMap.put("0103004", "sign_factor 类型错误");
		errorCodeMap.put("0103005", "sign_type 类型错误");
		errorCodeMap.put("0103007", "encrypt_type 错误");
		errorCodeMap.put("0103009", "version 值不正确");
		errorCodeMap.put("0103010", "encrypt_factor 类型错误");
		errorCodeMap.put("0201001", "app_id 不可用");
		errorCodeMap.put("0201002", "无权访问相关业务类型");
		errorCodeMap.put("0201003", "biz_time 不在有效期范围内");
		errorCodeMap.put("0201004", "重复请求");
		errorCodeMap.put("0201005", "sign 验证错误");
		errorCodeMap.put("0301000", "验证失败");
		errorCodeMap.put("0301001", "库中无此证件号码");
		errorCodeMap.put("0301006", "地址请求格式错误");
		errorCodeMap.put("0399001", "库中未查到结果数据（信息获取类）");
		errorCodeMap.put("0400000", "增值服务系统异常");
	}

	public static String getErrorCodeDescription(String errorCode) {
		return errorCodeMap.get(errorCode);
	}
}
