package Project;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GBNRecv {
	public static void main(String[] args) throws Exception {
		int count=0;
		DatagramSocket ds = new DatagramSocket(4000);
		DatagramSocket acksend = new DatagramSocket();
		FileOutputStream out = new FileOutputStream("Result.txt");
		while (true) {
			byte[] buf = new byte[2052];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			
			String str = new String(dp.getData(), 0, dp.getLength());
			if(str.equals("end")){
				break;
			}
			
			
			Message m=new Message();
			m.msg=dp.getData();
			//System.out.println("-----------"+dp.getData().length);
			//System.out.println(m.GetNum());
			if(count==m.GetNum()){
				Message m2=new Message();
				m2.num=count;
				count++;
				m2.Handler();
				DatagramPacket acksendpack = new DatagramPacket(m2.msg,
						m2.msg.length, InetAddress.getByName("127.0.0.1"), 4100);
				acksend.send(acksendpack);
				
				//System.out.println(str);
				byte[] tmp=new byte[dp.getLength()-4];
				for(int i=0;i<dp.getLength()-4;i++){
					tmp[i]=m.msg[i];
				}
				m.Decode();
				out.write(tmp);
				//System.out.println("Message:"+String.valueOf(count-1)+"   IP:" + dp.getAddress().getHostAddress()
						//+ ",PORT:" + dp.getPort());
				System.out.println("ACK "+String.valueOf(count-1));
			}
			else{
				Message m2=new Message();
				m2.num=count-1;
				m2.Handler();
				System.out.println("ACK "+String.valueOf(count-1));
				DatagramPacket acksendpack = new DatagramPacket(m2.msg,
						m2.msg.length, InetAddress.getByName("127.0.0.1"), 4100);
				acksend.send(acksendpack);
			}

			
			
			//System.in.read();
			//Thread.sleep(1000);
			//String ack = "1";
		}
		ds.close();
		acksend.close();
	}
}
