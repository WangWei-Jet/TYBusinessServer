package com.whty.example.server.tms.ty;

import java.nio.charset.Charset;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.example.utils.GPMethods;

public class TyTmsMinaServerHandlerAdapter extends IoHandlerAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
		logger.error("exception caught by server:{}\nerror detail:{}", session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
		String expression = message.toString();
		byte[] bf = expression.getBytes(Charset.forName("ISO-8859-1"));
		expression = GPMethods.bytesToHexString(bf);
		logger.info("received data from remote address:{}\nreceive data:{}", session.getRemoteAddress(), expression);
		String responseData = handleCompleteReceivedMessage(expression);
		if (responseData != null && responseData.length() > 0) {
			byte[] tempBytes = GPMethods.str2bytes(responseData);
			session.write(new String(tempBytes, Charset.forName("ISO-8859-1")));
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
		String expression = message.toString();
		byte[] bf = expression.getBytes(Charset.forName("ISO-8859-1"));
		expression = GPMethods.bytesToHexString(bf);
		logger.info("send data to remote address:{}\nsend data:{}", session.getRemoteAddress(), expression);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		logger.info("session {} closed.", session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		logger.info("session {} created.", session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		logger.info("session {} opened.", session);
	}

	private String handleCompleteReceivedMessage(String tmsRequestData) {
		if (tmsRequestData == null || tmsRequestData.length() < (2 + 8 + 32) * 2) {
			logger.info("请求数据长度错误!长度:" + (tmsRequestData == null ? 0 : tmsRequestData.length()));
			return null;
		}
		TyTmsBaseRequest tmsRequest = null;
		TyTmsBaseResponse tmsResponse = null;
		String requestCode = new String(GPMethods.str2bytes(tmsRequestData.substring(0, 2 * 2)));
		if ("10".equals(requestCode)) {
			logger.info("POS更新请求!");
			tmsRequest = new TyTmsCheckUpdateRequest(tmsRequestData, null);
			tmsResponse = new TyTmsCheckUpdateResponse(tmsRequest);
		} else if ("11".equals(requestCode)) {
			logger.info("POS下载请求!");
			tmsRequest = new TyTmsDownloadRequest(tmsRequestData, null);
			tmsResponse = new TyTmsDownloadResponse(tmsRequest);
		} else if ("12".equals(requestCode)) {
			logger.info("安装完成通知!");
			tmsRequest = new TyTmsNotificationRequest(tmsRequestData, null);
			tmsResponse = new TyTmsNotificationResponse(tmsRequest);
		} else {
			logger.info("未知请求");
			return null;
		}
		String responseMessage = tmsResponse.packResponse();
		return responseMessage;
	}

}
