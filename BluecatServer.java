import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class BluecatServer {

	int port_number;
	ServerSocket server_socket;
	Socket client_socket;
	
	public BluecatServer( int port ) throws IOException {
		port_number= port;
		server_socket= new ServerSocket( port_number );
		System.out.println( "Listening on port " + Integer.toString( port_number ) );
	}
	
	
	public void listen() throws IOException
	{
		// listen for a connection
		client_socket= server_socket.accept();
		
		// grab the input and output streams
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		
		// read the input
		String  thisLine = null;
		System.out.println( "Received from client: " );
		output.println("message recieved");
		for (thisLine = reader.readLine(); thisLine != null; thisLine = reader.readLine()) {
		    System.out.println(thisLine);
		}
		return;
		
		

	}
	
	void close()
	{
		try
		{
			client_socket.close();
			System.out.println( "Listening concluded; shutting down..." );
			server_socket.close();
		}
		catch( Exception e )
		{
			// ignore
		}
	}
	

}
//readline & println are enough for in/out.  Text files only.