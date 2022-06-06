package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public class LifeStealEvent implements Listener {

    LifeStolen plugin;
    public LifeStealEvent(LifeStolen ls){
        plugin = ls;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Entity e = event.getEntity().getKiller();

        if(HealthManager.getMaxHealth(p) > 2.0){
            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 2.0, p);
        }else if(HealthManager.getMaxHealth(p) <= 2.0){
            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 1.0, p);
        }

        if (e instanceof Player) {
                if (HealthManager.getMaxHealth(p) > 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());

                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("MaxHP")) {
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("MaxHP")) {
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("MaxHP"), (Player) e);
                    }

                    //((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                    //p.setMaxHealth(p.getMaxHealth() - 2.0);
                } else if (HealthManager.getMaxHealth(p) <= 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());
                    //((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                    //p.setMaxHealth(p.getMaxHealth() - 1.0);

                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("MaxHP")) {
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("MaxHP")) {
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("MaxHP"), (Player) e);
                    }
                }


            }

            if (HealthManager.getMaxHealth(p) == 1.0) {

                Date date = new Date(System.currentTimeMillis()+plugin.getConfig().getInt("banTime") * 1000);

                HealthManager.setMaxHealth(plugin.getConfig().getInt("returnHP"), p);
                Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(p.getName(), plugin.getConfig().getString("banReason"), date, null);
                p.kickPlayer(plugin.getConfig().getString("kickMessage"));
                //Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "minecraft:ban " + p.getName() + " You have ran out of hearts...");

                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    target.playSound(target, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
                    target.sendMessage(ChatColor.RED + p.getName() + " has ran out of hearts...");
                }

            }


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            HealthManager.setMaxHealth(20, event.getPlayer());
        }
    }
}
