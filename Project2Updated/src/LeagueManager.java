import com.teamtreehouse.controller.MainMenu;


import java.io.IOException;


public class LeagueManager {

  public static MainMenu mainMenu;


  public static void main(String[] args) throws IOException {
    mainMenu = new MainMenu();
    mainMenu.printRoleSelection();
  }

}
