//*****************************************************************************
// The Board class is the main class in the model. It contains most of the
// logic pertaining to the game. It also contains the data for most of the game
// such as the scenes and players.
//*****************************************************************************
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.swing.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.io.*;


public class Board
{
   //--------------------------------------------------------------------------
   //Sets up the fields required for the board
   //--------------------------------------------------------------------------
   private static int day;
   private static int maxDay;
   private static ArrayList<Player> allPlayers = new ArrayList<Player>();
   private static HashMap<String,Room> RoomMap = new HashMap<String,Room>();
   private static ArrayList<Scene> allScenes = new ArrayList<>();
   private static Player currentPlayer = null;
   private static int playerNumber = 0;
   private static int sceneCounter = 10;

   //--------------------------------------------------------------------------
   //The constructor takes in the number of players as an argument and applies
   //the aplicable rules.
   //--------------------------------------------------------------------------
   public Board(int numPlayers) throws Throwable
   {
     boardParser();
     cardParser();
     Collections.shuffle(allScenes);

      if(numPlayers == 2) {
         day = 1;
         maxDay = 3;
         allPlayers.add(new Player(RoomMap.get("trailer"), "Blue"));
         ImageIcon bIcon = new ImageIcon("dice/b1.png");
         JLabel blue = new JLabel(bIcon);
         blue.setBounds(allPlayers.get(0).getLocation()[0], allPlayers.get(0).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(0).getName(), blue);

         allPlayers.add(new Player(RoomMap.get("trailer"), "Green"));
         ImageIcon gIcon = new ImageIcon("dice/g1.png");
         JLabel green = new JLabel(gIcon);
         green.setBounds(allPlayers.get(1).getLocation()[0], allPlayers.get(1).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(1).getName(), green);
      } else if(numPlayers == 3) {
         day = 1;
         maxDay = 3;
         allPlayers.add(new Player(RoomMap.get("trailer"), "Blue"));
         ImageIcon bIcon = new ImageIcon("dice/b1.png");
         JLabel blue = new JLabel(bIcon);
         blue.setBounds(allPlayers.get(0).getLocation()[0], allPlayers.get(0).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(0).getName(), blue);

         allPlayers.add(new Player(RoomMap.get("trailer"), "Green"));
         ImageIcon gIcon = new ImageIcon("dice/g1.png");
         JLabel green = new JLabel(gIcon);
         green.setBounds(allPlayers.get(1).getLocation()[0], allPlayers.get(1).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(1).getName(), green);

         allPlayers.add(new Player(RoomMap.get("trailer"), "Yellow"));
         ImageIcon yIcon = new ImageIcon("dice/y1.png");
         JLabel yellow = new JLabel(yIcon);
         yellow.setBounds(allPlayers.get(2).getLocation()[0], allPlayers.get(2).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(2).getName(), yellow);
      } else {
         day = 1;
         maxDay = 4;
         allPlayers.add(new Player(RoomMap.get("trailer"), "Blue"));
         ImageIcon bIcon = new ImageIcon("dice/b1.png");
         JLabel blue = new JLabel(bIcon);
         blue.setBounds(allPlayers.get(0).getLocation()[0], allPlayers.get(0).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(0).getName(), blue);

         allPlayers.add(new Player(RoomMap.get("trailer"), "Green"));
         ImageIcon gIcon = new ImageIcon("dice/g1.png");
         JLabel green = new JLabel(gIcon);
         green.setBounds(allPlayers.get(1).getLocation()[0], allPlayers.get(1).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(1).getName(), green);

         allPlayers.add(new Player(RoomMap.get("trailer"), "Yellow"));
         ImageIcon yIcon = new ImageIcon("dice/y1.png");
         JLabel yellow = new JLabel(yIcon);
         yellow.setBounds(allPlayers.get(2).getLocation()[0], allPlayers.get(2).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(2).getName(), yellow);

         allPlayers.add(new Player(RoomMap.get("trailer"), "Red"));
         ImageIcon rIcon = new ImageIcon("dice/r1.png");
         JLabel red = new JLabel(rIcon);
         red.setBounds(allPlayers.get(3).getLocation()[0], allPlayers.get(3).getLocation()[0], 40, 40);
         GUIView.addPlayer(allPlayers.get(3).getName(), red);
      }

    currentPlayer = allPlayers.get(0);
    startDay();
   }//Ends constructor

