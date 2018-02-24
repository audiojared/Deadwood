//*****************************************************************************
// The BoardView provides the board pane for the GUIView class.
//*****************************************************************************
import javax.swing.*;
import java.awt.*;

public class BoardView extends JLayeredPane
{
   //--------------------------------------------------------------------------
   //Creates all of the fields required for BoardView
   //--------------------------------------------------------------------------
   private ImageIcon icon = new ImageIcon("board.jpg");
   private JLabel boardLabel = new JLabel();
   
   //--------------------------------------------------------------------------
   //The constructor creates the board object
   //--------------------------------------------------------------------------
   public BoardView()
   {
      boardLabel.setIcon(icon);
      
      this.add(boardLabel, new Integer(0));
      boardLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
   }//Ends constructor
   
   //--------------------------------------------------------------------------
   //getBoard()
   //Pre: N/A
   //Post: Icon
   //
   //Provides a way to get the board icon
   //--------------------------------------------------------------------------
   public ImageIcon getBoard()
   {
      return icon;
   }//Ends getBoard() method
}//Ends BoardView class
