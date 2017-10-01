package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + " dolaczyl");

        plugin.getPlayerManager().createPlayer(p);

        if(Bukkit.getOnlinePlayers().size() > 1) {
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.COUNTDOWN);
        }


    }
}
