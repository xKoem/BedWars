package com.koem.bedwars.tasks;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.itemdroppers.IslandDropper;
import com.koem.bedwars.itemdroppers.MainDropper;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.playerUpgrades.ArmorManager;
import org.bukkit.GameMode;
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
        for(MainDropper mainDropper :plugin.getMainDropperManager().getMainDroppers()) {
            mainDropper.dropItems();
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

        for(BWPlayer bwp :plugin.getPlayerManager().getBWPlayers().values()) {
            Player p = bwp.getPlayer();
            p.teleport(plugin.getTeamManager().getTeam(bwp.getTEAM()).getSpawnLocation());
            p.setGameMode(GameMode.SURVIVAL);

        }

    }
}
