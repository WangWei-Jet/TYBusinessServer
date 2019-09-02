/**
 * 
 */
package com.whty.example.posp.server.mina;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * <p>
 * Title:Auth
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月4日 上午10:11:36
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class Auth {
	public static SSLContext getSSLContextForClient(String trustCaFilePath, String caFilePwd) throws Exception {
		X509TrustManager x509m = new X509TrustManager() {

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				System.out.println("authType:" + authType);
				if (chain != null && chain.length > 0) {
					for (X509Certificate certificate : chain) {
						PublicKey publicKey = certificate.getPublicKey();

						System.out.println(
								"public key:" + publicKey.toString() + "\nAlgorithm:" + publicKey.getAlgorithm()
										+ "\nformat:" + publicKey.getFormat() + "\nencoded:" + publicKey.getEncoded());
						String certificateType = certificate.getType();
						System.out.println("certificate type:" + certificateType);
					}
				} else {
					System.out.println("chain is empty");
				}
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {

			}
		};

		// Trust Key Store
		// KeyStore keyStore = KeyStore.getInstance("JKS");
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(new FileInputStream(trustCaFilePath), caFilePwd.toCharArray());

		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
		trustManagerFactory.init(keyStore);
		TrustManager[] tms = trustManagerFactory.getTrustManagers();

		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		// sslContext.init(null, tms, null);
		sslContext.init(null, tms, null);
		return sslContext;
	}

	public static SSLContext getSSLContextForServer(String serverCerFilePath, String serverCerPwd) throws Exception {

		// Trust Key Store
		// KeyStore keyStore = KeyStore.getInstance("JKS");
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(serverCerFilePath), serverCerPwd.toCharArray());

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		keyManagerFactory.init(keyStore, serverCerPwd.toCharArray());
		KeyManager[] kms = keyManagerFactory.getKeyManagers();

		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		sslContext.init(kms, null, null);
		return sslContext;
	}

}
