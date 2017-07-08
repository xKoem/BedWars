package com.koem.bedwars.itemdroppers;

import com.koem.bedwars.BW;

import javax.xml.stream.Location;

/**
 * Created by koem on 08/07/2017.
 */
public class MainDropper {


    private final BW plugin;
    private final Location location;
    private short level;

    public MainDropper(BW plugin, Location location) {
        this.plugin = plugin;
        this.location = location;
        level = 1;
    }

    public void upgrade() {
        level += 1;
    }



}
