package com.koem.bedwars.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class EntityDamageByBlockListener implements Listener{

    public EntityDamageByBlockListener() {}

    @EventHandler
    void onEntityDamageByBlock(EntityDamageByBlockEvent e) {
        System.out.println(e.getEntity().getName());
    }
}
