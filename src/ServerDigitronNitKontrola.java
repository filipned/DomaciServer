import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerDigitronNitKontrola extends Thread {

	Socket soketZaKomKontrolni;
	volatile static String zahtjev;
	String odgovor;
	public ServerDigitronNitKontrola(Socket soketZaKomKontrolni) {
		this.soketZaKomKontrolni = soketZaKomKontrolni;
	}
	

	@Override
	public void run() {
		
		try {
			PrintStream kaKlijentu = new PrintStream(soketZaKomKontrolni.getOutputStream());
			BufferedReader odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomKontrolni.getInputStream()));
			
			while(true){
				try	{
					zahtjev = odKlijenta.readLine();
					
					if(zahtjev.equals("Sabiranje")) {
						odgovor = "Odobren";
					}
					if(zahtjev.equals("Oduzimanje")) {
						odgovor = "Odobren";
					}
					if(zahtjev.equals("Mnozenje")) {
						odgovor = "Odobren";
					}
					if(zahtjev.equals("Dijeljenje")) {
						odgovor = "Odobren";
					}
					
					
					kaKlijentu.println(odgovor);
				} catch(SocketException se) {
					
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
