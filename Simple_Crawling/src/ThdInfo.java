import java.io.IOException;
import java.net.URL;

// 채용 일정 읽어오는 스레드

public class ThdInfo extends Thread {
private URL url;
	
	public ThdInfo(URL url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			Bring.info(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
