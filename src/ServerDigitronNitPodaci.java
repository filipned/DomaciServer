import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ServerDigitronNitPodaci extends Thread {
	
	Socket soketZaKomPodaci;
	String brojeviOdKlijenta;
	String[] brojevi;
	int[] brojeviInt;
	String odgovor;
	String operacija1;
	
	public ServerDigitronNitPodaci(Socket soketZaKomPodaci) {
		this.soketZaKomPodaci = soketZaKomPodaci;
		
	}

	@Override
	public void run() {
		try {
			PrintStream kaKlijentu = new PrintStream(soketZaKomPodaci.getOutputStream());
			BufferedReader odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomPodaci.getInputStream()));
			
			brojeviOdKlijenta = odKlijenta.readLine();
			brojevi = brojeviOdKlijenta.split(" ");
			for (int i = 0; i < brojevi.length; i++) {
				System.out.println(brojevi[i]);
			}
			brojeviInt = new int[brojevi.length];
			
			for(int i = 0; i < brojevi.length; i++) {
			   brojeviInt[i] = Integer.parseInt(brojevi[i]);
			}
			for (int i = 0; i < brojeviInt.length; i++) {
				System.out.println(brojevi[i]);
			}
			operacija1 = ServerDigitronNitKontrola.operacija;
			if(operacija1.equals("Sabiranje")) {
				int sum = 0;
				for (int i = 0; i < brojeviInt.length; i++) {
					sum += brojeviInt[i];
				}
				odgovor = sum + "";
			}
			if(operacija1.equals("Oduzimanje")) {
				int sub = brojeviInt[0];
				for (int i = 1; i < brojeviInt.length; i++) {
					
					sub -= brojeviInt[i];
					
				}
				odgovor = sub + "";
			}
			if(operacija1.equals("Mnozenje")) {
				int pr = 1;
				for (int i = 0; i < brojeviInt.length; i++) {
					
					pr *= brojeviInt[i];
					
				}
				odgovor = pr + "";
			}
			if(operacija1.equals("Dijeljenje")) {
				int quo = brojeviInt[0];
				for (int i = 1; i < brojeviInt.length; i++) {
					
					quo /= brojeviInt[i];
					
				}
				odgovor = quo + "";
			}
			
			kaKlijentu.println(odgovor);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
