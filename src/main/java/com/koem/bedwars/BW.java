package com.koem.bedwars;

import com.koem.bedwars.tasks.GeneralTask;
import com.koem.bedwars.tasks.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Created by koem on 07/07/2017.
 */
public class BW extends JavaPlugin {

   // private Settings settings;
    private TaskManager taskManager;

    @Override
    public void onEnable() {

        taskManager = new TaskManager(this);
        Bukkit.getLogger().log(Level.INFO, "[" + this.getName() + "] Plugin zostal wlaczony");
    }

    @Override
    public void onDisable() {
        taskManager.dispose();

        Bukkit.getLogger().log(Level.INFO, "[" + this.getName() + "] Plugin zostal wylaczony");
    }
}
