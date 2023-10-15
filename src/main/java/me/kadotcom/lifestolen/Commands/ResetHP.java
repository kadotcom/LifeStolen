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
            if(p.isOp() || p.hasPermission(plugin.getCommand("resethp").getPermission())  || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    HealthManager.setMaxHealth(20, target);
                    target.sendMessage(ChatColor.RED + "Your hearts has been resetted.");

                }
            }else{
                p.sendMessage(plugin.getConfig().getString("permissions.resethp.permission-message").replace("&", "§"));
            }




        }
        return true;
    }

}
