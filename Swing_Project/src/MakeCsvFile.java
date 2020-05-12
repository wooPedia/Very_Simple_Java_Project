/* csv ���� ���� */

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
	���Ŀ� ����� csv���� ���� / ���� 1, ���� -1 ��ȯ
	stuNumber : ������ �л� �� 
	*/
	public int makeCSV(int stuNumber) throws IOException {
		int ret = 1;
		fileName = path + "stuInfo.csv"; // ���� ó���� csv ���� (��� ����)
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
	
	// ���ĵ� csv ���� ����, ����:1, ������:-1, csv_sorted ���� ����:-2
	public int makeCSV(Student[] array, String path) throws IOException {
		int ret = 1;
		int ret2 = -1; // ����ڰ� ������ csv ������ �������  ���� �����Ϸ��� �� �� ���� ó��
		
		fileName = path.replace("stuInfo.csv", "stuInfo_sorted.csv"); // ���� ó���� csv ���� (��� ����)
		csvFile = new File(fileName);
		
		try { // �⺻ ���� ����� ����ó��
			try { // output file�� ������� ���¿��� �� �Լ��� ȣ��� �� ���� ó��
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
				if(i % 100 == 0) bw.flush(); // 100������ flush
			}
			bw.flush();
		} catch(IOException e) {
			e.printStackTrace();
			ret = -1;
		} finally { // ret2�� -2�̸� ������ open���� �ʾ����Ƿ� close() ȣ������ ����
			if(ret2 != -2) { bw.close(); fw.close(); }
		}
		return ret;
	}
	
	
}
