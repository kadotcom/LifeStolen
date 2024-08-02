package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LSGive implements CommandExecutor {
    LifeStolen plugin;
    public LSGive(LifeStolen ls){
        plugin = ls;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.isOp() || p.hasPermission("lifestolen.lsgive") || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                Player giving = Bukkit.getPlayerExact(args[0]);
                String item = args[1];
                int amount = Integer.parseInt(args[2]);

                if(giving == null){
                    p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.offlinePlayer"));
                    return true;
                }
                if(amount <= 0){
                    p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.amountUnderZero"));
                    return true;
                }

                if(item.equalsIgnoreCase("heart")){
                    if(plugin.getConfig().getBoolean("heart.isEnabled")){
                        if(amount == 1){
                            p.getInventory().addItem(ItemManager.heart);
                        }else{
                            for(int i = 0; i < amount; i++){
                                p.getInventory().addItem(ItemManager.heart);
                            }
                        }
                    }else {
                        p.sendMessage(plugin.getConfig().getString("heart.disabledMessage").replace("&", "§"));
                    }
                }else if(item.equalsIgnoreCase("reviver")){
                    if(plugin.getConfig().getBoolean("reviver.isEnabled")){
                        if(amount == 1){
                            p.getInventory().addItem(ItemManager.reviver);
                        }else{
                            for(int i = 0; i < amount; i++){
                                p.getInventory().addItem(ItemManager.reviver);
                            }
                        }
                    }else {
                        p.sendMessage(plugin.getConfig().getString("reviver.disabledMessage").replace("&", "§"));
                    }
                }else{
                    p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.itemDoesNotExist"));
                    return true;
                }

                return true;
            }else{
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }

        }

        return true;
    }
}
