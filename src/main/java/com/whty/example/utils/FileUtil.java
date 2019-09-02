/**
 * 
 */
package com.whty.example.utils;

import java.io.File;

/**
 * <p>
 * Title:FileUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月7日 上午11:38:17
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FileUtil {
	/**
	 * 创建文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件
	 */
	public static File createFile(String filePath) {
		if (filePath == null || filePath.trim().length() == 0) {
			// 路径为空
			System.out.println("路径为空");
			return null;
		}
		File targetFile = new File(filePath);
		if (targetFile.isDirectory()) {
			// 文件路径是目录
			System.out.println("文件路径是目录");
			return null;
		}
		if (targetFile.exists()) {
			// 文件已存在
			System.out.println("文件已存在");
		}
		try {
			if (targetFile.getParentFile() == null) {
				// 文件路径有误
				System.out.println("文件路径有误");
				return null;
			}
			if (!targetFile.getParentFile().exists()) {
				// 父文件夹不存在，创建
				if (!targetFile.getParentFile().mkdirs()) {
					System.out.println("文件夹创建失败");
					return null;
				}
			}
			boolean result = targetFile.createNewFile();
			if (!result) {
				System.out.println("文件创建失败");
				return null;
			}
			// 创建成功
			return targetFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
