import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

  public static void main(String argsp[]) throws Exception {
    
    

    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:/Users/WeiDong/Desktop/contents.txt"));
    for(int i =1; i<=242; i++)
    {
      //String pretext = "D:/微云同步盘/";
      //String dir = Integer.toString(i)+"/";
      //createDir(pretext+dir);
      //System.out.println(pretext+dir);
      //System.in.read();
     String code = (getHtmlContent(
        "http://mfcam.net/page/"+i+"/", "utf-8"));
      //System.out.println(code);
      //GetPic(code,pretext, dir);
      GetLink(code,out);
    }
    out.close();

  }

  public static void GetPic(String html,String pretext, String dir) throws Exception {
    // new一个URL对象
    // File input = new File("C:/Users/WeiDong/Desktop/jsouptest.txt");
    Document doc = Jsoup.parse(html);
    // .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
    Elements name = doc.select("title"); // a with href
    //Elements jpgs = doc
        //.select("div[class = entry-content post_content] > p > a > img[src$=.jpg]:eq(0)");
    Elements jpgs = doc
        .select("div[class = entry-content post_content]  img[src$=.jpg]");  //For the later picture
    Elements links = doc
        .select("div[class = entry-content post_content] a[href]");
    Elements picName = doc
        .select("article > header[class=entry-header] > h2 > a");
    
    System.out.println(name.text());
    // System.out.println(doc.text());
    ArrayList pics = new ArrayList();
    ArrayList names = new ArrayList();
    ArrayList linklist = new ArrayList();
    for (Element tmp : jpgs) {
      pics.add(tmp.attr("src"));
      System.out.println((String)tmp.attr("src"));
      //names.add(tmp.attr("alt"));
    }
    for(Element tmp : picName)
    {
      names.add(tmp.text());
      System.out.println(tmp.text());
    }
    String beginPic = (String)names.get(0);
    String lastPic = (String)names.get(names.size()-1);
    //String beginPic = (String)pics.get(0);
    //String lastPic = (String)pics.get(pics.size()-1);
    //String str1[] = beginPic.split("-|/|\\.");
    //String str2[] = lastPic.split("-|/|\\.");
    //String begin = str1[str1.length-2];
    //String last = str2[str2.length-2];
    System.out.println(beginPic);
    System.out.println(lastPic);
    System.out.println("names.size: "+names.size());
    System.out.println("pics.size: "+pics.size());
    //System.in.read();
    for (Element tmp : links) {
      linklist.add(tmp.attr("href"));
    }
    try {
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
          pretext+dir+"link.txt"));
      BufferedOutputStream eout = new BufferedOutputStream(new FileOutputStream(
          pretext+dir+"error.txt"));
      for(int i = 0; i<names.size(); i++)
      {
        out.write(((String) names.get(i) + "\n").getBytes());
      }
      out.write(("\n\n").getBytes());
      for (int i = 0; i < pics.size(); i++) {
        out.write(((String) pics.get(i) + "\n").getBytes());
        System.out.println("Fucking"+(String)pics.get(i));
        String str1[] = ((String)pics.get(i)).split("-|/|\\.");
        if(download((String) pics.get(i),str1[str1.length-2] +"--"+Integer.toString(i) +".jpg",pretext+dir) == false)
        {
          eout.write(((String) pics.get(i) + "\n").getBytes());
          continue;
        }
        System.out.println(str1[str1.length-2] + " is done");
      }
      out.write(("\n\n\nLink:\n").getBytes());
      for (int i = 0; i < linklist.size(); i++) {
        out.write(((String) linklist.get(i) + "\n").getBytes());
      }
      
      out.close();
      eout.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(pretext+" "+beginPic+" "+ lastPic);
    renameToNewFile(pretext+dir,pretext+beginPic+" "+ lastPic);
    System.out.println("Done");
    // D:\微云同步盘
  }
  public static String getHtmlContent(URL url, String encode) {
    StringBuffer contentBuffer = new StringBuffer();

    int responseCode = -1;
    HttpURLConnection con = null;
    try {
      con = (HttpURLConnection) url.openConnection();
      con.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载
      con.setConnectTimeout(60000);
      con.setReadTimeout(60000);
      // 获得网页返回信息码
      responseCode = con.getResponseCode();
      if (responseCode == -1) {
        System.out.println(url.toString() + " : connection is failure...");
        con.disconnect();
        return null;
      }
      if (responseCode >= 400) // 请求失败
      {
        System.out.println("请求失败:get response code: " + responseCode);
        con.disconnect();
        return null;
      }

      InputStream inStr = con.getInputStream();
      InputStreamReader istreamReader = new InputStreamReader(inStr, encode);
      BufferedReader buffStr = new BufferedReader(istreamReader);

      String str = null;
      while ((str = buffStr.readLine()) != null)
        contentBuffer.append(str);
      inStr.close();
    } catch (IOException e) {
      e.printStackTrace();
      contentBuffer = null;
      System.out.println("error: " + url.toString());
    } finally {
      con.disconnect();
    }
    return contentBuffer.toString();
  }

  public static String getHtmlContent(String url, String encode) {
    if (!url.toLowerCase().startsWith("http://")) {
      url = "http://" + url;
    }
    try {
      URL rUrl = new URL(url);
      return getHtmlContent(rUrl, encode);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void GetLink(String html,BufferedOutputStream out) throws Exception {
    // new一个URL对象
    // File input = new File("C:/Users/WeiDong/Desktop/jsouptest.txt");
    Document doc = Jsoup.parse(html);
    Elements links = doc.select("h2[class = entry-title] > a[href]");
    try {
      for (Element tmp : links) {
        System.out.println((String)tmp.attr("title")+"\n");
        System.out.println((String)tmp.attr("href")+"\n\n\n");
        out.write(((String)tmp.attr("title")+"\n").getBytes());
        out.write(((String)tmp.attr("href")+"\n\n\n").getBytes());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Done");
    // D:\微云同步盘
  }

  public static boolean download(String urlString, String filename, String savePath) {
    try{
    // 构造URL
    URL url = new URL(urlString);
    // 打开连接
    URLConnection con = url.openConnection();
    con.setRequestProperty("User-Agent",
        "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
    // 设置请求超时为5s
    con.setConnectTimeout(5 * 1000);
    // 输入流
    InputStream is = con.getInputStream();

    // 1K的数据缓冲
    byte[] bs = new byte[1024];
    // 读取到的数据长度
    int len;
    // 输出的文件流
    File sf = new File(savePath);
    if (!sf.exists()) {
      sf.mkdirs();
    }
    OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
    // 开始读取
    while ((len = is.read(bs)) != -1) {
      os.write(bs, 0, len);
    }
    // 完毕，关闭所有链接
    os.close();
    is.close();
    return true;
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public static int saveImage(String strUrl,String name) {

    URLConnection uc = null;

    try {

      URL url = new URL(strUrl);

      uc = url.openConnection();

      uc.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

      // uc.setReadTimeout(30000);

      // 获取图片长度

      // System.out.println("Content-Length:     "+uc.getContentLength());

      // 获取文件头信息

      // System.out.println("Header"+uc.getHeaderFields().toString());

      if (uc == null)

        return 0;

      InputStream ins = uc.getInputStream();

      byte[] str_b = new byte[1024];

      int byteRead = 0;

      String[] images = strUrl.split("/");

      String imagename = "D:/微云同步盘/" + name+".jpg";

      File fwl = new File(imagename);

      FileOutputStream fos = new FileOutputStream(fwl);

      while ((byteRead = ins.read(str_b)) > 0) {

        fos.write(str_b, 0, byteRead);

      }
      ;

      fos.flush();

      fos.close();

    } catch (Exception e) {

      e.printStackTrace();

      //log.error("获取网页内容出错");

    } finally {

      uc = null;

    }

    return 1;

  }

  public static boolean createDir(String destDirName) {  
    File dir = new File(destDirName);  
    if (dir.exists()) {  
        System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
        return false;  
    }  
    if (!destDirName.endsWith(File.separator)) {  
        destDirName = destDirName + File.separator;  
    }  
    //创建目录  
    if (dir.mkdirs()) {  
        System.out.println("创建目录" + destDirName + "成功！");  
        return true;  
    } else {  
        System.out.println("创建目录" + destDirName + "失败！");  
        return false;  
    }  
  }  
    
    public static boolean renameToNewFile(String src, String dest)  
    {
        File srcDir = new File(src);  
        boolean isOk = srcDir.renameTo(new File(dest));
        System.out.println("renameToNewFile is OK ? :" +isOk);  
        return isOk;  
    } 
}
