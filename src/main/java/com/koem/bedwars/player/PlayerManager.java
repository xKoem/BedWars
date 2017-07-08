package com.koem.bedwars.player;

import com.koem.bedwars.BW;
import com.koem.bedwars.player.TeamManager.TEAM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerManager {

    private final BW plugin;
    private HashMap<Player, BWPlayer> players;


    public PlayerManager(BW plugin) {
        this.plugin = plugin;
    }

    public void loadPlayers() { //TODO: load players on game start
        for (Player p : Bukkit.getOnlinePlayers()) {
            //TODO: check for team select
            players.put(p, new BWPlayer(p, TEAM.BLUE));
        }
    }

    public boolean isTheSameTeam(Player p1, Player p2) {
        return players.get(p1).getTeam().equals(players.get(p2).getTeam());
    }

    public BWPlayer getBWPlayer(Player p) {
        return players.get(p);
    }





}



