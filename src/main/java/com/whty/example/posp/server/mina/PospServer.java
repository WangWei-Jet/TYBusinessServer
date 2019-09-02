/**
 * 
 */
package com.whty.example.posp.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.whty.example.utils.CustomLogger;

/**
 * <p>
 * Title:PospServer
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年4月28日 上午10:06:50
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class PospServer {

	CustomLogger logger = CustomLogger.getLogger(getClass());

	Map<Integer, NioSocketAcceptor> socketAcceptorMap = new HashMap<>();

	private static PospServer pospServer;

	private PospServer() {

	}

	public static PospServer getServer() {
		if (pospServer == null) {
			pospServer = new PospServer();
		}
		return pospServer;
	}

	/**
	 * 启动服务器
	 * 
	 * @return
	 */
	public boolean start(int port, SSLContext sslContext) {
		if (socketAcceptorMap.get(port) != null) {
			logger.error("端口已被占用，无法启动");
			return false;
		}
		try {
			NioSocketAcceptor socketAcceptor = new NioSocketAcceptor();
			socketAcceptor.setHandler(new PospCoreHandler());
			if (sslContext != null) {
				logger.debug("当前监听端口开启TLS");
				SslFilter sslFilter = new SslFilter(sslContext);
				// 服务器端
				sslFilter.setUseClientMode(false);
				socketAcceptor.getFilterChain().addFirst("tlsFilter", sslFilter);
			} else {
				logger.debug("当前监听端口不开启TLS");
			}
			// 数据格式=>添加前二字节表示数据长度
			PrefixedStringCodecFactory codecFactory = new PrefixedStringCodecFactory(Charset.forName("ISO-8859-1"));
			codecFactory.setDecoderPrefixLength(2);
			codecFactory.setEncoderPrefixLength(2);
			socketAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(codecFactory));

			// 添加log记录
			socketAcceptor.getFilterChain().addLast("logger", new LoggingFilter());

			// unbind的时候断开所有连接
			socketAcceptor.setCloseOnDeactivation(true);

			try {
				socketAcceptor.bind(new InetSocketAddress(port));
			} catch (IOException ioException) {
				logger.error("服务器创建端口监听失败", ioException);
				return false;
			}

			socketAcceptorMap.put(port, socketAcceptor);

			logger.debug("mina服务器启动成功...     端口号为：" + port);
		} catch (Exception e) {
			logger.error("Mina 服务器启动异常", e);
			return false;
		}

		return true;
	}

	/**
	 * 停止服务器
	 * 
	 * @return
	 */
	public boolean stop(int port) {
		NioSocketAcceptor acceptor = socketAcceptorMap.get(port);
		if (acceptor == null) {
			logger.error("port:" + port + "	无服务器监听，无需停止");
			return true;
		}
		acceptor.unbind();
		acceptor.dispose();
		logger.debug("mina服务器关闭...     端口号为：" + port);

		socketAcceptorMap.remove(port);
		return true;
	}

	public Map<Integer, NioSocketAcceptor> getActiveServer() {
		return socketAcceptorMap;
	}

}
