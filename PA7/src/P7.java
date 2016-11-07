/**Assignment 7              P7.java     Due Wednesday September 2, 2015
 * login: cs11vcn
 * This program will use command line arguments as the file name of the
 * file which will be opened. And it will also use the java exception
 * handling to check the user input and the data from the given file.
 **/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

/**
 * class P7
 * Read the file name from the command line. Open the file and read the
 * data in the file. It will use the java exception handling at the same
 * time.
 */
class P7
{
  private static final int ASIZE = 3; // Array size
  private static int []aTri = new int [ASIZE]; // 3 elements of triangle sides
  /**
   * public static void main(String [] args) throws IOException
   * Read the file and handle the exceptions. Also throws an IOException.
   */
  public static void main(String [] args) throws IOException
  {
    final int MAX = 100; //The maximum of the side.
    final int MIN = 1;   //The minimum of the side.
    //Whether the one side of the triangle is out of range
    boolean outOfRange = false;
    //Whether sum of 2 sides is less than 3rd side.
    boolean notTriangle = false;
    String inputStr = ""; // String for input line
    String [] s ;         // Array of strings for each word
    BufferedReader in;    // readLine() input from file
    
    //Check whether the format of user input is right
    if(args.length == 0)
    {
      System.err.println("Usage: java P7 [file].");
      System.exit(0);
    }
    try // FileNotFoundException
    { 
      in = new BufferedReader(new FileReader(args[0]));
      while ( ( inputStr = in.readLine() ) != null )// Read entire line
      {
        outOfRange = false;
        notTriangle = false;
        System.out.println();
        System.out.print("Input:     "+inputStr);
        try
        {
          s = inputStr.split(" "); // Parse into tokens
          
          //Check whether the given data has less than 3 elements.
          if(s.length < ASIZE)
          {
            System.err.println();
            System.err.println("           ERROR! Entered "
                + "less than 3 sides. Enter again.");
            continue;
          }
          
          for(int j=0 ; j < s.length ; ++j ) // Traverse array
          {
              aTri[j] = Integer.parseInt(s[j]); // Convert triangle side
              
              //Check whether the side is out of range or whether
              //sum of 2 sides is greater than 3rd side before checking
              //whether the index of array is out of bounds.
              if(j == ASIZE-1)
              {
                //Check whether one side of the triangle is out of range.
                for(int i=0; i<aTri.length; i++)
                {
                  if(aTri[i]<MIN||aTri[i]>MAX)
                  {
                    System.err.println();
                    System.err.println("           ERROR! "
                        + "Out of range 1-100. Enter again.");
                    outOfRange = true;
                    break;
                  }
                }
                
                //Check whether sum of 2 sides is greater than 3rd side
                //in the triangle.
                for(int i=0; i<aTri.length; i++)
                {
                  if(aTri[i%ASIZE]+aTri[(i+1)%ASIZE]<=aTri[(i+2)%ASIZE])
                  {
                    if(outOfRange == true)
                    {
                      System.err.println();
                      System.err.print("Input:     "+inputStr);
                    }
                    System.err.println();
                    System.err.println("           ERROR! "
                        + "Sum of 2 sides > 3rd side.");
                    notTriangle = true;
                    break;
                  }
                }
                
                //If out of range or not an triangle, than read next line.
                if(outOfRange || notTriangle)
                {
                  break;
                }
              }
          }
          if(outOfRange || notTriangle)
          {
            continue;
          }
          
          
          
          
          System.out.println();
          //Show the triangle.
          System.out.println("Triangle:  "+aTri[0]+
              " + "+aTri[1]+" + "+aTri[2]);
          //Calculate and show the perimeter of the triangle. 
          calcPerimeter();
          System.out.println();
        }
        //Handle ArrayIndexOutOfBoundsException.
        catch(ArrayIndexOutOfBoundsException e)
        {
          System.err.println();
          System.err.println("           Entered more than 3 sides.");
          System.err.println("           "
              + "First 3 sides accepted. Continuing ...");
          System.err.println("Triangle:  "+aTri[0]+
              " + "+aTri[1]+" + "+aTri[2]);
          //Calculate and show the perimeter of the triangle. 
          calcPerimeter();
        }
        
        //Handle NumberFormatException.
        catch(NumberFormatException e)
        {
          System.err.println(" are not valid sides of a triangle.");
          e.printStackTrace();
        }
      }
    }
    //Handle FileNotFoundException
    catch( FileNotFoundException e)
    {
      System.err.println("File \""+args[0]+"\" is not found.");
      System.err.println();
    }
    //Handle catch-all exception
    catch( Exception e)
    {
      System.err.println(e);
      e.printStackTrace();
    }
  }
  
  /**
   * public static void calcPerimeter()
   * Calculate and show the perimeter of the triangle. 
   */
  public static void calcPerimeter()
  {
    int perimeter;
    perimeter = aTri[0]+aTri[1]+aTri[2];
    System.out.println("Perimeter: "+perimeter);
  }
}