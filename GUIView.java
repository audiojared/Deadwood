//*****************************************************************************
// The GUIView class creates the graphical user interface for the players of
// the Deadwood game which is an enhancemaent of the game from the text based
// format.
//*****************************************************************************
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUIView extends JFrame
{
   //--------------------------------------------------------------------------
   //Creates the fields that the view will use
   //--------------------------------------------------------------------------
   private static BoardView board = new BoardView();
   private JScrollPane systemPane = new JScrollPane(systemResponse);
   private JScrollPane gamePane = new JScrollPane(board);
   private JLabel menuLabel = new JLabel("Menu");
   private JLabel moveLabel = new JLabel("Move to");
   private JTextField moveField = new JTextField();
   private JButton moveButton = new JButton("Move");
   private JLabel workLabel = new JLabel("Work part");
   private JTextField workField = new JTextField();
   private JButton workButton = new JButton("Work");
   private JLabel upgradeLabel = new JLabel("Upgrade Player");
   private JTextField upgradeField = new JTextField();
   private JButton upgradeCreditsButton = new JButton("Upgrade Cr");
   private JButton upgradeMoneyButton = new JButton("Upgrade $");
   private JButton rehearseButton = new JButton("Rehearse");
   private JButton actButton = new JButton("Act");
   private JButton endTurnButton = new JButton("End Turn");
   /*************

   JFrame enter button, listens to nextturnbutton
 
   *************/
   /*private frame.getRootPane().setDefaultButton(endTurnButton);*/
   private static JTextArea systemResponse = new JTextArea(4, 109);
   private JLabel infoLabel = new JLabel("Player Info");
   private JLabel playerLabel = new JLabel("Player: Blue");
   private JLabel levelLabel = new JLabel("Level: 1");
   private JLabel creditLabel = new JLabel("Credits: 0");
   private JLabel moneyLabel = new JLabel("Money: 0");
   private JLabel chipsLabel = new JLabel("Rehearse: 0");
   private static HashMap<String, JLabel> players = new HashMap<String, JLabel>();
   private static HashMap<String, JLabel> cards = new HashMap<String, JLabel>();
   private static HashMap<Integer, JLabel> takes = new HashMap<Integer, JLabel>();

   //--------------------------------------------------------------------------
   //The onstructor creates the JFrame and panels and adds all of the
   //components to the view.
   //--------------------------------------------------------------------------
   public GUIView()
   {
      int boardWidth = board.getBoard().getIconWidth();
      int boardHeight = board.getBoard().getIconHeight();

      //-----------------------------------------------------------------------
      //Sets up JFrame
      //-----------------------------------------------------------------------
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Deadwood");
      this.setSize(1340, 1080);
      this.setResizable(false);

      //-----------------------------------------------------------------------
      //Sets up the menu
      //-----------------------------------------------------------------------
      menuLabel.setBounds(boardWidth + 40, 0, 100, 20);
      moveField.setBounds(boardWidth + 10, 30, 100, 20);
      moveButton.setBounds(boardWidth + 10, 50, 100, 20);
      workField.setBounds(boardWidth +10, 80, 100, 20);
      workButton.setBounds(boardWidth + 10, 100, 100, 20);
      actButton.setBounds(boardWidth + 10, 130, 100, 20);
      rehearseButton.setBounds(boardWidth + 10, 160, 100, 20);
      upgradeField.setBounds(boardWidth + 10, 190, 100, 20);
      upgradeMoneyButton.setBounds(boardWidth + 10, 210, 100, 20);
      upgradeCreditsButton.setBounds(boardWidth + 10, 230, 100, 20);
      endTurnButton.setBounds(boardWidth + 10, 260, 100, 20);

      //-----------------------------------------------------------------------
      //Sets up the player information area
      //-----------------------------------------------------------------------
      infoLabel.setBounds(boardWidth + 30, 300, 100, 20);
      playerLabel.setBounds(boardWidth + 10, 330, 100, 20);
      levelLabel.setBounds(boardWidth + 10, 360, 100, 20);
      creditLabel.setBounds(boardWidth + 10, 390, 100, 20);
      moneyLabel.setBounds(boardWidth + 10, 420, 100, 20);
      chipsLabel.setBounds(boardWidth + 10, 450, 100, 20);

      //----------------------------------------------------------------------
      //Sets up system response pane
      //----------------------------------------------------------------------
      systemPane.setBounds(0, boardHeight + 10, boardWidth, 150);
      systemResponse.setEditable(false);

      //-----------------------------------------------------------------------
      //Adds the fields to the boardPane
      //-----------------------------------------------------------------------
      board.add(menuLabel, new Integer(2));
      board.add(moveField, new Integer(2));
      board.add(moveButton, new Integer(2));
      board.add(workField, new Integer(2));
      board.add(workButton, new Integer(2));
      board.add(actButton, new Integer(2));
      board.add(rehearseButton, new Integer(2));
      board.add(upgradeField, new Integer(2));
      board.add(upgradeMoneyButton, new Integer(2));
      board.add(upgradeCreditsButton, new Integer(2));
      board.add(endTurnButton, new Integer(2));

      board.add(infoLabel, new Integer (2));
      board.add(playerLabel, new Integer(2));
      board.add(levelLabel, new Integer(2));
      board.add(creditLabel, new Integer(2));
      board.add(moneyLabel, new Integer(2));
      board.add(chipsLabel, new Integer(2));

      board.add(systemPane, new Integer(2));

      this.add(gamePane);
   }//Ends constructor

   //--------------------------------------------------------------------------
   //setSystemResponse()
   //Pre: String
   //Post: N/A
   //
   //The setSystemResponse() method provides a way to output text to the users
   //view. The method receives the message to display as a string.
   //--------------------------------------------------------------------------
   public static void setSystemResponse(String output)
   {
      systemResponse.append(output + "\n");
   }//Ends setSystemResponse() method

   //--------------------------------------------------------------------------
   //addMoveListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the move button
   //--------------------------------------------------------------------------
   public void addMoveListener(ActionListener listenForMoveButton)
   {
      moveButton.addActionListener(listenForMoveButton);
   }//Ends addMoveListener() method

   //--------------------------------------------------------------------------
   //getMove()
   //Pre: N/A
   //Post: String
   //
   //Provides a way to get the text from the move text field
   //--------------------------------------------------------------------------
   public String getMove()
   {
      return moveField.getText();
   }//Ends getMove() method

   //--------------------------------------------------------------------------
   //getWork()
   //Pre: N/A
   //Post: String
   //
   //Provides a way to get the text from the work text field
   //--------------------------------------------------------------------------
   public String getWork()
   {
      return workField.getText();
   }//Ends getWork() method

   //--------------------------------------------------------------------------
   //getUpgrade()
   //Pre: N/A
   //Post: String
   //
   //Provides a way to get the text from the upgrade text field
   //--------------------------------------------------------------------------
   public String getUpgrade()
   {
      return upgradeField.getText();
   }//Ends getUpgrade() method

   //--------------------------------------------------------------------------
   //addWorkListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the work button
   //--------------------------------------------------------------------------
   public void addWorkListener(ActionListener listenForWorkButton)
   {
      workButton.addActionListener(listenForWorkButton);
   }//Ends addMoveListener() method

   //--------------------------------------------------------------------------
   //addUpgradeCreditsListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the upgrade credits button
   //--------------------------------------------------------------------------
   public void addUpgradeCreditsListener(ActionListener listenForUpgradeButton)
   {
      upgradeCreditsButton.addActionListener(listenForUpgradeButton);
   }//Ends addUpgradeListener() method

   //--------------------------------------------------------------------------
   //addUpgradeMoneyListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the upgrade money button
   //--------------------------------------------------------------------------
   public void addUpgradeMoneyListener(ActionListener listenForUpgradeButton)
   {
      upgradeMoneyButton.addActionListener(listenForUpgradeButton);
   }//Ends addUpgradeListener() method

   //--------------------------------------------------------------------------
   //adRrehearseListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the rehearse button
   //--------------------------------------------------------------------------
   public void addRehearseListener(ActionListener listenForRehearseButton)
   {
      rehearseButton.addActionListener(listenForRehearseButton);
   }//Ends addRehearseListener() method

   //--------------------------------------------------------------------------
   //addActListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the act button
   //--------------------------------------------------------------------------
   public void addActListener(ActionListener listenForActButton)
   {
      actButton.addActionListener(listenForActButton);
   }//Ends addActListener() method

   //--------------------------------------------------------------------------
   //addEndTurnListener()
   //Pre: Action Listener
   //Post: N/A
   //
   //Provides a way to add an ActionListener to the end turn button
   //--------------------------------------------------------------------------
   public void addEndTurnListener(ActionListener listenForEndTurnButton)
   {
      endTurnButton.addActionListener(listenForEndTurnButton);
   }//Ends addEndTurnListener() method

   //--------------------------------------------------------------------------
   //displayErrorMessage()
   //Pre: String
   //Post: N/A
   //
   //Allows the system to dislay an error message if an error message is
   //encountered
   //--------------------------------------------------------------------------
   public void displayErrorMessage(String errorMessage)
   {
      JOptionPane.showMessageDialog(this, errorMessage);
   }//Ends displayErrorMessage() method

   //--------------------------------------------------------------------------
   //setCaret()
   //Pre: N/A
   //Post: N/A
   //
   //This fuction scrolls to the last line of the system response pane
   //--------------------------------------------------------------------------
   public static void setCaret()
   {
      systemResponse.setCaretPosition(systemResponse.getText().length());
   }

   //--------------------------------------------------------------------------
   //setPlayer()
   //Pre: String
   //Post: N/A
   //
   //Allows for a way to set the current player label on the screen
   //--------------------------------------------------------------------------
   public void setPlayer(String player)
   {
      playerLabel.setText("Player: " + player);
   }//Ends setPlayer() method

   //--------------------------------------------------------------------------
   //setLevel()
   //Pre: String
   //Post: N/A
   //
   //Allows for a way to set the current player level label on the screen
   //--------------------------------------------------------------------------
   public void setLevel(String level)
   {
      levelLabel.setText("Level: " + level);
   }//Ends setLevel() method

   //--------------------------------------------------------------------------
   //setLevel()
   //Pre: int
   //Post: N/A
   //
   //Allows for a way to set the current player level label on the screen
   //--------------------------------------------------------------------------
   public void setLevel(int level)
   {
      levelLabel.setText("Level: " + level);
   }//Ends setLevel() method

   //--------------------------------------------------------------------------
   //setCredits()
   //Pre: String
   //Post: N/A
   //
   //Allows for a way to set the current player credits label on the screen
   //--------------------------------------------------------------------------
   public void setCredits(String credits)
   {
      creditLabel.setText("Credits: " + credits);
   }//Ends setCredits() method

   //--------------------------------------------------------------------------
   //setCredits()
   //Pre: int
   //Post: N/A
   //
   //Allows for a way to set the current player credits label on the screen
   //--------------------------------------------------------------------------
   public void setCredits(int credits)
   {
      creditLabel.setText("Credits: " + credits);
   }//Ends setCredits() method

   //--------------------------------------------------------------------------
   //setMoney()
   //Pre: String
   //Post: N/A
   //
   //Allows for a way to set the current player money label on the screen
   //--------------------------------------------------------------------------
   public void setMoney(String money)
   {
      moneyLabel.setText("Money: " + money);
   }//Ends setMoney() method

   //--------------------------------------------------------------------------
   //setMoney()
   //Pre: int
   //Post: N/A
   //
   //Allows for a way to set the current player money label on the screen
   //--------------------------------------------------------------------------
   public void setMoney(int money)
   {
      moneyLabel.setText("Money: " + money);
   }//Ends setMoney() method

   //--------------------------------------------------------------------------
   //setChips()
   //Pre: String
   //Post: N/A
   //
   //Allows for a way to set the current player chips label on the screen
   //--------------------------------------------------------------------------
   public void setChips(String chips)
   {
      chipsLabel.setText("Rehearse Counter: " + chips);
   }//Ends setChips() method

   //--------------------------------------------------------------------------
   //setChips()
   //Pre: int
   //Post: N/A
   //
   //Allows for a way to set the current player chips label on the screen
   //--------------------------------------------------------------------------
   public void setChips(int chips)
   {
      chipsLabel.setText("Rehearse: " + chips);
   }//Ends setChips() method

   public static void addPlayer(String name, JLabel label)
   {
      players.put(name, label);
      board.add(label, new Integer(3));
   }//Ends addPlayer() method

   public static JLabel getPlayer(String name)
   {
      return players.get(name);
   }//Ends getPlayer() method

   public static JLabel getCard(String sceneNum)
   {
      return cards.get(sceneNum);
   }//Ends getCard() method

   public static void addCard(String sceneNum, JLabel label)
   {
      cards.put(sceneNum, label);
      board.add(label, new Integer(1));
   }//Ends addCard() method

   public static void removeCard(String name)
   {
      board.remove(cards.get(name));
      cards.remove(name);
      board.repaint();
   }//Ends removeCard() method

   public static void addTake(int num, JLabel label)
   {
      takes.put(num, label);
      board.add(label, new Integer(1));
   }//Ends addTake() method

   public static void removeTake(int[] xy)
   {
      for (int key : takes.keySet())
      {
         if (takes.get(key).getX() == xy[0] && takes.get(key).getY() == xy[1])
         {
            takes.get(key).setVisible(false);
            board.repaint();
            break;
         }
      }//Ends for loop
   }//Ends removeTake() method

   public static void resetTakes()
   {
      for (int key : takes.keySet())
      {
         takes.get(key).setVisible(true);
      }//Ends for loop
      board.repaint();
   }//Ends resetTakes() method
}//Ends GUIView class
