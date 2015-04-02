import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class BluecatServer {

	
	public void listen(int port) throws IOException{
		ServerSocket listener = new ServerSocket(port);
		System.out.println("Now listening to port "+ port + "...");
		try {
            while (true) {
                Socket socket = listener.accept();
                try {
                	System.out.println("Sending information");
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("I'm printing stuff");
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}

}
//readline & println are enough for in/out.  Text files only.