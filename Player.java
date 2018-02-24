import java.util.*;
import javax.swing.*;


public class Player {

  private String name;
  private int credit;
  private int money;
  private int level;
  private int practiceChips;
  private Role currentRole;
  private Room currentRoom;
  private boolean hasWorked;
  private boolean hasMoved;
  private int score;



  public Player(Room initialRoom, String playerName) {

      credit = 0;
      money = 0;
      level = 1;
      practiceChips = 0;
      currentRole = null;
      currentRoom = initialRoom;
      hasWorked = false;
      hasMoved = false;
      name = playerName;
  }

  public String getName() {
    return name;
  }
  public int getCredit() {
    return credit;
  }
  public int getMoney() {
    return money;
  }
  public int getLevel() {
    return level;
  }
  public int getPracticeChips() {
    return practiceChips;
  }
  public Role getCurrentRole() {
    return currentRole;
  }
  public Room getCurrentRoom() {
    return currentRoom;
  }
  public void setRoleNull() {
    currentRole = null;
    GUIView.getPlayer(this.name).setBounds(this.getLocation()[0], this.getLocation()[1], GUIView.getPlayer(this.name).getIcon().getIconWidth(), GUIView.getPlayer(this.name).getIcon().getIconHeight());
  }
  public void setPracticeChips(int num) {
    practiceChips = num;
  }
  public void setLevel(int num) {
    level = num;
  }
  public int[] getLocation() {
    int[] xy = new int[2];
    int collision = 0;
    if(currentRole == null) { //coordinates for current room
      switch (name){
         case "Green":
            collision = 40;
            break;
         case "Yellow":
            collision = 80;
            break;
         case "Red":
            collision = 120;
            break;

         }
         if (currentRoom.getName().equals("trailer") || currentRoom.getName().equals("office"))
         {
            xy[0] = currentRoom.getCoordinates()[0] + collision;
            xy[1] = currentRoom.getCoordinates()[1];
         }
         else
         {
            xy[0] = currentRoom.getCoordinates()[0] + collision;
            xy[1] = currentRoom.getCoordinates()[1] + 115;
         }
      return xy;
    } else if(currentRole.getOnBoard()){ //coordinates for on the board role
      xy[0] = currentRole.getCoordinates()[0];
      xy[1] = currentRole.getCoordinates()[1];
      return xy;
    } else { //cordinates for on the card role
      xy[0] = currentRoom.getCoordinates()[0] + currentRole.getCoordinates()[0];
      xy[1] = currentRoom.getCoordinates()[1] + currentRole.getCoordinates()[1];
      return xy;
    }
  }

  public void exchangeMoney(int num) {
    money += num;
  }
  public void exchangeCredit(int num) {
    credit += num;
  }

  //resets the players values and moves the player to the trailer
  public void startDay(Room trailer) {
    currentRoom = trailer;
    currentRole = null;
    practiceChips = 0;
    hasMoved = false;
    hasWorked = false;
    
    GUIView.getPlayer(this.name).setBounds(this.getLocation()[0], this.getLocation()[1], GUIView.getPlayer(this.name).getIcon().getIconWidth(), GUIView.getPlayer(this.name).getIcon().getIconHeight());
  }

  //ends the players turn and resets their move and work flags
  public void endTurn() {
    hasMoved = false;
    hasWorked = false;
    GUIView.setSystemResponse(name + "'s turn has ended.");
  }

  //Changes the current room of the player to the supplied one
  public void moveTo(String newRoom) {
      //converts the supplied string to a room object
      Room desiredRoom = Board.convertRoom(newRoom);

      if(desiredRoom == null) {
        GUIView.setSystemResponse("That room does not exist. Please choose another.");
      } else if(hasMoved == true) {
        GUIView.setSystemResponse("You have already moved this turn.");
      } else if(!currentRoom.isAdjacent(desiredRoom)) {
        GUIView.setSystemResponse("Chosen room is not adjacent. Please choose an adjacent room.");
      } else if(currentRole != null) {
        GUIView.setSystemResponse("You cannot move while working a role.");
      } else if(hasWorked == true) {
        GUIView.setSystemResponse("You cannot move after acting.");
      } else {
        currentRoom = desiredRoom;
        GUIView.setSystemResponse("You have moved to the " + currentRoom.getName());
        hasMoved = true;
      }
      
      GUIView.getPlayer(this.name).setBounds(this.getLocation()[0], this.getLocation()[1], GUIView.getPlayer(this.name).getIcon().getIconWidth(), GUIView.getPlayer(this.name).getIcon().getIconHeight());
  }

