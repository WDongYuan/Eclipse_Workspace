package project;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPSend {
	public static void main(String[] args) throws Exception {
		
		
		
		FileInputStream in = new FileInputStream("Data.txt");
		//ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		System.out.println("bytes available:" + in.available());
		byte[] temp = new byte[2048];
		byte[] ackbuf = new byte[2048];
		int size = 0;

		DatagramSocket ds = new DatagramSocket();
		DatagramSocket ackrecv = new DatagramSocket(3100);
		boolean timeout=true;
		
		while ((size = in.read(temp)) != -1) {
			//out.write(temp, 0, size);
			//String str = new String(temp, 0, size);
			timeout=true;
			//System.out.println("Continue.");
			
			//String str = "hello , waynerQiu.com!";
			//DatagramSocket ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(temp, size,InetAddress.getByName("127.0.0.1"), 3000);
			while(timeout==true){
			ds.send(dp);

			//System.out.println("After send.");
			//ds.close(); // 关闭连接

			
			
			
			
			DatagramPacket ackrecvpack = new DatagramPacket(ackbuf, ackbuf.length);
			ackrecv.setSoTimeout(100);
			try{
				ackrecv.receive(ackrecvpack);
			}catch(SocketTimeoutException e){
				System.out.println("Timeout");
				continue;
			}
			timeout=false;
			String ack = new String(ackrecvpack.getData(), 0,
					ackrecvpack.getLength());
			if (ack.equals("1")) {
				System.out.println("ACK!");
			} else {
				System.out.println("Failure");
			}
			}
			//ackrecv.close();
			

		}
		String sendend="end";
		DatagramPacket dp = new DatagramPacket(sendend.getBytes(), sendend.length(),InetAddress.getByName("127.0.0.1"), 3000);
		ds.send(dp);
		
		in.close(); 
		System.out.println("The end.");
		ds.close();
		ackrecv.close();
		
		
		
		
		
//		DatagramSocket ds = new DatagramSocket();
//		String str = "hello , waynerQiu.com!";
//		DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(),
//				InetAddress.getByName("127.0.0.1"), 3000);
//		ds.send(dp);
//		ds.close(); // 关闭连接
//
//		DatagramSocket ackrecv = new DatagramSocket(3100);
//		byte[] ackbuf = new byte[1024];
//		DatagramPacket ackrecvpack = new DatagramPacket(ackbuf, ackbuf.length);
//		ackrecv.receive(ackrecvpack);
//		ackrecv.close();
//		String ack = new String(ackrecvpack.getData(), 0,
//				ackrecvpack.getLength());
//		if (ack.equals("1")) {
//			System.out.println("Receive successfully!");
//		} else {
//			System.out.println("Failure");
//		}
	}
}
