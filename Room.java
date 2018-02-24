import java.util.*;

public class Room{

  String name;
  ArrayList<String> neighbors = new ArrayList<>();
  int[] coordinates = new int[2];


  public Room(String name, ArrayList<String> neighbors, int[] coordinates){
     this.name = name;
     this.neighbors = neighbors;
     this.coordinates = coordinates;

  }

  public String getName(){
     return name;
  }

  public int[] getCoordinates(){
    return coordinates;
  }

  public void where() {
    GUIView.setSystemResponse(name);
    GUIView.setSystemResponse("The adjacent rooms are:");
    for(String i : neighbors){
      GUIView.setSystemResponse(i);
    }
  }

  public Scene getScene(){
     return null;
  }

  public void setScene(Scene sean) {

  }

  public ArrayList<Role> getRoles(){
     return null;
  }

  public boolean checkWrapped() {
    return false;
  }

  public void wrapScene() {

  }

  public void showRoles() {

  }

  public ArrayList<String> getNeighbors() {
    return neighbors;
  }

  public void upgradeCredit(Player p, int newLevel){
     GUIView.setSystemResponse("You must be in the Casting Office to upgrade your level.");
  }
  public void upgradeMoney(Player p, int newLevel){
     GUIView.setSystemResponse("You must be in the Casting Office to upgrade your level.");
  }

  public boolean isAdjacent(Room testNeighbor){
     for (String i : neighbors){//iterates through the list of its neighbors
        if (i.equals(testNeighbor.getName())){//Checks if any of the neighbors are equal to the desired room
           return true;
        }
     }
     GUIView.setSystemResponse("That room is not adjacent.");
     return false;
  }
  
  public void take()
  {
  }
}
