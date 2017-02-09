package com.teamtreehouse.controller;


import com.teamtreehouse.model.League;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.roles.Organizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.teamtreehouse.roles.Coach.printTeamRoster;


public class MainMenu {

    public static League league;
    public static List<Player> players;
    private Scanner scanner;


    private enum RoleMenu{
        ORGANIZER,
        COACH,
        EXIT
    }

    public MainMenu() {
        players = Arrays.asList(Players.load()); // load players array and convert to List.
        Collections.sort(players); // sort players - default way to sort will be Player's compareTo method.
        System.out.printf("There are currently %d registered players.%n", players.size());
        scanner = new Scanner(System.in);
        league = new League();
    }

    public void printRoleSelection() {
        printRoles();

        String userChoice = scanner.next();
        while (! userChoice.toLowerCase().equals("exit")) {
            if (userChoice.toLowerCase().equals("organizer")) {
                Organizer organizer = new Organizer();
                organizer.printOrganizerMenu();
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
        System.exit(0);
    }

    public void printRoles() {
        System.out.println("Please type in one of the following: ");
        for (RoleMenu item : RoleMenu.values()) {
            System.out.println(item);
        }
    }

}
