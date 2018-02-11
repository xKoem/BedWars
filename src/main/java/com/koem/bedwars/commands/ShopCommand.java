package com.koem.bedwars.commands;

import com.koem.bedwars.BedWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    private final BedWars plugin;

    public ShopCommand(BedWars plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Cannot use this command");
            return true;
        }
        Player p = (Player) sender;
        plugin.getShop().openShop(p);

        return true;
    }
}
