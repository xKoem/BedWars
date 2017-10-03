package com.koem.bedwars.tasks;

import com.koem.bedwars.BedWars;

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

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.COUNTDOWN)){
            System.out.println(plugin.getGameTask().getGameState().name());
            if(plugin.getGameTask().getCountdownTime() > 1) {
                plugin.getGameTask().setCountdownTime((byte) (plugin.getGameTask().getCountdownTime()-1));
                return;
            }
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.FIGHT);
            plugin.getTeamManager().randomTeams();
            //TODO: teleport players
            return;
        }

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)) {
            plugin.getGameTask().incGameTime();
            plugin.getGameTask().decTimePlayers();

        }


    }
}
