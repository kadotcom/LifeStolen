package me.kadotcom.lifestolen.Commands;

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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(ChatColor.AQUA + "Your exact health is " + p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + "");
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        }
        return true;
    }

}
