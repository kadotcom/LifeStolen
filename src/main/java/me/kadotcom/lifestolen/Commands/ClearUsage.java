package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Utils.UserDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClearUsage implements CommandExecutor {
    LifeStolen plugin;
    public ClearUsage(LifeStolen ls){
        plugin = ls;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;

            if(player.isOp() || player.hasPermission("lifestolen.clearusage") || player.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if (args.length < 1) {
                    player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f]] Usage: /clearusage [player] [heart/reviver]");
                    return  true;
                }

                UUID uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();

                if(!Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {
                    player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] User " + args[0] + " has not played on this server before." );
                    return true;
                }else{
                    if(args[1].equalsIgnoreCase("heart")){
                        if(!plugin.getConfig().getBoolean("heart.haveLimitedUses")){
                            player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] heart.haveLimitedUses is disabled, so this command will not be executed.");
                            return true;
                        }
                        UserDataHandler user = new UserDataHandler(plugin, uuid);
                        int usage = user.getUserFile().getInt("User.Config.Item.HeartUses");
                        player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] You had used " + usage + "/" + plugin.getConfig().getInt("heart.uses") + " hearts. This is now 0." );
                        user.getUserFile().set("User.Config.Item.HeartUses", 0);
                        user.saveUserFile();
                    }else if(args[1].equalsIgnoreCase("reviver")){
                        if(!plugin.getConfig().getBoolean("reviver.haveLimitedUses")){
                            player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] reviver.haveLimitedUses is disabled, so this command will not be executed.");
                            return true;
                        }
                        UserDataHandler user = new UserDataHandler(plugin, uuid);
                        int usage = user.getUserFile().getInt("User.Config.Item.ReviverUses");
                        player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] You had used " + usage + "/" + plugin.getConfig().getInt("reviver.uses") + " revivers. This is now 0." );
                        user.getUserFile().set("User.Config.Item.ReviverUses", 0);
                        user.saveUserFile();
                    }else{
                        player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Usage: /clearusage [player] [heart/reviver]");
                    }
                }

            }else{
                player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }

        }
        return true;
    }
}
