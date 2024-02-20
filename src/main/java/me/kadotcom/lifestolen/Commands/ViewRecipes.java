package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ViewRecipes implements CommandExecutor {
    LifeStolen plugin;
    public ViewRecipes(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if(sender instanceof Player){
        Player p = (Player) sender;

        if(!plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") || plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.viewrecipes.permission"))){
            Inventory gui = Bukkit.createInventory(p,45, plugin.getConfig().getString("translation.serverName") + " Crafting View");
            if(args[0].equalsIgnoreCase("heart")){
                ItemStack[] menu = {
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot1"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot2"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot3"))),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot4"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot5"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot6"))),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot7"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot8"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("HeartRecipe.Slot9"))),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                };
                gui.setContents(menu);
            }else if(args[0].equalsIgnoreCase("reviver")){
                ItemStack[] menu = {
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot1"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot2"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot3"))),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot4"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot5"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot6"))),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot7"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot8"))),
                        new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot9"))),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                };
                gui.setContents(menu);
            }else{
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + args[0] + " isn't a valid item in LifeStolen.");
                gui = null;
                return true;
            }
            p.openInventory(gui);
        }else if (plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.viewrecipes.permission"))){
            p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
        }

    }

        return true;
    }
}
