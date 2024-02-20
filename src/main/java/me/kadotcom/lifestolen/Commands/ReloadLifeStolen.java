package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadLifeStolen implements CommandExecutor {
    LifeStolen plugin;
    public ReloadLifeStolen(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.isOp() || p.hasPermission(plugin.getCommand("reloadls").getPermission())  || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                plugin.reloadConfig();
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Reloaded!");
            }else{
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }
        }
        return true;
    }
}
