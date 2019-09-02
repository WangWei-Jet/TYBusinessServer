/**
 * 
 */
package com.whty.example.test;

import java.io.Serializable;

/**
 * <p>
 * Title:Student
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月7日 上午11:22:15
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4143710137240543354L;
	private String studentName;
	private int studentAge;
	private transient String studentMajor;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentMajor() {
		return studentMajor;
	}

	public void setStudentMajor(String studentMajor) {
		this.studentMajor = studentMajor;
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", studentAge=" + studentAge + ", studentMajor=" + studentMajor
				+ "]";
	}

}
