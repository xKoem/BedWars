package com.koem.bedwars.tasks;


import com.koem.bedwars.BedWars;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */
public class GameTask {

    private final BedWars plugin;
    private byte countdownTime;
    private int gameTime;
    private HashMap<Player,Byte> playersToRespawn;
    private HashMap<Player, Player> playerLastDamager;  //<player, damager>

    public enum GAMESTATE{WAITING, COUNTDOWN, FIGHT}

    private GAMESTATE gameState;

    public GameTask(BedWars plugin) {
        this.plugin = plugin;
        playersToRespawn = new HashMap<>();
        playerLastDamager = new HashMap<>();
        countdownTime = 20;
        gameTime = 0;

        this.gameState = GAMESTATE.WAITING;
        //this.gameState = GAMESTATE.FIGHT;
    }

    public void setGameState(GAMESTATE gameState) {
        this.gameState = gameState;
    }

    public GAMESTATE getGameState() {
        return this.gameState;
    }

    public GAMESTATE checkGAMESTATE(String str) {
        for (GAMESTATE t : GAMESTATE.values()) {
            if (t.name().equalsIgnoreCase(str))
                return t;
        }
        return null;
    }

    public byte getCountdownTime() {
        return countdownTime;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setCountdownTime(byte countdownTime) {
        this.countdownTime = countdownTime;
    }

    public void incGameTime() {
        this.gameTime++;
    }

    public void decTimePlayers() {
        if(playersToRespawn.size() < 1) {
            return;
        }

        for(Player p: playersToRespawn.keySet()) {
            playersToRespawn.replace(p, (byte)(playersToRespawn.get(p)-1));
            if(playersToRespawn.get(p) < 0) {
                playersToRespawn.remove(p);
                System.out.println(p.getName() + " resp");
                //respawn p
                String team = plugin.getPlayerManager().getBWPlayer(p).getTeam().toString();
                Location l = new Location(p.getWorld(),
                        plugin.getSettings().getCfg().getDouble("SPAWN."+ team + ".X"),
                        plugin.getSettings().getCfg().getDouble("SPAWN."+ team + ".Y"),
                        plugin.getSettings().getCfg().getDouble("SPAWN."+ team + ".Z")
                );
                l.setYaw((float) plugin.getSettings().getCfg().getDouble("SPAWN."+ team + ".YAW"));
                p.teleport(l);
                p.setGameMode(GameMode.SURVIVAL);
            }
        }
    }

    public void putPlayerToRespawn(Player p) {
        playersToRespawn.put(p, (byte) 5);
    }

    public Integer getPlayersToRespawnSize() {
        return playersToRespawn.size();
    }


    public void putLastDamager(Player player, Player damager) {
        if(playerLastDamager.get(player) != null) {
            playerLastDamager.replace(player, damager);
            return;
        }
        playerLastDamager.put(player, damager);
    }

    public Player getLastDamager(Player p) {
        return playerLastDamager.get(p);
    }

    public void removeLastDamager(Player p) {
        playerLastDamager.remove(p);
    }
}
