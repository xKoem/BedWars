package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class PlayerDeathListener implements Listener {

    private final BW plugin;

    public PlayerDeathListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent e) {
        return;
    }
}
