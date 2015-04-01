//James Hagle
//Assignment 1


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class Bluecat {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        if(Arrays.asList(args).contains("-r")){
          	System.out.println("This is where I read a single line at a time");
        }
        if(Arrays.asList(args).contains("-f")){
           	System.out.println("This is where I read an entire file and send it to the SERVER");
        }
        if(args.length==0){
           	System.out.println("This is where I read from standard input");
        }
        if(Arrays.asList(args).contains("-o")){
           	System.out.println("This is where I create or overwrite a file");
        }
        
        if(Arrays.asList(args).contains("-f")){
           	System.out.println("This is where I read an entire file");
        }
        if(args[0].equals("-l")){
        	String port = new String(args[1]);
        	if(Arrays.asList(args).contains("-p")){
              	System.out.println("This is where I log all packets recieved and sent");
              	
              	System.out.println("optional data: " + args[args.length - 1]);
            }
            if(Arrays.asList(args).contains("-f")){
               	System.out.println("This is where I read an entire file and send it to the CLIENT");
            }
           	System.out.println("This is where I LISTEN");
           	BluecatServer server = new BluecatServer();
           	server.listen(port);
        }
	}
}
