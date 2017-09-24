package com.koem.bedwars.tasks;


import com.koem.bedwars.BedWars;

/**
 * Created by koem on 08/07/2017.
 */
public class GameTask {

    private final BedWars plugin;

    public enum GAMESTATE{WAITING, COUNTDOWN, FIGHT}

    private GAMESTATE gameState;

    public GameTask(BedWars plugin) {
        this.plugin = plugin;
        //this.gameState = GAMESTATE.WAITING;   //TODO: change for that
        this.gameState = GAMESTATE.FIGHT;   ////////
    }

    public void setGameState(GAMESTATE gameState) {
        this.gameState = gameState;
    }

    public GAMESTATE getGameState() {
        return this.gameState;
    }

    public GAMESTATE checkGAMESTATE(String str) {
        for (GAMESTATE t : GAMESTATE.values()) {
            if (t.name().equalsIgnoreCase(str))
                return t;
        }
        return null;
    }


}