   static public void startDay() {
     for(Player i : allPlayers) {//Places all the players in the trailer
       i.startDay(RoomMap.get("trailer"));
     }
     for(String key : RoomMap.keySet()) {//Skip over the trailer and office rooms so there is no attempt to get the roles on those rooms
       if(key.equals("trailer") || key.equals("office")) {
         continue;
       }
       for(Role i : RoomMap.get(key).getRoles()){//Makes the roles on the board available again
         i.setAvailable(true);
       }
       RoomMap.get(key).setScene(allScenes.get(0));//Gives the rooms new scenes
       allScenes.remove(0);
       ImageIcon icon = new ImageIcon(RoomMap.get(key).getScene().getCardImage());
       JLabel card = new JLabel(icon);
       card.setBounds(RoomMap.get(key).getCoordinates()[0], RoomMap.get(key).getCoordinates()[1], icon.getIconWidth(), icon.getIconHeight());
       GUIView.addCard(RoomMap.get(key).getScene().getNumber(), card);
     }
     sceneCounter = 10;
     GUIView.resetTakes();
   }

   public static void endDay() {
     //iterates through rooms
     for(String key : RoomMap.keySet()) {
       if(key.equals("trailer") || key.equals("office")) {
         continue;
       }
       //checks for wrapped scenes
       if(!RoomMap.get(key).getScene().isWrapped()) {
         GUIView.removeCard(RoomMap.get(key).getScene().getNumber());
         //iterates through players in the room
         for(Role i : RoomMap.get(key).getRoles()) {
           if(i != null){
             i.setPlayer(null);
           }
         }
       }
     }
     GUIView.setSystemResponse("The day has ended.");
     day++;
     if(day > maxDay) {
       endGame();
     }
     endTurn();
     startDay();
   }


   public static void endTurn() {
     currentPlayer.endTurn();
     playerNumber++;
     if(playerNumber == allPlayers.size()) {//Resets the player number if the end of the player list is reached
       playerNumber = 0;
     }
     currentPlayer = allPlayers.get(playerNumber);
     GUIView.setSystemResponse("It is " + currentPlayer.getName() + "'s turn.");
   }

   public static void decreaseSceneCounter() {
     sceneCounter--;
     if(sceneCounter == 1) {
       endDay();
     }
   }

  //Ends the game and finds the winner based on their score
  public static void endGame(){
    String winner = "";
    int points = 0;
    int tempPoints = 0;
    for(Player p : allPlayers){
      tempPoints = p.calcScore();
      GUIView.setSystemResponse(p.getName() + (" has " + tempPoints + " points!"));
      System.out.println(p.getName() + (" has " + tempPoints + " points!"));
      if (tempPoints >= points){
        points = tempPoints;
        winner = p.getName();
      }
    }
  GUIView.setSystemResponse(winner + " is the winner!");
  System.out.println(winner + " is the winner!");
  System.exit(0);
  }

   public static Player getPlayer() {
     return currentPlayer;
   }

   //Convert the string to a role object
   public static Role convertRole(String role) {
     Role desiredRole = null;

     for(Role i : currentPlayer.getCurrentRoom().getRoles()) {//Checks the list of on the board roles and tries to find the one with the same name as the given string
       if(role.equals(i.getName())) {
         desiredRole = i;
       }
     }

     for(Role i : currentPlayer.getCurrentRoom().getScene().getRoles()) {//Checks the list of on the card roles and tries to find the one with the same name as the given string
       if(role.equals(i.getName())) {
         desiredRole = i;
       }
     }

     return desiredRole;
   }

   public static Room convertRoom(String room) {
     return RoomMap.get(room);
   }

