package com.whty.example.socket.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.example.server.tms.TMSAPPInfoDownloadRequest;
import com.whty.example.server.tms.TMSAPPInfoDownloadResponse;
import com.whty.example.server.tms.TMSBaseRequest;
import com.whty.example.server.tms.TMSBaseResponse;
import com.whty.example.server.tms.TMSConfirmNotificationRequest;
import com.whty.example.server.tms.TMSConfirmNotificationResponse;
import com.whty.example.server.tms.TMSInitializationRequest;
import com.whty.example.server.tms.TMSRemoteAppDownloadRequest;
import com.whty.example.server.tms.TMSScriptFileDownloadConfirmationRequest;
import com.whty.example.server.tms.TMSScriptFileDownloadConfirmationResponse;
import com.whty.example.server.tms.TMSScriptFileDownloadRequest;
import com.whty.example.server.tms.TMSScriptFileDownloadResponse;
import com.whty.example.server.tms.TMSTerminalInfoUploadRequest;
import com.whty.example.server.tms.TMSTerminalSwitchRequest;
import com.whty.example.server.tms.ValidateResult;
import com.whty.example.utils.GPMethods;

public class TMSMinaServerHandlerAdapter extends IoHandlerAdapter {

	// private static final Logger logger = Logger
	// .getLogger(DefaultMinaServerHandlerAdapter.class);
	private static final Logger logger = LoggerFactory.getLogger(TMSMinaServerHandlerAdapter.class);

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
		logger.error("exception caught by server:" + cause.getMessage() + "\nIoSession Remote Address:"
				+ session.getRemoteAddress());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
		String expression = message.toString();
		byte[] bf = expression.getBytes(Charset.forName("ISO-8859-1"));
		expression = GPMethods.bytesToHexString(bf);
		logger.debug(
				"received data from remote address:" + session.getRemoteAddress() + "\nreceive data:" + expression);
		// session.write(message);
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
		logger.debug("send data to remote address:" + session.getRemoteAddress() + "\nsend data:" + expression);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		logger.debug("session between " + session.getRemoteAddress() + " closed.");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		logger.debug("session between " + session.getRemoteAddress() + " created.");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		logger.debug("session between " + session.getRemoteAddress() + " opened.");
	}

	private String handleCompleteReceivedMessage(String completeMessage) {
		if (completeMessage == null || completeMessage.length() < 14) {
			logger.debug("request message length error.TOO SHORT!");
			return null;
		}
		String stx = null;
		String lengthStr = null;
		String tempValidData = null;
		String etx = null;
		String lrc = null;
		try {
			stx = completeMessage.substring(0, 2);
			System.out.println("stx:" + stx);
			lengthStr = completeMessage.substring(2, 6);
			System.out.println("lengthStr:" + lengthStr);
			int lengthInt = Integer.valueOf(lengthStr);
			System.out.println("length:" + lengthInt);

			tempValidData = completeMessage.substring(6, 6 + lengthInt * 2);
			System.out.println("tempValidData:" + tempValidData);
			etx = completeMessage.substring(6 + lengthInt * 2, 8 + lengthInt * 2);
			System.out.println("etx:" + etx);
			String calLrc = GPMethods.doLrc(stx + lengthStr + tempValidData + etx);
			lrc = completeMessage.substring(8 + lengthInt * 2, 10 + lengthInt * 2);
			System.out.println("\nlrc:" + lrc + "\ncalLrc:" + calLrc);
			if (!calLrc.equalsIgnoreCase(lrc)) {
				logger.debug("lrc wrong!");
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.debug("request message length error.TOO SHORT!");
			return null;
		} catch (NumberFormatException e) {
			logger.debug("request message error.VALID DATA LENGTH ERROR!");
			return null;
		} catch (Exception e) {
			logger.debug("request message error.UNKNOW EXCEPTION!");
			return null;
		}

		String tpdu = tempValidData.substring(0, 10);
		String tmsVersion = tempValidData.substring(10, 14);
		String tmsRequestData = tempValidData.substring(14);
		String responseData = handleRequestData(tmsRequestData);
		if (responseData == null || responseData.length() == 0) {
			logger.debug("no response data will be sent.");
			return null;
		}
		tempValidData = tpdu + tmsVersion + responseData;
		lengthStr = GPMethods.getDecimalLen(tempValidData, 4);
		lrc = GPMethods.doLrc(stx + lengthStr + tempValidData + etx);
		String completeResponseData = stx + lengthStr + tempValidData + etx + lrc;
		return completeResponseData;
	}

	private String handleRequestData(String tmsRequestData) {
		if (tmsRequestData == null || tmsRequestData.length() < 8) {
			logger.debug("request data length error!");
			return null;
		}
		String requestCode = new String(GPMethods.str2bytes(tmsRequestData.substring(0, 8)));
		TMSBaseRequest tmsRequest = null;
		TMSBaseResponse tmsResponse = null;
		if ("7001".equals(requestCode)) {
			tmsRequest = new TMSRemoteAppDownloadRequest(tmsRequestData);
			// tmsResponse = new TMSRemoteAppDownloadResponse(tmsRequest, null);
			tmsResponse = new TMSBaseResponse(tmsRequest, null);
			logger.debug("receive remote app download request!");
		} else if ("7002".equals(requestCode)) {
			tmsRequest = new TMSTerminalInfoUploadRequest(tmsRequestData);
			tmsResponse = new TMSBaseResponse(tmsRequest, null);
			logger.debug("receive terminal info upload request!");
		} else if ("7003".equals(requestCode)) {
			tmsRequest = new TMSConfirmNotificationRequest(tmsRequestData);
			tmsResponse = new TMSConfirmNotificationResponse(tmsRequest, null);
			logger.debug("receive confirm notification request!");
		} else if ("7004".equals(requestCode)) {
			tmsRequest = new TMSInitializationRequest(tmsRequestData);
			tmsResponse = new TMSBaseResponse(tmsRequest, null);
			logger.debug("receive initialization request!");
		} else if ("7005".equals(requestCode)) {
			tmsRequest = new TMSTerminalSwitchRequest(tmsRequestData);
			tmsResponse = new TMSBaseResponse(tmsRequest, null);
			logger.debug("receive terminal switch request!");
		} else if ("7006".equals(requestCode)) {
			tmsRequest = new TMSAPPInfoDownloadRequest(tmsRequestData);
			tmsResponse = new TMSAPPInfoDownloadResponse(tmsRequest, null);
			logger.debug("receive app info download request!");
		} else if ("7007".equals(requestCode)) {
			tmsRequest = new TMSScriptFileDownloadRequest(tmsRequestData);
			tmsResponse = new TMSScriptFileDownloadResponse(tmsRequest, null);
			logger.debug("receive scriptfile download request!");
		} else if ("7008".equals(requestCode)) {
			tmsRequest = new TMSScriptFileDownloadConfirmationRequest(tmsRequestData);
			tmsResponse = new TMSScriptFileDownloadConfirmationResponse(tmsRequest, null);
			logger.debug("receive scriptfile download confirmation request!");
		}
		if (tmsRequest == null) {
			logger.debug("unrecognized message type!");
			return null;
		}
		ValidateResult validateResult = tmsRequest.validate();
		if (!validateResult.isValidated()) {
			logger.debug("request format validate fail!");
			return null;
		}
		if (tmsResponse == null) {
			return null;
		}
		tmsResponse.setResponseCode(validateResult.getResponseCode());
		String responseMessage = tmsResponse.packMessage();
		return responseMessage;
	}

}
