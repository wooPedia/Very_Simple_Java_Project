import java.io.IOException;
import java.net.URL;

// ä�� ���� �о���� ������

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
