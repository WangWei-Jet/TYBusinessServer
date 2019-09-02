/**
 * 
 */
package com.whty.example.posp.server.mina;

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
public class MultiClientTest {

	static String serverIp = "101.37.30.113";
	static int port = 20300;
	// static String serverIp = "59.173.2.76";
	// static int port = 5002;
	// 每次通讯间隔时间
	static long sleepTime = 60l;
	// 通讯总时长
	static int totalCommuSecond = 40;

	/**
	 * @param args
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {

		// 客户端数量
		int clientAmount = 200;
		for (int i = 0; i < clientAmount; i++) {
			new MyThread(i + "").start();
		}
	}

	static class MyThread extends Thread {
		private String threadName;

		/**
		 * @param threadName
		 */
		public MyThread(String threadName) {
			super();
			this.threadName = threadName;
			setName(threadName);
		}

		@Override
		public void run() {
			super.run();
			PospClient pospClient = new PospClient(null);
			IoSession ioSession = pospClient.connect(serverIp, port);
			if (ioSession == null) {
				System.out.println("客户端" + threadName + "连接失败");
				return;
			}
			String data = threadName;
			while (data.length() < 50) {
				if (data.length() == 49) {
					data = "A" + data;
				} else {
					data = "0" + data;
				}
			}
			// 开始通讯测试
			long startTime = System.currentTimeMillis();
			long endTime = System.currentTimeMillis();
			while (endTime - startTime < totalCommuSecond * 1000) {
				System.out.println("data:" + data);
				boolean result = pospClient.writeData(serverIp, port, data);
				System.out.println("客户端:" + getName() + "  发送数据成功");
				if (!result) {
					System.out.println("客户端:" + getName() + "通讯失败");
					pospClient.disconncet(serverIp, port);
					return;
				}
				try {
					sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("睡眠爆掉了");
				}
				endTime = System.currentTimeMillis();
			}
			System.out.println("客户端:" + getName() + "通讯完成");
			pospClient.shutDown();
		}

	}

}
