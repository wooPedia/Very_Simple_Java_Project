/* ���� ��� csv���Ͽ� ����� �л� ���� ���� ���� */

import java.util.Random;

// Student �ڽ� Ŭ����
public class StuTestResult extends Student {
	private String row;
	
	// �������� �л� ���� ����
	public StuTestResult() {
		Random rd = new Random();
		
		// �й��� ���� �ߺ� ���� ó�� �ؾ�������, �ʹ� �������� �� ���� ���� �ʾҽ��ϴ�..
		this.stuID = Integer.parseInt( Integer.toString((2019 - rd.nextInt(7))) 
				   						+ String.format("%04d", rd.nextInt(10000)) ); // 2013~2019 + 0000~9999
		
		this.grade = 1 + rd.nextInt(4); // 1~4�г�
		
		this.name = Info.name1[rd.nextInt(Info.name1.length)] 
					+ Info.name2[rd.nextInt(Info.name2.length)] 
					+ Info.name3[rd.nextInt(Info.name3.length)]; // �̸� 3�� ����
		
		this.gpa = Info.gpa[rd.nextInt(Info.gpa.length)]; // ����, ������
		/* stuID, grade, name, gpa */
		this.row = stuID + "," + grade + "," + name + "," + gpa; // a row of a csv file
	}

	public String getRow()   { return row;   }
	
}