/**
 * 
 */
package com.whty.example.posp.server.mina;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.mina.core.session.IoSession;

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
public class TestClient {

	/**
	 * @param args
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {

		PospClient pospClient = new PospClient(Auth.getSSLContextForClient("C:/truststore.jks", "222222"));
		// PospClient pospClient = new PospClient(null);
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入服务器地址，端口号进行操作;" + "\n输入'connect;127.0.0.1;9999'表示连接127.0.0.1:9999"
				+ "\n输入'disconnect;127.0.0.1;9999'表示断开与127.0.0.1:9999的连接"
				+ "\n输入'send;127.0.0.1;9999;123456'表示向127.0.0.1:9999端口发送数据'123456'" + "\n输入'quit'关闭功能输入");
		while (true) {
			String targetStr = scanner.nextLine();
			if ("quit".equals(targetStr)) {
				break;
			}
			String[] params = targetStr.split(";");
			if (params == null || params.length < 3) {
				System.out.println("输入的格式不对,请重新输入");
				continue;
			}
			String serverIp = params[1];
			if (!com.whty.example.utils.DataUtil.isIp(serverIp)) {
				System.out.println("请输入正确的ip");
				continue;
			}
			String port = params[2];
			Pattern pattern = Pattern.compile("[0-9]*");
			if (!pattern.matcher(port).matches()) {
				System.out.println("请输入数字");
				continue;
			}
			String operStr = params[0];
			if ("connect".equalsIgnoreCase(operStr)) {
				IoSession targetIoSesion = pospClient.connect(serverIp, Integer.valueOf(port));
				if (targetIoSesion == null) {
					System.out.println("建立连接失败");
					continue;
				}
			} else if ("disconnect".equalsIgnoreCase(operStr)) {
				boolean result = pospClient.disconncet(serverIp, Integer.valueOf(port));
				if (!result) {
					System.out.println("断开连接失败");
					continue;
				}
			} else if ("send".equalsIgnoreCase(operStr)) {
				String data = params[3];
				// 发送数据
				boolean result = pospClient.writeData(serverIp, Integer.valueOf(port), data);
				if (!result) {
					System.out.println("数据发送失败");
					continue;
				}
			} else {
				System.out.println("未知操作，请输入connect/disconnect");
				continue;
			}
			System.out.println("操作成功");
			System.out.println("当前连接服务器个数:" + pospClient.getActiveConnections().size());
		}
		System.out.println("功能输入已关闭");
		pospClient.shutDown();
		scanner.close();
	}

}
