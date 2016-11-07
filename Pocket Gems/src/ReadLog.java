import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ReadLog {

  public static void main(String[] args)
      throws FileNotFoundException, IOException {
  String filename = "C:\\Users\\WeiDong\\Lytmus\\question_2\\input_3.txt";
  if (args.length > 0) {
      filename = args[0];
  }

  String answer = parseFile(filename);
  System.out.println(answer);
}

static String parseFile(String filename)
      throws FileNotFoundException, IOException {
  /*
   *  Don't modify this function
   */
  BufferedReader input = new BufferedReader(new FileReader(filename));
  List<String> allLines = new ArrayList<String>();
  String line;
  while ((line = input.readLine()) != null) {
      allLines.add(line);
  }
  input.close();

  return parseLines(allLines.toArray(new String[allLines.size()]));
}

static String parseLines(String[] lines) {
  long start = -1;
  long conn = -1;
  long alltime = 0;
  long conntime = 0;
  SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
  try{
    for(int i=0; i<lines.length; i++){
      String[] log = lines[i].split("( :: )|\\(|\\)");
      Date date = format.parse(log[1]);
      long time = date.getTime();
      if(log[3].equals("START") && start==-1){
        start = time;
      }
      else if(log[3].equals("CONNECTED") && conn==-1){
        conn = time;
      }
      else if(log[3].equals("DISCONNECTED") && conn!=-1){
        conntime = conntime+(time-conn);
        conn = -1;
      }
      else if(log[3].equals("SHUTDOWN") && start!=-1){
        alltime = alltime+(time-start);
        start = -1;
        if(conn!=-1){
          conntime = conntime+(time-conn);
          conn = -1;
        }
      }
    }
  }
  catch(ParseException e){
    e.printStackTrace();
  }
  return String.valueOf(conntime*100/alltime)+"%";
}
  /*public static void main(String[] args) {
    // TODO Auto-generated method stub
    File file = new File("C:\\Users\\WeiDong\\Desktop\\Log.txt");
    BufferedReader reader = null;
    List<String> l = new ArrayList<String>();
    try {
      reader = new BufferedReader(new FileReader(file));
      String tmp = null;
      while ((tmp = reader.readLine()) != null) {
        System.out.println(tmp);
        l.add(tmp);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    long start = -1;
    long conn = -1;
    long alltime = 0;
    long contime = 0;
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
    try {
      for (int i = 0; i < l.size(); i++) {
        String[] log = l.get(i).split("( :: )|\\(|\\)");
        for (int j = 0; j < log.length; j++) {
          System.out.println("-" + log[j]);
        }
        Date date = format.parse(log[1]);
        long time = date.getTime();
        if (log[3].equals("START") && start == -1) {
          start = time;
        } else if (log[3].equals("CONNECTED")) {
          conn = time;
        } else if (log[3].equals("DISCONNECTED") && conn != -1) {
          contime = contime + (time - conn);
          conn = -1;
        } else if (log[3].equals("SHUTDOWN") && start!=-1) {
          alltime = alltime + (time - start);
          start = -1;
          if(conn!=-1){
            contime = contime+(time-conn);
          }
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    System.out.println(String.valueOf(contime*100/alltime)+"%");
  }*/

}