  //Gives the player a role to work on
  public void chooseRole(String newRole) {

      if(currentRoom.getName().equals("trailer") || currentRoom.getName().equals("office")) {//Checks whether or not they are in the office or trailer
        GUIView.setSystemResponse("You must be in a stage to work a role.");
        return;
      }

      Role desiredRole = Board.convertRole(newRole);//Converts a string to a role object

      if(desiredRole == null) {
        GUIView.setSystemResponse("That role does not exist.");
      } else {
        if(currentRole != null) {
          GUIView.setSystemResponse("Can't choose a role if you already have a role.");
        } else if(!desiredRole.getAvailable()){
          GUIView.setSystemResponse("That role is not available.");
        } else if(level < desiredRole.getLevel()){
          GUIView.setSystemResponse("You aren't a high enough level for that role.");
        } else if(!(currentRoom.getScene().getRoles().contains(desiredRole) || currentRoom.getRoles().contains(desiredRole))){
          GUIView.setSystemResponse("That role is not in this room.");
        } else if(currentRoom.getScene().isWrapped()) {
          GUIView.setSystemResponse("This scene has been wrapped.");
        } else {
          currentRole = desiredRole;
          desiredRole.changeAvailable();
          desiredRole.setPlayer(this);
          GUIView.setSystemResponse("You have taken the " + currentRole.getName() + " role.");
        }
      }
      GUIView.getPlayer(this.name).setBounds(this.getLocation()[0], this.getLocation()[1], GUIView.getPlayer(this.name).getIcon().getIconWidth(), GUIView.getPlayer(this.name).getIcon().getIconHeight());
  }

  public void upgradePlayerCredit(int newLevel) {
      currentRoom.upgradeCredit(this, newLevel);
  }

  public void upgradePlayerMoney(int newLevel) {
      currentRoom.upgradeMoney(this, newLevel);
  }

  public int rollDice() {
      return 1 + (int)(Math.random() * 6);
  }

  public int calcScore() {
      score = credit + money + (5 * level);
      return score;
  }

  public String toString() {//Provides a string version of the player object that gives information about their money, credit, and role

      String result;

      if(currentRole != null) {
          result = name + " ($" + money + ", " + credit + "cr, level " + level + ") working " + currentRole.getName() + " " + "\"" + currentRole.getLine() + "\"";
      }else {
          result = name + " ($" + money + ", " + credit + "cr, level " + level + ")";
      }

      return result;
  }

  public void rehearse(){
    //checks if the player has a role and their accumulated rehearsal markers don't exceed the budget of the scene
    if(currentRole == null){
      GUIView.setSystemResponse("Can't rehearse without a role");
    } else if(hasMoved == true) {
      GUIView.setSystemResponse("You have already moved this turn.");
    } else if(hasWorked == true) {
      GUIView.setSystemResponse("You have already worked.");
    } else if(practiceChips >= currentRoom.getScene().getBudget() - 1){
      GUIView.setSystemResponse("You already have max amount of Rehearsal Markers.");
    } else {
      //add 1 rehearsal marker to the player's count
      practiceChips += 1;
      hasWorked = true;
      GUIView.setSystemResponse("You recieved a practice chip.");
    }
  }

  public void act(){
    //checks if the player has a role

    if(currentRole == null) {
      GUIView.setSystemResponse("Can't act without a role.");
    } else if(hasWorked == true) {
      GUIView.setSystemResponse("You have already acted this turn.");
    } else if(hasMoved == true) {
      GUIView.setSystemResponse("You have already moved this turn.");
    } else {
      hasWorked = true;
      if(!currentRole.getOnBoard()){ //on-card acting
        if(rollDice() + practiceChips >= currentRoom.getScene().getBudget()){ //successful roll
          credit += 2;
          
          currentRoom.take();
          currentRoom.getScene().increaseCounter();
          GUIView.setSystemResponse("Success!");
          GUIView.setSystemResponse("You have earned 2 credits.");

          if(currentRoom.checkWrapped()) {
            currentRoom.wrapScene();
          }

        } else { //unsuccessful roll
          GUIView.setSystemResponse("You failed to role high enough.");
        }

      } else { //off-card acting
        if(rollDice() + practiceChips >= currentRoom.getScene().getBudget()){ //successful roll

          money += 1;
          credit += 1;

          GUIView.setSystemResponse("Success!");
          GUIView.setSystemResponse("You have earned 1 dollar and 1 credit.");
          currentRoom.take();
          currentRoom.getScene().increaseCounter();

          if(currentRoom.checkWrapped()) {
            currentRoom.wrapScene();
          }
        } else { //unsuccessful roll
          money += 1;
          GUIView.setSystemResponse("You failed to role high enough.");
          GUIView.setSystemResponse("You have earned 1 dollar.");
        }
      }
    }
  }
}
