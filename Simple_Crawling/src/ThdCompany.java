import java.io.IOException;
import java.net.URL;

// 회사 이름 읽어오는 스레드

public class ThdCompany extends Thread {
	private URL url;
	
	public ThdCompany(URL url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			Bring.company(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
