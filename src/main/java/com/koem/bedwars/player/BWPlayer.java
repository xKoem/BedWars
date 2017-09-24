package com.koem.bedwars.player;

import org.bukkit.entity.Player;

/**
 * Created by koem on 08/07/2017.
 */
public class BWPlayer {

    private Player p;
    private TeamManager.TEAM team;
    private Player lastDamager;
    //TODO: armour lvl
    private short kills;
    private short deaths;
    private short finals;

    public BWPlayer(Player p) {
        this.p = p;
        this.lastDamager = null;
        this.kills = 0;
        this.deaths = 0;
        this.finals = 0;
    }

    public void setTeam(TeamManager.TEAM team) {
        this.team = team;
    }

    public void setLastDamager(Player p) {
        this.lastDamager = p;
    }

    public void clearLastDamager() {
        this.lastDamager = null;
    }

    public Player getLastDamager() {
        return this.lastDamager;
    }

    public Player getPlayer() {
        return p;
    }

    public TeamManager.TEAM getTeam() {
        return team;
    }

    public short getKills() {
        return kills;
    }

    public short getDeaths() {
        return deaths;
    }

    public short getFinals() {
        return finals;
    }
}
