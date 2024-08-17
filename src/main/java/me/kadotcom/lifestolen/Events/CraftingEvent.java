package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingEvent implements Listener {

    LifeStolen plugin;
    public CraftingEvent(LifeStolen ls){
        plugin = ls;
    }
    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack[] matrix = inventory.getMatrix();

        boolean hasCustomItem = false;

        if(inventory.getResult() != null && inventory.getResult().getItemMeta().hasDisplayName() && inventory.getResult().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("reviver.itemName")) && inventory.getResult().getType() == Material.valueOf(plugin.getConfig().getString("reviver.item")) && ItemManager.doesRecipeContainCustomItem("HEART", "REVIVER")){
            for (ItemStack item : matrix) {
                if (item != null && item.getType() == Material.valueOf(plugin.getConfig().getString("heart.item"))) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals(plugin.getConfig().getString("heart.itemName"))) {
                        hasCustomItem = true;
                    } else {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                        return;
                    }
                }
            }

            if (!hasCustomItem) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }

    }
}
