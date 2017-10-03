package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class EntityDamageListener implements Listener {

    private final BedWars plugin;

    public EntityDamageListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntityDamage(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        if (!plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)) {
            e.setCancelled(true);
            return;
        }

        Player p = (Player) e.getEntity();

        System.out.println(p.getHealth() - e.getDamage());

        if (p.getHealth() - e.getDamage() >= 1.0) {
            return;
        }

        e.setCancelled(true);
        //TODO: send eq to damager
        //TODO: get cause and translate


        BWPlayer bwPlayer = plugin.getPlayerManager().getBWPlayer(p);

        if(null == bwPlayer.getLastDamager()) {
//////////////////////////////////////////no last damager - player made suicide

            Bukkit.broadcastMessage(p.getDisplayName() + " has been killed by with " + e.getCause());

        }else {

//////////////////////////////////////////last damager exist - killed by another player

            BWPlayer bwDamager = plugin.getPlayerManager().getBWPlayer(bwPlayer.getLastDamager());

            Bukkit.broadcastMessage(p.getDisplayName() + " has been killed by " + bwPlayer.getLastDamager().getName() + " with " + e.getCause());

            bwPlayer.setDeaths((short) (bwPlayer.getDeaths() + 1));
            bwDamager.setKills((short) (bwDamager.getKills() + 1));

            bwPlayer.clearLastDamager();
        }

        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(new Location(p.getWorld(), 0, 95, 0)); //TODO: get center map location
        p.setHealth(20.0d);
        p.setFoodLevel(20);

        plugin.getGameTask().putPlayerToRespawn(p);
        p.sendMessage(plugin.getSettings().getCfg().getString("PLAYER_RESPAWN_IN_5"));

    }

}
