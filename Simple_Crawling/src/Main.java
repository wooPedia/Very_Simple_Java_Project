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
		
		// 초기 URL 생성
		try {
			url = new URL("http://search.incruit.com/list/search.asp?col=job&il=y&kw=%2enet&occ1=150&startno=0");
		} catch (MalformedURLException ex) {
			System.err.println(ex);
		}
		
		// 결과 파일 경로, 이름 지정
		fileName = path + "result.txt";
		resultFile = new File(fileName);
		
		// 파일 쓰기 설정
		try {
			fw = new FileWriter(resultFile, false);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Buffer 이용
		bw = new BufferedWriter(fw);
		
		
		// 마지막 웹 페이지 수 가져오기
		pageNum = Bring.page(url);
		int p = 1;
		
		// 페이지 별로 웹 정보 읽어오고 파일 write
		for(int i = 0; i <= pageNum; i = i+20) {
			try {
				url = new URL("http://search.incruit.com/list/search.asp?col=job&il=y&kw=%2enet&occ1=150&startno=" + i);
			} catch (MalformedURLException ex) { System.err.println(ex); }
			
			ThdCompany th1 = new ThdCompany(url);  // 회사 이름 읽어오는 스레드
			ThdTitle th2 = new ThdTitle(url);	   // 제목 읽어오는 스레드
			ThdInfo th3 = new ThdInfo(url); 	   // 채용일정 읽어오는 스레드
			ThdEtc th4 = new ThdEtc(url);		   // 기타 사항 읽어오는 스레드
			ThdWrite th5 = new ThdWrite(bw);	   // 파일에 write하는 스레드
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
			System.out.println(p++ + " page  " + "실행 시간: " + (end - start)/1000.0 + "s");
		} // for
		
		bw.close();
		fw.close();
		System.out.println();
		System.out.println("result file has been successfully created!!");
	} // main()

} // Main class
