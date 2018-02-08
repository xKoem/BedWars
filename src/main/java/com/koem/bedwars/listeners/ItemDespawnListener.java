package com.koem.bedwars.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

public class ItemDespawnListener implements Listener {

    public ItemDespawnListener() {}

    @EventHandler
    void onItemDespawn(ItemDespawnEvent e) {
//        System.out.println(e.getEntity().getName());
        e.setCancelled(true);
        //e.getEntity().getName();
    }
}
