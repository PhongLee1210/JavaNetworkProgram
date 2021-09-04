package selftStudyNetworkProgram;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyLocalIPAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
      //Get IP address by LocalHost
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("IP address: " + address.toString());
		}catch(UnknownHostException e) {
			System.out.println("Couldn't find local host");
		}
	}
}
