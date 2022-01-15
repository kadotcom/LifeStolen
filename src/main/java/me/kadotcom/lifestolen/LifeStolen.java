package me.kadotcom.lifestolen;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public final class LifeStolen extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);


    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();
        Entity e = event.getEntity().getKiller();
        if(e instanceof Player) {

            System.out.println("Player " + e.getName() + " Killed " + p.getName());
            ((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2);
            p.setMaxHealth(p.getMaxHealth() - 2);
            ((Player) e).setHealth(((Player) e).getMaxHealth());
        }




        ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("resetHealth")){
            for(Player target : Bukkit.getServer().getOnlinePlayers()){
                target.setMaxHealth(20.0);
            }
        }

        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
