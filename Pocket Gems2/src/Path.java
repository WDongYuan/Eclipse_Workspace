import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
public class Path {
  static public HashMap<Character,List<Character>> map = new HashMap<Character,List<Character>>();
  static public char begin = ' ';
  static public char end = ' ';
  static public Set<Character> visit = new HashSet<Character>();
  static public List<String> result = new ArrayList<String>();
  static public List<Character> cur = new ArrayList<Character>();
  public static void main(String[] args)
          throws FileNotFoundException, IOException {
      String filename = "C:\\Users\\WeiDong\\Lytmus\\question_1\\input_3.txt";
      if (args.length > 0) {
          filename = args[0];
      }
      
      List<String> answer = parseFile(filename);
      System.out.println(answer);
      
  }
  
  static List<String> parseFile(String filename)
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

      return parseLines(allLines);        
  }
  
  static List<String> parseLines(List<String> lines) {
      String[] sarr = lines.get(0).split(" ");
      begin = sarr[0].charAt(0);
      end = sarr[1].charAt(0);
      for(int i=1; i<lines.size(); i++){
        sarr = lines.get(i).split(" ");
        char c = sarr[0].charAt(0);
        if(!map.containsKey(c)){
          map.put(c, new ArrayList<Character>());
        }
        List<Character> l =map.get(c);
        for(int j=2;j<sarr.length; j++){
          char ch = sarr[j].charAt(0);
          l.add(ch);
          if(!map.containsKey(ch)){
            map.put(ch, new ArrayList<Character>());
          }
        }
      }
      visit.add(begin);
      Go(begin);
      for(String s: result){
        System.out.println(s);
      }
      return result;
  }
  static public void Go(char c){
    if(c==end){
      StringBuilder sb = new StringBuilder("");
      for(int i=0; i<cur.size(); i++){
        sb.append(cur.get(i));
      }
      sb.append(end);
      result.add(sb.toString());
      return;
    }
    cur.add(c);
    List<Character> l = map.get(c);
    for(int i=0; i<l.size(); i++){
      char tmp = l.get(i);
      if(visit.contains(tmp)){
        continue;
      }
      visit.add(tmp);
      Go(tmp);
      visit.remove(tmp);
    }
    cur.remove(cur.size()-1);
  }
  /*static public HashMap<Character, List<Character>> map = new HashMap<Character,List<Character>>();
  static public Set<Character> visit = new HashSet<Character>();
  static public List<Character> cur = new ArrayList<Character>();
  static public List<String> result = new ArrayList<String>();
  static public char head = ' ';
  static public char end = ' ';
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    File file = new File("C:\\Users\\WeiDong\\Desktop\\graph.txt");
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String tmp = null;
      tmp = reader.readLine();
      String[] arr = tmp.split(" ");
      head  = arr[0].charAt(0);
      end = arr[1].charAt(0);
      while ((tmp = reader.readLine()) != null) {
        System.out.println(tmp);
        arr = tmp.split(" ");
        char c = arr[0].charAt(0);
        if(!map.containsKey(c)){
          map.put(c, new ArrayList<Character>());
        }
        List<Character> l = map.get(c);
        for(int i=2; i<arr.length; i++){
          char ch = arr[i].charAt(0);
          l.add(ch);
          if(!map.containsKey(ch)){
            map.put(ch, new ArrayList<Character>());
          }
        }
      }
      for(Entry<Character, List<Character>> en: map.entrySet()){
        List<Character> l = en.getValue();
        System.out.print(en.getKey()+": ");
        for(Character c:l){
          System.out.print(c+" ");
        }
        System.out.println();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    visit.add(head);
    Go(head);
    for(String s: result){
      System.out.println(s);
    }
  }
  public static void Go(char c){
    if(c==end){
      StringBuilder sb = new StringBuilder("");
      for(int i=0; i<cur.size(); i++){
        sb = sb.append(cur.get(i));
      }
      sb.append(end);
      result.add(sb.toString());
      return; 
    }
    cur.add(c);
    List<Character> l = map.get(c);
    for(int i=0; i<l.size(); i++){
      char tmp = l.get(i);
      if(visit.contains(tmp)){
        continue;
      }
      visit.add(tmp);
      Go(tmp);
      visit.remove(tmp);
    }
    cur.remove(cur.size()-1);
  }*/
}
