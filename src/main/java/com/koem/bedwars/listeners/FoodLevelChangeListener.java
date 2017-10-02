package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener{

    private final BedWars plugin;

    public FoodLevelChangeListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        System.out.println("food lvl changed");
        e.setFoodLevel(20);
    }

}
