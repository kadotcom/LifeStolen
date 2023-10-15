package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LifeStolenHelp implements CommandExecutor {
    LifeStolen plugin;
    public LifeStolenHelp(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if(!plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased")){
                if(args.length >= 1){
                    System.out.println(args.toString());
                    if(args[0].equals("1")){
                        p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                    }else if(args[0].equals("2")){
                        p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (2/2):\n/removehealth - Removes from a players health\n/withdraw - Withdraws a heart from yourself\n/reloadls - Reloads the plugin config\n/lshelp - Shows this message.");
                    }else{
                        p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                    }
                }else {
                    p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                }
            }

            if(plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.lshelp.permission"))){
                if(args.length >= 1){
                    System.out.println(args.toString());
                    if(args[0].equals("1")){
                        p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                    }else if(args[0].equals("2")){
                        p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (2/2):\n/removehealth - Removes from a players health\n/withdraw - Withdraws a heart from yourself\n/reloadls - Reloads the plugin config\n/lshelp - Shows this message.");
                    }else{
                        p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                    }
                }else {
                    p.sendMessage(ChatColor.RED + "LifeStolen " + ChatColor.RESET + "Commands (1/2):\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health");
                }
            }else if (plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.lshelp.permission"))){
                p.sendMessage("§f[§cLifeStolen§f] You don't have permission to use this command.");
            }

        }

        return true;
    }
}
