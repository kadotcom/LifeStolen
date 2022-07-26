package me.kadotcom.lifestolen.Managers;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Date;

public class BanManager {

    public static void ban(Player p, int sec, int returnHP, String reason, String kickMessage){
        Date date = new Date(System.currentTimeMillis()+sec * 1000);

        HealthManager.setMaxHealth(returnHP, p);
        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(p.getName(), reason.replace("&", "§"), date, null);
        p.kickPlayer(kickMessage.replace("&", "§"));
    }

    public static void ban(Player p, int sec, String reason, String kickMessage){
        Date date = new Date(System.currentTimeMillis()+sec * 1000);

        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(p.getName(), reason.replace("&", "§"), date, null);
        p.kickPlayer(kickMessage.replace("&", "§"));
    }

    public static void unban(OfflinePlayer p){
        Bukkit.getServer().getBanList(BanList.Type.NAME).pardon(p.getName());
    }
}
