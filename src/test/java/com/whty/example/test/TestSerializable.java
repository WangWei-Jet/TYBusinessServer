/**
 * 
 */
package com.whty.example.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.whty.example.utils.FileUtil;

/**
 * <p>
 * Title:TestSerializable
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月7日 上午11:21:49
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestSerializable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student student = new Student();
		student.setStudentName("OneWay");
		student.setStudentAge(18);
		student.setStudentMajor("计算机");
		System.out.println(student.toString());

		String filePath = "E:" + File.separator + "TestSerializable" + File.separator + "a.txt";
		File targetFile = FileUtil.createFile(filePath);
		try {
			OutputStream os = new FileOutputStream(targetFile);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(student);
			oos.close();
			System.out.println("student序列化完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sutdent序列化异常");
			return;
		}
		System.out.println("准备进行Object反序列化");
		try {
			InputStream is = new FileInputStream(targetFile);
			ObjectInputStream ois = new ObjectInputStream(is);
			Student serializableStudent = (Student) ois.readObject();
			ois.close();
			System.out.println(serializableStudent.toString());
			System.out.println("student反序列化完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sutdent反序列化异常");
			return;
		}
	}

}
