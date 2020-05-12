import java.io.IOException;
import java.net.URL;

// 기타 사항 읽어오는 스레드

public class ThdEtc extends Thread  {
private URL url;
	
	public ThdEtc(URL url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			Bring.etc(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
