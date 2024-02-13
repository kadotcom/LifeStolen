package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Utils.HTTP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Health implements CommandExecutor {
    LifeStolen plugin;
    public Health(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player p = (Player) sender;
            if(!plugin.getConfig().getBoolean("permissions.health.bePermissionBased") || plugin.getConfig().getBoolean("permissions.health.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.health.permission"))){
                p.sendMessage("§f[§cLifeStolen§f] " + ChatColor.AQUA + "Your exact health is " + p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + "");
                p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if (plugin.getConfig().getBoolean("permissions.health.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.health.permission"))){
                p.sendMessage("§f[§cLifeStolen§f] You don't have permission to use this command.");
            }

        }
        return true;
    }

}
