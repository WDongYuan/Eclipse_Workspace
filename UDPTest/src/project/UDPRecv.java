package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPRecv {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(4000);
		DatagramSocket acksend = new DatagramSocket();
		FileOutputStream out = new FileOutputStream("Result.txt");
		while (true) {
			byte[] buf = new byte[2048];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			String str = new String(dp.getData(), 0, dp.getLength());
			if(str.equals("end")){
				break;
			}
			//System.out.println(str);
			out.write(str.getBytes());
			System.out.println("IP:" + dp.getAddress().getHostAddress()
					+ ",PORT:" + dp.getPort());

			
			
			//System.in.read();
			//Thread.sleep(1000);
			String ack = "1";
			DatagramPacket acksendpack = new DatagramPacket(ack.getBytes(),
					ack.length(), InetAddress.getByName("127.0.0.1"), 4100);
			acksend.send(acksendpack);
		}
		ds.close();
		acksend.close();
	}
}