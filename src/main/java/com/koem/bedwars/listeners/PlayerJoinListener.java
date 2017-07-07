package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerJoinListener implements Listener{

    private final BW plugin;

    public PlayerJoinListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + " joined");
    }
}
