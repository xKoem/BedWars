package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.player.Team;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

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
            return;
        }

        if (e.getBlock().getType().equals(Material.BED_BLOCK)) {
            e.setCancelled(true);
            //TODO: maybe vips only???
        }


    }

    @EventHandler
    void onBreak(BlockBreakEvent e) {
        e.setDropItems(false);
        if(!plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)){
            e.setCancelled(true);
            return;
        }

        if (e.getBlock().getType().equals(Material.OBSIDIAN)) {
            return;
        }
        if (e.getBlock().getType().equals(Material.WOOL)) {
            return;
        }
        if (e.getBlock().getType().equals(Material.ENDER_STONE)) {
            return;
        }
        if (e.getBlock().getType().equals(Material.BED_BLOCK)) {
            //TODO: remove teams bed
            BWPlayer bwp =  plugin.getPlayerManager().getBWPlayer(e.getPlayer());
            if(e.getBlock().getLocation().distance(plugin.getTeamManager().getTeam(bwp.getTEAM()).getBedLocation()) < 5) {
                e.setCancelled(true);
                return;
            }
            Team t = plugin.getTeamManager().getTeam(e.getBlock().getLocation());
            if(null == t) {
                return;
            }
            t.setIsBedDestroyed(true);
            Bukkit.broadcastMessage(plugin.getSettings().getCfg().getString("BED_DESTROYED").replace("%player%", e.getPlayer().getDisplayName()).replace("%team%", t.getTeam().name()));
            e.setDropItems(false);
            return;
        }

        e.setCancelled(true);

    }
}
