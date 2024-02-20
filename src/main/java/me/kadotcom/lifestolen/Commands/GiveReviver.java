package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveReviver implements CommandExecutor {
    LifeStolen plugin;
    public GiveReviver(LifeStolen ls){
        plugin = ls;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.isOp() || p.hasPermission(plugin.getCommand("givereviver").getPermission())  || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if(plugin.getConfig().getBoolean("reviver.isEnabled")){
                    p.getInventory().addItem(ItemManager.reviver);
                }else {
                  p.sendMessage(plugin.getConfig().getString("reviver.disabledMessage").replace("&", "§"));
                }
                return true;
            }else{
                p.sendMessage("§f[§cLifeStolen§f] You don't have permission to use this command.");
            }

        }

        return true;
    }
}
