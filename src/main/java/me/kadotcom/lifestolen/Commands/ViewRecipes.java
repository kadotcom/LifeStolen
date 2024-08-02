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

        if(!plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") || plugin.getConfig().getBoolean("permissions.viewrecipes.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.viewrecipes.permission"))){
            if (args.length < 1) {
                sender.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Usage: " + plugin.getCommand("viewrecipes").getUsage());
                return true;
            }
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
                ItemStack[] menu = new ItemStack[] {
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
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.FLINT),
                        new ItemStack(Material.FLINT),
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

                if(plugin.getConfig().getString("ReviverRecipe.Slot1").equalsIgnoreCase("HEART")){
                    menu[12] = ItemManager.heart;
                }else{
                    menu[12] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot1")));
                }

                if(plugin.getConfig().getString("HeartRecipe.Slot2").equalsIgnoreCase("HEART")){
                    menu[13] = ItemManager.heart;
                }else{
                    menu[13] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot2")));
                }
                if(plugin.getConfig().getString("HeartRecipe.Slot3").equalsIgnoreCase("HEART")){
                    menu[14] = ItemManager.heart;
                }else{
                    menu[14] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot3")));
                }

                if(plugin.getConfig().getString("HeartRecipe.Slot4").equalsIgnoreCase("HEART")){
                    menu[21] = ItemManager.heart;
                }else{
                    menu[21] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot4")));
                }
                if(plugin.getConfig().getString("HeartRecipe.Slot5").equalsIgnoreCase("HEART")){
                    menu[22] = ItemManager.heart;
                }else{
                    menu[22] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot5")));
                }
                if(plugin.getConfig().getString("HeartRecipe.Slot6").equalsIgnoreCase("HEART")){
                    menu[23] = ItemManager.heart;
                }else{
                    menu[23] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot6")));
                }

                if(plugin.getConfig().getString("HeartRecipe.Slot7").equalsIgnoreCase("HEART")){
                    menu[30] = ItemManager.heart;
                }else{
                    menu[30] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot7")));
                }
                if(plugin.getConfig().getString("HeartRecipe.Slot8").equalsIgnoreCase("HEART")){
                    menu[31] = ItemManager.heart;
                }else{
                    menu[31] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot8")));
                }
                if(plugin.getConfig().getString("HeartRecipe.Slot9").equalsIgnoreCase("HEART")){
                    menu[32] = ItemManager.heart;
                }else{
                    menu[32] = new ItemStack(Material.valueOf(plugin.getConfig().getString("ReviverRecipe.Slot9")));
                }

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
