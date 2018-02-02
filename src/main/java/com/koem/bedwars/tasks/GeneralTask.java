package com.koem.bedwars.tasks;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.itemdroppers.IslandDropper;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.playerUpgrades.ArmorManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created by koem on 07/07/2017.
 */
public class GeneralTask implements Runnable {

    private BedWars plugin;

    public GeneralTask(BedWars plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        //plugin.getIslandDroppersManager().generateIslandDroppers();
//
        for(IslandDropper islandDropper :plugin.getIslandDroppersManager().getIslandDroppers()) {
            islandDropper.countdownToDrop();
        }

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.COUNTDOWN)){
            System.out.println(plugin.getGameTask().getGameState().name());
            if(plugin.getGameTask().getCountdownTime() > 1) {
                plugin.getGameTask().setCountdownTime((byte) (plugin.getGameTask().getCountdownTime()-1));
                return;
            }
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.FIGHT);
            plugin.getTeamManager().randomTeams();
            giveLeatherArmorAll();
            teleportAll();

            return;
        }

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)) {
            plugin.getGameTask().incGameTime();
            plugin.getGameTask().decTimePlayers();

        }


    }

    private void giveLeatherArmorAll() {
        ArmorManager am = plugin.getArmorManager();
        for(Player p: plugin.getPlayerManager().getBWPlayers().keySet()) {
            am.applyLeatherArmor(p);
        }
    }

    private void teleportAll() {
        World w = Bukkit.getWorld("world");
        Float redYaw = (float) plugin.getSettings().getCfg().getDouble("SPAWN.RED.YAW");
        Float greenYaw = (float) plugin.getSettings().getCfg().getDouble("SPAWN.GREEN.YAW");
        Float blueYaw = (float) plugin.getSettings().getCfg().getDouble("SPAWN.BLUE.YAW");
        Float yellowYaw = (float) plugin.getSettings().getCfg().getDouble("SPAWN.YELLOW.YAW");

        Location redLoc = new Location(w,
                plugin.getSettings().getCfg().getDouble("SPAWN.RED.X"),
                plugin.getSettings().getCfg().getDouble("SPAWN.RED.Y"),
                plugin.getSettings().getCfg().getDouble("SPAWN.RED.Z")
        );
         Location greenLoc = new Location(w,
                plugin.getSettings().getCfg().getDouble("SPAWN.GREEN.X"),
                plugin.getSettings().getCfg().getDouble("SPAWN.GREEN.Y"),
                plugin.getSettings().getCfg().getDouble("SPAWN.GREEN.Z")
        );
         Location blueLoc = new Location(w,
                plugin.getSettings().getCfg().getDouble("SPAWN.BLUE.X"),
                plugin.getSettings().getCfg().getDouble("SPAWN.BLUE.Y"),
                plugin.getSettings().getCfg().getDouble("SPAWN.BLUE.Z")
        );
         Location yellowLoc = new Location(w,
                plugin.getSettings().getCfg().getDouble("SPAWN.YELLOW.X"),
                plugin.getSettings().getCfg().getDouble("SPAWN.YELLOW.Y"),
                plugin.getSettings().getCfg().getDouble("SPAWN.YELLOW.Z")
        );

        redLoc.setYaw(redYaw);
        greenLoc.setYaw(greenYaw);
        blueLoc.setYaw(blueYaw);
        yellowLoc.setYaw(yellowYaw);


        for(BWPlayer bwp :plugin.getPlayerManager().getBWPlayers().values()) {
            Player p = bwp.getPlayer();
            switch (bwp.getTeam()) {
                case RED:
                    p.teleport(redLoc);
                    break;
                case GREEN:
                    p.teleport(greenLoc);
                    break;
                case BLUE:
                    p.teleport(blueLoc);
                    break;
                case YELLOW:
                    p.teleport(yellowLoc);
                    break;
                default:
                    break;
            }
            p.setGameMode(GameMode.SURVIVAL);

        }


    }
}
