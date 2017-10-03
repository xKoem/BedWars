package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by koem on 08/07/2017.
 */

public class EntityDamageByEntityListener implements Listener {

    private final BedWars plugin;

    public EntityDamageByEntityListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        if(!(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT))) {
            e.setCancelled(true);
            return;
        }

        Player p = ((Player) e.getEntity());

        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        //damager and player instance of Player

        Player damager = (Player) e.getDamager();

        if (plugin.getPlayerManager().isTheSameTeam(p, damager)) { //check if same team
            e.setDamage(0d);
            e.setCancelled(true);
            return;
        }

        plugin.getPlayerManager().getBWPlayer(p).setLastDamager(damager);

    }
}
