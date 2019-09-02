package com.landi.tms.thirdinterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * JDK版本这里有坑，注意jdk版本匹配（需要用1.6的编译）
 * <p>
 * Title:TyRule
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年4月27日 下午3:31:29
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyRule extends Rule {

	@Override
	public String getAppVer(String filePath) throws Exception {
		System.out.println("getAppVer invoked");
		saveLog("getAppVer invoked");
		saveLog("filePath:" + filePath);
		List<String> tList = FileViewer.getListFiles(filePath, "bin", false);
		if ((tList == null) || (tList.size() <= 0)) {
			saveLog("该目录下没有文件！");
			return null;
		}
		// 文件00位置的前八个字节为应用名称
		File targetFile = new File(tList.get(0));
		if (!targetFile.exists()) {
			saveLog("target file not exists");
			return null;
		}
		InputStream is = new FileInputStream(targetFile);
		int fileByteSize = is.available();
		if (fileByteSize < 8) {
			saveLog("target file size too short");
			is.close();
			return null;
		}
		byte[] appVerBytes = new byte[8];
		is.read(appVerBytes, 0, 8);
		is.close();
		String appVersion = new String(appVerBytes);
		saveLog("getAppVer returned");
		saveLog("app version:" + appVersion);
		System.out.println("app version:" + appVersion);
		return appVersion;
	}

	@Override
	public String getAppName(String filePath) throws Exception {
		System.out.println("getAppName invoked");
		saveLog("getAppName invoked");
		saveLog("filePath:" + filePath);
		List<String> tList = FileViewer.getListFiles(filePath, "bin", false);
		if ((tList == null) || (tList.size() <= 0)) {
			saveLog("该目录下没有文件！");
			return null;
		}
		File targetFile = new File(tList.get(0));
		if (!targetFile.exists()) {
			saveLog("target file not exists");
			return null;
		}
		InputStream is = new FileInputStream(targetFile);
		int fileByteSize = is.available();
		if (fileByteSize < 0x104 + 16) {
			saveLog("target file size too short");
			is.close();
			return null;
		}
		byte[] appNameBytes = new byte[16];
		int packSize = 0;
		int currentIndex = 0;
		byte[] tempBuffer = new byte[0x104];
		while ((packSize = is.read(tempBuffer)) != -1) {
			currentIndex += packSize;
			if (currentIndex <= 0x104 * 2 && currentIndex > 0x104) {
				System.arraycopy(tempBuffer, 0, appNameBytes, 0, 16);
				break;
			}
		}
		is.close();
		String appName = new String(appNameBytes);
		saveLog("getAppName returned");
		saveLog("app name:" + appName);
		System.out.println("app name:" + appName);
		return appName;
	}

	private void saveLog(String msg) {
		try {
			FileWriter fw = null;
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("/home/tms/tytmp.txt");
			fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "	" + msg);
			pw.flush();
			try {
				fw.flush();
				pw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
