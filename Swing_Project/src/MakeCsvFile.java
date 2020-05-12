/* csv 파일 생성 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MakeCsvFile {
	
	private String path;
	private String fileName;
	private File csvFile;
	private FileWriter fw = null;
	private BufferedWriter bw = null;
	
	public MakeCsvFile() {
		path = Main.class.getResource("").getPath();
	}
	
	/* 
	정렬에 사용할 csv파일 생성 / 성공 1, 실패 -1 반환
	stuNumber : 생성할 학생 수 
	*/
	public int makeCSV(int stuNumber) throws IOException {
		int ret = 1;
		fileName = path + "stuInfo.csv"; // 정렬 처리할 csv 파일 (경로 지정)
		csvFile = new File(fileName);
		
		try {
			fw = new FileWriter(csvFile, false);
			bw = new BufferedWriter(fw);
			
			bw.write("stuID,grade,name,gpa\n");
			for(int i = 0; i < stuNumber; i++) {
				StuTestResult sc = new StuTestResult();
				bw.write(sc.getRow() + "\n");
				if(i % 100 == 0) bw.flush();
			}
			bw.flush();
		} catch(IOException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			bw.close();
			fw.close();
		}
		return ret;
	}
	
	// 정렬된 csv 파일 생성, 정상:1, 비정상:-1, csv_sorted 열린 상태:-2
	public int makeCSV(Student[] array, String path) throws IOException {
		int ret = 1;
		int ret2 = -1; // 사용자가 생성된 csv 파일을 열어놓고  파일 생성하려고 할 때 예외 처리
		
		fileName = path.replace("stuInfo.csv", "stuInfo_sorted.csv"); // 정렬 처리할 csv 파일 (경로 지정)
		csvFile = new File(fileName);
		
		try { // 기본 파일 입출력 예외처리
			try { // output file을 열어놓은 상태에서 이 함수가 호출될 시 예외 처리
				fw = new FileWriter(csvFile, false);
			} catch(FileNotFoundException e) {
				ret2 = -2;
				return ret2;
			}
				
			bw = new BufferedWriter(fw);
			bw.write("stuID,grade,name,gpa\n");
			for(int i = 0; i < array.length; i++) {
				bw.write(array[i].getStuID() + "," + array[i].getGrade() + 
						"," + array[i].getName() + "," + array[i].getGpa() + "\n");
				if(i % 100 == 0) bw.flush(); // 100번마다 flush
			}
			bw.flush();
		} catch(IOException e) {
			e.printStackTrace();
			ret = -1;
		} finally { // ret2가 -2이면 파일을 open하지 않았으므로 close() 호출하지 않음
			if(ret2 != -2) { bw.close(); fw.close(); }
		}
		return ret;
	}
	
	
}
