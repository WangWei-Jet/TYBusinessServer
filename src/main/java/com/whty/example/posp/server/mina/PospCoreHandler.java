package com.whty.example.posp.server.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.whty.example.utils.CustomLogger;
import com.whty.example.utils.GPMethods;

public class PospCoreHandler extends IoHandlerAdapter {

	private CustomLogger logger = CustomLogger.getLogger(getClass());

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// 连接建立的时候被调用，在I/O processor线程，耗时操作尽量不要在此处处理
		super.sessionCreated(session);

		logger.debug("session created:\n" + "remote address:" + session.getRemoteAddress() + "\nlocal address:"
				+ session.getLocalAddress());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// sessionCreated调用之后被调用,不与 I/O processor在同一个线程
		super.sessionOpened(session);

		logger.debug("session opened:\n" + "remote address:" + session.getRemoteAddress() + "\nlocal address:"
				+ session.getLocalAddress());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// 连接断开的时候被调用
		super.sessionClosed(session);

		logger.debug("session closed:\n" + "remote address:" + session.getRemoteAddress() + "\nlocal address:"
				+ session.getLocalAddress());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// 连接状态空闲的时候被调用，UDP连接的时候不会被调用
		super.sessionIdle(session, status);

		logger.debug("session idle:\n" + "remote address:" + session.getRemoteAddress() + "\nlocal address:"
				+ session.getLocalAddress());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// 异常的时候触发，如果是 IOException，则连接会被自动关闭
		super.exceptionCaught(session, cause);

		logger.debug("exception caught:\n" + "remote address:" + session.getRemoteAddress() + "\nlocal address:"
				+ session.getLocalAddress());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// 收到数据的时候被调用
		super.messageReceived(session, message);
		String expression = message.toString();
		byte[] bf = expression.getBytes(Charset.forName("ISO-8859-1"));
		expression = GPMethods.bytesToHexString(bf);
		logger.debug("message received:" + expression + "\n" + "remote address:" + session.getRemoteAddress()
				+ "\nlocal address:" + session.getLocalAddress());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// 发送数据后被调用
		super.messageSent(session, message);
		String expression = message.toString();
		byte[] bf = expression.getBytes(Charset.forName("ISO-8859-1"));
		expression = GPMethods.bytesToHexString(bf);
		logger.debug("message sent:" + expression + "\n" + "remote address:" + session.getRemoteAddress()
				+ "\nlocal address:" + session.getLocalAddress());
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.inputClosed(session);

		logger.debug("input closed:\n" + "remote address:" + session.getRemoteAddress() + "\nlocal address:"
				+ session.getLocalAddress());
	}

}
