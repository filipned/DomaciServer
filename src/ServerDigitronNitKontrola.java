import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerDigitronNitKontrola extends Thread {
	
	ServerSocket serverSoketPodaci = null;
	Socket soketZaKomKontrolni = null;
	
	volatile static String zahtjev;
	
	String odgovor;
	
	public ServerDigitronNitKontrola(Socket soketZaKomKontrolni, ServerSocket serverSoketPodaci) {
		this.soketZaKomKontrolni = soketZaKomKontrolni;
		this.serverSoketPodaci = serverSoketPodaci;
	}
	

	@Override
	public void run() {
		
		try {
			PrintStream kaKlijentu = new PrintStream(soketZaKomKontrolni.getOutputStream());
			BufferedReader odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomKontrolni.getInputStream()));
			
			while(true){
				boolean zahtjevDobar = false;
				try	{
					zahtjev = odKlijenta.readLine();
					
					if(zahtjev.equals("Sabiranje")) {
						odgovor = "Odobren";
						zahtjevDobar = true;
					} else	if(zahtjev.equals("Oduzimanje")) {
						odgovor = "Odobren";
						zahtjevDobar = true;
					} else	if(zahtjev.equals("Mnozenje")) {
						odgovor = "Odobren";
						zahtjevDobar = true;
					} else if(zahtjev.equals("Dijeljenje")) {
						odgovor = "Odobren";
						zahtjevDobar = true;
					} else {
						odgovor = "Nije odobren";
					}
					
					kaKlijentu.println(odgovor);
					
					if (zahtjevDobar) {
						Socket soket = serverSoketPodaci.accept();
						new ServerDigitronNitPodaci(soket, zahtjev).start();
					}
					
					
					
				} catch(SocketException se) {
					soketZaKomKontrolni.close();
					odKlijenta.close();
					kaKlijentu.close();
					ServerDigitron.kontrolniSoketi.remove(this);
				}
			}

		} catch (IOException e) {
			
		}
		
	}
}
