package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerQuitListener implements Listener {

    private final BW plugin;

    public PlayerQuitListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(p.getDisplayName() + " left");

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.COUNTDOWN)) {
            if(Bukkit.getOnlinePlayers().size() < 10) {
                plugin.getGameTask().setGameState(GameTask.GAMESTATE.WAITING);
                //TODO: cancel countdown
            }
        }
    }

}
