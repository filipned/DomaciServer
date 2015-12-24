import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerDigitronNitKontrola extends Thread {

	Socket soketZaKomKontrolni;
	String zahtjev;
	String odgovor;
	static String operacija = "";
	public ServerDigitronNitKontrola(Socket soketZaKomKontrolni) {
		this.soketZaKomKontrolni = soketZaKomKontrolni;
	}
	

	@Override
	public void run() {
		
		try {
			PrintStream kaKlijentu = new PrintStream(soketZaKomKontrolni.getOutputStream());
			BufferedReader odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomKontrolni.getInputStream()));
			while(true){
				zahtjev = odKlijenta.readLine();
				System.out.println("prije");
				System.out.println(zahtjev);
				
				if(zahtjev.equals("Sabiranje")) {
					operacija = "Sabiranje";
					odgovor = "Odobren";
				}
				if(zahtjev.equals("Oduzimanje")) {
					operacija = "Oduzimanje";
					odgovor = "Odobren";
				}
				if(zahtjev.equals("Mnozenje")) {
					operacija = "Mnozenje";
					odgovor = "Odobren";
				}
				if(zahtjev.equals("Dijeljenje")) {
					operacija = "Dijeljenje";
					odgovor = "Odobren";
				}
				
				System.out.println(operacija+"evo je");
				System.out.println(odgovor+"evo ga");
				kaKlijentu.println(odgovor);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
