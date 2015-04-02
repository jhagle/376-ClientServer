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
		//Act as server with '-l'
        if(args[0].equals("-l")){
        	System.out.println("I am a SERVER");
        	//Port number must immediately follow '-l'
        	String port = new String(args[1]);
        	try{
        		//Ensure port number is an int
        		int portNum = Integer.parseInt(port);
               	//-f reads entire file and sends it to the client
                if(Arrays.asList(args).contains("-f")){
                	if(Arrays.asList(args).contains("-r")){
                		System.out.println("You may only select -f or -r, not both. Now Closing");
                		return;
                	}
                   	System.out.println("This is where I read an entire file and send it to the CLIENT");
                   	System.out.println("File Name: " + args[(Arrays.asList(args).indexOf("-f")) + 1]);
                }
              //-r reads file line by line and sends them to the client
                if(Arrays.asList(args).contains("-r")){
                	if(Arrays.asList(args).contains("-f")){
                		System.out.println("You may only select -f or -r, not both. Now Closing");
                		return;
                	}
                  	System.out.println("This is where I read a single line at a time");
                  	System.out.println("File Name: " + args[(Arrays.asList(args).indexOf("-r")) + 1]);
                }
                
                //-p logs all packets sent back and forth in a given file
               	if(Arrays.asList(args).contains("-p")){
                  	System.out.println("This is where I log all packets recieved and sent");
                  
                  	System.out.println("Log File Name: " + args[(Arrays.asList(args).indexOf("-p")) + 1]);
                }
               	//If no further parameters than reads from standard input
                else{
                	System.out.println("This is where I read from standard input");
                }
               	//Instantiate server
               	BluecatServer server = new BluecatServer();
               	server.listen(portNum);
        	} catch (NumberFormatException e) {
        		System.out.println("You must enter a port number after '-l'");
        	}
        	
            return;
        }
        
        
        //If no '-l' then it acts as client
        else{
        	System.out.println("I am a CLIENT");
	        
	        if(Arrays.asList(args).contains("-f")){
	        	if(Arrays.asList(args).contains("-r")){
	        		System.out.println("You may only select -f or -r, not both. Now Closing");
	        		return;
	        	}
	           	System.out.println("This is where I read an entire file and send it to the SERVER");
	           	System.out.println("File Name: " + args[(Arrays.asList(args).indexOf("-f")) + 1]);
	        }
	        
	        if(Arrays.asList(args).contains("-r")){
	        	if(Arrays.asList(args).contains("-f")){
	        		System.out.println("You may only select -f or -r, not both. Now Closing");
	        		return;
	        	}
	          	System.out.println("This is where I read a single line at a time and send it to the SERVER");
	          	System.out.println("File Name: " + args[(Arrays.asList(args).indexOf("-r")) + 1]);
	        }
	
	        if(Arrays.asList(args).contains("-o")){
	           	System.out.println("This is where I create or overwrite a file");
	           	System.out.println("File Name: " + args[(Arrays.asList(args).indexOf("-o")) + 1]);
	        }
	        
	        else{
	           	System.out.println("This is where I read from standard input");
	        }
        
	        String port = args[(args.length)-1];
	        try{
        		int portNum = Integer.parseInt(port);
        		BluecatClient client = new BluecatClient();
               	client.send(portNum);
	        }
	        catch (NumberFormatException e) {
        		System.out.println("You must enter a port number as the last command line parameter");
        	}
        }
	}
}
