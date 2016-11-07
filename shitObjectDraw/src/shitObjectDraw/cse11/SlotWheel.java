package shitObjectDraw.cse11;
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
/*
 * Name: SlotWheel Class
 * Purpose: this class holds the methods, constructors and variables
 * Parameters: None.
 * Return:  void.
 */

  public class SlotWheel extends ActiveObject implements ActionListener
 {
   private RandomDoubleGenerator p =
           new RandomDoubleGenerator(0,1);
            //randomly generate double numbers between 0 and 1.

   private JButton spin; //instance variables
   private int index;
   private int mTicks;
   private int cTicks; 
   private int theDelay;
   private Image[] image;
   private VisibleImage icon;
   private FramedRect frame;
   private Slots game;
   private static final double IMAGE_WIDTH = 110;
   private static final double IMAGE_HEIGHT = 145; 
   private static final double BUFFER1 = 0.0,
                               BUFFER2 = 0.25,
                               BUFFER3 = 0.5,
                               BUFFER4 = 0.75,
                               BUFFER5 = 1.0;
   private static final int FIRST = 0,
                            SECOND = 2,
                            THIRD = 4,
                            FOURTH = 6;
   private static final int NUM_OF_IMAGES = 8;
   private boolean begin;

/*
 * Name: SlotWheel constructor
 * Purpose: this class reference the parameters to 
 *          instance variables.
 * Parameters: imageArray, image array type, the array of images.
 *             ticks, int type, the number of ticks.
 *             delay, int type, the second of delay.
 *             x, double type, the x position of wheel.
 *             y, double type, the y position of wheel.
 *             canvas, DrawingCanvas type, the canvas to put wheels on.
 *             button, JButton type, the button will listen for user' action
 * Return:  void.
 */

   public SlotWheel(Image[] imageArray, int ticks, int delay, double x, 
                     double y,DrawingCanvas canvas,JButton button, Slots slot)
  {
   mTicks = ticks; //refer instance variables to instance variables
   cTicks = ticks;
   game = slot;
   image = imageArray;
   index = getWheelIndex(p.nextValue());
   icon = new VisibleImage(image[index],x,y,canvas); 
                    //new images on array at specific index
   frame = new FramedRect ( x-1,y-1,IMAGE_WIDTH+1, IMAGE_HEIGHT+1,canvas);
                    //the frame aroung the picture
   spin = button;
   theDelay = delay;
   spin.addActionListener(this);//allow button to listen to user action.
   start(); //begin run method
  }

/*
 * Name: getWheelIndex method
 * Purpose: get the index to show the whole images.
 * Parameters: random, double type, the random number from
 *             random double generator .
 * Return: i, int type, the index.
 */

   public int getWheelIndex(double random)
  {
   int i = 0; 

   if(random > BUFFER1 && random <= BUFFER2)
       //checking conditions for the random double
   {
    i = FIRST; //the first whole image index
   }

   else if(random > BUFFER2 && random <= BUFFER3)
   {
    i = SECOND; //the second whole image index 
   }

   else if(random > BUFFER3 && random <= BUFFER4)
   {
    i= THIRD; //the third whole image index
   }

   else if(random > BUFFER4 && random <= BUFFER5)
   {
    i= FOURTH; // the four whole image index
   }

    return i;
  }

/*
 * Name: actionPerformed method
 * Purpose: fires the event on the JButton
 * Parameters: evt, ActionEvent type, the event on the button 
 * Return: void.
 */
 
   public void actionPerformed(ActionEvent evt)
  {
   if(evt.getSource() == spin) //if it's clicked
   {
    begin = true; //change the boolean
    cTicks=mTicks; //reset the ticks
    index = getWheelIndex(p.nextValue());
            //get the index
   } 
  } 
   
/*
 * Name: run method
 * Purpose: calculate the number of ticks, and show pictures
 *          to create a spinning wheel effect.
 * Parameters: none
 * Return: void.
 */

   public void run()
  {
   while(true)
  {
   if( cTicks > 0 && begin) //while ticks is greater than 0
        synchronized(this) //execute the follow at same time
      {
       cTicks--; //decrement the ticks
       index = (index + 1) % NUM_OF_IMAGES;
                   //calculate the index
         
       if( cTicks == 0) //when tick reach 0
       {
        begin = false;
        game.winMan();
        if( index % 2 ==1) // if land on a odd index
        {
         index = (index +1 )% NUM_OF_IMAGES;//set index to even
        }
       }
      icon.setImage(image[index]); // show the image
     }
    
   pause(theDelay);//pause for the next wheel.
  }
 }

/*
 * Name: tickNumber method
 * Purpose: return thr ticks number.
 * Parameters: none
 * Return: void.
 */

   public int tickNumber()
  {
   return mTicks-cTicks;
  }

/*
 * Name: getImage method
 * Purpose: return the image's index.
 * Parameters: none
 * Return: void.
 */

   public int getImage()
  {
   return index;
  }

}//end of class