package me.kadotcom.lifestolen.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetHP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.isOp() || p.hasPermission("lifestolen.resethealth") || p.hasPermission("lifestolen.*")){
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    target.setMaxHealth(20.0);
                    target.playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    target.sendMessage(ChatColor.RED + "Your hearts has been resetted.");

                }
            }else{
                p.sendMessage("You do not have the lifestolen.resethealth permission.");
            }




        }
        return true;
    }

}
