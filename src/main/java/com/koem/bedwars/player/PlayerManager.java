package com.koem.bedwars.player;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.TeamManager.TEAM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerManager {

    private final BedWars plugin;
    private HashMap<Player, BWPlayer> players;


    public PlayerManager(BedWars plugin) {
        this.plugin = plugin;
        players = new HashMap<>();
    }

//    public void loadPlayers() { //TODO: load players on game start
//        for (Player p : Bukkit.getOnlinePlayers()) {
//            //TODO: check for team select
//            if(p.getName().toLowerCase().equals("koem"))
//                players.put(p, new BWPlayer(p, TEAM.RED));
//            else
//                players.put(p, new BWPlayer(p, TEAM.BLUE));
//        }
//    }

    public void createPlayer(Player p) {
        players.put(p, new BWPlayer(p));
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }


    public boolean isTheSameTeam(Player p1, Player p2) {
        if(!players.containsKey(p1)) {
            System.out.println("p1 is null");
            return true;
        }
        if(!players.containsKey(p2)) {
            System.out.println("p1 is null");
            return true;
        }//check if spect want to kill somebody xD


        System.out.println("p1 team: "+ players.get(p1).getTeam().toString() + "p2 team: "+ players.get(p2).getTeam().toString());

        return players.get(p1).getTeam().equals(players.get(p2).getTeam());
    }

    public BWPlayer getBWPlayer(Player p) {
        return players.get(p);
    }





}



