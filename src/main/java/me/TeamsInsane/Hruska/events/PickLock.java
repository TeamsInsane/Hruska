package me.TeamsInsane.Hruska.events;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import me.TeamsInsane.Hruska.Core;
import me.TeamsInsane.Hruska.utils.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.Bisected;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class PickLock implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.IRON_DOOR) return;
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_NUGGET && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Color.format("&8&oMaster key"))){
            Player player = e.getPlayer();
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(itemStack);
            player.playSound(e.getClickedBlock().getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 3, (float) 0.2);
            player.sendMessage(Color.format(Core.configuration.getConfig().getString("doorBreakMessage")));
            Bisected data = (Bisected) e.getClickedBlock().getBlockData();
            e.getClickedBlock().setType(Material.AIR);
            if (data.getHalf() == Bisected.Half.BOTTOM) e.getClickedBlock().getLocation().add(0, 1, 0).getBlock().setType(Material.AIR);
            else e.getClickedBlock().getLocation().add(0, -1, 0).getBlock().setType(Material.AIR);
        }
    }

    @EventHandler
    public void onMythicMobDeath(MythicMobDeathEvent e){
       if (getRandom(1, 2) == 1)
           dropLockPick(e.getEntity().getLocation(), getRandom(Core.configuration.getConfig().getInt("minDoorKeysDropPerMythicMob"), Core.configuration.getConfig().getInt("maxDoorKeysDropPerMythicMob")));
    }

    private void dropLockPick(Location location, int amount){
        ItemStack itemStack = new ItemStack(Material.IRON_NUGGET, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Color.format("&8&oMaster key"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.format("&fBreaks down iron doors"));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        location.getWorld().dropItem(location, itemStack);
    }

    private int getRandom(int lower, int upper) {
        Random random = new Random();
        return random.nextInt((upper - lower) + 1) + lower;
    }
}
