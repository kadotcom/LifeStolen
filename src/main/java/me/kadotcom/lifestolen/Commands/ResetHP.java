package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetHP implements CommandExecutor {
    LifeStolen plugin;
    public ResetHP(LifeStolen ls){
        plugin = ls;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.isOp() || p.hasPermission("lifestolen.resethp") || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if(args[0].equalsIgnoreCase("all")){
                    for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.startHP"), target);
                        target.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + ChatColor.RED + plugin.getConfig().getString("translation.ingameMessages.resetHealth"));
                    }
                }else{
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if(target != null){
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.startHP"), target);

                        target.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + ChatColor.RED + plugin.getConfig().getString("translation.ingameMessages.resetHealth"));
                    }else{
                        p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.offlinePlayer"));
                        return true;
                    }
                }
            }else{
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }
        }
        return true;
    }

}
