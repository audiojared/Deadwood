import java.util.*;
import javax.swing.*;

public class Office extends Room{

   static final int[][] upgrades = {{4,10,18,28,40},{5,10,15,20,25}};
   public Office(String name, ArrayList<String> neighbors, int[] coordinates){
      super(name, neighbors, coordinates);
   }
   public void upgradeCredit(Player p, int newLevel){
      if(newLevel <= 1 || newLevel > 6) {
         GUIView.setSystemResponse("Desired level must be between 1 and 6.");
      } 
      else if(newLevel <= p.getLevel()) {
         GUIView.setSystemResponse("You must choose a level that is greater than your current one.");
      } 
      else {
      //Checks the array that compares the credit price to their number of credits
         if(p.getCredit() >= upgrades[1][newLevel - 2]) {
            p.exchangeCredit(-upgrades[1][newLevel - 2]);
            p.setLevel(newLevel);
            GUIView.setSystemResponse("You are now level " + newLevel);
         
            String location = "";
            ImageIcon icon;
            if (p.getName().toLowerCase().equals("blue"))
            {
               location = "dice/b" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
            else if (p.getName().toLowerCase().equals("green"))
            {
               location = "dice/g" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
            else if (p.getName().toLowerCase().equals("yellow"))
            {
               location = "dice/y" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
            else if (p.getName().toLowerCase().equals("red"))
            {
               location = "dice/r" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
         } 
         else {
            GUIView.setSystemResponse("You can't afford that level.");
         }
      }
   }
   public void upgradeMoney(Player p, int newLevel){
      if(newLevel <= 1 || newLevel > 6) {
         GUIView.setSystemResponse("Desired level must be between 1 and 6.");
      } 
      else {
         if(p.getMoney() >= upgrades[0][newLevel - 2]) {//Checks the array that compares the dollar price to their number of dollars
            p.exchangeMoney(-upgrades[1][newLevel - 2]);
            p.setLevel(newLevel);
            GUIView.setSystemResponse("You are now level " + newLevel);
         
            String location = "";
            ImageIcon icon;
            if (p.getName().toLowerCase().equals("blue"))
            {
               location = "dice/b" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
            else if (p.getName().toLowerCase().equals("green"))
            {
               location = "dice/g" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
            else if (p.getName().toLowerCase().equals("yellow"))
            {
               location = "dice/y" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
            else if (p.getName().toLowerCase().equals("red"))
            {
               location = "dice/r" + newLevel + ".png";
               icon = new ImageIcon(location);
               GUIView.getPlayer(p.getName()).setIcon(icon);
               GUIView.getPlayer(p.getName()).setBounds(p.getLocation()[0], p.getLocation()[1], icon.getIconWidth(), icon.getIconHeight());
            }
         } 
         else {
            GUIView.setSystemResponse("You can't afford that level.");
         }
      }
   }
}
