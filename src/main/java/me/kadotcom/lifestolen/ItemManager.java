package me.kadotcom.lifestolen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack heart;

    public static void init(){
      createHeart();
    }

    private static void createHeart(){
        ItemStack item = new ItemStack(Material.RED_DYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Heart");
        List<String> lore = new ArrayList<>();
        lore.add("This will give you one heart.");
        meta.setLore(lore);
        item.setItemMeta(meta);
        heart = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("heart"), item);
        sr.shape("RRR"
                , "RGR"
                , "DRD");

        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        sr.setIngredient('R', Material.REDSTONE_BLOCK);
        sr.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
        Bukkit.getServer().addRecipe(sr);
    }
}
