package com.whty.example.socket.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.whty.example.utils.GPMethods;

public class TestMinaClientHander implements IoHandler {

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession arg0, Object message) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("client接受信息:"+message.toString());
		String str = message.toString();
		byte[] bf = str.getBytes(Charset.forName("ISO-8859-1"));
		str = GPMethods.bytesToHexString(bf);
		System.out.println("client received data:" + str);
	}

	@Override
	public void messageSent(IoSession arg0, Object message) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("client发送信息" + message.toString());
		String str = message.toString();
		byte[] bf = str.getBytes(Charset.forName("ISO-8859-1"));
		str = GPMethods.bytesToHexString(bf);
		System.out.println("client send data:" + str);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("client disconnected from:" + session.getRemoteAddress().toString());
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("client connected with:" + session.getRemoteAddress().toString());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("IDLE " + session.getIdleCount(status));
	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("session opened.");
	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}