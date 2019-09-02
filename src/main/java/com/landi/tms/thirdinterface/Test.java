/**
 * 
 */
package com.landi.tms.thirdinterface;

import com.landi.tms.thirdinterface.Rule;
import com.landi.tms.thirdinterface.TyRule;

/**
 * <p>
 * Title:Test
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年4月18日 下午3:02:40
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("请输入文件路径，回车结束:");
		// Scanner scanner = new Scanner(System.in);
		// String inputPath = scanner.nextLine();
		// scanner.close();
		String inputPath = "F:/tp30l.bin";
		Rule apiRule = new TyRule();
		try {
			String appName = apiRule.getAppName(inputPath);
			String appVersion = apiRule.getAppVer(inputPath);
			System.out.println("appName:" + appName + "\nappVersion:" + appVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// Properties ppp = new Properties();
		// InputStream is =
		// Test.class.getClassLoader().getResourceAsStream("properties/config.properties");
		// ppp.load(is);
		// System.out.println(ppp.getProperty("ctxPath"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

}
