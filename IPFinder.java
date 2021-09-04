package selftStudyNetworkProgram;
//Import Library
import java.net.*;
import java.util.*;

public class IPFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostname; String host2;
		Scanner input = new Scanner(System.in);
		InetAddress address;
		System.out.println("\nEnter your host name: ");
		hostname = input.next();
		try {
      //Get IP address by hostname
			address = InetAddress.getByName(hostname);
			System.out.println("IP address: " + address.toString());
		}catch(UnknownHostException e) {
			System.out.println("Couldn't find host");
		}
	}
}
