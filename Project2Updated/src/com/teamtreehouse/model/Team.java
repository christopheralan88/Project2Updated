package com.teamtreehouse.model;


import java.util.Set;
import java.util.TreeSet;

public class Team implements Comparable<Team> {

    private String teamName;
    private String coach;
    private Set<Player> players = new TreeSet<>();
    private final int MAX_PLAYERS = 11;


    public Team(String teamName, String coach){
        this.teamName = teamName;
        this.coach = coach;
    }

    @Override
    public int compareTo(Team other) {
        // We always want to sort by team name
        if (this.equals(other)) {
            return 0 ;
        }
        return this.teamName.compareTo(other.teamName);
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        if (teamName != team.teamName) return false;
        if (coach != team.coach) return false;
        return players.equals(team.players);
    }*/

    public String getCoach() {
        return coach;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) {
            System.out.printf("This team is full.  %d players are already assigned to the team. %n", MAX_PLAYERS);
            return;
        }

        if (getPlayers().add(player)) {
            // if player is added successfully (there were no duplicates in TreeSet, then add() method returns true.
            System.out.println("Player added successfully!");
        } else {
            System.out.println("The player has already been added to the team.");
        }
    }

    public void removePlayer(Player player) {
        // if player is removed successfully, then remove() method returns true.
        if (getPlayers().remove(player)) {
            System.out.println("Player removed successfully!");
        } else {
            System.out.println("The player does not exist on the team's roster, so the player could not be removed");
        }
    }
}
