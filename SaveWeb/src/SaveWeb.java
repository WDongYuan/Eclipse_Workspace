import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URL;  
import java.net.URLConnection; 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class SaveWeb {  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
        //new一个URL对象  
      //File input = new File("C:/Users/WeiDong/Desktop/jsouptest.txt");
      Document doc = Jsoup.connect("http://mfcam.net/category/korean-bj/korean-bj-lily/").get();
      //.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
      Elements name = doc.select("title"); // a with href
      Elements jpgs = doc.select("a[href$=.jpg]");
        // a with src ending .png

      //Element masthead = doc.select("div.masthead").first();
        // div with class=masthead

      //Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
      System.out.println(name.text());
      System.out.println(doc.text());
      ArrayList pics = new ArrayList();
      for(Element tmp : jpgs)
      {
        pics.add(tmp.attr("href"));
      }
      try
      {
          BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream("C:/Users/WeiDong/Desktop/jsouptest.txt"));
          for(int i=0; i<pics.size();i++)
          {
            out.write(((String)pics.get(i)+"\n").getBytes());
            download((String)pics.get(i), name.text()+Integer.toString(i+1)+".jpg","D:/微云同步盘");
            System.out.println(Integer.toString(i+1)+" is done");
          }
          out.close();
      }
      catch(IOException e)
      {
          e.printStackTrace();
      }
      System.out.println("Done");
      //D:\微云同步盘
    } 
    
    
    
    
    public static void download(String urlString, String filename,String savePath) throws Exception {  
      // 构造URL  
      URL url = new URL(urlString);  
      // 打开连接  
      URLConnection con = url.openConnection();  
      //设置请求超时为5s  
      con.setConnectTimeout(5*1000);  
      // 输入流  
      InputStream is = con.getInputStream();  
    
      // 1K的数据缓冲  
      byte[] bs = new byte[1024];  
      // 读取到的数据长度  
      int len;  
      // 输出的文件流  
     File sf=new File(savePath);  
     if(!sf.exists()){  
         sf.mkdirs();  
     }  
     OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
      // 开始读取  
      while ((len = is.read(bs)) != -1) {  
        os.write(bs, 0, len);  
      }  
      // 完毕，关闭所有链接  
      os.close();  
      is.close();  
  }   
}  
