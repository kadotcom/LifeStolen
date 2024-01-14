package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ItemManager;
import me.kadotcom.lifestolen.Utils.HTTP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VerCheck implements CommandExecutor {
    LifeStolen plugin;
    public VerCheck(LifeStolen ls){
        plugin = ls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            String ver = plugin.getDescription().getVersion();

            if(!plugin.getConfig().getBoolean("permissions.vercheck.bePermissionBased")){
                if (!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220").equalsIgnoreCase(plugin.getDescription().getVersion()) && !plugin.getDescription().getVersion().contains("Tested")) {
                    p.sendMessage("&7You are currently using LifeStolen version " + ver + ", you are using an outdated version of LifeStolen.");
                } else {
                    p.sendMessage("&7You are currently using LifeStolen version " + ver);
                }
            }

            if(plugin.getConfig().getBoolean("permissions.vercheck.bePermissionBased") && p.hasPermission(plugin.getConfig().getString("permissions.vercheck.permission"))){
                if (!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220").equalsIgnoreCase(plugin.getDescription().getVersion()) && !plugin.getDescription().getVersion().contains("Tested") && plugin.getConfig().getBoolean("disableVersionMessage")) {
                    p.sendMessage("&7You are currently using LifeStolen version " + ver + ", you are using an outdated version of LifeStolen.");
                } else {
                    p.sendMessage("&7You are currently using LifeStolen version " + ver);
                }
            }else if (plugin.getConfig().getBoolean("permissions.vercheck.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.vercheck.permission"))){
                p.sendMessage("§f[§cLifeStolen§f] You don't have permission to use this command.");
            }
        }
        return true;
    }
}
