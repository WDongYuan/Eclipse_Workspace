/**Assignment 3                 P3.java                Due August 15, 2015
 * login: cs11vcn
 * This program sorts Strings which are input by the user using comparision
 *  methods into 1-D and 2-D arrays.
 **/
import java.util.Scanner;     //Read sentence from keyboard
public class P3 {

  /**
   * main() reads input and displays the result in 1-D array after calling the
   * method sort1D(). Then calls the method sort2D() to display the result in
   * 2-D array. 
   **/
  public static void main(String[] args) {
    String sentence;                          //Read line
    Scanner scan = new Scanner(System.in);
    
    System.out.print("Enter your words to be sorted (exit ^D): ");
    do
    {
      sentence = scan.nextLine();             //Read entire line
      String words[] = sentence.split(" +");  //Allocate words input
      
      sort1D(words);                          //Sort 1-D array of Strings
      
      System.out.print("1-D Sorted: ");
      for(int i=0; i<words.length;i++)
        System.out.print(words[i]+" ");
      System.out.println();
      
      sort2D(words);                          //Sort 2-D array of Strings    
      
      System.out.print("Enter your words to be sorted (exit ^D): ");
    }while(scan.hasNext());                   //Loop until no more input lines
    System.exit(0);                           //Terminates current JVM
    }

  /**
   * sort1D(String []w)
   * @param w
   * Sort words in sentence (case insensitive)
   **/
  public static void sort1D(String []w)
  {
    for(int i=0; (i<w.length-1) && (w[i]!=null); i++)
      for(int j=w.length-1; i<j && (w[j]!=null); j--)
        //Sort words - case insensiive
        /**
         * Bubble Sort
         * For two adjacent elements. If the latter one is smaller than the
         * former one, then  swap their positions.
         **/
        if(w[j-1].compareToIgnoreCase(w[j]) > 0)
        {
          String tmp = w[j];//tmp is a temporary variable for swaping
          w[j] = w[j-1];
          w[j-1] = tmp;
        }
  }
  
  /**
   * sort2D(String []sw)
   * @param sw
   * Sort into 2-D table of 26 rows. Each row represents words that begin with
   * same 1st letter.
   */
  public static void sort2D(String [] sw)
  {
    //26 letters in the alphabet - # of rows
    final int ALPHA = 26;
    
    //Sequence to next alpha letter
    boolean nextAlpha = false;
    char A = 'A';  //1st letter of alphabet uppercase
    char a = 'a';  //1st letter of alphabet lowercase
    int col;       //Number of words start with letter
    //Allocate 26 rows for each alpha letter
    String a2D[][] = new String [ALPHA][];
    
    //Initialize a2D with nulls
    for(int i=0; i<ALPHA; i++)
      a2D[i] = null;
    
    col=0; //initialize col at the beginning of the for loop
    //Allow 26 elements for alphabet
    for(int i=0,j=0; j<=a2D.length; j++)
    {
      if(col>0)
        //Alloc row with # words starts with letter
        a2D[j-1] = new String[col];
      
      nextAlpha = false;
      
      //Traverse sw[] and put its words in the corresponding line in a2D
      for(col=0; (i<sw.length) && !nextAlpha; i++)
      {
        //No words start with letter, skip to next alpha
        if(sw[i]==null)
        {
          nextAlpha = true;
          break;
        }
        
        /**
         * If 1st letter of sw[i] matches corresponding letter for a2D[j],
         * put it in a2D[j]
         */
        if(sw[i].charAt(0) == A+j || sw[i].charAt(0) == a+j)
        {
          col++;  //move to next words in sw
          nextAlpha = false;
        }
        else
          break;
      }
    }
    
    col = 0;
    /**
     * Nested for loop to assign from sw[] into a2D delimited by 
     * length of each row
     **/
    for(int i=0; i<ALPHA; i++)  //traverse each row of array a2D
    {
      /* *
       * put all words of sw beginning with the same letter in the same 
       * line of a2D
       * */
      for(int j=0; a2D[i]!=null && j<a2D[i].length; j++)
      {
        if(sw[col] == null)
        {
          col++;
        }
        a2D[i][j] = sw[col];
        col++;
        //print the word after putting it in a2D
        System.out.println("a2D["+i+"]["+j+"]"+": "+a2D[i][j]);
        if(j == a2D[i].length-1)
          System.out.println();
      }
    }
  }

}
