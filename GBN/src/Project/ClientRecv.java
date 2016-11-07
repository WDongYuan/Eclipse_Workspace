package Project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientRecv extends Thread{
	public int num;
	private DatagramSocket ackrecv;
	public ClientRecv() throws Exception{
		num=-1;
		ackrecv = new DatagramSocket(3100);
	}
	public void run(){
		while(true){
		Message m=new Message();
		DatagramPacket ackrecvpack = new DatagramPacket(m.msg,m.msg.length);
		try {
			ackrecv.receive(ackrecvpack);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.Decode();
		num=m.num;
		System.out.println("receive:"+String.valueOf(num));
		}
	}
}
