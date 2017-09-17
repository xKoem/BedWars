package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerJoinListener implements Listener {

    private final BedWars plugin;

    public PlayerJoinListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + " joined");
        if(Bukkit.getOnlinePlayers().size() < 2) {
            return;
        }
        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.WAITING)) {
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.FIGHT);
            //TODO: begin countdown;
            plugin.getPlayerManager().loadPlayers(); //TODO:TEST if players in same team can hit each other
        }


    }
}
