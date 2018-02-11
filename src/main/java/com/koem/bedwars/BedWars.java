package com.koem.bedwars;

import com.koem.bedwars.Inventory.Shop;
import com.koem.bedwars.commands.BedWarsCommand;
import com.koem.bedwars.commands.ShopCommand;
import com.koem.bedwars.commands.TeamCommand;
import com.koem.bedwars.itemdroppers.IslandDroppersManager;
import com.koem.bedwars.itemdroppers.MainDropperManager;
import com.koem.bedwars.listeners.*;
import com.koem.bedwars.player.PlayerManager;
import com.koem.bedwars.player.TeamManager;
import com.koem.bedwars.tasks.GameTask;
import com.koem.bedwars.tasks.TaskManager;
import com.koem.bedwars.playerUpgrades.ArmorManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
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
    private IslandDroppersManager islandDroppersManager;
    private MainDropperManager mainDropperManager;
    private Shop shop;

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
        settings = new Settings(this);
        armorManager = new ArmorManager(this);
        playerManager = new PlayerManager(this);

        removeItems();
        islandDroppersManager = new IslandDroppersManager(this);
        mainDropperManager = new MainDropperManager(this);

        teamManager = new TeamManager(this, (short) getSettings().getCfg().getInt("TEAM_SIZE"));
        taskManager = new TaskManager(this);
        gameTask = new GameTask(this);

        shop = new Shop(this);

        noDaylightCycle();
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
        pm.registerEvents(new EntityPickupItemListener(), this);
        pm.registerEvents(new ItemDespawnListener(), this);
        pm.registerEvents(new EntityDamageByBlockListener(), this);
        pm.registerEvents(new PlayerDropItemListener(), this);
        pm.registerEvents(new ItemMergeListener(this), this);
    }

    private void registerCommands() {
        this.getCommand("team").setExecutor(new TeamCommand(this));
        this.getCommand("bedwars").setExecutor(new BedWarsCommand(this));
        this.getCommand("shop").setExecutor(new ShopCommand(this));
    }

    private void noDaylightCycle() {
        World w = Bukkit.getWorld(getSettings().getCfg().getString("WORLD_NAME"));
        w.setGameRuleValue("doMobSpawning", "false");
        w.setGameRuleValue("keepInventory", "false");
        w.setGameRuleValue("announceAdvancements", "false");
        w.setGameRuleValue("naturalRegeneration", "true");
        w.setGameRuleValue("doDaylightCycle", "false");
        w.setTime(6000);
    }

    private void removeItems() {
        World world = getServer().getWorld(getSettings().getCfg().getString("WORLD_NAME"));//get the world
        List<Entity> entList = world.getEntities();//get all entities in the world

        for(Entity current : entList) {//loop through the list
            if (current instanceof Item) {//make sure we aren't deleting mobs/players
                current.remove();//remove it
            }
        }
    }

    public IslandDroppersManager getIslandDroppersManager() {
        return islandDroppersManager;
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
    public ArmorManager getArmorManager() {
        return armorManager;
    }
    public MainDropperManager getMainDropperManager() {
        return mainDropperManager;
    }
    public Shop getShop() {
        return shop;
    }
}
