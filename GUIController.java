//*****************************************************************************
// The GUIController class provides the Deadwood project with a controller
// for the GUIView which allows interaction between the GUIView and game
// model.
//*****************************************************************************
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController
{
   //--------------------------------------------------------------------------
   //Creates the fields that that the controller will use
   //--------------------------------------------------------------------------
   private GUIView theView;
   private Board theBoard;
   
   //--------------------------------------------------------------------------
   //The constructor links the view with the game model and creates the
   //listeners.
   //--------------------------------------------------------------------------
   public GUIController(GUIView theView, Board theBoard)
   {
      this.theView = theView;
      this.theBoard = theBoard;
      
      this.theView.addMoveListener(new MoveListener());
      this.theView.addWorkListener(new WorkListener());
      this.theView.addUpgradeCreditsListener(new UpgradeCreditsListener());
      this.theView.addUpgradeMoneyListener(new UpgradeMoneyListener());
      this.theView.addRehearseListener(new RehearseListener());
      this.theView.addActListener(new ActListener());
      this.theView.addEndTurnListener(new EndTurnListener());
   }//Ends constructor
   
   class MoveListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            String input = theView.getMove().trim().toLowerCase();
            
            theBoard.getPlayer().moveTo(input);
            
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends MoveListener class
   
   class WorkListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            String input = theView.getWork().trim().toLowerCase();
            
            theBoard.getPlayer().chooseRole(input);
            
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends MoveListener class
   
   class UpgradeCreditsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            String input = theView.getUpgrade().trim().toLowerCase();
         
            theBoard.getPlayer().upgradePlayerCredit(Integer.parseInt(input));
            
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends UpgradeListener class
   
   class UpgradeMoneyListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            String input = theView.getUpgrade().trim().toLowerCase();
         
            theBoard.getPlayer().upgradePlayerMoney(Integer.parseInt(input));
            
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends UpgradeListener class
   
   class RehearseListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            theBoard.getPlayer().rehearse();
            
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends RehearseListener class
   
   class ActListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            theBoard.getPlayer().act();
            
            theView.setPlayer(theBoard.getPlayer().getName());
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends ActListener class
   
   class EndTurnListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            theBoard.endTurn();
            
            theView.setPlayer(theBoard.getPlayer().getName());
            theView.setLevel(theBoard.getPlayer().getLevel());
            theView.setMoney(theBoard.getPlayer().getMoney());
            theView.setChips(theBoard.getPlayer().getPracticeChips());
            
            theView.setCredits(theBoard.getPlayer().getCredit());
            
            theView.setCaret();
         } catch (Exception ex)
         {
            theView.displayErrorMessage("Please enter a valid command");
            theView.setCaret();
         }//Ends try-catch block
      }//Ends actionPerformed method
   }//Ends EndTurnListener class
}//Ends GUIContronller class
