/**Assignment 4                 P4.java                Due August 19, 2015
 * login: cs11vcn
 * This program is for computing the monthly interest for savings account.
 * the basic savings and interest can be given by the user.
 **/
import java.util.Scanner;               //Scan input from keyboard
/**
* public class P4: Application driver class instantiates P4SavingsAcct 
* objects
*/
public class P4 
{
  /**
  * Instantiates P4SavingsAcct objects for beginning balances (method 
  * header comment)
  */
  public static void main(String[] args) {
    final int MON1 = 1;               //1st month to open account
    final double START_BAL = 2000;    //Starting balance
    final double START_INT_RATE = .5; //Starting interest rate
    boolean isFirstRun = true;        //No input on first run
    boolean errIntRate = false;       //Valid interest rate?
    boolean errSaveBal = false;       //Valid savings balance?
    int month;                        //Loop counter
    double saveBal;                   //New savings balance
    double intRate;                   //new interest rate
    
    Scanner scan = new Scanner(System.in);//Read input from keyboard
    String inputStr = new String();       //Repeat program
    //Overloaded double ctor
    P4SavingsAcct saver1 = new P4SavingsAcct(START_BAL);
    //No arg ctor, Start $1500
    P4SavingsAcct saver2 = new P4SavingsAcct();
    P4SavingsAcct.modifyIntRate(START_INT_RATE);//Starting interest rate
    
    do
    {
      if(!isFirstRun)  //Testing ctor, no input 1st run
      {
        //Test for the new savings balance
        do
        {
          saver1.setSaveBal(START_BAL);  //Start $2000 balance
          System.out.print("Enter new savings balance: ");
          saveBal = scan.nextDouble();
          errSaveBal = !saver2.setSaveBal(saveBal);
        }while(errSaveBal);
       
        
        //Test for the new interest
        do
        {
          System.out.print("Enter new interest rate: ");
          intRate = scan.nextDouble();
          //When the new input interest is invalid
          if(errIntRate = !saver2.modifyIntRate(intRate))
          {
            System.out.print("Error! Enter valid Range of ");
            System.out.println(P4SavingsAcct.MIN+"-"+P4SavingsAcct.MAX_RATE);
          }
          else
            saver1.modifyIntRate(intRate);
        }while(errIntRate);
      }
      isFirstRun = false;  //Allow input after 1st run
      
      System.out.println("\nMonthly balances for one year at " 
                         + saver1.getRate());
      
      System.out.printf( " %-6s%10s%12s\n", "MONTH", "SAVER 1", "SAVER 2" );
      System.out.printf( " %-6s%11s%12s\n", "=====", "========", "========" );
      System.out.printf( " %-6s%11s%12s\n", "Base", 
          saver1.toString(), saver2);
      
      for(month = MON1; month <= P4SavingsAcct.MON12; month++)
      {
        saver1.calcMonthlyInt();//Calaulate the new savings balance of saver1
        saver2.calcMonthlyInt();//Calaulate the new savings balance of saver2
        System.out.printf(" %-6d%10s%12s\n", month,saver1.toString(), saver2);
      }
      System.out.printf( " %-6s%11s%12s\n", "=====", "========", "========" );
      System.out.print("Want to calculate more savings (y/n)? ");


      inputStr = scan.next();
      //Whether the user want to continue
    }while(inputStr.charAt(0)!='n'&&inputStr.charAt(0)!='N');
    scan.close();
    System.exit(0);  //Exit program
  }

}
