package pa7;
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Font;

public class Spin100Controller extends WindowController implements ActionListener
{


   private static final int NUM_OF_IMAGES = 20;
   private static final double IMAGE_WIDTH = 185;
   private static final double IMAGE_HEIGHT = 99;
   private static final int WHEEL_DELAY = 1;

   private static final int WHEEL_Y_COORD = 10;
   private static final int TEXT1_X_COORD = 10;
   private static final int TEXT2_X_COORD = 830;
   private static final int TEXT3_X_COORD = 420;
   private static final int GAME_OVER_Y_COORD = 5; 

   private static final int FRAME_WIDTH = 840; //width for the canvas
   private static final int FRAME_HEIGHT = 660;// height for the canvas
   private static final int TITLE_FT_SIZE = 24;

   private static int score1 = 0;
   private static int score2 = 0;

   private Text text1;
   private Text text2;
   private Text text3;
   private JButton spinButtonP1 = new JButton ("Click to Spin P1");
   private JButton spinButtonP2 = new JButton ("Click to Spin P2");
   private JButton finishButtonP1 = new JButton ("Finish Player1");
   private JButton finishButtonP2 = new JButton ("Finish Player2");
   private JButton restartButton = new JButton ("Restart");
   private JLabel spinTitle = new JLabel("Spin100");
   private JLabel player1Title = new JLabel("Player 1's score is " + score1);
   private JLabel player2Title = new JLabel("Player 2's score is " + score2);
   private JPanel botPanel = new JPanel(),
                  topPanel = new JPanel(new GridLayout(3,1)),
                  topPanel1 = new JPanel(),
                  topPanel2 = new JPanel(),
                  topPanel3 = new JPanel(),                
                  iconPanel = new JPanel();

   
   private Image [] imageArray;


   public static void main(String[] args) {
     new Acme.MainFrame(new Spin100Controller(), args,
                       FRAME_WIDTH, FRAME_HEIGHT);
   }


  public void begin(){
    iconPanel.add(spinButtonP1);
    iconPanel.add(finishButtonP1);
    iconPanel.add(restartButton);
    iconPanel.add(spinButtonP2);
    iconPanel.add(finishButtonP2);
    botPanel.add(iconPanel);


    topPanel1.add(spinTitle);
    topPanel2.add(player1Title);
    topPanel3.add(player2Title);

    topPanel.add(topPanel3,SwingConstants.CENTER);
    topPanel.add(topPanel2,SwingConstants.CENTER);
    topPanel.add(topPanel1,SwingConstants.CENTER);

    spinTitle.setFont(new Font("Comic Sans MS", Font.BOLD, TITLE_FT_SIZE));
    this.add(botPanel, BorderLayout.SOUTH);
    this.add(topPanel, BorderLayout.NORTH);


    this.validate();

    spinButtonP1.addActionListener(this);
    spinButtonP2.addActionListener(this);
    finishButtonP1.addActionListener(this);
    finishButtonP2.addActionListener(this);
    restartButton.addActionListener(this);

    text1 = new Text("P1 Winner",TEXT1_X_COORD,GAME_OVER_Y_COORD, canvas);
    text2 = new Text("P2 Winner",TEXT2_X_COORD,GAME_OVER_Y_COORD, canvas);
    text3 = new Text("Tie", TEXT3_X_COORD,GAME_OVER_Y_COORD, canvas);
    //text2.move(-text2.getWidth()/2,0);
    text1.setColor(Color.GREEN);
    text2.setColor(Color.GREEN);
    text3.setColor(Color.BLUE);


  }

  public void actionPerformed(ActionEvent ev)
  {
   
  }


}