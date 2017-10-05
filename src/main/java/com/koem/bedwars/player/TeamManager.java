package com.koem.bedwars.player;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.teamUpgrades.TeamUpgrades;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */
public class TeamManager {

    private final BedWars plugin;

    HashMap<TEAM, Team> teamList;

    public TeamManager(BedWars plugin, short teamSize) {
        this.plugin = plugin;


        HashMap<TeamUpgrades.TEAM_UPGRADE, Integer> teamUpgrades = new HashMap<>();
        for(TeamUpgrades.TEAM_UPGRADE t: TeamUpgrades.TEAM_UPGRADE.values()) {
            teamUpgrades.put(t, 0);
        }       //generating teamUpgrades map

        Location l = new Location(plugin.getServer().getWorld("world"), 0, 0,0 );  ///TODO: get teams & beds locations

        teamList = new HashMap<>();
        for(TEAM t: TEAM.values()) {
            teamList.put(t, new Team(t, l, l, teamUpgrades));
        }


    }

    public enum TEAM {RED, BLUE, GREEN, YELLOW}

    public static TEAM getTEAM(String str) {
        for (TEAM t : TEAM.values()) {
            if (t.name().equalsIgnoreCase(str))
                return t;
        }
        return null;
    }

    public Team getTeam(TEAM t) {
        return teamList.get(t);
    }

    public TEAM getTheSmallestTeam() {
        Team smallestTeam = null;
        byte smallestValue = 4;
        for (Team t: teamList.values()) {
            System.out.println("   checked:     " + t.getTeam().toString() + "      >    "  + t.getTeamPlayers());
            if(t.getTeamPlayers() < smallestValue) {
                smallestTeam = t;
                smallestValue = t.getTeamPlayers();
            }
        }
        System.out.println("!!! ADDED:     " + smallestTeam + "    >    "  + smallestValue);

        return smallestTeam.getTeam();
    }

    public void randomTeams() {
        HashMap<Player, BWPlayer> players = plugin.getPlayerManager().getBWPlayers();
        for (BWPlayer p: players.values()) {
            if(null != p.getTeam()) {
                continue;
            }
            p.setTeam(getTheSmallestTeam());

            plugin.getPlayerManager().setPlayerColor(p.getPlayer(), p.getTeam());

        }
    }
}
