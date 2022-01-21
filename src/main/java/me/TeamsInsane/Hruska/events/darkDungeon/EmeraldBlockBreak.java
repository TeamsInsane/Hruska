package me.TeamsInsane.Hruska.events.darkDungeon;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EmeraldBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (e.getBlock().getType() == Material.EMERALD_BLOCK && (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_SHOVEL || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_SHOVEL)){
            e.getBlock().setType(Material.AIR);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.EMERALD, 1));
        }
    }
}
