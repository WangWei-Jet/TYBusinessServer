/**
 * 
 */
package com.whty.example.posp.server.mina;

import java.util.Scanner;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

/**
 * <p>
 * Title:Test
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年4月28日 下午1:49:20
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestServer {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int[] ports = new int[] { 9999, 19999, 29999 };
		for (int port : ports) {
			SSLContext sslContext = Auth.getSSLContextForServer("C:/server.p12", "123456");
			PospServer.getServer().start(port, sslContext);
			System.out.println("当前服务器监听端口数:" + PospServer.getServer().getActiveServer().size());
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("输入端口号，回车可停止对应端口的服务器监听");
		while (true) {
			String portStr = scanner.nextLine();
			Pattern pattern = Pattern.compile("[0-9]*");
			if (!pattern.matcher(portStr).matches()) {
				System.out.println("请输入数字");
				continue;
			}
			PospServer.getServer().stop(Integer.valueOf(portStr));
			System.out.println("当前服务器监听端口数:" + PospServer.getServer().getActiveServer().size());
			if (PospServer.getServer().getActiveServer().size() == 0) {
				break;
			}
		}
		System.out.println("当前服务器已无监听端口");
		scanner.close();
	}

}
