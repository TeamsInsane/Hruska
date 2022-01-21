package me.TeamsInsane.Hruska.Items;

import me.TeamsInsane.Hruska.Core;
import me.TeamsInsane.Hruska.utils.Color;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;


public class S_allium implements Listener {

    @EventHandler
    private void onRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() != Material.ALLIUM || e.getAction() != Action.RIGHT_CLICK_AIR || player.getInventory().getItemInMainHand().getItemMeta() == null) return;
        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Color.format("&dFresh onion?!"))){
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 3));

            ItemStack old = new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount() - 1);
            ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
            old.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(old);
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 1)), 120);
        }
    }
}
