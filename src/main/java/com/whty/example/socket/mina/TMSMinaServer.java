package com.whty.example.socket.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TMSMinaServer extends HttpServlet {

	private static final long serialVersionUID = -8402570483855258171L;

	private static NioSocketAcceptor socketAcceptor = null;
	private static final int socketListeningPort = 10011;
	private static final Logger logger = LoggerFactory.getLogger(TMSMinaServer.class);

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		logger.info("*********************Starting default mina server************************");
		System.out.println("starting...");
		try {
			// 创建一个非阻塞的server端的Socket
			socketAcceptor = new NioSocketAcceptor();
			int len = socketAcceptor.getSessionConfig().getSendBufferSize();
			System.out.println("default send buffer size:" + len);
			socketAcceptor.getSessionConfig().setSendBufferSize(2048);
			len = socketAcceptor.getSessionConfig().getSendBufferSize();
			System.out.println("planed send buffer size:" + len);
			DefaultIoFilterChainBuilder chain = socketAcceptor.getFilterChain();
			// addSupportTLS(chain);
			// 设置过滤器
			chain.addLast("logger", new LoggingFilter());
			CustomMinaCodecFactory codecFactory = new CustomMinaCodecFactory(Charset.forName("ISO-8859-1"));
			chain.addLast("codec", new ProtocolCodecFilter(codecFactory));

			// 设置读取数据的缓冲区大小
			// acceptor.getSessionConfig().setReadBufferSize(1024 * 102400);
			// // 读写通道10秒内无操作进入空闲状态
			// acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
			// 10);
			// 添加逻辑处理器
			socketAcceptor.setHandler(new TMSMinaServerHandlerAdapter());
			// 绑定端口
			socketAcceptor.bind(new InetSocketAddress(socketListeningPort));
			logger.info("*********************server started success     listening port：" + socketListeningPort
					+ "************************");
		} catch (Exception e) {
			logger.info("*********************server started fail*********************");
		}
	}

}
