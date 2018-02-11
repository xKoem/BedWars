package com.koem.bedwars.Inventory;

import com.koem.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Shop {

    enum Inv {MAIN, UTILITY}

    private final BedWars plugin;
    private HashMap<Inv, Inventory> shop;

    public Shop(BedWars plugin) {
        this.plugin = plugin;
        createShop();
    }

    private void createShop() {
        shop = new HashMap<>();
        for (Inv it: Inv.values()) {
            shop.put(it, Bukkit.createInventory(null, 36, plugin.getSettings().getCfg().getString("SHOP_NAME." + it.name())));
        }

    }

    public void openShop(Player p) {
        p.openInventory(shop.get(Inv.MAIN));
    }
}
