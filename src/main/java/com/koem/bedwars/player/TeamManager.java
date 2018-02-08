package com.koem.bedwars.player;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.teamUpgrades.TeamUpgrades;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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


        FileConfiguration cfg = plugin.getSettings().getCfg();



        teamList = new HashMap<>();
        for(TEAM t: TEAM.values()) {


            Float yaw = (float) plugin.getSettings().getCfg().getDouble("SPAWN."+t.name()+".YAW");
            Location spawn = new Location(plugin.getServer().getWorld(cfg.getString("WORLD_NAME")),
                    cfg.getDouble("SPAWN."+t.name()+".X"),
                    cfg.getDouble("SPAWN."+t.name()+".Y"),
                    cfg.getDouble("SPAWN."+t.name()+".Z"));
            spawn.setYaw(yaw);
            System.out.println(spawn);
            Location bed = new Location(plugin.getServer().getWorld(cfg.getString("WORLD_NAME")),
                    cfg.getDouble("BED."+t.name()+".X"),
                    cfg.getDouble("BED."+t.name()+".Y"),
                    cfg.getDouble("BED."+t.name()+".Z"));
            bed.setYaw(yaw);

            teamList.put(t, new Team(t, spawn, bed, teamUpgrades));
        }

        spawnBeds();  //TODO: do wywalenia


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

    public Team getTeam(Location bedLocation) {
        for(Team t: teamList.values()) {
            if(t.getBedLocation().distance(bedLocation) < 5)
                return t;
        }
        return null;
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
            if(null != p.getTEAM()) {
                continue;
            }
            p.setTeam(getTheSmallestTeam());

            plugin.getPlayerManager().setPlayerColor(p.getPlayer(), p.getTEAM());

        }
    }

    private void spawnBeds() {
        for(TEAM team: teamList.keySet()) {
            Location l = teamList.get(team).getBedLocation();
            l.getBlock().setType(Material.BED_BLOCK);

        }
    }
}
