package me.kadotcom.lifestolen.Commands;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

        public class Withdraw implements CommandExecutor {
            LifeStolen plugin;
            public Withdraw(LifeStolen ls){
                plugin = ls;
            }

            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if(sender instanceof Player){
                    Player p = (Player) sender;

                    if(plugin.getConfig().getBoolean("heart.isEnabled")){
                        if(HealthManager.getMaxHealth(p) > 2.0){
                            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 2, p);
                            p.getInventory().addItem(ItemManager.heart);
                        }
                    }else {
                        p.sendMessage(plugin.getConfig().getString("heart.disabledMessage").replace("&", "§"));
                    }
                }

        return true;
    }
}
