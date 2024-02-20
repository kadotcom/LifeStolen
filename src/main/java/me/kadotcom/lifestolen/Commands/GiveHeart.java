package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveHeart implements CommandExecutor {
    LifeStolen plugin;
    public GiveHeart(LifeStolen ls){
        plugin = ls;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.isOp() || p.hasPermission("lifestolen.giveheart") || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if(plugin.getConfig().getBoolean("heart.isEnabled")){
                    p.getInventory().addItem(ItemManager.heart);
                }else {
                  p.sendMessage(plugin.getConfig().getString("heart.disabledMessage").replace("&", "§"));
                }
                return true;
            }else{
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }

        }

        return true;
    }
}
