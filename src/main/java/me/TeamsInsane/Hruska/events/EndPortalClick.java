package me.TeamsInsane.Hruska.events;

import me.TeamsInsane.Hruska.Core;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class EndPortalClick implements Listener{

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e){
        if (e.getAction() != Action.LEFT_CLICK_BLOCK || e.getClickedBlock() == null) return;
        if (e.getClickedBlock().getType() == Material.END_PORTAL_FRAME){
            Player player = e.getPlayer();

            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, (float) 0.2);
            player.spawnParticle(Particle.PORTAL, e.getClickedBlock().getLocation().add(0.5, 1, 0.5), 500);
            if (getRandom() == 1){
                BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
                bukkitScheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> {
                    e.getClickedBlock().setType(Material.AIR);
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Core.configuration.getConfig().getString("commandForEndPortalclick"));
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2, (float) 0.2);
                    player.spawnParticle(Particle.CLOUD, e.getClickedBlock().getLocation(), 100);

                    Key key = new Key();
                    key.dropKey(e.getClickedBlock().getLocation(), 1);
                }, 5);
            }
        }
    }

    private int getRandom() {
        Random random = new Random();
        return  random.nextInt(5);
    }
}
