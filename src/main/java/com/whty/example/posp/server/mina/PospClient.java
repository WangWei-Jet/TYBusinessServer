/**
 * 
 */
package com.whty.example.posp.server.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.whty.example.server.tms.application.Utils;
import com.whty.example.utils.CustomLogger;

/**
 * <p>
 * Title:PospClient
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年4月28日 下午2:09:32
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class PospClient {

	private CustomLogger logger = CustomLogger.getLogger(getClass());
	private NioSocketConnector connector = new NioSocketConnector();

	private Map<String, IoSession> ioSessionMap = new HashMap<>();

	public PospClient(SSLContext sslContext) {
		init(sslContext);
	}

	private void init(SSLContext sslContext) {
		connector.setHandler(new PospCoreHandler());

		if (sslContext != null) {
			logger.debug("当前连接请求开启TLS");
			SslFilter sslFilter = new SslFilter(sslContext);
			// 客户端
			sslFilter.setUseClientMode(true);
			connector.getFilterChain().addFirst("tlsFilter", sslFilter);
		} else {
			logger.debug("当前连接请求不开启TLS");
		}
		PrefixedStringCodecFactory codecFactory = new PrefixedStringCodecFactory(Charset.forName("ISO-8859-1"));
		codecFactory.setDecoderPrefixLength(2);
		codecFactory.setEncoderPrefixLength(2);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(codecFactory));

		connector.getFilterChain().addLast("logger", new LoggingFilter());

		connector.setDefaultRemoteAddress(new InetSocketAddress("127.0.0.1", 9998));
		// 设置连接超时
		connector.setConnectTimeoutMillis(8000);
	}

	public IoSession connect(String serverIp, Integer port) {
		try {
			logger.debug("input serverIp:" + serverIp + "\ninput port:" + port);
			if (serverIp == null || port == null || port.intValue() <= 0) {
				logger.debug("输入的服务器ip和端口参数错误");
				return null;
			}
			IoSession targetIoSession = ioSessionMap.get(serverIp + ":" + port);
			if (targetIoSession != null && targetIoSession.isConnected()) {
				logger.debug("与服务器的连接未断，无需重连");
				return targetIoSession;
			}
			ConnectFuture connectFuture = null;
			if (serverIp == null || port == null) {
				connectFuture = connector.connect();
			} else {
				connectFuture = connector.connect(new InetSocketAddress(serverIp, port));
			}
			// 等待建立连接成功，阻塞
			connectFuture.awaitUninterruptibly();
			targetIoSession = connectFuture.getSession();
			if (targetIoSession == null) {
				logger.debug("获取与服务器的连接会话失败");
				connector.dispose();
				return targetIoSession;
			}
			logger.debug("连接服务器成功" + "\nserver=>" + targetIoSession.getRemoteAddress() + "\nclient=>"
					+ targetIoSession.getLocalAddress());

			ioSessionMap.put(serverIp + ":" + port, targetIoSession);

			return targetIoSession;
		} catch (Exception e) {
			logger.error("创建与服务器的连接失败", e);
			return null;
		}
	}

	public boolean writeData(String serverIp, Integer port, String data) {
		logger.debug("input serverIp:" + serverIp + "\ninput port:" + port);
		try {
			if (serverIp == null || port == null || port.intValue() <= 0) {
				logger.debug("输入的服务器ip和端口参数错误");
				return false;
			}
			IoSession targetIoSession = ioSessionMap.get(serverIp + ":" + port);
			if (targetIoSession == null || !targetIoSession.isConnected()) {
				logger.debug("未发现与服务器的连接，无法发送数据");
				return false;
			}
			byte[] tempBytes = Utils.hexString2Bytes(data);
			WriteFuture writeFuture = targetIoSession.write(new String(tempBytes, "ISO-8859-1"));
			boolean result = writeFuture.awaitUninterruptibly(3000);
			logger.debug("数据发送结果:" + result);
			return true;
		} catch (Exception e) {
			logger.error("数据发送失败", e);
			return false;
		}
	}

	public boolean disconncet(String serverIp, Integer port) {
		try {
			IoSession targetIoSession = ioSessionMap.get(serverIp + ":" + port);
			if (targetIoSession == null) {
				logger.debug("未发现与服务器的连接，无需断开");
				return true;
			}
			targetIoSession.closeOnFlush();
			ioSessionMap.remove(serverIp + ":" + port);
			return true;
		} catch (Exception e) {
			logger.error("断开连接出现异常", e);
			return false;
		}
	}

	public boolean shutDown() {
		try {
			connector.dispose();
			return true;
		} catch (Exception e) {
			logger.error("关闭客户端的时候异常", e);
			return false;
		}
	}

	public Map<String, IoSession> getActiveConnections() {
		return ioSessionMap;
	}
}
