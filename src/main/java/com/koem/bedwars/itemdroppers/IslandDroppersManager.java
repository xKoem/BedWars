package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BW;

import java.util.HashMap;

/**
 * Created by koem on 08/07/2017.
 */
public class IslandDroppersManager {

    private final BW plugin;
    private IslandDropper[] islandDroppers;

    public IslandDroppersManager(BW plugin) {
        this.plugin = plugin;
    }

    public enum DROPPERTYPE{DIAMIND, EMERALD}

    public void generateIslandDroppers() {
        //TODO:gen droppers
    }


}
