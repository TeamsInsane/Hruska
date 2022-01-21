package me.TeamsInsane.Hruska.events.darkDungeon;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import me.TeamsInsane.Hruska.Core;
import me.TeamsInsane.Hruska.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import java.util.ArrayList;

public class DarkKey implements Listener {

    @EventHandler
    public void dropKey(MythicMobDeathEvent e) {
        if (
                e.getMob().getDisplayName().toLowerCase().contains(Core.configuration.getConfig().getString("darkKeyDropMobSearchName1")) &&
                e.getMob().getDisplayName().toLowerCase().contains(Core.configuration.getConfig().getString("darkKeyDropMobSearchName2"))
        ) {
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.scheduleSyncDelayedTask(Core.getInstance(), () -> {
                ItemStack itemStack = new ItemStack(Material.TRIPWIRE_HOOK, Core.configuration.getConfig().getInt("DarkKeysDropPerBoss"));
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(Color.format("&6&lDark Dungeon Key"));
                ArrayList<String> lore = new ArrayList<>();
                lore.add(Color.format("&cOpens the exit"));
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), itemStack);
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.NETHERITE_SHOVEL, 1));

                e.getEntity().getWorld().getPlayers().forEach(player -> player.sendTitle(Color.format(Core.configuration.getConfig().getString("titleAfterBossDeath1")), "", 5, 100, 5));
                e.getEntity().getWorld().getPlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3, (float) 0.2));
            }, 10);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getClickedBlock() == null) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.OBSERVER &&
                player.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Color.format("&6&lDark Dungeon Key"))) {
            ItemStack itemStack = new ItemStack(player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount() - 1);
            itemStack.setItemMeta(player.getInventory().getItemInMainHand().getItemMeta());
            player.getInventory().setItemInMainHand(itemStack);
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
            player.playSound(player.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 5, (float) 0.9);
            player.spawnParticle(Particle.CLOUD, player.getLocation(), 20);
        }
    }
}


