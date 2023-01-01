package me.kadotcom.lifestolen.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifeStolenHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player plr = (Player) sender;

            plr.sendMessage("LifeStolen Commands:\n/resethp - Reset everyone onlines health\n/health - Shows your health\n/giveheart - Gives you a heart item.\n/sethealth - Sets to a player health.\n/addhealth - Add to a players health\n/removehealth - Removes from a players health\n/withdraw - Withdraws a heart from yourself\n/reloadls - Reloads the plugin config\n/lshelp - Shows this text");
        }

        return true;
    }
}
