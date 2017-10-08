package com.koem.bedwars;

import com.koem.bedwars.commands.BedWarsCommand;
import com.koem.bedwars.commands.TeamCommand;
import com.koem.bedwars.listeners.*;
import com.koem.bedwars.player.PlayerManager;
import com.koem.bedwars.player.TeamManager;
import com.koem.bedwars.tasks.GameTask;
import com.koem.bedwars.tasks.TaskManager;
import com.koem.bedwars.playerUpgrades.ArmorManager;
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
    private ArmorManager armorManager;

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
        settings = new Settings(this);
        armorManager = new ArmorManager(this);
        playerManager = new PlayerManager(this);
        teamManager = new TeamManager(this, (short) getSettings().getCfg().getInt("TEAM_SIZE"));
        taskManager = new TaskManager(this);
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
        pm.registerEvents(new CreatureSpawnListener(), this);
        pm.registerEvents(new WeatherChangeListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
    }

    private void registerCommands() {
        this.getCommand("team").setExecutor(new TeamCommand(this));
        this.getCommand("bedwars").setExecutor(new BedWarsCommand(this));
    }



    public Settings getSettings() {
        if (settings == null) settings = new Settings(this);
        return settings;
    }
    public TeamManager getTeamManager() {
        if (teamManager == null)
            teamManager = new TeamManager(this, (short) getSettings().getCfg().getInt("TEAM_SIZE")); 
        return teamManager;
    }
    public PlayerManager getPlayerManager() {
        //if (playerManager == null) playerManager = new PlayerManager(this);
        return playerManager;
    }
    public GameTask getGameTask() {
        if (gameTask == null) gameTask = new GameTask(this);
        return gameTask;
    }
    public ArmorManager getArmorManager() {
        if (armorManager == null) armorManager = new ArmorManager(this);
        return armorManager;
    }
}
