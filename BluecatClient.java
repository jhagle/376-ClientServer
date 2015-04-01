import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class BluecatClient {

	public static void main(String[] args) throws IOException {
		InetAddress serverAddress = InetAddress.getLoopbackAddress();
	        Socket s = new Socket(serverAddress, 9090);
	        BufferedReader input =
	            new BufferedReader(new InputStreamReader(s.getInputStream()));
	        String answer = input.readLine();
	        System.out.println(answer);
	        System.exit(0);
    }
}