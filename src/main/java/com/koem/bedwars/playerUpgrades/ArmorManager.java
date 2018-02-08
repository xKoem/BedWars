package com.koem.bedwars.playerUpgrades;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.player.Team;
import com.koem.bedwars.player.TeamManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ArmorManager {

    private final BedWars plugin;

    private HashMap<Integer, Armor> armorLvl;

    private HashMap<TeamManager.TEAM, Armor> leatherArmor;

    public ArmorManager(BedWars plugin) {
        this.plugin = plugin;
        armorLvl = new HashMap<>();
        leatherArmor = new HashMap<>();

        for(TeamManager.TEAM t: TeamManager.TEAM.values()){
            leatherArmor.put(t, new Armor(t));
        }
        armorLvl.put(1, new Armor(Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS));
        armorLvl.put(2, new Armor(Material.IRON_LEGGINGS, Material.IRON_BOOTS));
        armorLvl.put(3, new Armor(Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS));
    }

    public void applyLeatherArmor(Player p) {
        BWPlayer bwPlayer = plugin.getPlayerManager().getBWPlayer(p);
        Team team = plugin.getTeamManager().getTeam(bwPlayer.getTEAM());
        leatherArmor.get(team.getTeam()).giveArmorToPlayer(p);
    }

    public void applyArmor(Player p) {
        BWPlayer bwPlayer = plugin.getPlayerManager().getBWPlayer(p);
        //Team team = plugin.getTeamManager().getTEAM(bwPlayer.getTEAM());
        Integer lvl = bwPlayer.getArmorLvl();
        if(0 == lvl) {
            System.out.println("Tried to apply lvl 0 armor");
            return;
        }
        armorLvl.get(lvl).giveArmorToPlayer(p);
    }

}
