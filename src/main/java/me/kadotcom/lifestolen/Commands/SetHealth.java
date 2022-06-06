package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.Managers.HealthManager;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHealth implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Player sName = Bukkit.getPlayer(args[0]);


            if(sName != null){


                try {
                    int i = Integer.parseInt(args[1]);

                    HealthManager.setMaxHealth(i, sName);
                } catch (NumberFormatException e) {
                    p.sendMessage("'" + args[1] + "' isn't a valid number");
                }




            }else{
                p.sendMessage("Player not found!");
            }

        }

        return true;
    }
}
