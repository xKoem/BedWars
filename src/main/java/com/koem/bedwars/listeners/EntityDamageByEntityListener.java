package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by koem on 08/07/2017.
 */

public class EntityDamageByEntityListener implements Listener {

    private final BW plugin;

    public EntityDamageByEntityListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = ((Player) e.getEntity());

        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) e.getDamager();
        if (plugin.getPlayerManager().isTheSameTeam(p, damager)) { //check if same team
            e.setCancelled(true);
        }
        System.out.println(p.getName() + "s last damager: " + damager.getName()); //TODO:TEST
        plugin.getPlayerManager().getBWPlayer(p).setLastDamager(damager);

    }
}
