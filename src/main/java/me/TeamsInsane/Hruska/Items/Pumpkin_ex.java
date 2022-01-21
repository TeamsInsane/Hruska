package me.TeamsInsane.Hruska.Items;

import me.TeamsInsane.Hruska.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Pumpkin_ex {

    public void recipeFurnace(){
        ItemStack result = new ItemStack(Material.ORANGE_DYE);
        ItemMeta itemMeta = result.getItemMeta();
        itemMeta.setDisplayName(Color.format("&6Pumpkin extract"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Used to create");
        lore.add("bandages and ...");
        itemMeta.setLore(lore);
        result.setItemMeta(itemMeta);

        FurnaceRecipe recipe = new FurnaceRecipe(NamespacedKey.minecraft("pumpkin_ex"), result, Material.PUMPKIN, 15, 100);

        Bukkit.getServer().addRecipe(recipe);
    }


}
