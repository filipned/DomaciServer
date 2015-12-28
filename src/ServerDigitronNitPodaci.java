import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ServerDigitronNitPodaci extends Thread {
	
	private Socket soketZaKomPodaci = null;
	
	private String brojeviOdKlijenta;
	private String[] brojevi;
	private double[] brojeviDouble;
	
	private String odgovor;
	private String operacija;
	
	public ServerDigitronNitPodaci(Socket soketZaKomPodaci, String operacija) {
		this.soketZaKomPodaci = soketZaKomPodaci;
		this.operacija = operacija;
		
	}

	@Override
	public void run() {
		try {
			PrintStream kaKlijentu = new PrintStream(soketZaKomPodaci.getOutputStream());
			BufferedReader odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomPodaci.getInputStream()));

			try	{
				brojeviOdKlijenta = odKlijenta.readLine();
				
				brojevi = brojeviOdKlijenta.split(" ");
				brojeviDouble = new double[brojevi.length];

				for(int i = 0; i < brojevi.length; i++) {
				   brojeviDouble[i] = Double.parseDouble(brojevi[i]);
				}
					
				if(operacija.equals("Sabiranje")) {
					double sum = 0;
					for (int i = 0; i < brojeviDouble.length; i++) {
						sum += brojeviDouble[i];
					}
					odgovor = sum + "";
				}
				
				if(operacija.equals("Oduzimanje")) {
					double sub = brojeviDouble[0];
					for (int i = 1; i < brojeviDouble.length; i++) {
						
						sub -= brojeviDouble[i];
						
					}
					odgovor = sub + "";
				}
				
				if(operacija.equals("Mnozenje")) {
					double pr = 1;
					for (int i = 0; i < brojeviDouble.length; i++) {
						
						pr *= brojeviDouble[i];
						
					}
					odgovor = pr + "";
				}
				
				if(operacija.equals("Dijeljenje")) {
					double quo = brojeviDouble[0];
					for (int i = 1; i < brojeviDouble.length; i++) {
						
						quo /= brojeviDouble[i];
						
					}
					odgovor = quo + "";
				}
			} catch(SocketException se) {
				soketZaKomPodaci.close();
				kaKlijentu.close();
				odKlijenta.close();
			}
			
			kaKlijentu.println(odgovor);
			
			soketZaKomPodaci.close();
			kaKlijentu.close();
			odKlijenta.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
