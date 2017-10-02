package com.koem.bedwars;

import com.koem.bedwars.commands.BedWarsCommand;
import com.koem.bedwars.commands.TeamCommand;
import com.koem.bedwars.listeners.*;
import com.koem.bedwars.player.PlayerManager;
import com.koem.bedwars.player.TeamManager;
import com.koem.bedwars.tasks.GameTask;
import com.koem.bedwars.tasks.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Created by koem on 07/07/2017.
 */
public class BedWars extends JavaPlugin {

    private Settings settings;
    private TaskManager taskManager;
    private PlayerManager playerManager;
    private GameTask gameTask;
    private TeamManager teamManager;


    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
        settings = new Settings(this);
        taskManager = new TaskManager(this);
        playerManager = new PlayerManager(this);
        teamManager = new TeamManager(this, (short) getSettings().getCfg().getInt("TEAM_SIZE"));
        gameTask = new GameTask(this);
        Bukkit.getLogger().log(Level.INFO, "[" + this.getName() + "] Plugin zostal wlaczony");
    }

    @Override
    public void onDisable() {
        taskManager.dispose();

        Bukkit.getLogger().log(Level.INFO, "[" + this.getName() + "] Plugin zostal wylaczony");
    }


    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerDeathListener(this), this);
        pm.registerEvents(new PlaceBreakListener(this), this);
        pm.registerEvents(new EntityDamageByEntityListener(this), this);
        pm.registerEvents(new EntityDamageListener(this), this);
        pm.registerEvents(new PlayerQuitListener(this), this);
        pm.registerEvents(new EntityExplodeListener(this), this);
        pm.registerEvents(new FoodLevelChangeListener(this), this);

    }

    private void registerCommands() {
        this.getCommand("team").setExecutor(new TeamCommand(this));
        this.getCommand("bedwars").setExecutor(new BedWarsCommand(this));
    }



    public Settings getSettings() {
        return settings;
    }
    public TeamManager getTeamManager() {
        return teamManager;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public GameTask getGameTask() {
        return gameTask;
    }
}
