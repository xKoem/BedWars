package com.koem.bedwars.player;

import com.koem.bedwars.BedWars;
import org.bukkit.Location;

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

}
