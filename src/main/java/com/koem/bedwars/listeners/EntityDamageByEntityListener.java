package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class EntityDamageByEntityListener {

    private final BW plugin;

    public EntityDamageByEntityListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player)){
            return;
        }

        Player p = ((Player) e.getEntity());

        if(p.getHealth() - e.getDamage() >= 1.0) {
            return;
        }

        e.setCancelled(true);
        //TODO: send eq to damager
        Bukkit.broadcastMessage(p.getDisplayName() + " has been killed by " + e.getDamager().getName()); //TODO: check for void

        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(new Location(p.getWorld(), 0, 70, 0)); //TODO: get center map location

        //TODO: wait 5s and chech for teams bed



    }
}
