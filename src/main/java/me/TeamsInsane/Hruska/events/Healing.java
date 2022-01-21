package me.TeamsInsane.Hruska.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Healing implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e){
        Player player = e.getPlayer();
        Material item = e.getItem().getType();
        if (item == Material.POTATO) addHealth(player, 1);
        else if (item == Material.MELON_SLICE) addHealth(player, 2);
        else if (item == Material.PUMPKIN_PIE){
            addHealth(player, 6);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2));
        } else if (item == Material.SWEET_BERRIES){
            addHealth(player, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 1));
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        EquipmentSlot equipmentSlot = e.getHand();
        if (equipmentSlot != null && equipmentSlot.equals(EquipmentSlot.HAND) && e.getAction().isRightClick() && Material.SUGAR == player.getInventory().getItemInMainHand().getType()){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1));
            player.getInventory().setItemInMainHand(new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount()-1));
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
        }
    }

    private void addHealth(Player player, int amount){
        if (player.getHealth() == 20.0) return;
        player.setHealth(Math.min(player.getHealth() + amount, 20.0));
    }
}
