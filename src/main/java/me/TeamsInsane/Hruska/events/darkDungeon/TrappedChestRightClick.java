package me.TeamsInsane.Hruska.events.darkDungeon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class TrappedChestRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if (e.getClickedBlock() == null) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
            Location location = e.getClickedBlock().getLocation();
            switch (getRandom(1, 3)) {
                case 1:
                    location.getBlock().setType(Material.AIR);
                    location.getWorld().createExplosion(location, (float) 1.5);
                    e.getPlayer().spawnParticle(Particle.EXPLOSION_HUGE, e.getClickedBlock().getLocation(), 3, 2.0, 2.0, 2.0);
                    location.getWorld().dropItem(location, new ItemStack(Material.GUNPOWDER, 1));
                    break;
                case 2:
                case 3:
                    location.getBlock().setType(Material.AIR);
                    break;
            }
        }
    }

    public int getRandom(int lower, int upper) {
        Random random = new Random();
        return random.nextInt((upper - lower) + 1) + lower;
    }
}
