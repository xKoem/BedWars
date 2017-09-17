package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;

/**
 * Created by koem on 08/07/2017.
 */
public class IslandDroppersManager {

    private final BedWars plugin;
    private IslandDropper[] islandDroppers;

    public IslandDroppersManager(BedWars plugin) {
        this.plugin = plugin;
    }

    public enum DROPPERTYPE{DIAMIND, EMERALD}

    public void generateIslandDroppers() {
        //TODO:gen droppers
    }


}
