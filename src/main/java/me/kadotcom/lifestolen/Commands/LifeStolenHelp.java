package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;

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
            if(!plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased") || plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.lshelp.permission"))){
                /*
                This is gonna go under a HUGE rework.
                So I am gonna delete the code in here for now, if I do a tested 5.0 without this done, expect it to be disabled.
                 */
            }else if (plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.lshelp.permission"))){
                p.sendMessage("§f[§cLifeStolen§f] You don't have permission to use this command.");
            }

        }

        return true;
    }
}
