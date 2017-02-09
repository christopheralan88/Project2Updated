package com.teamtreehouse.roles;


import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import static com.teamtreehouse.controller.MainMenu.league;

public class Coach {

    private String name;


    public Coach(String name) {
        this.name = name;
    }

    public static void printTeamRoster(String coachName) {
        for (Team team : league.teams) {
            if (team.getCoach().toLowerCase().equals(coachName.toLowerCase())) {
                System.out.println("Last Name      First Name       Height In Inches        Prev Exp");
                for (Player player : team.getPlayers()) {
                    System.out.printf("%-15s%-17s%-24d%-9s %n", player.getLastName(), player.getFirstName(), player.getHeightInInches(),
                        player.isPreviousExperience());
                }
                return;
            }
        }
        System.out.println("Sorry that coach does not exist.");
    }
}
