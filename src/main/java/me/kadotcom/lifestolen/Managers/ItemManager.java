package me.kadotcom.lifestolen.Managers;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private static final LifeStolen main = LifeStolen.getPlugin(LifeStolen.class);
    public static ItemStack heart;
    public static ItemStack reviver;
    public static void init(){
        if(main.getConfig().getBoolean("heart.isEnabled")){
            createHeart();
        }
        if(main.getConfig().getBoolean("reviver.isEnabled")){
            createReviver();
        }
    }
    private static void createHeart(){
        ItemStack item = new ItemStack(Material.matchMaterial(main.getConfig().getString("heart.item")), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.getConfig().getString("heart.itemName").replace("&","§"));
        List<String> lore = new ArrayList<>();
        StringBuilder equals = new StringBuilder("=");
        for(int i = 0; i < main.getConfig().getString("heart.itemLore").replace("&","§").length(); i++){
            equals.append("=");
        }
        lore.add(ChatColor.GRAY + "" + equals);
        lore.add(main.getConfig().getString("heart.itemLore").replace("&","§"));
        lore.add(ChatColor.GRAY + "" + equals);
        meta.setLore(lore);
        if(main.getConfig().getBoolean("heart.useCustomModelData")){
            meta.setCustomModelData(main.getConfig().getInt("heart.customModelDataID"));
        }
        item.setItemMeta(meta);
        heart = item;

        if(main.getConfig().getBoolean("heart.isCraftable")){
            ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("heart"), item);
            sr.shape("123"
                    , "456"
                    , "789");

            sr.setIngredient('1', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot1")));
            sr.setIngredient('2', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot2")));
            sr.setIngredient('3', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot3")));
            sr.setIngredient('4', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot4")));
            sr.setIngredient('5', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot5")));
            sr.setIngredient('6', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot6")));
            sr.setIngredient('7', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot7")));
            sr.setIngredient('8', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot8")));
            sr.setIngredient('9', Material.valueOf(main.getConfig().getString("HeartRecipe.Slot9")));
            main.getServer().addRecipe(sr);
        }
    }
    private static void createReviver(){
        ItemStack item = new ItemStack(Material.matchMaterial(main.getConfig().getString("reviver.item")), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.getConfig().getString("reviver.itemName").replace("&","§"));
        List<String> lore = new ArrayList<>();
        StringBuilder equals = new StringBuilder("=");
        for(int i = 0; i < main.getConfig().getString("heart.itemLore").replace("&","§").length(); i++){
            equals.append("=");
        }
        lore.add(ChatColor.GRAY + "" + equals);
        lore.add(main.getConfig().getString("reviver.itemLore").replace("&","§"));
        lore.add(ChatColor.GRAY + "" + equals);
        if(main.getConfig().getBoolean("reviver.useCustomModelData")){
            meta.setCustomModelData(main.getConfig().getInt("reviver.customModelDataID"));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        reviver = item;

        if(main.getConfig().getBoolean("reviver.isCraftable")){
            ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("reviver"), item);
            sr.shape("123"
                    , "456"
                    , "789");

            if(main.getConfig().getString("ReviverRecipe.Slot1").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('1', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('1', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot1")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot2").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('2', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('2', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot2")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot3").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('3', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('3', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot3")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot4").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('4', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('4', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot4")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot5").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('5', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('5', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot5")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot6").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('6', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('6', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot6")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot7").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('7', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('7', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot7")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot8").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('8', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('8', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot8")));
            }
            if(main.getConfig().getString("ReviverRecipe.Slot9").equals("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                sr.setIngredient('9', Material.valueOf(main.getConfig().getString("heart.item")));
            }else{
                sr.setIngredient('9', Material.valueOf(main.getConfig().getString("ReviverRecipe.Slot9")));
            }
            main.getServer().addRecipe(sr);
        }


    }


    public static boolean doesRecipeContainCustomItem(String item, String recipe){
        if(recipe.equalsIgnoreCase("REVIVER")){
            if(item.equalsIgnoreCase("HEART") && main.getConfig().getBoolean("heart.isEnabled")){
                if(main.getConfig().getString("ReviverRecipe.Slot1").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot2").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot3").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot4").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot5").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot6").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot7").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot8").equals("HEART") || main.getConfig().getString("ReviverRecipe.Slot9").equals("HEART")){
                    return true;
                }else{
                    return false;
                }
            }
        }

        return false;
    }
}