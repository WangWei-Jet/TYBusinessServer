package com.whty.example.server.tms.ty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 天喻终端应用/参数下载服务器,负责下发终端参数和升级固件
 * <p>
 * Title:TyTmsMinaServer
 * </p>
 * <p>
 * Description:负责下发终端参数和升级固件
 * </p>
 * <p>
 * Date:2018年5月17日 下午4:41:19
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyTmsMinaServer extends HttpServlet {

	private static final long serialVersionUID = 8231706695949575193L;
	private NioSocketAcceptor socketAcceptor = null;
	private final int socketListeningPort = 10011;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void destroy() {
		super.destroy();
		if (socketAcceptor != null) {
			socketAcceptor.unbind();
			socketAcceptor.dispose();
		}
		logger.info("ty tms mina server stopped. related port:" + socketListeningPort);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		logger.info("Starting ty tms mina server");
		try {
			// 创建一个非阻塞的server端的Socket
			socketAcceptor = new NioSocketAcceptor();
			socketAcceptor.getSessionConfig().setSendBufferSize(2048);
			DefaultIoFilterChainBuilder chain = socketAcceptor.getFilterChain();
			// 设置过滤器
			// 数据格式=>添加前二字节表示数据长度
			PrefixedStringCodecFactory codecFactory = new PrefixedStringCodecFactory(Charset.forName("ISO-8859-1"));
			codecFactory.setDecoderPrefixLength(2);
			codecFactory.setEncoderPrefixLength(2);
			chain.addLast("codec", new ProtocolCodecFilter(codecFactory));
			chain.addLast("logger", new LoggingFilter());
			// 添加逻辑处理器
			socketAcceptor.setHandler(new TyTmsMinaServerHandlerAdapter());
			socketAcceptor.setCloseOnDeactivation(true);
			// 绑定端口
			socketAcceptor.bind(new InetSocketAddress(socketListeningPort));
			logger.info("server started success     listening port：" + socketListeningPort);
		} catch (Exception e) {
			logger.error("server started fail", e);
		}
	}

}
