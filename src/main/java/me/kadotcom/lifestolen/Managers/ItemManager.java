package me.kadotcom.lifestolen.Managers;

import me.kadotcom.lifestolen.LifeStolen;
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

    private static final LifeStolen main = LifeStolen.getPlugin(LifeStolen.class);

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
        sr.shape("123"
                , "456"
                , "789");

        sr.setIngredient('1', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot1")));
        sr.setIngredient('2', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot2")));
        sr.setIngredient('3', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot3")));
        sr.setIngredient('4', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot4")));
        sr.setIngredient('5', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot5")));
        sr.setIngredient('6', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot6")));
        sr.setIngredient('7', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot7")));
        sr.setIngredient('8', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot8")));
        sr.setIngredient('9', Material.matchMaterial(main.getConfig().getString("HeartRecipe.Slot9")));
        Bukkit.getServer().addRecipe(sr);
    }


}
