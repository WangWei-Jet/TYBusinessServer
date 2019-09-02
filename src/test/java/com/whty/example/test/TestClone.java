/**
 * 
 */
package com.whty.example.test;

/**
 * <p>
 * Title:TestClone
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月7日 上午10:29:57
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TestClone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student studentA = new Student();
		studentA.setStudentName("OneWay");
		studentA.setStudentAge(18);
		System.out.println(studentA.toString());
		Student studentB = (Student) studentA.clone();
		System.out.println(studentB.toString());

	}

	static class Student implements Cloneable {
		private String studentName;
		private int studentAge;

		@Override
		protected Object clone() {
			Object newObject = null;
			try {
				newObject = super.clone();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return newObject;
		}

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

		@Override
		public String toString() {
			return "Student [studentName=" + studentName + ", studentAge=" + studentAge + "]";
		}

	}

}
