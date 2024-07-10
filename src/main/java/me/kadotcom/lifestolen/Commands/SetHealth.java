package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHealth implements CommandExecutor {
    LifeStolen plugin;
    public SetHealth(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Player sName = Bukkit.getPlayer(args[0]);

            if(p.isOp() || p.hasPermission("lifestolen.sethealth") || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if(sName != null){
                    try {
                        int i = Integer.parseInt(args[1]);

                        HealthManager.setMaxHealth(i, sName);
                    } catch (NumberFormatException e) {
                        p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] '" + args[1] + "' isn't a valid number");
                    }
                }else{
                    p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.offlinePlayer"));
                }
            }else{
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }
        }

        return true;
    }
}
