package com.whty.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static Logger logger = LoggerFactory.getLogger(Utils.class);

	/**
	 * 通过调用系统命令删除一个文件夹及下面的所有文件
	 * 
	 * @param file
	 */
	public static void deleteFileByWinCom(File file) {
		Runtime rt = Runtime.getRuntime();
		String cmd = null;
		try {
			if (file.isFile()) {
				cmd = "cmd.exe /c del /q/a/f/s " + file.getAbsolutePath();
			} else {
				cmd = "cmd.exe /c rd /s/q " + file.getAbsolutePath();
			}
			rt.exec(cmd);
			System.out.println("成功执行了命令...");
		} catch (Exception e) {
			System.out.println("调用系统命令失败了...");
		}
	}

	/**
	 * 通过递归调用删除一个文件夹下面的所有文件
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.isFile()) {// 表示该文件不是文件夹
			file.delete();
		} else {
			// 首先得到当前的路径
			String[] childFilePaths = file.list();
			for (String childFilePath : childFilePaths) {
				File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
				deleteFile(childFile);
			}
			file.delete();
		}
	}

	/**
	 * 数据转换成bmp图片（第三方提供的dll）
	 * 
	 * @param imageStr
	 *            图片数据字符串
	 * @param filename
	 *            转换成图片后的图片名
	 * @return 转换后图片的完成路径
	 */
	public static String transferDataToImage(String imageStr, String directoryPath, String filename) {

		// String targetFilePath = "E:/img.wlt";
		// String pictureOriginalStr =
		// "574C66007E00320000FF85175151513E710DD564F30DB6B4C0E226C2E2DCCBE652C24D431F3CE7A2A46CE24216B3A0EDD7D62020A602B62CAAC46C0D2DA0775954D3888941283D6609CD511A0023F0568CD1A351AEDC5251515A3E8123B3CCC62A7E68C0CE4A8C67BDA762E2FF9070F6C1EE56296D16A68C2F79F22C72A03071A823D927FC4020BE258155725BB3E9987B1B2E1AAE51A67A6E77C1FA8BE5E65FCA17C5EC7C102847A859548C3D361A42DC5F4FE4600D9B74E58428919D1926DB3D8870A9D075FA0EF0442DC67729C5D6C5EDCC3161A818BD950C91A20D4CAB528BF5A99463A2179BE32501D4F9B3C897B9F01AA0469665B3CDAB4AD1F3D81C2358075BFD56231FBECCCCEB7FD610004BEE3299DF11C9BBBA87ED570DC5FF137AFCC7D32E12761ED4784A385785DEAE51D8C39011C3FFAA3F1BD1BBF130569F7707C4B82AD50705E1AF6079938999449287804FA4614D4A0DBAA8E0E29EBC5DC83A8DB908C6B8B2B3D93C1792FDE2DEFD4EE76923C1524B41384BB8D66495F1D78835935B9A45778B508C606B27F693523721EC5C11519C13FB7086C38CEB7B831D9474F702B20E21F6A5D2A4D2B1DFD25DCB0C29F1DA66A473EAE4E266810B3673ED88719B29117734303A7F5BBAE9AE51CAB39B263E0BCF802F47DE05D244FD7755907B086FB9766BC9BE1227FC7C21AAA5EFA22531CBA31A8ED50B7941115C00F0A1538457374CDBA34AD4E8AC4E2F5B6994DC635B45D4B7800F6131D2808E53F83CA00314136643F9EB202D0DBDBCD0541DD79640B827757375E8D53E2F0986A891683456855F264819A1A8E97B643309498209FEEECD6A866A80FC6C93FBB42952F3DA491A7A661F831B41C045A345051D01208634F9EC856FC87E7208F2E79485737BB8867FF20393B3E0EE9E36C8B4F58CCF85B929BDF7B7862B79A7C4EE83B89E2FFED3DC01649CE48CEFC692F6062B73B365E3747E0E3989075BB29208995A8125B0C2242AC26CE7EDA4C58E36DC9378D7BE37B1E5DBE41E0EDCBEFDFB38444FB00249294867520ADAF603AF25C83A775389964C5EA29B0724A728EDB0F2FC616C47F86DBC365DE075F5B753F5AA61B9CB0AF9B680240AAAC365BA86008BB65B21AE515C171F62DE458D3658AE519285A3CEBDAD9F01F5F094C26E9D6CC3D0EF98CD56FB28174F7CD0F44E11FCD60585D954A8C0059DAF40300AC594E81124145A3E3743FE51A0AFE9E7EEA2B80CBAD9686730625BE38B67BF4609E982A0898A773B2026EBCF3107E8D43D789D64F90F8AAB96591DF15A485B924B9A6367BABF42769DA21D4A6C66B44D32866A9E43081B135A3EA635EDECD8233D7324EEABB0377005D340AFF526C1ACBEE6C7A6EE1982617049B6515E8B27B1025BA2D676F75DD7789E90D851FFFD877FD19A3634644C1B68933746EE53ED0C86C6";

		// SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		// String timeStr = format.format(new Date());
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 目录及其下面的文件被删除
		deleteFile(directory);
		directory.mkdirs();
		filename = directory.getPath() + "/" + filename;
		File targetFile = new File(filename);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream inputStream = null;
		try {
			inputStream = new FileOutputStream(targetFile);
			inputStream.write(GPMethods.str2bytes(imageStr));
			inputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("图片数据写入完成");
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("input file path:" + filename);
		JNALibrary.INSTANTCE.GetBmp(filename, 2);
		return filename.replace(".wlt", ".bmp");
	}

	/**
	 * 
	 * @param imgFile
	 *            图片路径
	 * @return 图片base64编码字符串
	 */
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		// Base64 encoder = new Base64();
		return new String(Base64.encode(data));
	}

	public static void main(String[] args) {

		JNALibrary.INSTANTCE.GetBmp("C:/pic.wlt", 2);

	}
}
