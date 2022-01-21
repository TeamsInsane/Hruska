package me.TeamsInsane.Hruska.events.darkDungeon;

import me.TeamsInsane.Hruska.Core;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class DropperLeftClick implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e){
        if (e.getClickedBlock() == null) return;
        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.DROPPER && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE){
            Player player = e.getPlayer();

            player.getInventory().setItemInMainHand(new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount() - 1));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, (float) 0.9);
            player.spawnParticle(Particle.CLOUD, e.getClickedBlock().getLocation().add(0.5, 1.3, 0.5), 10);
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 2, (float) 0.9), 10);
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 2, (float) 0.9), 15);
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 2, (float) 0.2), 20);
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 2, (float) 0.2), 25);
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> {
                switch (getRandom(1, 3)){
                    case 1:
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, (float) 0.9);
                        player.spawnParticle(Particle.CRIT, e.getClickedBlock().getLocation().add(0.5, 1.3, 0.5), 45);
                        break;
                    case 2:
                        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2, (float) 0.9);
                        player.spawnParticle(Particle.COMPOSTER, e.getClickedBlock().getLocation().add(0.5, 1.3, 0.5), 15);
                        e.getClickedBlock().getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.EMERALD, 1));
                    case 3:
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_HURT, 2, (float) 0.9);
                        player.spawnParticle(Particle.PORTAL, e.getClickedBlock().getLocation().add(0.5, 1, 0.5), 500);
                }
            }, 30);
        }
    }

    public int getRandom(int lower, int upper) {
        Random random = new Random();
        return random.nextInt((upper - lower) + 1) + lower;
    }
}
