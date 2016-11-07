package Project;

public class Message {
	public int num;
	public byte[] data;
	public byte[] msg;
	public int size;
	public Message(){
		num=0;
		data=new byte[2048];
		msg=new byte[2052];
		size=0;
	}
	public void Handler(){
		byte[] temp=intToByte4(num);
		for(int i=0;i<4;i++)
		{
			msg[i]=temp[i];
		}
		for(int i=4;i<2052;i++)
		{
			msg[i]=data[i-4];
		}
	}
	public void Decode(){
		byte[]temp=new byte[4];
		for(int i=0;i<4;i++){
			temp[i]=msg[i];
		}
		num=byte4ToInt(temp,0);
		for(int i=4;i<2052;i++){
			data[i-4]=msg[i];
		}
	}
	public int GetNum(){
		byte[]temp=new byte[4];
		for(int i=0;i<4;i++){
			temp[i]=msg[i];
		}
		int n=byte4ToInt(temp,0);
		return n;
	}

public static byte[] intToByte4(int i) {  
    byte[] targets = new byte[4];  
    targets[3] = (byte) (i & 0xFF);  
    targets[2] = (byte) (i >> 8 & 0xFF);  
    targets[1] = (byte) (i >> 16 & 0xFF);  
    targets[0] = (byte) (i >> 24 & 0xFF);  
    return targets;  
} 
public static int byte4ToInt(byte[] bytes, int off) {  
    int b0 = bytes[off] & 0xFF;  
    int b1 = bytes[off + 1] & 0xFF;  
    int b2 = bytes[off + 2] & 0xFF;  
    int b3 = bytes[off + 3] & 0xFF;  
    return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;  
}  
}
