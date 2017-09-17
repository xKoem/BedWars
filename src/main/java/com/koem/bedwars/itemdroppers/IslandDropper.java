package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;



/**
 * Created by koem on 08/07/2017.
 */
public class IslandDropper {

    private final BedWars plugin;
    private final Location location;
    private final IslandDroppersManager.DROPPERTYPE dropperType;
    private final World world;
    private byte level;

    public IslandDropper(BedWars plugin, Location location, IslandDroppersManager.DROPPERTYPE dropperType, World world) {
        this.plugin = plugin;
        this.location = location;
        this.dropperType = dropperType;
        this.world = world;
        level = 1;
    }

    public void upgrade() { //TODO: will time be better?
        level += 1;
    }

    public void dropItem() {
        if(dropperType.equals(IslandDroppersManager.DROPPERTYPE.DIAMIND))
            world.dropItem(location, new ItemStack(Material.DIAMOND));
        else
            world.dropItem(location, new ItemStack(Material.EMERALD));
    }


}
