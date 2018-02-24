//*****************************************************************************
// The Deadwood class is the main entry point into the Deadwood game program.
// It creates the model, view, and controller for the game.
//*****************************************************************************
import java.util.Scanner;

public class Deadwood
{
   public static void main(String[] args) throws Throwable
   {
      //-----------------------------------------------------------------------
      //Trys to use the commandline argument as the number of players. If not
      //between 2-4 players, re-prompts the user.
      //-----------------------------------------------------------------------

      Scanner scan = new Scanner(System.in);
      int numPlayers;

      if(args.length != 0) {
        numPlayers = Integer.parseInt(args[0]);

        while(numPlayers < 2 || numPlayers > 4) {
          System.out.print("Please enter a valid number of players (2-4): ");
          numPlayers = Integer.parseInt(scan.next());
        }

        Board theBoard = new Board(numPlayers);
        GUIView theView = new GUIView();
        GUIController theController = new GUIController(theView, theBoard);

        theView.setVisible(true);

      } else {

        System.out.print("Please enter the number of players (2-4): "); //Prompts the user for the number of players
        numPlayers = Integer.parseInt(scan.next());

        while(numPlayers < 2 || numPlayers > 4) {
          System.out.print("Please enter a valid number of players (2-4): ");
          numPlayers = Integer.parseInt(scan.next());
        }

        GUIView theView = new GUIView();
        Board theBoard = new Board(numPlayers);
        GUIController theController = new GUIController(theView, theBoard);

        theView.setVisible(true);
      }

   }//Ends main method
}//Ends Deadwood class
