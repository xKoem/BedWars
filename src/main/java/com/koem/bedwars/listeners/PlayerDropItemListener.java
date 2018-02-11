package com.koem.bedwars.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    public PlayerDropItemListener() {}

    @EventHandler
    void onPlayerDropItem(PlayerDropItemEvent e) {
        if(e.getItemDrop().getItemStack().getType().equals(Material.WOOD_SWORD)) {
            e.setCancelled(true);
            return;
        }
        if(e.getItemDrop().getItemStack().getType().equals(Material.STONE_SWORD)) {
            e.setCancelled(true);
            return;
        }
        if(e.getItemDrop().getItemStack().getType().equals(Material.IRON_SWORD)) {
            e.setCancelled(true);
            return;
        }
        if(e.getItemDrop().getItemStack().getType().equals(Material.DIAMOND_SWORD)) {
            e.setCancelled(true);
            return;
        }
    }
}
