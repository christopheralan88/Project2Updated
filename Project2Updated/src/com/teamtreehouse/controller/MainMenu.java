package com.teamtreehouse.controller;


import com.teamtreehouse.model.League;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.roles.Organizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.teamtreehouse.roles.Coach.printTeamRoster;


public class MainMenu {

    public static League league;
    public static List<Player> players;
    private BufferedReader reader;
    private Map<String, String> choices;


    private enum RoleMenu{
        ORGANIZER,
        COACH,
        EXIT
    }

    public MainMenu() {
        players = Arrays.asList(Players.load()); // load players array and convert to List.
        Collections.sort(players); // sort players - default way to sort will be Player's compareTo method.
        System.out.printf("There are currently %d registered players.%n", players.size());
        reader = new BufferedReader(new InputStreamReader(System.in));
        league = new League();
        choices = new HashMap<>();
        choices.put("ORGANIZER", "login as an Organizer");
        choices.put("COACH", "login as a Coach");
        choices.put("EXIT", "exit the program");
    }

    public void printRoleSelection() throws IOException {
        String userChoice;
        do {
            printRoles();
            userChoice = reader.readLine();
            if (userChoice.toLowerCase().equals("organizer")) {
                Organizer organizer = new Organizer();
                organizer.run();
            } else if (userChoice.toLowerCase().equals("coach")) {
                System.out.println("Enter the name of the coach who's roster you want to display");
                String coachName = reader.readLine();
                printTeamRoster(coachName);
            }
        } while (! userChoice.toLowerCase().equals("exit"));
        System.exit(0);

        /*String userChoice = scanner.next();
        while (! userChoice.toLowerCase().equals("exit")) {
            if (userChoice.toLowerCase().equals("organizer")) {
                Organizer organizer = new Organizer();
                organizer.run();
                printRoles(); //after above method is exited, display MainMenu roles to user for them to choose.
                userChoice = scanner.next();
            } else if (userChoice.toLowerCase().equals("coach")) {
                System.out.println("What coach's roster do you want to display?");
                String coachName = scanner.next();
                printTeamRoster(coachName);
                printRoles(); //after above method is exited, display MainMenu roles to user for them to choose.
                userChoice = scanner.next();
            } else {
                System.out.println("That was not a menu choice.  Please try again.");
                userChoice = scanner.next();
            }
        }
        System.exit(0);*/
    }

    public void printRoles() {
        System.out.println("Choose an option below: ");
        for (Map.Entry<String, String> entry : choices.entrySet()) {
            System.out.printf("Choose %s to %s %n", entry.getKey(), entry.getValue());
        }
    }

}
