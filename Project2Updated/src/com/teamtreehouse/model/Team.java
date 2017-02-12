package com.teamtreehouse.model;


import java.util.Set;
import java.util.TreeSet;

public class Team implements Comparable<Team> {

    private String teamName;
    private String coach;
    private Set<Player> players = new TreeSet<>();
    public static final int MAX_PLAYERS = 11;


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

    public boolean addPlayer(Player player) {
        if (getPlayers().add(player)) {
            // if player is added successfully (there were no duplicates in TreeSet), then add() method returns true.
            return true;
        } else {
            return false; // the player is already on the team roster.
        }
    }

    public boolean removePlayer(Player player) {
        if (getPlayers().remove(player)) {
            return true;
        } else {
            return false;
        }
    }
}
