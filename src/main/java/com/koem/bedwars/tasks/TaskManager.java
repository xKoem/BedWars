package com.koem.bedwars.tasks;

import com.koem.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by koem on 07/07/2017.
 */
public class TaskManager {
    private final BedWars plugin;
    private BukkitTask generalBukkitTask;

    public TaskManager(BedWars plugin) {
        this.plugin = plugin;
        generalBukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, new GeneralTask(plugin), 0, 20);
    }

    public BukkitTask getGeneralBukkitTask() {
        return generalBukkitTask;
    }

    public void dispose() {
        this.generalBukkitTask.cancel();
    }
}
