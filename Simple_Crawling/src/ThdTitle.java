import java.io.IOException;
import java.net.URL;

// ���� �о���� ������

public class ThdTitle extends Thread {
	private URL url;
	
	public ThdTitle(URL url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		try {
			Bring.title(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
