import java.util.*;
public class Stage extends Room{

  private Scene roomScene;
  private ArrayList<Role> roles = new ArrayList<>();
  private int maxTakes;
  int[][] takesCoordinates = new int[3][4];
  public Stage(String name, ArrayList<String> neighbors, int[] coordinates, Scene roomScene, ArrayList<Role> roles, int maxTakes, int[][] takesCoordinates){
     super(name, neighbors, coordinates);
     this.roomScene = roomScene;
     this.roles = roles;
     this.maxTakes = maxTakes;
     this.takesCoordinates = takesCoordinates;
  }
  public ArrayList<Role> getRoles(){
     return roles;
  }
  public void take(){
    int[] xy = new int[2];
    xy[0] = takesCoordinates[roomScene.getShotCounter()][0];
    xy[1] = takesCoordinates[roomScene.getShotCounter()][1];
    GUIView.removeTake(xy);
  }

  public Scene getScene(){
     return roomScene;
  }
  public void setScene(Scene newScene) {
    roomScene = newScene;
  }
  public int getMaxTakes(){
     return maxTakes;
  }

  public void where() {//Displays the current location along with whether the scene is wrapped or what scene is being shot
    if(roomScene.isWrapped()) {
      GUIView.setSystemResponse(name + " wrapped");
    } else {
      GUIView.setSystemResponse(name + " shooting " + roomScene.getName());
    }
    GUIView.setSystemResponse("The adjacent rooms are:");//Displays the rooms that are adjacent to the current room
    for(String i : neighbors){
      GUIView.setSystemResponse(i);
    }
  }

  public boolean checkWrapped() {
    return roomScene.getShotCounter() == maxTakes;
  }

  public void showRoles(){//Shows which roles are in the room
    if(roomScene.isWrapped()) {
      GUIView.setSystemResponse(name + " is wrapped.");
    } else {
      GUIView.setSystemResponse("The roles in this room are: ");
      for (Role i : roles){
        GUIView.setSystemResponse(i.getName() + "  " + i.getLevel());
      }
      ArrayList<Role> sceneRoles = roomScene.getRoles();
      for (Role j : sceneRoles){
        GUIView.setSystemResponse(j.getName() + "  " + j.getLevel());
      }
    }
  }

  public void wrapScene() {

    GUIView.setSystemResponse("Scene has wrapped.");

    ArrayList<Role> copySceneRoles = roomScene.getRoles();
    ArrayList<Integer> diceRolls = new ArrayList<Integer>();
    ArrayList<Player> players = new ArrayList<Player>();
    int counter = 0;
    boolean hasOnCardRoles = false;

    //rolls number of die equal to budget
    for(int i = 0; i < roomScene.getBudget(); i++) {
      diceRolls.add(roomScene.rollDice());
    }

    Collections.sort(diceRolls);
    Collections.reverse(diceRolls);

    //reverse
    Collections.reverse(copySceneRoles);

    //gets players from scene roles
    for(Role i : copySceneRoles) {
      players.add(i.getPlayer());
    }
    //for each die, it gives money equal to roll to player.
    for(int i = 0; i < diceRolls.size(); i++) {
      if(players.get(counter) != null) {
        players.get(counter).exchangeMoney(diceRolls.get(i));
        players.get(counter).setPracticeChips(0);
        players.get(counter).setRoleNull();
        hasOnCardRoles = true;
        GUIView.setSystemResponse(players.get(counter) + " has earned $" + diceRolls.get(i));
      }

      counter++;

      if(counter == players.size()) {//If we have run out of players but still have more dice rolls, loop back to the first player
        counter = 0;
      }
    }


    //if there was a player working on the card, pay players money equal to role level.
    if(hasOnCardRoles) {
      for(Role i : roles) {
        if(i.getPlayer() != null) {
          i.getPlayer().exchangeMoney(i.getLevel());
          i.getPlayer().setPracticeChips(0);
          i.getPlayer().setRoleNull();
          GUIView.setSystemResponse(i.getPlayer().getName() + " has earned $" + i.getLevel());
        }
      }
    } else {
      //if there was no player working on the card, reset player role, without paying.
      for(Role j : roles) {
        if (j.getPlayer() != null){
         j.getPlayer().setPracticeChips(0);
         j.getPlayer().setRoleNull();
        }
      }
    }

    for(Role k : roles) {//Set the role's player null
      k.setPlayer(null);
      
    }

    roomScene.changeWrapped();
    GUIView.removeCard(roomScene.getNumber());
    Board.decreaseSceneCounter();
  }
}
