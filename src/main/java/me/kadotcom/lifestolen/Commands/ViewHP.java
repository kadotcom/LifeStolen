package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewHP implements CommandExecutor {
    LifeStolen plugin;
    public ViewHP(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;
            if(!plugin.getConfig().getBoolean("permissions.viewhp.bePermissionBased") || plugin.getConfig().getBoolean("permissions.viewhp.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.viewhp.permission"))){
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + ChatColor.AQUA + "Your exact health is " + p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + "");
                p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if (plugin.getConfig().getBoolean("permissions.viewhp.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.viewhp.permission"))){
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }

        }
        return true;
    }

}