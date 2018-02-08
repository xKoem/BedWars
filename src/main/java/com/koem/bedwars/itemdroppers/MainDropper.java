package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by koem on 08/07/2017.
 */
public class MainDropper {


    private final BedWars plugin;
    private final Location location;
    private final TeamManager.TEAM team;
    private short level;
    private World world;
    private HashMap<Material, double[]> dropChances;


    public MainDropper(BedWars plugin, Location location, String teamName) {
        this.plugin = plugin;
        this.location = location;
        this.team = TeamManager.TEAM.valueOf(teamName);
        this.location.add(0.0, 0.2, 0.0);
        this.world = location.getWorld();

        dropChances = new HashMap<>();
        dropChances.put(Material.IRON_INGOT, new double[]{0.5, 0.7, 0.9});  //TODO: get from cfg?
        dropChances.put(Material.GOLD_INGOT, new double[]{0.3, 0.4, 0.6});
        dropChances.put(Material.DIAMOND, new double[]{0.0, 0.1, 0.2});
        level = 0;
    }

    public void upgrade() {
        level += 1;
    }

    public void dropItems() {
        Random random = new Random();
        double randNr;
        int rand1, rand2;
        for (Material material :dropChances.keySet()) {
            randNr = random.nextDouble();
            if(dropChances.get(material)[level] >= randNr) {
                rand1 = random.nextInt(3) -1;
                rand2 = random.nextInt(3) -1;
                ItemStack itemStack = new ItemStack(material);
                Location l = location.clone();
                l.add(rand1, 0, rand2);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.GOLD + "Dropped");
                List<String> s = new ArrayList<>();
                s.add(rand1 + " " + rand2);
                itemMeta.setLore(s);
                itemStack.setItemMeta(itemMeta);
                world.dropItemNaturally(l, itemStack).setVelocity(new Vector(0,0,0));
            }
        }
    }


}
