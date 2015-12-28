import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class ServerDigitron {

	static LinkedList<ServerDigitronNitKontrola> kontrolniSoketi = new LinkedList<ServerDigitronNitKontrola>();
	
	public static void main(String[] args) {
		
		try {
			
			ServerSocket oslSoketZaKontrolu = new ServerSocket(1908);
			ServerSocket oslSoketZaPodatke = new ServerSocket(185);

            
			while(true) {
				Socket soket = oslSoketZaKontrolu.accept();
				kontrolniSoketi.addFirst(new ServerDigitronNitKontrola(soket, oslSoketZaPodatke));
				kontrolniSoketi.getFirst().start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
