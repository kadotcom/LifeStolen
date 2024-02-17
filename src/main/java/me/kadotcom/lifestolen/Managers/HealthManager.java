package me.kadotcom.lifestolen.Managers;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Utils.UserDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealthManager {
    private static final LifeStolen main = LifeStolen.getPlugin(LifeStolen.class);

    public static void setMaxHealth(double h, Player p){
        UserDataHandler user = new UserDataHandler(main, p.getUniqueId());
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(h);

        if(user.getUserFile() != null){
            user.getUserFile().set("User.Config.Info.Health",h);
            user.saveUserFile();
        }else{
            user.createUser(p);
            user.getUserFile().set("User.Config.Info.Health",h);
            user.saveUserFile();
        }

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
