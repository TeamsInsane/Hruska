package me.TeamsInsane.Hruska.events;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import me.TeamsInsane.Hruska.Core;
import me.TeamsInsane.Hruska.utils.Color;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Key implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if (e.getAction() != Action.LEFT_CLICK_BLOCK || e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.OBSERVER) return;
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Color.format("&c&lDungeon Key"))){
            Player player = e.getPlayer();
            ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
            ItemStack item = new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount() - 1);
            item.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(item);
            BlockFace blockFace = player.getTargetBlockFace(20);
            if (blockFace == BlockFace.WEST || blockFace == BlockFace.EAST){
                for(int y = e.getClickedBlock().getY() + 1; y >= e.getClickedBlock().getY() - 1; y--)
                    for(int z = e.getClickedBlock().getZ() - 1; z <= e.getClickedBlock().getZ() + 1; z++)
                        player.getLocation().getWorld().getBlockAt(e.getClickedBlock().getX(), y, z).setType(Material.AIR);
            } else if (blockFace == BlockFace.SOUTH || blockFace == BlockFace.NORTH){
                for(int y = e.getClickedBlock().getY() + 1; y >= e.getClickedBlock().getY() - 1; y--)
                    for(int x = e.getClickedBlock().getX() - 1; x <= e.getClickedBlock().getX() + 1; x++)
                        player.getLocation().getWorld().getBlockAt(x, y, e.getClickedBlock().getZ()).setType(Material.AIR);
            }
            player.playSound(e.getPlayer().getLocation(), Sound.BLOCK_GRINDSTONE_USE, 2, (float) 0.2);
            player.spawnParticle(Particle.CLOUD, e.getClickedBlock().getLocation(), 20);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Core.configuration.getConfig().getString("commandForObserverClick"));
        }
    }

    @EventHandler
    public void onDarkElfDeath(MythicMobDeathEvent e) {
        if (e.getMob().getDisplayName().toLowerCase().contains(Core.configuration.getConfig().getString("KeyDropMobSearchName1")) && e.getMob().getDisplayName().toLowerCase().contains(Core.configuration.getConfig().getString("KeyDropMobSearchName2"))) {
            dropKey(e.getEntity().getLocation(), Core.configuration.getConfig().getInt("KeysDropPerBoss"));
            e.getEntity().getWorld().getPlayers().forEach(player -> player.sendTitle(Color.format(Core.configuration.getConfig().getString("titleAfterBossDeath2")), "", 5, 100, 5));
            e.getEntity().getWorld().getPlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3, (float) 0.2));
        }
    }

    public void dropKey(Location location, int amount){
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Color.format("&c&lDungeon Key"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Color.format("&6Opens treasure troves"));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        location.getWorld().dropItem(location, item);
    }
}
