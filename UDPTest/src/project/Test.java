package project;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws Exception {
		FileInputStream in=new FileInputStream("Data.txt");  
        
        ByteArrayOutputStream out=new ByteArrayOutputStream(1024);  
          
        System.out.println("bytes available:"+in.available());  
          
        byte[] temp=new byte[1024];  
          
        int size=0;  
          
        while((size=in.read(temp))!=-1)  
        {  
            out.write(temp,0,size);  
            String str=new String(temp,0,size);
            //System.out.println("--------------------\n"+str);
        }
        int i=110;
        System.out.println(String.valueOf(i).length());
        String s="000110";
        System.out.println(Integer.valueOf(s));
          
        in.close();  
          
//        byte[] bytes=out.toByteArray();  
//        System.out.println("bytes size got is:"+bytes.length);  

	}

}
