package com.koem.bedwars.playerUpgrades;

import com.koem.bedwars.player.Team;
import com.koem.bedwars.player.TeamManager;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Armor {

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;


    public Armor(Material leggings, Material boots) {
        this.helmet = null;
        this.chestplate = null;
        this.leggings = new ItemStack(leggings);
        this.boots = new ItemStack(boots);
    }

    public Armor(TeamManager.TEAM t) {
        this.helmet = new ItemStack(Material.LEATHER_HELMET);
        this.chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        this.leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        this.boots = new ItemStack(Material.LEATHER_BOOTS);

        switch (t) {
            case RED:
                paint(Color.RED);
                break;
            case GREEN:
                paint(Color.GREEN);
                break;
            case BLUE:
                paint(Color.BLUE);
                break;
            case YELLOW:
                paint(Color.YELLOW);
                break;
        }
    }

    private void paint(Color c) {
        LeatherArmorMeta lam = (LeatherArmorMeta) boots.getItemMeta();
        lam.setColor(c);
        helmet.setItemMeta(lam);
        chestplate.setItemMeta(lam);
        leggings.setItemMeta(lam);
        boots.setItemMeta(lam);
    }

    public void giveArmorToPlayer(Player p) {
        p.getInventory().setBoots(boots);
        p.getInventory().setLeggings(leggings);
        if(null == helmet) {
            return;
        }
        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chestplate);
    }


}
