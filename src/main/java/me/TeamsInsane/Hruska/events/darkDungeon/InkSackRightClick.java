package me.TeamsInsane.Hruska.events.darkDungeon;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InkSackRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        EquipmentSlot equipmentSlot = e.getHand();
        if (equipmentSlot != null && equipmentSlot.equals(EquipmentSlot.HAND) && player.getInventory().getItemInMainHand().getType() == Material.INK_SAC) {
            player.getInventory().setItemInMainHand(new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount() - 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 240, 0));
            player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1, (float) 0.5);
            player.spawnParticle(Particle.ASH, player.getLocation(), 1000, 2.0, 2.0, 2.0);
            player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 3, 2.0, 2.0, 2.0);
        }
    }
}
