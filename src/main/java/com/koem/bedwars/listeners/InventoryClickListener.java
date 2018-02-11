package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClickListener implements Listener{


    public InventoryClickListener() {

    }

    @EventHandler
    void onInventoryClick(InventoryClickEvent e) {
        if(e.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
            e.setCancelled(true);
            return;
        }
        if(0 == e.getSlot()) {
            e.setCancelled(true);
            return;
        }

    }
}
