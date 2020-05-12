import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	private static URL url;
	private static int pageNum;
	
	private static String path = Main.class.getResource("").getPath();
	private static String fileName;
	private static File resultFile;
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// �ʱ� URL ����
		try {
			url = new URL("http://search.incruit.com/list/search.asp?col=job&il=y&kw=%2enet&occ1=150&startno=0");
		} catch (MalformedURLException ex) {
			System.err.println(ex);
		}
		
		// ��� ���� ���, �̸� ����
		fileName = path + "result.txt";
		resultFile = new File(fileName);
		
		// ���� ���� ����
		try {
			fw = new FileWriter(resultFile, false);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Buffer �̿�
		bw = new BufferedWriter(fw);
		
		
		// ������ �� ������ �� ��������
		pageNum = Bring.page(url);
		int p = 1;
		
		// ������ ���� �� ���� �о���� ���� write
		for(int i = 0; i <= pageNum; i = i+20) {
			try {
				url = new URL("http://search.incruit.com/list/search.asp?col=job&il=y&kw=%2enet&occ1=150&startno=" + i);
			} catch (MalformedURLException ex) { System.err.println(ex); }
			
			ThdCompany th1 = new ThdCompany(url);  // ȸ�� �̸� �о���� ������
			ThdTitle th2 = new ThdTitle(url);	   // ���� �о���� ������
			ThdInfo th3 = new ThdInfo(url); 	   // ä������ �о���� ������
			ThdEtc th4 = new ThdEtc(url);		   // ��Ÿ ���� �о���� ������
			ThdWrite th5 = new ThdWrite(bw);	   // ���Ͽ� write�ϴ� ������
			th5.setPriority(Thread.MIN_PRIORITY);
			
			long start = System.currentTimeMillis();
			
			th1.start();
			th2.start();
			th3.start();
			th4.start();

			th1.join();
			th2.join();
			th3.join();
			th4.join();
			
			th5.start();
			th5.join();
			
			long end = System.currentTimeMillis();
			System.out.println(p++ + " page  " + "���� �ð�: " + (end - start)/1000.0 + "s");
		} // for
		
		bw.close();
		fw.close();
		System.out.println();
		System.out.println("result file has been successfully created!!");
	} // main()

} // Main class
