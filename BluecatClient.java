import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class BluecatClient {

	
	public void send(int port){
		try{
		InetAddress serverAddress = InetAddress.getLoopbackAddress();
        Socket s = new Socket(serverAddress, port);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);
        System.exit(0);
		}catch(Exception e){
		System.out.println(e.getMessage());
		return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
    }
}