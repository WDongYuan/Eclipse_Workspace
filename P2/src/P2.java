/**Assignment 2                	P2.java                Due August 12, 2015
 * login: cs11vcn
 * This program displays the total price and unit price of a "ROOT BEER
 * FLOAT" order.
 **/
import java.util.Scanner;
public class P2 {
  private static final double HALF_PINT$$  = 1.89;
  private static final double PINT$$       = 3.19;
  private static final double QUART$$      = 4.59;
  
  //ounce size of type A
  private static final int    OZ_A         = 8;
  //ounce size of type B
  private static final int    OZ_B         = 16;
  //ounce size of type C
  private static final int    OZ_C         = 32;
  
  //the total number of the drinks
  private static int          totalDrink   = 0;
  //the number of the free drinks
  private static int          freeDrink    = 0;
  //the price to be paid
  private static double       myPrice      = 0;
  //the money has been saved
  private static double       savings      = 0;
  //whether there is a free drink
  private static boolean      hasFreeDrink = false;
  
  
  public static void main(String args[])
  {
	char choice;
    boolean invalidTypeDrink = false;
    boolean invalidNumDrinks = false;
    Scanner scan = new Scanner(System.in);
    String inputStr = null;
    char type;
    int num;
    unitPrice();
    do
    {
      menu();
      do{
        System.out.print("Enter type of drink (A B or C):   ");
    	invalidTypeDrink = false;
        inputStr = scan.next();
        type = inputStr.charAt(0);
        
        
        if(inputStr.length()!=1)
        {
          System.out.println("ERROR: only - \"A B C\"");
          invalidTypeDrink = true;
        }
        else if(type!='A' && type!='a'
        		&& type!='B' && type!='b'
        		&& type!='C' && type!='c')
        {
          System.out.println("ERROR: only - \"A B C\"");
          invalidTypeDrink = true;
        }
        else
        {
          invalidTypeDrink = false;
        }
      }while( invalidTypeDrink == true);
      
      do
      {
    	System.out.print("Enter Quantity(1-5):              ");
    	invalidNumDrinks = false;
    	num = scan.nextInt();
    	
    	if(num >5 || num < 1)
    	{
    	  invalidNumDrinks = true;
    	  System.out.println("ERROR only \"1-5\"!");
    	}
      }while(invalidNumDrinks == true);
      
      if(num == 1)
      {
    	myPrice +=calcPrice(type);
      }
      else
      {
    	myPrice +=calcPrice(type, num);
      }
      
      savings += freeDrink(type);
      
      System.out.print("Want more Root Beer Floats (y/n)? ");
      inputStr = scan.next();
      choice = inputStr.charAt(0);
      
    }while(choice != 'n' && choice != 'N');
    
    payDrinks(myPrice, savings);
    
    System.exit(0);
  }
  
  /**
   * unitPrice()
   * Calculate the price per ounce and displays these values
   */
  public static void unitPrice(){
	double ozPriceA = HALF_PINT$$ / OZ_A;
	double ozPriceB = PINT$$ / OZ_B;
	double ozPriceC = QUART$$ / OZ_C;
	System.out.println("****************************"+
	                   "****************************");
	System.out.printf("PRICE/OZ:  Half-Pint:$%.2f  ",ozPriceA);
	System.out.printf("Pint:$%.2f  ",ozPriceB);
	System.out.printf("Quart:$%.2f\n",ozPriceC);
	System.out.println("****************************"+
                       "****************************");
  }
  
  /**
   * menu()
   * Display the given types and prices of drink
   */
  public static void menu()
  {
	System.out.println("  ******************ROOT BEAR FLOAT"+
                       "*******************");
	
	System.out.println("          TYPE                 "+
                       "          PRICE        ");
	
	System.out.println("        ===============        "+
                       "      ===============");
	
	System.out.printf("         A) HALF_PINT              "+
                       "        %.2f           \n", HALF_PINT$$);
	
	System.out.printf("         B) PINT                   "+
                      "        %.2f       \n", PINT$$);
	
	System.out.printf("         A) QUART                  "+
                      "        %.2f       \n", QUART$$);
	
	System.out.println("  *******************************"+
                       "*********************");
  }
  
  /**
   * calcPrice()
   * @param typeDrink
   * Calculate the price of drink order for only one drink
   */
  public static double calcPrice (char typeDrink)
  {
	totalDrink ++;
	if(((totalDrink) % 6) == 0)
	{
	  hasFreeDrink = true;
      return  0;
	}
	else
	{
	  hasFreeDrink = false;
	  switch (typeDrink)
	  {
	  case 'a' :
	  case 'A' :
		return HALF_PINT$$;
	  case 'b' :
	  case 'B' :
		return PINT$$;
	  case 'c' :
	  case 'C' :
		return QUART$$;
	  default :
		return 0;
	  }
	}
  }
  
  /**
   * calcPrice()
   * @param typeDrink
   * @param numDrink
   * Calculate the price of dirnk order for more than one drink
   */
  public static double calcPrice (char typeDrink, int numDrink)
  {
	double perDrink$$ = 0;
	double price = 0;
	switch (typeDrink)
	  {
	  case 'a' :
	  case 'A' :
		perDrink$$ = HALF_PINT$$;
		break;
	  case 'b' :
	  case 'B' :
		perDrink$$ = PINT$$;
		break;
	  case 'c' :
	  case 'C' :
		perDrink$$ = QUART$$;
		break;
	  default :
		perDrink$$ = 0;
		break;
	  }
	
	if(((totalDrink % 6) + numDrink) >= 6)
	{
    	System.out.println("************************************HAS FREE DRINK");
      hasFreeDrink = true;
	  price = perDrink$$ * (numDrink-1);
	  totalDrink += numDrink; 
	  return price;
	}
	else
	{
	  hasFreeDrink = false;
	  price = perDrink$$ * numDrink;
	  totalDrink += numDrink; 
	  return price;
	}
  }
  
  /**
   * freeDrink()
   * @param typeDrink
   * Determine if a free drink is awarded, adjust counters, and 
   * return the price of all these free drinks. 
   */
  public static double freeDrink(char typeDrink)
  {
	if(hasFreeDrink == false)
	{
	  return 0;
	}
	else
	{
	  freeDrink ++;
	  hasFreeDrink = false;
	  switch (typeDrink)
	  {
	  case 'a' :
	  case 'A' :
	    return HALF_PINT$$;
	  case 'b' :
	  case 'B' :
	    return PINT$$;
	  case 'c' :
	  case 'C' :
	    return QUART$$;
	  default :
	    return 0;
      }
	}
  }
  
  /**
   * payDrinks()
   * @param price
   * @param moneySaved
   * Display the total bill, number of free drinks, and amounts saved
   */
  public static void payDrinks(double price, double moneySaved)
  {
	System.out.printf("Total:  $%.2f included %d FREE drink(s)\n", price, freeDrink);
	System.out.printf("You SAVED $%.2f", savings);
  }
  
}
