package me.kadotcom.lifestolen.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LifeStolenHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player plr = (Player) sender;
            if(args.length >= 1){
                System.out.println(args.toString());
                if(args[0].equals("1")){
                    plr.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                }else if(args[0].equals("2")){
                    plr.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (2/2):\n/removehealth - Removes from a players health\n/withdraw - Withdraws a heart from yourself\n/reloadls - Reloads the plugin config\n/lshelp - Shows this message.");
                }else{
                    plr.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                }
            }else {
                plr.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
            }
        }

        return true;
    }
}
