import com.teamtreehouse.controller.MainMenu;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;

import java.io.IOException;


public class LeagueManager {

  public static MainMenu mainMenu;
  //public static Player[] players;


  public static void main(String[] args) throws IOException {
    //players = Players.load();
    //System.out.printf("There are currently %d registered players.%n", players.length);
    mainMenu = new MainMenu();
    mainMenu.printRoleSelection();
  }

}
