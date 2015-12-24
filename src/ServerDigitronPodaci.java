import java.io.IOException;
import java.net.ServerSocket;

public class ServerDigitronPodaci extends Thread {

	@Override
	public void run() {

		ServerSocket oslSoketZaPodatke;
		try {
			oslSoketZaPodatke = new ServerSocket(185);

			while (true) {
				new ServerDigitronNitPodaci(oslSoketZaPodatke.accept()).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
