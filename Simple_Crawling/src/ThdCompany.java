import java.io.IOException;
import java.net.URL;

// ȸ�� �̸� �о���� ������

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
