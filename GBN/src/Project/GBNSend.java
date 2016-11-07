package Project;

import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class GBNSend {

	private int winwidth;
	private List<Message> buffer;
	private int winbase;
	private int pointer;
	static private DatagramSocket ds;
	//private DatagramSocket ackrecv;
	static private FileInputStream in;

	public GBNSend() throws Exception {
		pointer = 0;//the next message to send
		winwidth = 10;
		buffer = new ArrayList<Message>();
		winbase = 0;
		ds = new DatagramSocket();
		//ackrecv = new DatagramSocket(3100);
		in = new FileInputStream("Data.txt");
	}
	public void Run() throws Exception{
		ClientRecv cr=new ClientRecv();
		cr.start();
		int flag=SendMessage();
		long time=System.currentTimeMillis();
		while(!(flag==0 && pointer==0)){
			//System.out.println("--------------"+pointer);
			if(flag==1||flag==-1){
				Thread.sleep(10);
				flag=SendMessage();
			}
			int temp=cr.num;
			if(temp>=winbase){
				time=System.currentTimeMillis();
				//System.out.println("Move "+String.valueOf(temp-winbase+1));
				pointer=pointer-(temp-winbase+1);
				for(int i=0;i<temp-winbase+1;i++)
				{
					buffer.remove(0);
				}
				winbase=temp+1;
			}
			if(System.currentTimeMillis()-time>100){
				//System.out.println("resend");
				Resend();
				time=System.currentTimeMillis();
			}
		}
	}

	public int SendMessage() throws Exception {
		Message m = new Message();
		if (pointer < winwidth) {
			//System.out.println("pointer "+String.valueOf(pointer));
			m.num = winbase + pointer;
			int size = 0;
			if ((size = in.read(m.data)) != -1) {
				m.Handler();
				m.size=size+4;
				size = size+4;
				System.out.println("send "+String.valueOf(m.num));
				DatagramPacket dp = new DatagramPacket(m.msg, size,
						InetAddress.getByName("127.0.0.1"), 3000);
				ds.send(dp);
				buffer.add(m);
				pointer++;
				return 1;//succeed
			} else {
				return 0;//finish the text
			}
		}
		else
		{
			return -1;//the window is full
		}
	}
	public void Resend() throws Exception{
		int count=buffer.size();
		for(int i=0;i<count;i++){
			System.out.println("resend "+buffer.get(i).num);
			DatagramPacket dp = new DatagramPacket(buffer.get(i).msg, buffer.get(i).size,
					InetAddress.getByName("127.0.0.1"), 3000);
			ds.send(dp);
		}
	}

//	public int RecvMessage() throws Exception {
//		Message m=new Message();
//		DatagramPacket ackrecvpack = new DatagramPacket(m.msg,m.msg.length);
//		ackrecv.receive(ackrecvpack);
//		m.Decode();
//		return m.num;
//	}

	public static void main(String[] args) throws Exception {
		GBNSend send=new GBNSend();
		send.Run();
		
		
		Thread.sleep(1000);
		String sendend="end";
		DatagramPacket dp = new DatagramPacket(sendend.getBytes(), sendend.length(),InetAddress.getByName("127.0.0.1"), 3000);
		ds.send(dp);
		
		in.close(); 
		System.out.println("The end.");
		ds.close();
	}

}