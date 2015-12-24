import java.io.IOException;
import java.net.ServerSocket;

public class ServerDigitronPodaci {

	synchronized public static void main(String[] args) {

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
