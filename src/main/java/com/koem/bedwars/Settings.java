package com.koem.bedwars;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by koem on 07/07/2017.
 */
public class Settings {

    private int maxPlayers = 16;
    private final BW plugin;
    private FileConfiguration cfg;

    public Settings(BW plugin) {
        this.plugin = plugin;
        cfg = plugin.getConfig();
    }

    public void reloadConfig() {
        cfg = plugin.getConfig();
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
