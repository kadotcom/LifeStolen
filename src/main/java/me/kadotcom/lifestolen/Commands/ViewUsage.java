package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Utils.UserDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ViewUsage implements CommandExecutor {
    LifeStolen plugin;
    public ViewUsage(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;

            if(!plugin.getConfig().getBoolean("permissions.viewusage.bePermissionBased") || plugin.getConfig().getBoolean("permissions.viewusage.bePermissionBased") && player.hasPermission(plugin.getConfig().getString("permissions.viewusage.permission"))){
                if (args.length < 1) {
                    player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Usage: /viewusage [heart/reviver]");
                    return  true;
                }

                if(args[0].equalsIgnoreCase("heart")){
                    if(!plugin.getConfig().getBoolean("heart.haveLimitedUses")){
                        player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] heart.haveLimitedUses is disabled, so this command will not be executed.");
                        return true;
                    }
                    UserDataHandler user = new UserDataHandler(plugin, player.getUniqueId());
                    int usage = user.getUserFile().getInt("User.Config.Item.HeartUses");
                    player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] You have used " + usage + "/" + plugin.getConfig().getInt("heart.uses") + " hearts.");
                }else if(args[0].equalsIgnoreCase("reviver")){
                    if(!plugin.getConfig().getBoolean("reviver.haveLimitedUses")){
                        player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] reviver.haveLimitedUses is disabled, so this command will not be executed.");
                        return true;
                    }
                    UserDataHandler user = new UserDataHandler(plugin, player.getUniqueId());
                    int usage = user.getUserFile().getInt("User.Config.Item.ReviverUses");
                    player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] You have used " + usage + "/" + plugin.getConfig().getInt("reviver.uses") + " revivers." );
                }else{
                    player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Usage: /viewusage [heart/reviver]");
                }
            }else if (plugin.getConfig().getBoolean("permissions.viewusage.bePermissionBased") && !player.hasPermission(plugin.getConfig().getString("permissions.viewusage.permission"))) {
                player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }

            }

        return true;
    }
}
