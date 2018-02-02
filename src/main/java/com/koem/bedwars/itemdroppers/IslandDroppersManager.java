package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koem on 08/07/2017.
 */
public class IslandDroppersManager {

    private final BedWars plugin;
    private List<IslandDropper> islandDroppers;

    public IslandDroppersManager(BedWars plugin) {
        this.plugin = plugin;
        generateIslandDroppers();
//        islandDroppers = new ArrayList<>();
//        World w = Bukkit.getWorld("world");
//        islandDroppers.add(new IslandDropper(plugin, new Location(w ,0.5, 81, 0.5), DROPPERTYPE.DIAMIND));
    }

    public enum DROPPERTYPE{DIAMIND, EMERALD}

    public void generateIslandDroppers() {
        FileConfiguration config = plugin.getSettings().getCfg();
        World w = Bukkit.getWorld("world");
        islandDroppers = new ArrayList<>();
       // System.out.println(config.getDouble("DIAMOND.D" +  + ".X"));
        for(String it: config.getConfigurationSection("DIAMOND").getKeys(false)) {
            Location l = new Location(w,
                    config.getDouble("DIAMOND." + it + ".X"),
                    config.getDouble("DIAMOND." + it + ".Y"),
                    config.getDouble("DIAMOND." + it + ".Z")
            );
            islandDroppers.add(new IslandDropper(plugin, l, DROPPERTYPE.DIAMIND));
        }

        for(String it: config.getConfigurationSection("EMERALD").getKeys(false)) {
            Location l = new Location(w,
                    config.getDouble("EMERALD." + it + ".X"),
                    config.getDouble("EMERALD." + it + ".Y"),
                    config.getDouble("EMERALD." + it + ".Z")
            );
            islandDroppers.add(new IslandDropper(plugin, l, DROPPERTYPE.EMERALD));
        }

    }

    public List<IslandDropper> getIslandDroppers() {
        return islandDroppers;
    }
}
