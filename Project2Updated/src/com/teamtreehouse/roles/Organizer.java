package com.teamtreehouse.roles;


import com.teamtreehouse.controller.MainMenu;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.*;


public class Organizer {

    private Scanner scanner;


    private enum OrganizerMenu {
        CREATE_TEAM,
        ADD_PLAYER,
        REMOVE_PLAYER,
        TEAM_HEIGHT_REPORT,
        LEAGUE_BALANCE_REPORT,
        EXIT
    }

    public Organizer() {
        scanner = new Scanner(System.in);
    }

    public void printOrganizerMenu() {
        promptForChoice();

        String userChoice = scanner.next();
        while (! userChoice.toLowerCase().equals("exit")) { // exit
            if (userChoice.toLowerCase().equals("create_team")) { // create team
                System.out.println("What is the name of the team?");
                String teamName = scanner.next();
                System.out.println("What is the coach's name?");
                String coach = scanner.next();
                createTeam(teamName, coach);
                promptForChoice();
                userChoice = scanner.next();
            } else if (userChoice.toLowerCase().equals("add_player")) { // add player
                printTeams();
                System.out.println("Choose the index of the team you want to add a player to");
                int teamIndex = scanner.nextInt();
                printAllPlayers();
                System.out.println("Type the index of the player you want to add");
                int playerIndex = scanner.nextInt();
                addPlayer(teamIndex, playerIndex);
                promptForChoice();
                userChoice = scanner.next();
            } else if (userChoice.toLowerCase().equals("remove_player")) { // remove player
                printTeams();
                System.out.println("Choose the index of the team you want to remove a player from");
                int teamIndex = scanner.nextInt();
                printTeamPlayers(teamIndex);
                System.out.println("Choose the index of a player you want to remove");
                int playerIndex = scanner.nextInt();
                removePlayer(teamIndex, playerIndex);
                promptForChoice();
                userChoice = scanner.next();
            } else if (userChoice.toLowerCase().equals("team_height_report")) { // team height report
                printTeams();
                System.out.println("Choose the index of the team you want to run the report for");
                int teamIndex = scanner.nextInt();
                printTeamHeightReport(teamIndex);
                promptForChoice();
                userChoice = scanner.next();
            } else if (userChoice.toLowerCase().equals("league_balance_report")) {
                printLeagueBalanceReport();
                promptForChoice();
                userChoice = scanner.next();
            } else {
                System.out.println("That was not a menu choice.  Please try again.");
                userChoice = scanner.next();
            }
        }
    }

    private void printLeagueBalanceReport() {
        System.out.println("Team            Experienced     Not Experienced");
        List<Team> teams = MainMenu.league.teams;
        for (int i=0; i < teams.size(); i++) {

            Set<Player> players = teams.get(i).getPlayers();
            //Map<String, Integer> expMap = players.stream().collect(Collectors.groupingBy(Player::isPreviousExperience));
            Map<String, Integer> expMap = new HashMap<>();

            for (Player player : players) {
                String exp = expGroup(player);
                expMap.put(exp, expMap.getOrDefault(exp, 0) + 1);
            }

            System.out.printf("%-16s%-16s%-15d %n", teams.get(i).getTeamName(), expMap.getOrDefault("Experienced", 0),
                    expMap.getOrDefault("Not Experienced", 0));
        }
    }

    /* the expMap variable in the printLeagueBalanceReport cannot use primitive types, so I have to make the boolean values
      strings in the method below. */
    private String expGroup(Player player) {
        if (player.isPreviousExperience()) {
            return "Experienced";
        } else {
            return "Not Experienced";
        }
    }

