/**
 * 
 */
package com.landi.tms.thirdinterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:FileViewer
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年4月25日 下午4:29:36
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FileViewer {
	public static List<String> getListFiles(String path, String suffix, boolean isdepth) {
		try {
			List<String> lstFileNames = new ArrayList<String>();
			File file = new File(path);
			return listFile(lstFileNames, file, suffix, isdepth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<String> listFile(List<String> lstFileNames, File f, String suffix, boolean isdepth) {
		try {
			if (f.isDirectory()) {
				File[] t = f.listFiles();

				for (int i = 0; i < t.length; i++)
					if ((isdepth) || (t[i].isFile()))
						listFile(lstFileNames, t[i], suffix, isdepth);
			} else {
				String filePath = f.getAbsolutePath();
				if (!suffix.equals("")) {
					int begIndex = filePath.lastIndexOf(".");
					String tempsuffix = "";

					if (begIndex != -1) {
						tempsuffix = filePath.substring(begIndex + 1, filePath.length());
						if (tempsuffix.equals(suffix))
							lstFileNames.add(filePath);
					}
				} else {
					lstFileNames.add(filePath);
				}
			}
			return lstFileNames;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
