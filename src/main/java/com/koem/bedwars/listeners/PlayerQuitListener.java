package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
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

    private final BedWars plugin;

    public PlayerQuitListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(p.getDisplayName() + " left");

        if(!(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT))) {
            plugin.getPlayerManager().removePlayer(p);
        }

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.COUNTDOWN)) {
            if(Bukkit.getOnlinePlayers().size() < 2) {
                plugin.getGameTask().setGameState(GameTask.GAMESTATE.WAITING);
                plugin.getGameTask().setCountdownTime((byte) 20);
            }
        }
    }

}
