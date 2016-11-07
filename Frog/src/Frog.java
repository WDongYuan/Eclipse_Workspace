
import java.util.*;
import java.util.LinkedList;
public class Frog {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    //int[] arr = {1,-1,0,2,3,5};
    int[] arr = {1,2,3,4,5,6};
    int D = 3;
    System.out.println(Monkey(arr,D));
    return;
  }
  public static int Monkey(int[] arr, int D){
    int[] time = arr;
    int len = arr.length;
    if(D>len){
      return 0;
    }
    int[] left = new int[len];
    int[] right = new int[len];
    int prepos = -1;
    for(int i=0; i<len; i++){
      if(arr[i]==-1){
        left[i] = -1;
        right[i] = -1;
        continue;
      }
      if(i-prepos>D){
        return -1;
      }
      left[i] = prepos;
      if(prepos!=-1){
        right[prepos] = i;
      }
      prepos = i;
    }
    if(len-prepos>D){
      return -1;
    }
    if(prepos==-1){
      if(len>=D){
        return -1;
      }
      return 0;
    }
    right[prepos]= len;
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int maxtime = 0;
    for(int i=0; i<time.length;i++){
      if(time[i]!=-1){
        map.put(time[i],i);
        maxtime = maxtime<time[i]?time[i]:maxtime;
      }
    }
    boolean pass = true;
    int min = maxtime;
    while(pass && maxtime>=0){
      if(!map.containsKey(maxtime)){
        maxtime--;
        continue;
      }
      int pos = map.get(maxtime);
      int posleft = left[pos];
      int posright = right[pos];
      if(posright-posleft>D){
        min = maxtime;
        pass = false;
        break;
      }
      if(posleft!=-1){
        right[posleft] = posright;
      }
      if(posright!=len){
        left[posright] = posleft;
      }
      maxtime--;
    }
    return min;
  }

}
