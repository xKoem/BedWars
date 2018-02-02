package com.koem.bedwars.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EntityPickupItemListener implements Listener{

    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent event) {
        if (event.getItem().getItemStack().getItemMeta() == null){
            return;
        }

        if (event.getItem().getItemStack().getItemMeta().getDisplayName() == null){
            return;
        }

        if (event.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "ItemDropper")){
            event.setCancelled(true);
        }

    }
}
