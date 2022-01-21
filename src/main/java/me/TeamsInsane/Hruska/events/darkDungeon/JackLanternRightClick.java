package me.TeamsInsane.Hruska.events.darkDungeon;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class JackLanternRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if (e.getClickedBlock() == null) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.JACK_O_LANTERN && e.getPlayer().getInventory().getItemInMainHand().getType() != Material.LANTERN){
            e.getPlayer().getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.EMERALD, 1));
            e.getPlayer().playEffect(EntityEffect.TOTEM_RESURRECT);
            e.getClickedBlock().setType(Material.AIR);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1, (float) 0.5);
        }
    }
}
