package com.whty.example.server.tms.ty;

import java.io.IOException;
import java.io.InputStream;

import com.whty.example.utils.GPMethods;

public class TyTmsDownloadResponse extends TyTmsBaseResponse {

	byte[] hardware;

	/**
	 * @param request
	 */
	public TyTmsDownloadResponse(TyTmsBaseRequest request) {
		super(request);
		InputStream is = getClass().getClassLoader().getResourceAsStream("spring.xml");
		// 获取固件内容
		try {
			int fileLength = is.available();
			hardware = new byte[fileLength];
			int readLength = is.read(hardware, 0, fileLength);
			if (readLength < 0) {
				logger.info("文件读取出错");
			}
		} catch (IOException e) {
			logger.error("文件操作出错{}", e);
		}
	}

	@Override
	public String packValidateFailMessage(String responseCode) {
		String dataLength = GPMethods.generateZero(8);
		String data = responseCode + dataLength;
		data = GPMethods.bytesToHexString(data.getBytes());
		String lrc = GPMethods.doLrc(data);
		return data + lrc;
	}

	@Override
	public String packValidateSuccessMessage() {
		TyTmsDownloadRequest downloadRequest = null;
		try {
			downloadRequest = (TyTmsDownloadRequest) request;
		} catch (Exception e) {
			logger.error("传入的请求有误{}", e);
			return null;
		}
		// 起始位（下载开始位置，断点续传）
		String start = downloadRequest.getStart();
		int requestDownloadPosition = Integer.valueOf(start);
		// 长度（可下载的最大长度）
		String length = downloadRequest.getLength();
		int requestDataLen = Integer.valueOf(length);

		int responseDataLen = Math.min(requestDataLen, hardware.length - requestDownloadPosition);
		String dataLength = responseDataLen + "";
		while (dataLength.length() < 8) {
			dataLength = "0" + dataLength;
		}
		byte[] targetData = new byte[responseDataLen];
		System.arraycopy(hardware, requestDownloadPosition, targetData, 0, targetData.length);
		String data = GPMethods.bytesToHexString(targetData);

		String dataSection = "00" + dataLength + data;
		dataSection = GPMethods.bytesToHexString(dataSection.getBytes());
		String lrc = GPMethods.doLrc(dataSection);
		return dataSection + lrc;

	}
}
