/**
 * Assignment 6                 P6.java           Due Saturday, August 29
 * login: cs11vcn
 * This is a JavaFX UI program with javadoc comments to display a deck of
 * playing card images, shuffle, sort in ascending and descending order 
 * using Buttons.
 * Layout Panes include: GridPane, BorderPane, and HBox.
 * Random generates random numbers for the shuffle using the
 * System.currentTimeMillis() seed.
 */
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * public class P6 extends Application
 * This is a subclass of javafx.application.Application.
 * It will show the UI to the user for manipulating
 * the card.
 * @author WeiDong Yuan
 * @version vsersion 1.0.2
 */
public class P6 extends Application{
  private final int ROWS = 6;
  private final int COLS = 9;
  private final int ASIZE = 54;
  private int aCardDeck[] = new int[ASIZE];
  private int playerA = 0;
  private int playerB = 1;
  private int cardsEachPlayer = 5;
  private int playerNum = 2;


  /**
   * public void start(Stage primaryStage)
   * Initialize JavaFX application.
   * @param primaryStage The stage of the application.
  */
  public void start(Stage primaryStage)
  {
    for ( int i = 0; i < ASIZE; i++ ) // ASIZE is 54
      aCardDeck[i] = i+1; // Populate with elements values 1-54
    GridPane gPane = new GridPane(); // Instantiate Grid for cards in rows/cols
    for (int i = 0, k = 0 ; i < ROWS && k < ASIZE; i++) // 6 rows, 9 columns
      for (int j = 0; j < COLS ; j++)
        gPane.add(new ImageView("card/" +aCardDeck[k++] + ".png"),j,i);
    
    
    Button btShuffle = new Button("Shuffle");
    btShuffle.setOnAction
    ( e -> // Lamda Event Handler
      { // Like anonymous inner class
        int r, c, n;
        shuffle(aCardDeck); // Random shuffle
        gPane.getChildren().clear();
        for ( r = n = 0; r < ROWS && n < ASIZE; r++)
          for (c = 0; c < COLS ; c++)
            gPane.add(new ImageView("card/" +aCardDeck[n++] + ".png"),c,r);
      }
    );
    
    Button btAscendSort = new Button("AscendSort");
    btAscendSort.setOnAction
    ( e -> // Lamda Event Handler
      { // Like anonymous inner class
        int r, c, n;
        ascendSort(aCardDeck); // Sort in ascending order.
        gPane.getChildren().clear();
        for ( r = n = 0; r < ROWS && n < ASIZE; r++)
          for (c = 0; c < COLS ; c++)
            gPane.add(new ImageView("card/" +aCardDeck[n++] + ".png"),c,r);
      }
    );
    
    Button btDescendSort = new Button("DescendSort");
    btDescendSort.setOnAction
    ( e -> // Lamda Event Handler
      { // Like anonymous inner class
        int r, c, n;
        descendSort(aCardDeck); //Sort in descending order.
        gPane.getChildren().clear();
        for ( r = n = 0; r < ROWS && n < ASIZE; r++)
          for (c = 0; c < COLS ; c++)
            gPane.add(new ImageView("card/" +aCardDeck[n++] + ".png"),c,r);
      }
    );
    
    Button btAscendRank = new Button("Ascend Rank");
    btAscendRank.setOnAction
    ( e -> // Lamda Event Handler
      { // Like anonymous inner class
        int r, c, n;
        ascendRank(aCardDeck); // Sort in ascending rank order.
        gPane.getChildren().clear();
        for ( r = n = 0; r < ROWS && n < ASIZE; r++)
          for (c = 0; c < COLS ; c++)
            gPane.add(new ImageView("card/" +aCardDeck[n++] + ".png"),c,r);
      }
    );
    
    Button btDescendRank = new Button("Descend Rank");
    btDescendRank.setOnAction
    ( e -> // Lamda Event Handler
      { // Like anonymous inner class
        int r, c, n;
        descendRank(aCardDeck); //Sort in descending rank order.
        gPane.getChildren().clear();
        for ( r = n = 0; r < ROWS && n < ASIZE; r++)
          for (c = 0; c < COLS ; c++)
            gPane.add(new ImageView("card/" +aCardDeck[n++] 
                + ".png"),c,r);
      }
    );
    
    Button btDeal = new Button("Deal");
    btDeal.setOnAction
    ( e -> // Lamda Event Handler
      { // Like anonymous inner class
        shuffle(aCardDeck); // Deal the cards for the player.
        gPane.getChildren().clear();
        for(int n=0; n<playerNum*cardsEachPlayer;n++)
        {
          gPane.add(new ImageView("card/" +aCardDeck[n] 
              + ".png"),n/playerNum,n%playerNum);
        }
      }
    );
    
    Button btExit = new Button("Exit");
    btExit.setOnAction
    ( e -> // Lamda Event Handler
      {
        System.exit(0);//Exit the program.
      }
    );
    
    HBox hBox = new HBox(7); // Row of buttons
    
    // Add 7 buttons to box
    hBox.getChildren().add(btAscendSort);
    hBox.getChildren().add(btDescendSort);
    hBox.getChildren().add( btShuffle ); // Add button to box
    hBox.getChildren().add(btAscendRank);
    hBox.getChildren().add(btDescendRank);
    hBox.getChildren().add(btDeal);
    hBox.getChildren().add(btExit);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(gPane); // Layout rows of cards in center
    pane.setBottom(hBox); // Layout buttons at bottom
    BorderPane.setAlignment(hBox, Pos.CENTER);
    Scene scene = new Scene(pane, 650, 600); // Create scene, place in stage
    primaryStage.setTitle("P6"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * public void shuffle(int []a)
   * Random swap of cards
   * @param a Array representing the cards.
   */
  public void shuffle(int []a)
  {
    long seed;
    Random rand = new Random(); // local variable in shuffle()
    seed = System.currentTimeMillis(); // Current time milliseconds type long
    rand.setSeed(seed); // Assign seed to random object
    for(int i=0; i<ASIZE; i++)
    {
      int r = rand.nextInt(ASIZE); // Generate next int in range 0-53, swa
      swap(i, r);
    }
  }
  
  /**
   * public void ascendSort(int []a)
   * Sorts in ascending order.
   * @param a Array representing the cards.
   */
  public void ascendSort(int []a)
  {
    for(int i=0; i<ASIZE; i++)
    {
      for(int j=ASIZE-1; j>i; j--)
      {
        if(a[j] < a[j-1])
        {
          swap(j, j-1);
        }
      }
    }
  }
  
  /**
   * public void descendSort( int [] a)
   * Sorts in descending order.
   * @param a Array representing the cards.
   */
  public void descendSort( int [] a)
   {
    for(int i=0; i<ASIZE; i++)
    {
      for(int j=ASIZE-1; j>i; j--)
      {
        if(a[j] > a[j-1])
        {
          swap(j, j-1);
        }
      }
    }
  }
  
  /**
   * public void ascendRank(int []a)
   * Sorts in ascending rank order.
   * @param a Array representing the cards.
   */
  public void ascendRank(int []a)
  {
    ascendSort(a);
    for(int i=0; i<ASIZE-2; i++)
    {
      for(int j=ASIZE-3; j>i; j--)
      {
        if((a[j]-1)%13 < (a[j-1]-1)%13)
        {
          swap(j, j-1);
        }
      }
    }
  }
  
  /**
   * public void descendRank(int []a)
   * Sort in descend rank order.
   * @param a Array representing the cards.
   */
  public void descendRank(int []a)
  {
    descendSort(a);
    for(int i=2; i<ASIZE-1; i++)
    {
      for(int j=ASIZE-1; j>i; j--)
      {
        if((a[j]-1)%13 > (a[j-1]-1)%13)
        {
          swap(j, j-1);
        }
      }
    }
  }
  
  public void swap( int j, int k)
  {
    int tmp;
    tmp = aCardDeck[j];
    aCardDeck[j] = aCardDeck[k];
    aCardDeck[k] = tmp;
  }
  /**
   * public static void main(String[] args)
   * Begins the program and shows the interface.
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}