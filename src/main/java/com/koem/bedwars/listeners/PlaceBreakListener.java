package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koem on 08/07/2017.
 */
public class PlaceBreakListener implements Listener {

    private final BedWars plugin;

    public PlaceBreakListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType().equals(Material.TNT)) {
//            Location l = e.getBlock().getLocation();
//            World w = e.getBlock().getWorld();
//            w.spawnEntity(l, EntityType.PRIMED_TNT);
            Block b = e.getBlock();
            e.getBlock().setType(Material.AIR);
            Entity ent = b.getWorld().spawn(b.getLocation().add(0.5,0,0.5), TNTPrimed.class);
            ((TNTPrimed) ent).setFuseTicks(80); //TODO: 60 for vip
            ent.setVelocity(new Vector(0,0,0));
        }

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