   //parses out the board xml file and places each room into a hashmap, and makes objects for each room and role.
   public static void boardParser() throws Throwable{
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse("board.xml");

      NodeList children  = doc.getElementsByTagName("set");
      //loops through each set
      int takeNumber  = 0;
      ImageIcon icon;
      JLabel label;
      for(int i = 0; i < children.getLength(); ++i) {
         Element set = (Element)children.item(i);
         NodeList parts = set.getElementsByTagName("part");
         NodeList takes = set.getElementsByTagName("takes");
         NodeList lines = set.getElementsByTagName("line");
         //room area
         Element area = (Element)set.getElementsByTagName("area").item(0);
         int[] roomCoordinates = new int[2];
         roomCoordinates[0] = Integer.parseInt(area.getAttribute("x"));
         roomCoordinates[1] = Integer.parseInt(area.getAttribute("y"));

         int[][] takesCoordinates = new int[3][4];
         ArrayList<Role> roles = new ArrayList<>();
         //gets coordinates for the takes.
         for(int j = 0; j < takes.getLength(); ++j) {
            Element take = (Element)takes.item(j);
            for (int g = 0; g < take.getElementsByTagName("area").getLength(); g++){
               Element takearea = (Element)take.getElementsByTagName("area").item(g);
               icon = new ImageIcon("shot.png");
               label = new JLabel(icon);
               label.setBounds(Integer.parseInt(takearea.getAttribute("x")), Integer.parseInt(takearea.getAttribute("y")), icon.getIconWidth(), icon.getIconHeight());
               GUIView.addTake(takeNumber, label);
               takeNumber++;

               takesCoordinates[g][0] =  Integer.parseInt(takearea.getAttribute("x"));
               takesCoordinates[g][1] =  Integer.parseInt(takearea.getAttribute("y"));
               takesCoordinates[g][2] =  Integer.parseInt(takearea.getAttribute("h"));
               takesCoordinates[g][3] =  Integer.parseInt(takearea.getAttribute("w"));
            }
         }
         //initiates each room's roles, and puts them in one arraylist.
         for(int j = 0; j < parts.getLength(); ++j) {
            Element line = (Element)lines.item(j);
            Element part = (Element)parts.item(j);
            //role area
            int[] roleCoordinates = new int[2];
            Element roleArea = (Element)part.getElementsByTagName("area").item(0);
            roleCoordinates[0] = Integer.parseInt(roleArea.getAttribute("x"));
            roleCoordinates[1] = Integer.parseInt(roleArea.getAttribute("y"));
            Role temp_role = new Role(part.getAttribute("name").toLowerCase(), true, Integer.parseInt(part.getAttribute("level")), true, null, line.getTextContent(), roleCoordinates);
            roles.add(temp_role);
         }

         NodeList neighbors = set.getElementsByTagName("neighbor");
         ArrayList<String> neighbor_temp = new ArrayList<>();
         //initiates each room and puts them in one hashmap.
         for(int j = 0; j < neighbors.getLength(); ++j) {
            Element neighbor = (Element)neighbors.item(j);
            neighbor_temp.add(neighbor.getAttribute("name").toLowerCase());
         }
         int takeLength = set.getElementsByTagName("take").getLength();
         Stage s = new Stage(set.getAttribute("name").toLowerCase(),neighbor_temp, roomCoordinates, null, roles, takeLength, takesCoordinates);
         RoomMap.put(set.getAttribute("name").toLowerCase(), s);
      }

      //office and trailer are stored differently than the rest of the rooms in the xml file, but i want them stored in the same hashmap, so this manually adds them.
      int[] office_coordinates = new int[2];
      ArrayList<String> office_neighbors = new ArrayList<String>();
      office_coordinates[0] = 9;
      office_coordinates[1] = 459;
      office_neighbors.add("train station");
      office_neighbors.add("ranch");
      office_neighbors.add("secret hideout");
      Room office = new Office("office",office_neighbors, office_coordinates);
      RoomMap.put("office", office);

      int[] trailer_coordinates = new int[2];
      ArrayList<String> trailer_neighbors = new ArrayList<String>();
      trailer_coordinates[0] = 991;
      trailer_coordinates[1] = 248;
      trailer_neighbors.add("main street");
      trailer_neighbors.add("saloon");
      trailer_neighbors.add("hotel");
      Room trailer = new Room("trailer",trailer_neighbors, trailer_coordinates);
      RoomMap.put("trailer", trailer);
   }
   //parses out the card xml file, and sorts the cards into an arraylist. Initiates scene and role objects.
   public static void cardParser() throws Throwable{
      int cardCount = 0;
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse("cards.xml");

      NodeList children  = doc.getElementsByTagName("card");
      //goes through each card
      for(int i = 0; i < children.getLength(); ++i) {
         cardCount++;
         Element card = (Element)children.item(i);
         NodeList parts = card.getElementsByTagName("part");
         Element scene = (Element)card.getElementsByTagName("scene").item(0);

         NodeList lines = card.getElementsByTagName("line");
         ArrayList<Role> roles = new ArrayList<>();
         //goes through each role in the card and places them into an arraylist held by the card
         for(int j = 0; j < parts.getLength(); ++j) {
            Element line = (Element)lines.item(j);
            Element part = (Element)parts.item(j);

            int[] roleCoordinates = new int[2];

            Element roleArea = (Element)part.getElementsByTagName("area").item(0);
            roleCoordinates[0] = Integer.parseInt(roleArea.getAttribute("x"));
            roleCoordinates[1] = Integer.parseInt(roleArea.getAttribute("y"));
            Role temp_role = new Role(part.getAttribute("name").toLowerCase(), true, Integer.parseInt(part.getAttribute("level")), false, null, line.getTextContent(), roleCoordinates);
            roles.add(temp_role);
         }
         Scene temp_scene = new Scene(card.getAttribute("name"), 0, Integer.parseInt(card.getAttribute("budget")), roles, false, false, Integer.toString(cardCount));
         allScenes.add(temp_scene);
      }
   }
}//Ends Board class
