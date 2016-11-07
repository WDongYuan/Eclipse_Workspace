package project;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPLost {

	public static void main(String[] args) throws Exception {
		// DatagramSocket ds = new DatagramSocket(3000);
		// byte[] buf = new byte[1024];
		// DatagramPacket dp = new DatagramPacket(buf, buf.length);
		// ds.receive(dp);
		// String str = new String(dp.getData(), 0, dp.getLength());
		// System.out.println(str);
		// System.out.println("IP:" + dp.getAddress().getHostAddress() +
		// ",PORT:"
		// + dp.getPort());
		// ds.close();

			DatagramSocket lostrecv = new DatagramSocket(3000);
			DatagramSocket lostsend = new DatagramSocket();
			DatagramSocket ackrecv = new DatagramSocket(4100);
			DatagramSocket acksend = new DatagramSocket();
			while(true){
			byte[] lostbuf = new byte[2048];
			DatagramPacket recvpack = new DatagramPacket(lostbuf,
					lostbuf.length);
			lostrecv.receive(recvpack);
			//lostrecv.close();

			DatagramPacket sendpack = new DatagramPacket(recvpack.getData(),
					recvpack.getLength(), InetAddress.getByName("127.0.0.1"),
					4000);
			if(((int)(Math.random()*100+1))%10==2){
				System.out.println("Lost!!!");
				continue;
			}
			lostsend.send(sendpack);
			String str=new String(recvpack.getData(),0,recvpack.getLength());
			if(str.equals("end")){
				break;
			}
			//System.out.println("After send.");
			//lostsend.close();

			// ------------------------------ACK---------------------------------------
			byte[] ackbuf = new byte[2048];
			DatagramPacket ackrecvpack = new DatagramPacket(ackbuf,
					ackbuf.length);
			ackrecv.receive(ackrecvpack);
			//ackrecv.close();

			DatagramPacket acksendpack = new DatagramPacket(
					ackrecvpack.getData(), ackrecvpack.getLength(),
					InetAddress.getByName("127.0.0.1"), 3100);
			acksend.send(acksendpack);
			//acksend.close();
			}
			lostrecv.close();
			lostsend.close();
			ackrecv.close();
			acksend.close();
	}

}
