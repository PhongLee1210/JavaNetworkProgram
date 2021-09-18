package selftStudyNetworkProgram;
//Import library
import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoClient {
	// Declare variables
	private static InetAddress hostIP;
	private static final int PORT = 1234;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			hostIP = InetAddress.getLocalHost();
		}catch(UnknownHostException e) {
			System.out.println("Host ID not found!");
			System.exit(1);
		}
			requestServer();
	}
	private static void requestServer() {
		Socket link = null;
		try {
			link = new Socket(hostIP, PORT);
			Scanner input = new Scanner(link.getInputStream()); //Step 1.
			PrintWriter output = new PrintWriter(link.getOutputStream(), true); //Step 2.
			// Set up stream for keyboard entry
			Scanner userEntry = new Scanner(System.in);
			String message, response;
			do {
				System.out.println("Enter message: ");
				message = userEntry.nextLine();
				output.println(message);		//Step 3.
				response = input.nextLine();	//Step 3.
				System.out.println("\nSERVER> " + response);
			}while(!message.equals("***CLOSE***"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				System.out.println("\n Closing connection... *");
				link.close();		//Step 4.
				
			}catch(IOException e) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
