package com.whty.example.socket.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.whty.example.utils.GPMethods;

public class TestMinaClient {

	public static void main(String[] args) {
		System.out.println("path:" + TestMinaClient.class.getClassLoader().getResource("").getPath());

		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		/*PrefixedStringCodecFactory codecFactory = new PrefixedStringCodecFactory(Charset.forName("ISO-8859-1"));
		codecFactory.setDecoderPrefixLength(2);
		codecFactory.setEncoderPrefixLength(2);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(codecFactory));*/

		CustomMinaCodecFactory codecFactory = new CustomMinaCodecFactory(Charset.forName("ISO-8859-1"));
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(codecFactory));

		// connector.getFilterChain().addLast(
		// "codec",
		// new ProtocolCodecFilter(new TextLineCodecFactory(Charset
		// .forName("UTF-8"))));
		// connector.getFilterChain().addLast("codec",
		// new ProtocolCodecFilter(new DataCodecFactory(true)));
		// connector.getFilterChain().addLast(
		// "codec",
		// new ProtocolCodecFilter(new TextLineCodecFactory(Charset
		// .forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
		// LineDelimiter.WINDOWS.getValue())));
		connector.setHandler(new TestMinaClientHander());
		// connector.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
		// 外网映射
		// ConnectFuture connectFuture = connector.connect(new
		// InetSocketAddress(
		// "59.173.2.76", 1201));
		// 连接服务器
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 10011));
		// 等待建立连接
		connectFuture.awaitUninterruptibly();
		System.out.println("connect success.");

		IoSession session = connectFuture.getSession();
		// session.write("你好");
		Scanner sc = new Scanner(System.in);

		boolean quit = false;

		while (!quit) {

			String str = sc.next();
			if (str.equalsIgnoreCase("quit")) {
				quit = true;
				session.closeNow();
				continue;
			} else if (str.equalsIgnoreCase("disconnect")) {
				session.closeNow();
				continue;
			} else if (str.equalsIgnoreCase("connect")) {
				if (session != null && session.isConnected()) {
					continue;
				} else {
					connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 10011));
					// 等待建立连接
					connectFuture.awaitUninterruptibly();
					System.out.println("connect success.");

					session = connectFuture.getSession();
					continue;
				}
			}
			if (session == null || !session.isConnected()) {
				System.out.println("no connection detected.");
				continue;
			}
			if (str.length() % 2 != 0) {
				str += "F";
			}
			// str = new String(GPMethods.str2bytes(str));
			if (str.equalsIgnoreCase("8583")) {
				// 8583 message
				byte[] testData = new byte[] { (byte) 0x31, (byte) 0x32 };
				session.write(testData);
				continue;
			} else if (str.equalsIgnoreCase("1111")) {
				// send test data
				String stx = "02";
				String validData = "7001" + "D20170720                                         " + "666668888855555"
						+ "20170720" + "00" + "201712" + "16846145168461451684614516846145";
				validData = "6066668888" + "3030" + GPMethods.bytesToHexString(validData.getBytes());
				String lengthStr = GPMethods.getDecimalLen(validData, 4);
				String etx = "03";
				String lrc = "50";
				String requestData = stx + lengthStr + validData + etx + lrc;
				byte[] tempData = GPMethods.str2bytes(requestData);
				session.write(new String(tempData, Charset.forName("ISO-8859-1")));
				// byte[] tempData = GPMethods.str2bytes("123456");
				// session.write(new String(tempData,
				// Charset.forName("ISO-8859-1")));
				continue;
			}
			session.write(str);
		}

		// close scanner
		sc.close();

		// close session
		if (session != null) {
			if (session.isConnected()) {
				session.getCloseFuture().awaitUninterruptibly();
			}
			connector.dispose(true);
		}

	}
}