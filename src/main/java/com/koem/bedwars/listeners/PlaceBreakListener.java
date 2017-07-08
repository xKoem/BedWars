package com.koem.bedwars.listeners;

import com.koem.bedwars.BW;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class PlaceBreakListener implements Listener {

    private final BW plugin;

    public PlaceBreakListener(BW plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType().equals(Material.BED)) {
            e.setCancelled(true);
            //TODO: maybe vips only???
        }
    }

    @EventHandler
    void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.OBSIDIAN)) {
            return;
        }
        if (e.getBlock().getType().equals(Material.WOOL)) {
            return;
        }
        if (e.getBlock().getType().equals(Material.ENDER_STONE)) {
            return;
        }
        if (e.getBlock().getType().equals(Material.BED)) {
            //TODO: remove teams bed
        }

        e.setCancelled(true);

    }
}
