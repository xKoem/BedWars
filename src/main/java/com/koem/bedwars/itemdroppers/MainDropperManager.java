package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainDropperManager {

    private final BedWars plugin;
    private List<MainDropper> mainDroppers;


    public MainDropperManager(BedWars plugin) {
        this.plugin = plugin;
        generateMainDroppers();
    }

    private void generateMainDroppers() {
        mainDroppers = new ArrayList<>();
        FileConfiguration config = plugin.getSettings().getCfg();
        World w = Bukkit.getWorld(plugin.getSettings().getCfg().getString("WORLD_NAME"));

        for(String it: config.getConfigurationSection("FURNACE").getKeys(false)) {
            Location l = new Location(w,
                    config.getDouble("FURNACE." + it + ".X"),
                    config.getDouble("FURNACE." + it + ".Y"),
                    config.getDouble("FURNACE." + it + ".Z")
            );
            mainDroppers.add(new MainDropper(plugin, l, it));
        }
    }

    public List<MainDropper> getMainDroppers() {
        return mainDroppers;
    }
}
