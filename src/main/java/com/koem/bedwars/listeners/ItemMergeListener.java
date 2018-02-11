package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.ItemStack;

public class ItemMergeListener implements Listener {

    private final BedWars plugin;

    public ItemMergeListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onItemMerge(ItemMergeEvent e) {

        if(!e.getEntity().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Dropped")) {
            return;
        }
        ItemStack mergedItemStack = e.getEntity().getItemStack();
        if(mergedItemStack.getType().equals(Material.DIAMOND)) {
            int amount = plugin.getSettings().getCfg().getInt("MAX_ITEMS.DIAMOND");
            if(mergedItemStack.getAmount() >= amount) {
                e.getEntity().getItemStack().setAmount(amount-1);
            }
            return;
        }

        if(mergedItemStack.getType().equals(Material.EMERALD)) {
            int amount = plugin.getSettings().getCfg().getInt("MAX_ITEMS.EMERALD");
            if(mergedItemStack.getAmount() >= amount) {
                e.getEntity().getItemStack().setAmount(amount-1);
            }
            return;
        }

    }
}
