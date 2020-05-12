/* 정렬 처리 클래스 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class Sort {

	static Student[] sortedArray = null;

	int i = 0;

	// 오름차순
	// type(정렬 기준)
	// type: stuID(1), grade(2), default = 1
	public static Student[] ascSort(Vector<Student> vec, int type) {

		if (type != 1 && type != 2) type = 1;
		
		if (type == 1) { // stuID 기준 정렬
			TypeAscStuID c = new TypeAscStuID();
			sortedArray = vec.toArray(new Student[0]); // Student class형 temp
			Arrays.sort(sortedArray, c);
		} else if (type == 2) { // grade 기준 정렬
			TypeAscGrade c = new TypeAscGrade();
			sortedArray = vec.toArray(new Student[0]);
			Arrays.sort(sortedArray, c);
		}
		return sortedArray;
	}

	// 내림차순
	// type: stuID(1), grade(2), default = 1
	public static Student[] dscSort(Vector<Student> vec, int type) {

		if (type != 1 && type != 2) type = 1;
		
		if (type == 1) { // stuID 기준 정렬
			TypeDscStuID c = new TypeDscStuID();
			sortedArray = vec.toArray(new Student[0]);
			Arrays.sort(sortedArray, c);
		} else if (type == 2) { // grade 기준 정렬
			TypeDscGrade c = new TypeDscGrade();
			sortedArray = vec.toArray(new Student[0]);
			Arrays.sort(sortedArray, c);
		}
		return sortedArray;
	}
} // Sort class

// 학번 기준 오름차순
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

// 학번 기준 내림차순
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

// 학년 기준 오름차순
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

// 학년 기준 내림차순
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