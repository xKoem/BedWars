package com.koem.bedwars.player;

import com.koem.bedwars.BW;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */
public class TeamManager {

    private final BW plugin;
    private HashMap<TEAM, Integer> team;

    public TeamManager(BW plugin, short teamSize) {
        this.plugin = plugin;
        generateTeam(TEAM.RED);
    }

    private void generateTeam(TEAM team) {

    }

    public enum TEAM {RED, BLUE, GREEN, YELLOW}

}
