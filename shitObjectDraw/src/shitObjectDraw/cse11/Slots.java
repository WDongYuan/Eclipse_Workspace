package shitObjectDraw.cse11;
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Font;

/*
 * Name: Slot Class
 * Purpose: this class holds the methods, constructors and variables
 * Parameters: None.
 * Return:  void.
 */

  public class Slots extends WindowController implements ActionListener
 {
   private static final int NUM_OF_IMAGES = 8;//instance variable declarance
   private static final double IMAGE_WIDTH = 110;
   private static final double IMAGE_HEIGHT = 145;
   private static final double WHEELS_Y_OFFSET = 5;
   private static final double SPACE_BETWEEN_WHEELS = 5;
   private static final double X1 = 82,
                               X2 = X1+IMAGE_WIDTH+SPACE_BETWEEN_WHEELS,
                               X3 = X2+IMAGE_WIDTH+SPACE_BETWEEN_WHEELS,
                               Y = 5;
   private static final int WIN_X_OFFSET = 75;
   private static final int WIN_Y_OFFSET = 160;
   private static final int WHEEL_1_TICKS = 22;
   private static final int WHEEL_2_TICKS = WHEEL_1_TICKS + 6;
   private static final int WHEEL_3_TICKS = WHEEL_2_TICKS + 6;
   private static final int WHEEL_1_DELAY = 1000;
   private static final int WHEEL_2_DELAY = WHEEL_1_DELAY + 25;
   private static final int WHEEL_3_DELAY = WHEEL_2_DELAY + 25;
   private JButton spinButton = new JButton ("Click to Spin");
   private JLabel title = new JLabel("xX|Casino Royale|Xx");
   private JPanel botPanel = new JPanel(),
                 topPanel = new JPanel();
   private SlotWheel wheelA,wheelB,wheelC;
   private Image sungod,sungod2,bear,bear2,triton,triton2,library,library2;
   private Image[] imageArray;
   private int tickTimes1, tickTimes2, tickTimes3;
   private Text wintxt;
   private static final int WIN_FT_SIZE = 60;
   private static final int TITLE_FT_SIZE = 40;
   private static final int FRAME_WIDTH = 840; //width for the canvas
   private static final int FRAME_HEIGHT = 660;// height for the canvas


public static void main(String[] args) {
     new Acme.MainFrame(new Slots(), args,
                       FRAME_WIDTH, FRAME_HEIGHT);
   }



/*
 * Name: Begin method
 * Purpose: this class creates the interface of the slot machine
 * Parameters: None.
 * Return:  void.
 */
   public void begin()
  {
   wintxt = new Text("xX|WIN|Xx", WIN_X_OFFSET, WIN_Y_OFFSET,canvas);
             //create the win text.
   wintxt.setColor(Color.RED);
   wintxt.setFontSize(WIN_FT_SIZE);//set font size
   wintxt.setFont("Times"); //set font
   wintxt.setBold(true);

   botPanel.add(spinButton, SwingConstants.CENTER);
           //bot panel to hold button
   topPanel.add(title, SwingConstants.CENTER);
           //top panel to hold title
   title.setFont(new Font("Utopia", Font.BOLD, TITLE_FT_SIZE)); 
   title.setForeground(Color.gray);
   this.add(botPanel,BorderLayout.SOUTH);
   this.add(topPanel, BorderLayout.NORTH);
   this.validate();//validate
   spinButton.addActionListener(this);
     
   sungod =  getImage("sungod.jpg");//create images
   sungod2 = getImage("sungod-bear.jpg");
   bear= getImage("bear.jpg");
   bear2 = getImage("bear-triton.jpg");
   triton = getImage("triton.jpg");
   triton2 = getImage("triton-library.jpg");
   library = getImage("library.jpg");
   library2 = getImage("library-sungod.jpg");
   
   
   imageArray = new Image[]{sungod,sungod2,bear,bear2,
                            triton,triton2,library,library2};
                          //put images into an array

   wheelA = new SlotWheel(imageArray,WHEEL_1_TICKS,
                          WHEEL_1_DELAY,X1,Y,canvas,spinButton,this);
                          //create wheels
   wheelB = new SlotWheel(imageArray,WHEEL_2_TICKS,
                          WHEEL_2_DELAY,X2,Y,canvas,spinButton,this);
   wheelC = new SlotWheel(imageArray,WHEEL_3_TICKS,
                          WHEEL_3_DELAY,X3,Y,canvas,spinButton,this);
   wintxt.hide(); //hide the win text.
   
  }

/*
 * Name: actionPerformed method
 * Purpose: hide the win text when button is clicked
 * Parameters: ev, ActionEvent type, the event of the mouse.
 * Return:  void.
 */
  



   public void actionPerformed(ActionEvent ev)
  {
   wintxt.hide();
  }

/*
 * Name: winMan method
 * Purpose: check if the pictures are all the same, and display win text
 * Parameters: none.
 * Return:  void.
 */
  
   public void winMan()
  {
   tickTimes1=wheelA.tickNumber();//get the tick numbers
   tickTimes2=wheelB.tickNumber();
   tickTimes3=wheelC.tickNumber();
   if(tickTimes1==WHEEL_1_TICKS && tickTimes2== WHEEL_2_TICKS 
      && tickTimes3 == WHEEL_3_TICKS)//check if ticks are equal
   {

    if(wheelA.getImage() == wheelB.getImage() 
        && wheelB.getImage() == wheelC.getImage())
                     //check if image reference are the same
    {
     wintxt.show(); //show the win text.
    }
   }
  }
 
 }//end of class