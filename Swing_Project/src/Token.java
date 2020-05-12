/* �����ϱ� ���� �о���� csv ������ ������ �ʵ庰�� �и��� �� ���Ϳ� �˸°� ���� */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Token {

	private Vector<Integer> stuID;
	private Vector<Integer> grade;
	private Vector<String>  name;
	private Vector<String>  gpa; 

	public Token() throws IOException {
		stuID = new Vector<>();
		grade = new Vector<>();
		name  = new Vector<>();
		gpa   = new Vector<>();
		createToken();
	}

	public Vector<Integer> getStuID() { return stuID; }
	public Vector<Integer> getGrade() { return grade; }
	public Vector<String>  getName()  { return name;  }
	public Vector<String>  getGpa()   { return gpa;   }
	

	public Vector<Student> getStuVector() {
		Vector<Student> vec = new Vector<>();
		for(int i = 0; i < stuID.size(); i++ ) {
			vec.add(new Student(stuID.get(i), grade.get(i), name.get(i), gpa.get(i)));
		}
		return vec;
	}
	
	public void createToken() throws IOException {

		String path = Main.class.getResource("").getPath();
		Scanner sc = new Scanner(new File(path + "stuInfo.csv"));
		sc.useDelimiter(",");

		int i = 0;

		while (sc.hasNext()) {
			String tok[]; // ������ Į���� �и��� ���� ����

			if (i < 3) { // ù ��° row ó�� (�ʵ���)
				sc.next();
				i++;
			} else if (i == 3) { // ù ��° row�� ������ �ʵ�� �� ��° row�� ù ��° �ʵ� �и�
				tok = (sc.next()).split("\n");
				stuID.add(Integer.parseInt(tok[1]));
				i++;
			}

			if (i > 3) {
				grade.add(Integer.parseInt(sc.next()));
				name.add(sc.next());
				tok = (sc.next()).split("\n");
				gpa.add(tok[0]);
				if (tok.length == 2)
					stuID.add(Integer.parseInt(tok[1]));
			}
		} // while
		sc.close();
	} // CreateToken()
	
}
