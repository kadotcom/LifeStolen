package me.kadotcom.lifestolen.Managers;

import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealthManager {
    public static void setMaxHealth(double h, Player p){
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(h);
    }
    public static double getMaxHealth(Player p){
        return p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }
    public static void heal(double h, Player p){
        p.setHealth(h);
    }
    public static void setOfflinePlayerMaxHealth(double h, OfflinePlayer p){
        p.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(h);

    }
}
