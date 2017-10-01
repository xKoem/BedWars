package com.koem.bedwars.tasks;


import com.koem.bedwars.BedWars;

/**
 * Created by koem on 08/07/2017.
 */
public class GameTask {

    private final BedWars plugin;
    private byte countdownTime;
    private int gameTime;

    public enum GAMESTATE{WAITING, COUNTDOWN, FIGHT}

    private GAMESTATE gameState;

    public GameTask(BedWars plugin) {
        this.plugin = plugin;
        countdownTime = 20;
        gameTime = 0;

        this.gameState = GAMESTATE.WAITING;
        //this.gameState = GAMESTATE.FIGHT;
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

    public byte getCountdownTime() {
        return countdownTime;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setCountdownTime(byte countdownTime) {
        this.countdownTime = countdownTime;
    }

    public void incGameTime() {
        this.gameTime++;
    }
}
