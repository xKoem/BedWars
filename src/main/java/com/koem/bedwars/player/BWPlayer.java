package com.koem.bedwars.player;

import com.koem.bedwars.BedWars;
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
    private BedWars plugin;
    private Integer armorLvl;

    public BWPlayer(Player p, BedWars plugin) {
        this.plugin = plugin;
        this.p = p;
        this.team = null;
        this.lastDamager = null;
        this.kills = 0;
        this.deaths = 0;
        this.finals = 0;
        this.armorLvl = 0;
    }

    public void setTeam(TeamManager.TEAM team) {
        this.team = team;
        //TODO: inc team value
        plugin.getTeamManager().getTeam(team).addPlayer();
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

    public void setKills(short kills) {
        this.kills = kills;
    }

    public void setDeaths(short deaths) {
        this.deaths = deaths;
    }

    public Integer getArmorLvl() {
        return armorLvl;
    }
}
