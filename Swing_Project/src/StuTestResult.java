/* 정렬 대상 csv파일에 사용할 학생 정보 랜덤 생성 */

import java.util.Random;

// Student 자식 클래스
public class StuTestResult extends Student {
	private String row;
	
	// 랜덤으로 학생 정보 생성
	public StuTestResult() {
		Random rd = new Random();
		
		// 학번은 원래 중복 제외 처리 해야하지만, 너무 복잡해질 것 같아 하지 않았습니다..
		this.stuID = Integer.parseInt( Integer.toString((2019 - rd.nextInt(7))) 
				   						+ String.format("%04d", rd.nextInt(10000)) ); // 2013~2019 + 0000~9999
		
		this.grade = 1 + rd.nextInt(4); // 1~4학년
		
		this.name = Info.name1[rd.nextInt(Info.name1.length)] 
					+ Info.name2[rd.nextInt(Info.name2.length)] 
					+ Info.name3[rd.nextInt(Info.name3.length)]; // 이름 3자 조합
		
		this.gpa = Info.gpa[rd.nextInt(Info.gpa.length)]; // 학점, 절대평가
		/* stuID, grade, name, gpa */
		this.row = stuID + "," + grade + "," + name + "," + gpa; // a row of a csv file
	}

	public String getRow()   { return row;   }
	
}