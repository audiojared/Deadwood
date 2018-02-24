import java.util.*;
public class Role{

  private String name;
  private boolean available;
  private int level;
  private boolean onBoard;
  private Player currentPlayer;
  private String line;
  int[] coordinates = new int[2];
  public Role(String name, boolean available, int level, boolean onBoard, Player currentPlayer, String line, int[] coordinates){
     this.name = name;
     this.available = available;
     this.level = level;
     this.onBoard = onBoard;
     this.currentPlayer = currentPlayer;
     this.line = line;
     this.coordinates = coordinates;
  }
  public String getName(){
     return name;
  }
  public int[] getCoordinates(){
    return coordinates;
  }
  public boolean getOnBoard(){
     return onBoard;
  }
  public boolean getAvailable(){
     return available;
  }
  public int getLevel(){
     return level;
  }
  public Player getPlayer(){
     return currentPlayer;
  }
  public String getString(){
     return "";
  }
  public String getLine() {
    return line;
  }
  public void changeAvailable(){
     available = !available;
  }
  public void setAvailable(boolean b){
    available = b;
  }
  public void setPlayer(Player newPlayer){
     currentPlayer = newPlayer;
  }
}
