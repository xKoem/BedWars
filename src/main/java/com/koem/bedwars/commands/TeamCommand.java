package com.koem.bedwars.commands;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.TeamManager;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor{

    private final BedWars plugin;

    public TeamCommand(BedWars plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;

//        if(plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)) {  //TODO: comment for test only
//            p.sendMessage(plugin.getSettings().getCfg().getString("TEAM_CMD_ERROR_NOT_ALLOWED"));
//            return true;
//        }

        if(1 != args.length) {
            p.sendMessage(plugin.getSettings().getCfg().getString("TEAM_CMD_ERROR_BAD_USAGE"));
            return true;
        }

        String team = args[0].toUpperCase();
        TeamManager.TEAM t = TeamManager.getTEAM(team);

        if(t == null) {
            p.sendMessage(plugin.getSettings().getCfg().getString("TEAM_CMD_ERROR_BAD_USAGE"));
            return true;
        }

        plugin.getPlayerManager().getBWPlayer(p).setTeam(t);
        plugin.getPlayerManager().setPlayerColor(p, t);

        p.sendMessage(plugin.getSettings().getCfg().getString("TEAM_JOINED").replace("%team%", t.name()));


        return true;
    }
}
