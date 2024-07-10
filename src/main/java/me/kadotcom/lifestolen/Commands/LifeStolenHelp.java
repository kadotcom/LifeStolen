package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

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

                for(Map.Entry<String, Map<String, Object>> pdf : plugin.getDescription().getCommands().entrySet()){
                    String cmdName = pdf.getKey();
                    Object cmdProperties = pdf.getValue();
                    String stringedProperties = cmdProperties.toString().replace("{","").replace("}","").replace("description=","").replace(", usage="," | ");
                    p.sendMessage(ChatColor.WHITE + "/" + cmdName + " | " + stringedProperties);
                }
                //p.sendMessage("§f[§cLifeStolen§f] This command is disabled as it's going under a rework that will be out either next Tested release, or in 5.0.");
            }else if (plugin.getConfig().getBoolean("permissions.lshelp.bePermissionBased") && !p.hasPermission(plugin.getConfig().getString("permissions.lshelp.permission"))){
                p.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] " + plugin.getConfig().getString("translation.errorMessages.noPermission"));
            }

        }

        return true;
    }
}
