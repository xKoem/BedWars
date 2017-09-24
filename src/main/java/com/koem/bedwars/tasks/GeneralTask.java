package com.koem.bedwars.tasks;

import com.koem.bedwars.BedWars;

/**
 * Created by koem on 07/07/2017.
 */
public class GeneralTask implements Runnable {

    private BedWars plugin;
    private byte countdownTime;
    private int gameTime;

    public GeneralTask(BedWars plugin) {
        this.plugin = plugin;
        countdownTime = 20;
        gameTime = 0;
    }

    @Override
    public void run() {

        System.out.println(plugin.getGameTask().getGameState().name());

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.COUNTDOWN)){
            if(countdownTime > 1) {
                countdownTime--;
                return;
            }
            plugin.getGameTask().setGameState(GameTask.GAMESTATE.FIGHT);
            //TODO: teleport players

            return;
        }

        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)) {
            gameTime++;
        }


    }
}
