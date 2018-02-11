package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;

/**
 * Created by koem on 08/07/2017.
 */
public class EntityExplodeListener implements Listener{

    private final BedWars plugin;

    public EntityExplodeListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntityExplode(EntityExplodeEvent e) {

        if(e.blockList().size() > 0) {
            Iterator<Block> iter = e.blockList().iterator();
            while (iter.hasNext()) {
                Block b = iter.next();
                if (b.getType().equals(Material.WOOL)) {
                    b.setType(Material.AIR);
                    continue;
                }
                if (b.getType().equals(Material.ENDER_STONE)) {
                    b.setType(Material.AIR);
                    continue;
                }
                iter.remove();
            }
        }
    }
}
