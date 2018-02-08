package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)){
            return;
        }

        Player p = e.getPlayer();

        Location l = new Location(p.getWorld(),
                plugin.getSettings().getCfg().getDouble("MAP.LOBBY.X"),
                plugin.getSettings().getCfg().getDouble("MAP.LOBBY.Y"),
                plugin.getSettings().getCfg().getDouble("MAP.LOBBY.Z")
        );
        p.teleport(l);

        Bukkit.broadcastMessage(plugin.getSettings().getCfg().getString("PLAYER_JOINED").replace("%player%", p.getDisplayName()));



        plugin.getPlayerManager().createPlayer(p);

        if(Bukkit.getOnlinePlayers().size() >= plugin.getSettings().getCfg().getInt("PLAYERS_TO_COUNTDOWN")) {
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.COUNTDOWN);
        }
    }
}
