/* ��ӿ� Student Ŭ���� */

public class Student {
	protected int    stuID; // �й�
	protected int    grade; // �г�
	protected String name;  // �̸�
	protected String gpa;   // ���� (A+ ~ F)
	
	public Student() {;}
	
	public Student(int stuID, int grade, String name, String gpa) {
		this.stuID = stuID;
		this.grade = grade;
		this.name = name;
		this.gpa = gpa;
	}

	public void setStuID(int stuID)  { this.stuID = stuID; }
	public void setGrade(int grade)  { this.grade = grade; }
	public void setName(String name) { this.name = name; }
	public void setGPA(String gpa)   { this.gpa = gpa;}

	public int    getStuID() { return stuID; }
	public int    getGrade() { return grade; }
	public String getName()  { return name; }
	public String getGpa()   { return gpa; }
	
}
