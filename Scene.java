import java.util.*;

public class Scene{

  private String name;
  private int shotCounter = 0;
  private int budget;
  private ArrayList<Role> sceneRoles = new ArrayList<>();
  private boolean wrapped;
  private boolean flipped;
  private String number;
  public Scene(String name, int shotCounter, int budget, ArrayList<Role> sceneRoles, boolean wrapped, boolean flipped, String number){
     this.name = name;
     this.shotCounter = shotCounter;
     this.budget = budget;
     this.sceneRoles = sceneRoles;
     this.wrapped = wrapped;
     this.flipped = flipped;
     this.number = number;
  }
  public String getName(){
     return name;
  }
  public boolean getFlipped(){
    return flipped;
  }
  public int getShotCounter(){
     return shotCounter;
  }
  public int getBudget(){
     return budget;
  }
  public ArrayList<Role> getRoles(){
     return sceneRoles;
  }
  public boolean isWrapped(){
     return wrapped;
  }
  public void changeWrapped(){
     wrapped = !wrapped;
  }
  public int rollDice(){//generates a random number between 1 and 6
     return 1 + (int)(Math.random() * 6);
  }

  public void increaseCounter() {
      
      shotCounter++;
  }

  public String getCardImage() {
    if (Integer.parseInt(number) < 10)
    {
      return "cards/0" + number + ".png";
    }
    else
    {
      return "cards/" + number + ".png";
    }
  }
   public String getNumber()
   {
      return this.number;
   }//Ends getNumber() method
}
