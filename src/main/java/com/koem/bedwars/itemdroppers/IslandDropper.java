package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;


/**
 * Created by koem on 08/07/2017.
 */
public class IslandDropper {

    private final BedWars plugin;
    private final Location dropLocation;
    private final IslandDroppersManager.DROPPERTYPE dropperType;
    private final World world;
    private byte level;
    private int timeToSpawn;
    private int defaultTimeToSpawn;

    public IslandDropper(BedWars plugin, Location location, IslandDroppersManager.DROPPERTYPE dropperType) {
        this.plugin = plugin;
        this.dropLocation = location.clone(); //height of the drop
        this.dropLocation.add(0.0, 1.0, 0.0);
        this.dropperType = dropperType;
        this.world = location.getWorld();
        level = 1;
        timeToSpawn = 10;
        defaultTimeToSpawn = 10;
        location.add(0.0, 2.0, 0.0);
        ItemStack is;
        if(dropperType.equals(IslandDroppersManager.DROPPERTYPE.DIAMIND))
            is = new ItemStack(Material.DIAMOND);
        else
            is = new ItemStack(Material.EMERALD);

        //glow
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "ItemDropper");
        meta.addEnchant( Enchantment.LURE, 1, false );
        meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        is.setItemMeta( meta );
        //eo glow

        Item item = world.dropItem(location, is);
        item.setVelocity(new Vector(0,0,0));
        item.setGravity(false);
    }

    public void upgrade() { //TODO: will time be better?
        level += 1;
    }

    public void countdownToDrop() {
        timeToSpawn--;
        if(0 == timeToSpawn) {
            dropItem();
            timeToSpawn = 10;
        }
    }

    public void dropItem() {
        if(dropperType.equals(IslandDroppersManager.DROPPERTYPE.DIAMIND))
            world.dropItemNaturally(this.dropLocation, new ItemStack(Material.DIAMOND)).setVelocity(new Vector(0,0,0));
        else
            world.dropItemNaturally(this.dropLocation, new ItemStack(Material.EMERALD)).setVelocity(new Vector(0,0,0));
    }


}
