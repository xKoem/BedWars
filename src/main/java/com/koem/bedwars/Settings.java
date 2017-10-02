package com.koem.bedwars;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * Created by koem on 07/07/2017.
 */
public class Settings {

    private final BedWars plugin;
    private FileConfiguration cfg;

    public Settings(BedWars plugin) {
        this.plugin = plugin;

        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {
            System.out.println("config.yml not found, creating!");
            plugin.saveDefaultConfig();
        } else {
            System.out.println("config.yml found, loading!");
        }

        cfg = plugin.getConfig();

    }

    public void reloadConfig() {
        cfg = plugin.getConfig();
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

}
