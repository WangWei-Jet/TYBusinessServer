package com.whty.example.utils;

import java.util.Properties;

public class Configuration2048 {
	private static Properties config;

	public static Properties getConfig() {
		config = new Properties();
		config.setProperty("protocol", "TLSv1.2");
		config.setProperty("serverCer", "D:\\Program Files\\正式密钥\\server.p12");
		// config.setProperty("serverCer", "D:\\Program Files\\test密钥\\server.p12");
		config.setProperty("serverCerPwd", "123456");
		config.setProperty("serverKeyPwd", "123456");
		config.setProperty("serverTrustCer", "D:\\Program Files\\正式密钥\\truststore.jks");
		// config.setProperty("serverTrustCer", "D:\\Program
		// Files\\test密钥\\truststore.jks");
		config.setProperty("serverTrustCerPwd", "222222");
		config.setProperty("clientCer", "D:\\Program Files\\正式密钥\\client.p12");
		// config.setProperty("clientCer", "D:\\Program Files\\test密钥\\client.p12");
		config.setProperty("clientCerPwd", "123456");
		config.setProperty("clientKeyPwd", "123456");
		config.setProperty("clientTrustCer", "D:\\Program Files\\正式密钥\\truststore.jks");
		// config.setProperty("clientTrustCer", "D:\\Program
		// Files\\test密钥\\truststore.jks");
		config.setProperty("clientTrustCerPwd", "222222");
		config.setProperty("serverListenPort", "443");
		config.setProperty("serverThreadPoolSize", "5");
		config.setProperty("serverRequestQueueSize", "10");
		config.setProperty("socketStreamEncoding", "ISO-8859-1");
		config.setProperty("authority", "2");
		return config;
	}
}