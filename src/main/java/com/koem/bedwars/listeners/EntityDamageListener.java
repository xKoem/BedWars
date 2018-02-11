package com.koem.bedwars.listeners;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.BWPlayer;
import com.koem.bedwars.tasks.GameTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by koem on 08/07/2017.
 */
public class EntityDamageListener implements Listener {

    private final BedWars plugin;

    public EntityDamageListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntityDamage(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
                e.setCancelled(true);
            }
            return;
        }

        if (!plugin.getGameTask().getGameState().equals(GameTask.GAMESTATE.FIGHT)) {
            e.setCancelled(true);
            if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID) || e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                Player p = (Player) e.getEntity();
                Location l = new Location(p.getWorld(),
                        plugin.getSettings().getCfg().getDouble("MAP.LOBBY.X"),
                        plugin.getSettings().getCfg().getDouble("MAP.LOBBY.Y"),
                        plugin.getSettings().getCfg().getDouble("MAP.LOBBY.Z")
                );
                p.teleport(l);
            }
            return;
        }

        if(e.isCancelled()) {
            e.setCancelled(true);
            return;
        }

        Player p = (Player) e.getEntity();


        System.out.println(p.getHealth() - e.getDamage());

        if (p.getHealth() - e.getDamage() > 0) {
            return;
        }

        e.setCancelled(true);
        //TODO: send eq to damager
        //TODO: get cause and translate


        BWPlayer bwPlayer = plugin.getPlayerManager().getBWPlayer(p);

        if(null == bwPlayer.getLastDamager()) {
//////////////////////////////////////////no last damager - player made suicide

            Bukkit.broadcastMessage(p.getDisplayName() + " has been killed by with " + e.getCause());

        }else {

//////////////////////////////////////////last damager exist - killed by another player

            BWPlayer bwDamager = plugin.getPlayerManager().getBWPlayer(bwPlayer.getLastDamager());

            Bukkit.broadcastMessage(p.getDisplayName() + " has been killed by " + bwPlayer.getLastDamager().getDisplayName() + " with " + e.getCause());

            bwPlayer.setDeaths((short) (bwPlayer.getDeaths() + 1));
            bwDamager.setKills((short) (bwDamager.getKills() + 1));

            bwPlayer.clearLastDamager();
        }

        p.setGameMode(GameMode.SPECTATOR);
        playerTeleportToCenter(p);

        if(!plugin.getPlayerManager().getBWPlayer(p).getTeam().isBedDestroyed()) {
            plugin.getGameTask().putPlayerToRespawn(p);
            p.sendMessage(plugin.getSettings().getCfg().getString("PLAYER_RESPAWN_IN_5"));
        }
        p.setHealth(20.0d);

    }

    private void playerTeleportToCenter(Player p) {
        Location l = new Location(p.getWorld(),
                plugin.getSettings().getCfg().getDouble("MAP.CENTER.X"),
                plugin.getSettings().getCfg().getDouble("MAP.CENTER.Y"),
                plugin.getSettings().getCfg().getDouble("MAP.CENTER.Z")
            );

        String team = plugin.getPlayerManager().getBWPlayer(p).getTEAM().toString();
        Float yaw = (float) plugin.getSettings().getCfg().getDouble("SPAWN." + team + ".YAW");
        yaw += 180f;
        if(yaw > 180f)
            yaw -= 360f;
        l.setYaw(yaw);
        l.setPitch(20.0f);
        p.teleport(l);
    }
}
