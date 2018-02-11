package com.koem.bedwars.player;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.TeamManager.TEAM;
import org.bukkit.ChatColor;
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
//            if(p.getDisplayName().toLowerCase().equals("koem"))
//                players.put(p, new BWPlayer(p, TEAM.RED));
//            else
//                players.put(p, new BWPlayer(p, TEAM.BLUE));
//        }
//    }

    public void createPlayer(Player p) {
        players.put(p, new BWPlayer(p, plugin));
    }

    public void removePlayer(Player p) {
        BWPlayer BWp = players.get(p);
        if(null != BWp.getTEAM()){
            TEAM t = BWp.getTEAM();
            Team team = plugin.getTeamManager().getTeam(t);
            team.removePlayer();
        }
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


        System.out.println("p1 team: "+ players.get(p1).getTEAM().toString() + "p2 team: "+ players.get(p2).getTEAM().toString());

        return players.get(p1).getTEAM().equals(players.get(p2).getTEAM());
    }

    public BWPlayer getBWPlayer(Player p) {
        return players.get(p);
    }


    public HashMap<Player, BWPlayer> getBWPlayers() {
        return players;
    }

    public void setPlayerColor(Player p, TEAM t) {
        switch (t) {
            case RED:
                p.setPlayerListName(ChatColor.RED + p.getDisplayName());
                break;
            case GREEN:
                p.setPlayerListName(ChatColor.GREEN + p.getDisplayName());
                break;
            case BLUE:
                p.setPlayerListName(ChatColor.BLUE + p.getDisplayName());
                break;
            case YELLOW:
                p.setPlayerListName(ChatColor.YELLOW + p.getDisplayName());
                break;
            default:
                break;
        }
    }

}



