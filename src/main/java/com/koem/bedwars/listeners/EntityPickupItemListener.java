package com.koem.bedwars.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.meta.ItemMeta;

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
            return;
        }
        if (event.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Dropped")){
            ItemMeta im = event.getItem().getItemStack().getItemMeta();
            im.setDisplayName(null);
            im.setLore(null);
            event.getItem().getItemStack().setItemMeta(im);
            return;
        }


    }
}
