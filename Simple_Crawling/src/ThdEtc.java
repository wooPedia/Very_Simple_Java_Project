import java.io.IOException;
import java.net.URL;

// ��Ÿ ���� �о���� ������

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
