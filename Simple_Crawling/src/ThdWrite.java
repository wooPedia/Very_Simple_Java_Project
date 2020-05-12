import java.io.BufferedWriter;
import java.io.IOException;

// 파일 write 스레드

public class ThdWrite extends Thread {
	private BufferedWriter bw = null;
	
	public ThdWrite(BufferedWriter bw) {
		this.bw = bw;
	}
	
	@Override
	public void run() {
		try {
			Bring.write(bw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
