package com.koem.bedwars.tasks;


import com.koem.bedwars.BW;

/**
 * Created by koem on 08/07/2017.
 */
public class GameTask {

    private final BW plugin;

    public enum GAMESTATE{WAITING, COUNTDOWN, FIGHT}

    private GAMESTATE gameState;

    public GameTask(BW plugin) {
        this.plugin = plugin;
        this.gameState = GAMESTATE.WAITING;
    }

    public void setGameState(GAMESTATE gameState) {
        this.gameState = gameState;
    }

    public GAMESTATE getGameState() {
        return this.gameState;
    }


}
