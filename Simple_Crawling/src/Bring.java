import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Bring {
	
	// �� ����Ʈ�κ��� �о�� ������ ���� �迭
	private static String[] company_arr;
	private static String[] title_arr;
	private static String[] info_arr;
	private static String[] etc_arr;
	
	// �� ����Ʈ�κ��� ȸ�� �̸� �о� �����ϴ� �޼���
	public static String[] company(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "euc-kr"));
		String line;
		
		while((line = br.readLine()) != null) {
			if(line.contains("<ul class=\"litype01\">")) break;
		}
		while((line = br.readLine()) != null) {
			if(line.contains("<li>")) break;
		} // line => <li>~
		br.close();
		
		company_arr = line.split("\'_blank\'>");

		for(int i = 1; i < company_arr.length; i++) {
			company_arr[i] = company_arr[i].split("</a>")[0];
		}
		
		return company_arr;
	} // company()
	
	// �� ����Ʈ�κ��� ���� �о� �����ϴ� �޼���
	public static String[] title(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "euc-kr"));
		String line;
		
		while((line = br.readLine()) != null) {
			if(line.contains("<ul class=\"litype01\">")) break;
		}
		while((line = br.readLine()) != null) {
			if(line.contains("<li>")) break;
		} // line => <li>~
		br.close();

		title_arr = line.split("<span class=\"rcrtTitle\">");
		
		for(int i = 1; i < title_arr.length; i++) {
			title_arr[i] = title_arr[i].split("target=\"_blank\">")[1].split("</a>")[0];
			if(title_arr[i].contains("<strong>")) {
				title_arr[i] = title_arr[i].replace("<strong>", "");
				title_arr[i] = title_arr[i].replace("</strong>", "");
			}
		}
		
		return title_arr;
	} // title()

	// �� ����Ʈ�κ��� ä�� ���� �о� �����ϴ� �޼���
	public static String[] info(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "euc-kr"));
		String line;
		
		while((line = br.readLine()) != null) {
			if(line.contains("<ul class=\"litype01\">")) break;
		}
		while((line = br.readLine()) != null) {
			if(line.contains("<li>")) break;
		} // line => <li>~
		br.close();

		info_arr = line.split("<span class=\"info\">");
		
		for(int i = 1; i < info_arr.length; i++) {
			info_arr[i] = info_arr[i].split("</span>")[0];
		}
		
		return info_arr;
	} // info()

	// �� ����Ʈ�κ��� ��Ÿ ���� �о� �����ϴ� �޼���
	public static String[] etc(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "euc-kr"));
		String line;
		
		while((line = br.readLine()) != null) {
			if(line.contains("<ul class=\"litype01\">")) break;
		}
		while((line = br.readLine()) != null) {
			if(line.contains("<li>")) break;
		} // line => <li>~
		br.close();
		
		etc_arr = line.split("<p class=\"etc\"><span>");
		
		for(int i = 1; i < etc_arr.length; i++) {
			etc_arr[i] = etc_arr[i].split("</span>")[0];
		}
		
		return etc_arr;
	} // etc()
	
	// ������ ������ �� �о� �����ϴ� �޼���
	public static int page(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "euc-kr"));
		String line;
		String tmp = null;
		int pageNum = 0;
		
		while((line = br.readLine()) != null) {
			if(line.contains("������������")) {
				tmp = line.split("class=\"f_next_n\">")[0];
			}
		}
		String[] temp = tmp.split("startno=");
		pageNum = Integer.parseInt(temp[temp.length-1].replace("\"", "").trim());
		
		return pageNum;
	} // page()
	
	// ���� write �޼���
	public static void write(BufferedWriter bw) throws IOException {
		
		for(int i = 1; i < company_arr.length; i++) {
			String tmp = company_arr[i] + "\t" + title_arr[i] + "  " + info_arr[i] + "\r\n " + etc_arr[i] + "\r\n\r\n";
			bw.write(tmp);
		}
		bw.flush();

	} // write()
	
}
