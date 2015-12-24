import java.io.IOException;
import java.net.ServerSocket;


public class ServerDigitron {

	public static void main(String[] args) {
		
		try {
			
			ServerSocket oslSoketZaKontrolu = new ServerSocket(1908);
//			Thread t=new Thread(){
//				public void run() {
//					ServerDigitronPodaci p=new ServerDigitronPodaci();
//				};
//			};
			
			while(true) {
				new ServerDigitronNitKontrola(oslSoketZaKontrolu.accept()).start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
