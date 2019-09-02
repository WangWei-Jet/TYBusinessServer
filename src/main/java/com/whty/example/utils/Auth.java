package com.whty.example.utils;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Properties;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class Auth {
	private static SSLContext sslContext;

	/**
	 * 
	 * @param serverCerlocation:服务器证书路径
	 * @param caCerlocation：ca证书路径
	 * @return
	 * @throws Exception
	 */
	public static SSLContext getSSLContext(String serverCerlocation, String caCerlocation) throws Exception {
		Properties p = Configuration2048.getConfig();
		String protocol = p.getProperty("protocol");
		String serverCer = serverCerlocation;
		if (serverCer == null || serverCer.length() == 0) {
			serverCer = p.getProperty("serverCer");
		}
		String serverCerPwd = p.getProperty("serverCerPwd");
		String serverKeyPwd = p.getProperty("serverKeyPwd");

		// Key Stroe
		// KeyStore keyStore = KeyStore.getInstance("PKCS12");
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(serverCer), serverCerPwd.toCharArray());

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		keyManagerFactory.init(keyStore, serverKeyPwd.toCharArray());
		KeyManager[] kms = keyManagerFactory.getKeyManagers();

		TrustManager[] tms = null;
		if (Configuration2048.getConfig().getProperty("authority").equals("2")) {
			// 双向
			String serverTrustCer = caCerlocation;
			if (serverTrustCer == null || serverTrustCer == null) {
				serverTrustCer = p.getProperty("serverTrustCer");
			}
			String serverTrustCerPwd = p.getProperty("serverTrustCerPwd");

			// Trust Key Store
			// keyStore = KeyStore.getInstance("JKS");
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream(serverTrustCer), serverTrustCerPwd.toCharArray());

			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
			trustManagerFactory.init(keyStore);
			tms = trustManagerFactory.getTrustManagers();
		}
		sslContext = SSLContext.getInstance(protocol);

		sslContext.init(kms, tms, null);

		return sslContext;
	}

	/**
	 * 
	 * @param clientCerlocation:客户端证书路径
	 * @param caCerlocation：ca证书路径
	 * @return
	 * @throws Exception
	 */
	public static SSLContext getSSLClientContext(String clientCerlocation, String caCerlocation) throws Exception {
		Properties p = Configuration2048.getConfig();
		String protocol = p.getProperty("protocol");
		String clientCer = clientCerlocation;
		if (clientCer == null || clientCer.length() == 0) {
			clientCer = p.getProperty("clientCer");
		}
		String serverCerPwd = p.getProperty("clientCerPwd");
		String serverKeyPwd = p.getProperty("clientKeyPwd");

		// Key Stroe
		// KeyStore keyStore = KeyStore.getInstance("PKCS12");
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(clientCer), serverCerPwd.toCharArray());

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		keyManagerFactory.init(keyStore, serverKeyPwd.toCharArray());
		KeyManager[] kms = keyManagerFactory.getKeyManagers();

		TrustManager[] tms = null;
		if (Configuration2048.getConfig().getProperty("authority").equals("2")) {
			String serverTrustCer = caCerlocation;
			if (serverTrustCer == null || serverTrustCer == null) {
				serverTrustCer = p.getProperty("serverTrustCer");
			}
			String serverTrustCerPwd = p.getProperty("serverTrustCerPwd");

			// Trust Key Store
			// keyStore = KeyStore.getInstance("JKS");
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream(serverTrustCer), serverTrustCerPwd.toCharArray());

			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
			trustManagerFactory.init(keyStore);
			tms = trustManagerFactory.getTrustManagers();
		}
		sslContext = SSLContext.getInstance(protocol);

		sslContext.init(kms, tms, null);

		return sslContext;
	}
}