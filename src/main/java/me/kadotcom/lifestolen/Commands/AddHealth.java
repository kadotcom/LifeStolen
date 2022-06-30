package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddHealth implements CommandExecutor {
    LifeStolen plugin;
    public AddHealth(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Player sName = Bukkit.getPlayer(args[0]);

            if(p.isOp() || p.hasPermission(plugin.getConfig().getString("permissions.addhealth.permission")) || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if(sName != null){


                    try {
                        int i = Integer.parseInt(args[1]);

                        HealthManager.setMaxHealth(HealthManager.getMaxHealth(sName) + i, sName);
                    } catch (NumberFormatException e) {
                        p.sendMessage("'" + args[1] + "' isn't a valid number");
                    }




                }else{
                    p.sendMessage("Player not found!");
                }
            }else{
                p.sendMessage(plugin.getConfig().getString("permissions.sethealth.permission-message").replace("&", "§"));
            }





        }
        return true;
    }
}