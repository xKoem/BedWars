package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BedWars;

import javax.xml.stream.Location;

/**
 * Created by koem on 08/07/2017.
 */
public class MainDropper {


    private final BedWars plugin;
    private final Location location;
    private short level;

    public MainDropper(BedWars plugin, Location location) {
        this.plugin = plugin;
        this.location = location;
        level = 1;
    }

    public void upgrade() {
        level += 1;
    }



}
