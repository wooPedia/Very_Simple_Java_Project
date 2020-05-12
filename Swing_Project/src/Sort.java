/* ���� ó�� Ŭ���� */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class Sort {

	static Student[] sortedArray = null;

	int i = 0;

	// ��������
	// type(���� ����)
	// type: stuID(1), grade(2), default = 1
	public static Student[] ascSort(Vector<Student> vec, int type) {

		if (type != 1 && type != 2) type = 1;
		
		if (type == 1) { // stuID ���� ����
			TypeAscStuID c = new TypeAscStuID();
			sortedArray = vec.toArray(new Student[0]); // Student class�� temp
			Arrays.sort(sortedArray, c);
		} else if (type == 2) { // grade ���� ����
			TypeAscGrade c = new TypeAscGrade();
			sortedArray = vec.toArray(new Student[0]);
			Arrays.sort(sortedArray, c);
		}
		return sortedArray;
	}

	// ��������
	// type: stuID(1), grade(2), default = 1
	public static Student[] dscSort(Vector<Student> vec, int type) {

		if (type != 1 && type != 2) type = 1;
		
		if (type == 1) { // stuID ���� ����
			TypeDscStuID c = new TypeDscStuID();
			sortedArray = vec.toArray(new Student[0]);
			Arrays.sort(sortedArray, c);
		} else if (type == 2) { // grade ���� ����
			TypeDscGrade c = new TypeDscGrade();
			sortedArray = vec.toArray(new Student[0]);
			Arrays.sort(sortedArray, c);
		}
		return sortedArray;
	}
} // Sort class

// �й� ���� ��������
class TypeAscStuID implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getStuID() > o2.getStuID()) {
			return 1;
		} else if (o1.getStuID() < o2.getStuID()) {
			return -1;
		} else {
			return 0;
		}
	}
}

// �й� ���� ��������
class TypeDscStuID implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getStuID() < o2.getStuID()) {
			return 1;
		} else if (o1.getStuID() > o2.getStuID()) {
			return -1;
		} else {
			return 0;
		}
	}
}

// �г� ���� ��������
class TypeAscGrade implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getGrade() > o2.getGrade()) {
			return 1;
		} else if (o1.getGrade() < o2.getGrade()) {
			return -1;
		} else {
			return 0;
		}
	}
}

// �г� ���� ��������
class TypeDscGrade implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getGrade() < o2.getGrade()) {
			return 1;
		} else if (o1.getGrade() > o2.getGrade()) {
			return -1;
		} else {
			return 0;
		}
	}
}