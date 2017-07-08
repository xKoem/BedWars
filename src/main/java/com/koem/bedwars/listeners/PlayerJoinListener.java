package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerJoinListener implements Listener {

    private final BW plugin;

    public PlayerJoinListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + " joined");
        if(Bukkit.getOnlinePlayers().size() < 10) {
            return;
        }
        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.WAITING)) {
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.COUNTDOWN);
            //TODO: begin countdown;
            plugin.getPlayerManager().loadPlayers(); //TODO:TEST if players in same team can hit each other

        }


    }
}
