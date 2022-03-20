package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class LifeStealEvent implements Listener {

    private final LifeStolen main = LifeStolen.getPlugin(LifeStolen.class);
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Entity e = event.getEntity().getKiller();
        if (e instanceof Player) {

            if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() > 2.0) {
                System.out.println("Player " + e.getName() + " Killed " + p.getName());
                ((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                p.setMaxHealth(p.getMaxHealth() - 2.0);
            } else if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() <= 2.0) {
                System.out.println("Player " + e.getName() + " Killed " + p.getName());
                ((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                p.setMaxHealth(p.getMaxHealth() - 1.0);

            }

            if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == 1.0 && main.getConfig().getBoolean("kickWhenPlayerIsAtTheMinHP")) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    target.playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    target.sendMessage(ChatColor.RED + p.getName() + " has ran out of hearts...");
                    if(!p.hasPermission("lifestolen.savefromkick") && main.getConfig().getBoolean("kickStaffAtMinHP")){
                        p.kickPlayer("You have ran out of hearts...");
                    }else if (p.hasPermission("lifestolen.savefromkick") && !main.getConfig().getBoolean("kickStaffAtMinHP")){ }
                }
            } else if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == 1.0 && !main.getConfig().getBoolean("kickWhenPlayerIsAtTheMinHP")) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    target.playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    target.sendMessage(ChatColor.RED + p.getName() + " has ran out of hearts...");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == 1.0 && main.getConfig().getBoolean("kickWhenPlayerIsAtTheMinHP"))
            event.getPlayer().kickPlayer("You have ran out of hearts...");
        if (!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().setMaxHealth(main.getConfig().getDouble("maxHealth"));
        }
    }
}
