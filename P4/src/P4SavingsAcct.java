/**Assignment 4                 P4SavingsAcct.java       Due August 19, 2015
 * login: cs11vcn
 * This program is for computing the monthly interest for savings account.
 * the basic savings and interest can be given by the user.
 **/

/**
* public class P4SavingsAcct: Holds balance and interest rate data 
* members/methods
*/
public class P4SavingsAcct
{
private final double INIT_MIN_BAL = 1500; // Initial Balance for no arg ctor
final static int MON12 = 12; // 12 months annual term
final static double MIN = 0; // Minimum for rate and balance
final static double MAX_RATE = 5.5; // Maximum for interest rate
private static double annualIntRate = 0; // Int rate for all accounts of class
private double balance; // Balance for currrent account

/**
 * P4SavingsAcct()
 * No argument constructor
 **/
public P4SavingsAcct()
{
  balance = INIT_MIN_BAL;//Initialize the savings balance with INIT_MIN_BAL
}

/**
 * P4SavingsAcct(double bal)
 * Ctor initialize with double parameter
 */
public P4SavingsAcct(double bal)
{
  balance = bal;//Initialize the savings balance with bal
}

/**
 *public double getRate()
 *return annual interest rate
 */
public double getRate()
{
  return annualIntRate;
}

/**
 *public boolean setSaveBal()
 *returns true if a positive value and assigned into instance variables
 *else false and not assigned into instance variable
 */
public boolean setSaveBal(double bal)
{
  //Test for whether the input balance is valid
  if(bal<0)
  {
    //The balance is invalid
    System.out.println("Error! Enter Positive Balance.");
    return false;
  }
  balance = bal;
  return true;
}

/**
 *public boolean modifyIntRate()
 *Similar to setSaveBal except valid range is 0-5.5 for interest rate
 */
public static boolean modifyIntRate(double newRate)
{
  //Test for whether the interest is valid
  if(newRate>MAX_RATE || newRate<MIN)
  {
    //The interest is invalid
    return false;
  }
  else
  {
    //The interest is valid
    annualIntRate = newRate;
    return true;
  }
}

/**
 *public void calcMonthlyInt()
 *If balance is below $300, charge $3 monthly interest fee before
 *interest paid. Use a local final constant.
 *Calculates cumulative balance with annual interest rate divided 
 *by 12 months
 */
public void calcMonthlyInt()
{
  //Balance below CHARGED_LIMITS should be charged the interest fee
  final int CHARGED_LIMITS = 300;
  //The in interest fee should be charged
  final int CHARGED_FEE = 3;
  //The balance after being charged the $3 interest fee
  double charged_bal;
  if(balance<CHARGED_LIMITS)
  {
    //Calculate the new savings balance with a interest fee
    charged_bal=balance-CHARGED_FEE;
    balance = charged_bal+(charged_bal*annualIntRate/100)/MON12;
  }
  else
  {
    //Calculate the new savings balance without a interest fee
    balance += (balance*annualIntRate/100)/MON12;
  }
}

/**
 *returns a formatted "$%.2f" string, for balance
 */
public String toString( )
{
  return "$"+String.format("%.2f",balance);
}
}
