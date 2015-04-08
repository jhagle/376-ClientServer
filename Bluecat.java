//James Hagle
//Assignment 1


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class Bluecat {
	
	String writeFile;
	

	public static void main(String[] args) throws IOException {
		//Act as server with '-l'
        if(args[0].equals("-l")){
        	System.out.println("I am a SERVER");
        	//Port number must immediately follow '-l'
        	String port = new String(args[1]);
        	try{
        		//Ensure port number is an int
        		int portNum = Integer.parseInt(port);
               	BluecatServer server = new BluecatServer(portNum);
               	server.listen();
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
                	
                }
               	//Instantiate server
               	server.close();
        	} catch (NumberFormatException e) {
        		System.out.println("You must enter a port number after '-l'");
        	}
        	
            
        }
        
        
        //If no '-l' then it acts as client
        else{
        	String port = args[(args.length)-1];
        	String contents;
	        int portNum = Integer.parseInt(port);
	        try{

        	System.out.println("I am a CLIENT");
	        
	        if(Arrays.asList(args).contains("-f")){
	        	if(Arrays.asList(args).contains("-r")){
	        		System.out.println("You may only select -f or -r, not both. Now Closing");
	        		return;
	        	}
	           	System.out.println("This is where I read an entire file and send it to the SERVER");
	           	String filename = args[(Arrays.asList(args).indexOf("-f")) + 1];
	           	String file = readFile(filename);
	           	contents = file;
	           	BluecatClient client = new BluecatClient(portNum);
				client.communicate(file);
				client.close();
				return;
	        }

	        if(Arrays.asList(args).contains("-r")){
	        	if(Arrays.asList(args).contains("-f")){
	        		System.out.println("You may only select -f or -r, not both. Now Closing");
	        		return;
	        	}
	          	System.out.println("This is where I read a single line at a time and send it to the SERVER");
	           	String filename = args[(Arrays.asList(args).indexOf("-r")) + 1];
	           	String file = readLine(filename);
	           	contents = file;
	           	BluecatClient client = new BluecatClient(portNum);
				client.communicate(file);
				client.close();
				return;
	        } else{
	           	System.out.println("This is where I read from standard input");
	           	BufferedReader reader= new BufferedReader( 
						 new InputStreamReader(System.in) );
				System.out.print( "Enter message: " );
				String message= reader.readLine();
				contents = message;
				BluecatClient client = new BluecatClient(portNum);
				client.communicate(message);
				client.close();
				
	        }
	        if(Arrays.asList(args).contains("-o")){
	           	System.out.println("This is where I create or overwrite a file");
	           	write((args[(Arrays.asList(args).indexOf("-o")) + 1]), contents);
	           	System.out.println("File Name: " + args[(Arrays.asList(args).indexOf("-o")) + 1]);
	        } 
	        }
	        catch (NumberFormatException e) {
        		System.out.println("You must enter a port number as the last command line parameter");
        		
        	}

        }
	}



static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}

static String readLine(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append('\n');
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}

static void write(String writeFile, String content) throws IOException {
	PrintWriter out = new PrintWriter(writeFile);
	out.println(content);
	out.close();
}

}

