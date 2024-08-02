package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ViewRecipes implements CommandExecutor {
    LifeStolen plugin;
    public ViewRecipes(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(!plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") ||
                    (plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") &&
                            p.hasPermission(plugin.getConfig().getString("permissions.viewrecipes.permission")))){

                if (args.length < 1) {
                    sender.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Usage: " + plugin.getCommand("viewrecipes").getUsage());
                    return true;
                }

                Inventory gui = Bukkit.createInventory(p, 45, plugin.getConfig().getString("translation.serverName") + " Crafting View");

                if(args[0].equalsIgnoreCase("heart")){
                    ItemStack[] menu = new ItemStack[45];
                    for (int i = 0; i < menu.length; i++) {
                        menu[i] = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    }

                    menu[12] = getItemStack("HeartRecipe.Slot1");
                    menu[13] = getItemStack("HeartRecipe.Slot2");
                    menu[14] = getItemStack("HeartRecipe.Slot3");
                    menu[21] = getItemStack("HeartRecipe.Slot4");
                    menu[22] = getItemStack("HeartRecipe.Slot5");
                    menu[23] = getItemStack("HeartRecipe.Slot6");
                    menu[30] = getItemStack("HeartRecipe.Slot7");
                    menu[31] = getItemStack("HeartRecipe.Slot8");
                    menu[32] = getItemStack("HeartRecipe.Slot9");

                    gui.setContents(menu);
                } else if(args[0].equalsIgnoreCase("reviver")){
                    ItemStack[] menu = new ItemStack[45];
                    for (int i = 0; i < menu.length; i++) {
                        menu[i] = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    }

                    menu[12] = getItemStack("ReviverRecipe.Slot1");
                    menu[13] = getItemStack("ReviverRecipe.Slot2");
                    menu[14] = getItemStack("ReviverRecipe.Slot3");
                    menu[21] = getItemStack("ReviverRecipe.Slot4");
                    menu[22] = getItemStack("ReviverRecipe.Slot5");
                    menu[23] = getItemStack("ReviverRecipe.Slot6");
                    menu[30] = getItemStack("ReviverRecipe.Slot7");
                    menu[31] = getItemStack("ReviverRecipe.Slot8");
                    menu[32] = getItemStack("ReviverRecipe.Slot9");

                    gui.setContents(menu);
                } else {
                    p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + args[0] + " isn't a valid item in LifeStolen.");
                    return true;
                }

                p.openInventory(gui);
            } else if (plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") &&
                    !p.hasPermission(plugin.getConfig().getString("permissions.viewrecipes.permission"))){
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }
        }

        return true;
    }

    private ItemStack getItemStack(String configKey) {
        String materialName = plugin.getConfig().getString(configKey);
        if (materialName.equals("HEART")) {
            return ItemManager.heart;
        } else {
            return new ItemStack(Material.valueOf(materialName));
        }
    }
}