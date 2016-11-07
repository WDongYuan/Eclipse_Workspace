/**Assignment 1                	P1.java                Due August 8, 2015
 * login: cs11vcn
 * This program displays a table of 7 years of federal taxes and earned income.
 **/
import java.util.Scanner;                               //Scan input from keyboard
public class P1 {
	//main() accepts numerical input and prints table of taxes, 403b, and income
	public static void main(String[] args){
        final int       MIN_WAGE         = 1;           //Minimum wages/hour
        final int       HRS_WEEK         = 45;        	//45 work hours/week
        final int       MAX_PAY          = 500;        	//Paid MAX $500/hr
        final int       WEEKS_IN_YR      = 52;          //Paid 52 weeks in a year
        final int       YEAR7        	 = 7;	        //7 years on a job
        final double 	HR_RAISE         = .05;         //5% annual hourly raise
        
        final double 	TAX_RATE1        = .10;         //10% 0~$9225 income taxed by FED
        final double 	TAX_RATE2        = .15;         //15% $9225-$37450 taxed by FED
        final double 	TAX_RATE3        = .25;         //25% to $90750 taxed by FED
        final double 	TAX_RATE4        = .28;         //28% to $189300 taxed by FED
        final double 	TAX_RATE5        = .33;         //33% to $411,500 taxed by FED
        final double 	TAX_RATE6        = .35;         //35% < $413,200 taxed by FED
        final double 	TAX_RATE7        = .396;        //39.6% > $413,200 taxed by FED
        
        final double 	TAX_BRACK1       = 9225;        //10% 0~$9225 income taxed by FED
        final double 	TAX_BRACK2       = 37450;       //15% $9225-$37450 taxed by FED
        final double 	TAX_BRACK3       = 90750;       //25% to $90750 taxed by FED
        final double 	TAX_BRACK4       = 189300;      //28% to $189300 taxed by FED
        final double 	TAX_BRACK5       = 411500;      //33% to $411,500 taxed by FED
        final double 	TAX_BRACK6       = 413200;      //35% < $413,200 taxed by FED
        
        final double 	B403_RATE1       = .10;         //10% defer retirement plan
        final double 	B403_RATE2       = .15;         //15% defer retirement plan
        final double 	B403_RATE3       = .20;         //20% defer retirement plan
        final double 	B403_MAX         = 18000;       //Max contribution $17000 in 2011
        
        boolean inputErr = true;                        //Input error flag
        char	choice;                                 //Repeat loop
        int        yr   = 1;                            //Year #1
        double	hrWage	= 0;                            //Input hourly wage
        double	gross	= 0;                            //Gross income for the year
        double	inc     = 0;                            //Reduced gross income for the year
        double	taxRate	= 0;                            //Federal Tax Rate from the bracket
        double	fedTax	= 0;                            //Federal taxes paid
        double	net     = 0;                            //Net wages after taxes
        double	b403	= 0;                            //403B tax deferred retirement income
        
        Scanner	scan = new Scanner(System.in);          //Read input form keyboard
        String	inputStr = null;                        //Input string
        
        do                                              //do loop repeats entire program
        {
        	inputErr = true;                            //initialize the inputErr at the beginning of every do loop
        	while( inputErr == true )                   //while loop for hourly wage input ...
        	{
                System.out.print("\nEnter hourly wage to display 2015 income: ");
                hrWage = scan.nextDouble();
                if( hrWage >= MIN_WAGE && hrWage <= MAX_PAY )
                {
                	inputErr = false;
                }
                else
                {
                	inputErr = true;
                	System.out.println("ERROR: Enter a number (1-500) for hourly wages!");
                }
        	}
        	gross = hrWage * HRS_WEEK * WEEKS_IN_YR;    //calculate the gross
        	
        	

    		System.out.println("******************************EARNED INCOME******************************");
    		System.out.println("Year        Gross_Earnings       403B       FedTax     Net_Earnings");
    		System.out.println("=========================================================================");
        	for( yr = 1; yr <= YEAR7; ++yr)            //for loop for calculating the entries in the columns
        	{
        		if( yr != 1)
        		{
        			gross += gross * HR_RAISE;          //yearly raise in hourly wage
        		}
        		
        		
        		
        		switch ( gross < 30000? 0 : (gross <= 79999? 1 : 2)) //switch statement for calculating the 403b tax
            	{
            	case 0 :                                //gross < 30000
            		b403 = gross * B403_RATE1;
            		break;
            	case 1 :                                //30000 <= gross <= 79999
            		b403 = gross * B403_RATE2;
            	    break;
            	case 2 :
            		b403 = gross * B403_RATE3;          //gross > 79999
            		if(b403 > B403_MAX) 
                		b403 = B403_MAX;
            		break;
            	default :
            		break;
            	}
            	
            	
        		
        		inc = gross - b403;
        		
        		fedTax = 0;                             //initialize the fedTax for every do loop
        		
        		
        		//if else statement for calculating the federal tax
        		if ( inc >= TAX_BRACK1 )                                                //taxable gross earning larger than TAX_BRACK1
        		{
        			fedTax += TAX_BRACK1 * TAX_RATE1;                                   //10% 0~$9225 income taxed by FED
        			if( inc >= TAX_BRACK2 )                                             //taxable gross earning larger than TAX_BRACK2
        			{
        				fedTax += (TAX_BRACK2 - TAX_BRACK1) * TAX_RATE2;                //15% $9225-$37450 taxed by FED
        				if( inc >= TAX_BRACK3 )                                         //taxable gross earning larger than TAX_BRACK3
        				{
        					fedTax += (TAX_BRACK3 - TAX_BRACK2) * TAX_RATE3;            //25% to $90750 taxed by FED
        					if( inc >= TAX_BRACK4 )                                     //taxable gross earning larger than TAX_BRACK4
            				{
            					fedTax += (TAX_BRACK4 - TAX_BRACK3) * TAX_RATE4;        //28% to $189300 taxed by FED
            					if( inc >= TAX_BRACK5 )                                 //taxable gross earning larger than TAX_BRACK5
                				{
                					fedTax += (TAX_BRACK5 - TAX_BRACK4) * TAX_RATE5;    //33% to $411,500 taxed by FED
                					if( inc >= TAX_BRACK6 )                             //taxable gross earning larger than TAX_BRACK6
                    				{
                    					fedTax += (TAX_BRACK6 - TAX_BRACK5) * TAX_RATE6;//35% < $413,200 taxed by FED
                    					fedTax += (inc - TAX_BRACK6) * TAX_RATE7;       //39.6% > $413,200 taxed by FED
                    				}
                        			else
                            		{
                            			fedTax += (inc - TAX_BRACK5) * TAX_RATE6;       //35% < $413,200 taxed by FED
                            		}
                				}
                    			else
                        		{
                        			fedTax += (inc - TAX_BRACK4) * TAX_RATE5;           //33% to $411,500 taxed by FED
                        		}
            				}
                			else
                    		{
                    			fedTax += (inc - TAX_BRACK3) * TAX_RATE4;               //28% to $189300 taxed by FED
                    		}
        				}
            			else
                		{
                			fedTax += (inc - TAX_BRACK2) * TAX_RATE3;                   //25% to $90750 taxed by FED
                		}
        			}
        			else
            		{
            			fedTax += (inc - TAX_BRACK1) * TAX_RATE2;                       //15% $9225-$37450 taxed by FED
            		}
        		}
        		else
        		{
        			fedTax += inc * TAX_RATE1;                                          //10% 0~$9225 income taxed by FED
        		}
        		
        		net = gross - b403 - fedTax;                                            //calculating net wages after taxes
        		
                
                //for loop ...
                System.out.printf("%4d%18.2f%16.2f%12.2f", yr, gross, b403, fedTax);
                System.out.printf("%14.2f\n", net);
        	}
    		System.out.println("=========================================================================");
            System.out.printf("\nWant to calculate another tax table (y/n)? ");
            inputStr = scan.next();                             //Read and assign to string
            choice = inputStr.charAt(0);                        //Assign 1st character
        }while(choice != 'n' && choice != 'N');
        
        scan.close();                                           //Close the scanner
        System.exit(0);                                         //Close connection
	}

}
