package com.koem.bedwars.commands;


import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BedWarsCommand implements CommandExecutor{

    private final BedWars plugin;

    public BedWarsCommand(BedWars plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender.hasPermission("BW.BedWarsCommand"))) {
            return false;
        }

        if(args.length < 1) {
            sender.sendMessage(plugin.getSettings().getCfg().getString("BED_WARS_CMD_ERROR_BAD_USAGE"));
            return true;
        }

        if(args[0].equalsIgnoreCase("changegamestate")) {
            if(sender.hasPermission("BW.ChangeState")) {

                if(args.length < 2){
                    sender.sendMessage(plugin.getSettings().getCfg().getString("BED_WARS_CMD_ERROR_BAD_USAGE"));
                    return true;
                }


                GameTask.GAMESTATE gs = plugin.getGameTask().checkGAMESTATE(args[1]);

                if(null == gs) {
                    sender.sendMessage(plugin.getSettings().getCfg().getString("BED_WARS_CMD_ERROR_BAD_USAGE"));
                    return true;
                }

                plugin.getGameTask().setGameState(gs);
                return true;

            }
        }

        if(args[0].equalsIgnoreCase("teamlist")){
            if(!(sender.hasPermission("BW.GetTeams"))) {
                return true;
            }
            for(BWPlayer p :plugin.getPlayerManager().getBWPlayers().values()) {
                System.out.println(p.getPlayer().getName() + " " +p.getTeam() + " : " + plugin.getTeamManager().getTeam(p.getTeam()).getTeamPlayers());
            }
        }



        return false;
    }
}