    private void printTeamHeightReport(int teamIndex) {
        // get team's players
        Set<Player> players = MainMenu.league.teams.get(teamIndex).getPlayers();
        Map<String, List<Player>> heightMap = new HashMap<>();

        for (Player player : players) {
            String height = heightGroup(player);
            // get map's current player list.  If map's get method returns null, then exception is caught.
            List<Player> newList;
            try {
                newList = new ArrayList<>(heightMap.get(height));
            } catch (NullPointerException npe) {
                newList = new ArrayList<>();
            }

            // add player to list.
            newList.add(player);

            // sort list before adding it to map.
            Collections.sort(newList);

            // put key-value pair of height and newList in map.
            heightMap.put(height, newList );
        }

        // 35-40 group
        System.out.println("35-40");
        System.out.println("Last Name       First Name");
        try {
            for (Player player : heightMap.get("35-40")) {
                System.out.printf("%-16s%-16s %n", player.getLastName(), player.getFirstName());
            }
        } catch (NullPointerException npe) {
            System.out.printf("%-16s%-16s %n", "none", "none");
        }

        // 41-46 group
        System.out.printf("%n41-46%n");
        System.out.println("Last Name       First Name");
        try {
            for (Player player : heightMap.get("41-46")) {
                System.out.printf("%-16s%-16s %n", player.getLastName(), player.getFirstName());
            }
        } catch (NullPointerException npe) {
            System.out.printf("%-16s%-16s %n", "none", "none");
        }

        // 47-50 group
        System.out.printf("%n47-50%n");
        System.out.println("Last Name       First Name");
        try {
            for (Player player : heightMap.get("47-50")) {
                System.out.printf("%-16s%-16s %n", player.getLastName(), player.getFirstName());
            }
        } catch (NullPointerException npe) {
            System.out.printf("%-16s%-16s %n%n", "none", "none");
        }

    }

    private String heightGroup(Player player) {
        int height = player.getHeightInInches();
        if (height >= 35 && height <= 40) {
            return "35-40";
        } else if (height >= 41 && height <= 46) {
            return "41-46";
        } else {
            return "47-50";
        }
    }

    private void promptForChoice() {
        System.out.println("Please type in one of the following: ");
        for (OrganizerMenu item : OrganizerMenu.values()) {
            System.out.println(item);
        }
    }

    private void printAllPlayers() {
        List<Player> players = Arrays.asList(Players.load());
        Collections.sort(players);

        System.out.println("Index      Last Name      First Name       Height In Inches        Prev Exp");
        for (int i=0; i < players.size(); i++) {
            System.out.printf("%-11d%-15s%-17s%-24d%-16s %n", i,
                    players.get(i).getLastName(), players.get(i).getFirstName(),
                    players.get(i).getHeightInInches(), players.get(i).isPreviousExperience());
        }
    }

    private void createTeam(String teamName, String coach) {
        /* create new array from Players class because the players field in the MainMenu class won't have an accurate
        count of all players once players are added to teams. */
        Player[] players = Players.load();

        // check that the number of teams is less than the number of available players.
        if (MainMenu.league.teams.size() < players.length) {
            Team newTeam = new Team(teamName, coach);
            MainMenu.league.teams.add(newTeam);
            System.out.println("Team created successfully!");
        }
    }

    private void addPlayer(int teamIndex, int playerIndex) {
        try {
            Team team = MainMenu.league.teams.get(teamIndex);
            team.addPlayer(MainMenu.players.get(playerIndex));
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("That team or player index does not exist");
        }
    }

    private void printTeams() {
        System.out.println("League teams: ");
        System.out.println("Index       Team Name");
        List<Team> teams = MainMenu.league.teams;
        Collections.sort(teams);
        for (int i=0; i < teams.size(); i++) {
            System.out.printf("%-12d%-15s %n", i, teams.get(i).getTeamName());
        }
    }

    private void printTeamPlayers(int teamIndex) {
        try {
            Player[] players = MainMenu.league.teams.get(teamIndex).getPlayers().toArray(new Player[0]);  //TODO:  simpler way to do this?
            System.out.println("Index      Last Name      First Name       Height In Inches        Prev Exp");
            for (int i=0; i < players.length; i++) {
                System.out.printf("%-11d%-15s%-17s%-24d%-16s %n", i, players[i].getLastName(), players[i].getFirstName(), players[i].getHeightInInches(),
                        players[i].isPreviousExperience());
            }
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("That team index does not exist.");
        }
    }

    private void removePlayer(int teamIndex, int playerIndex) {
        try {
            Team team = MainMenu.league.teams.get(teamIndex);

            // array of team's players
            Player[] players = team.getPlayers().toArray(new Player[0]);

            // get player that organizer wants to remove from team
            Player player = players[playerIndex];

            // remove player from team
            team.removePlayer(player);

        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("That player index does not exist");
        }
    }
}
