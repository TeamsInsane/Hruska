package me.TeamsInsane.Hruska.events.darkDungeon;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CarvedPumpkinRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CARVED_PUMPKIN && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.LANTERN) {
            Player player = e.getPlayer();
            player.getInventory().setItemInMainHand(new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount() - 1));
            e.getClickedBlock().setType(Material.JACK_O_LANTERN);
        }
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e){
        if (e.getBlockPlaced().getType() == Material.LANTERN) e.setCancelled(true);
    }
}
