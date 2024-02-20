package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ReviveManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Revive implements CommandExecutor {
    LifeStolen plugin;
    public Revive(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.isOp() || p.hasPermission(plugin.getCommand("revive").getPermission()) || p.hasPermission(plugin.getConfig().getString("permissions.permissionToDoEverything"))){
                if (args.length < 1) {
                    sender.sendMessage("§f[§cLifeStolen§f] Usage: " + plugin.getCommand("revive").getUsage());
                }
                new ReviveManager(plugin).revivePlayer(p, args[0]);
            }else{
                p.sendMessage("§f[§cLifeStolen§f] You don't have permission to use this command.");
            }
        }
        return true;
    }
}