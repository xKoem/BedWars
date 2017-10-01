package com.koem.bedwars.player;

import org.bukkit.Location;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */

public class Team {

    private TeamManager.TEAM team;
    private Location spawnLocation;
    private HashMap<TeamUpgrades.TEAM_UPGRADE, Integer> teamUpgrades;
    private Location bedLocation;
    private final byte maxTeamPlayers = 4;
    private byte teamPlayers = 0;


    public Team(TeamManager.TEAM team, Location spawnLocation, Location bedLocation, HashMap<TeamUpgrades.TEAM_UPGRADE, Integer> teamUpgrades) {
        this.team = team;
        this.spawnLocation = spawnLocation;
        this.bedLocation = bedLocation;
        this.teamUpgrades = teamUpgrades;
    }




    public boolean isTeamFull() {
        return teamPlayers >= maxTeamPlayers;
    }

    public void addPlayer() {
        teamPlayers++;
    }

    public void removePlayer() {
        teamPlayers--;
    }

    public TeamManager.TEAM getTeam() {
        return team;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public HashMap<TeamUpgrades.TEAM_UPGRADE, Integer> getTeamUpgrades() {
        return teamUpgrades;
    }

    public void setTeamUpgrades(HashMap<TeamUpgrades.TEAM_UPGRADE, Integer> teamUpgrades) {
        this.teamUpgrades = teamUpgrades;
    }

    public byte getTeamPlayers() {
        return teamPlayers;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

}

