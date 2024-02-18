package me.kadotcom.lifestolen.Managers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeManager {
    public static GameMode getGamemode(Player p){ return p.getGameMode(); }
    public static void setGamemode(GameMode gm, Player p){
        p.setGameMode(gm);
    }
    public static void setGamemodeAndHealth(GameMode gm, int hp, Player p){
        p.setGameMode(gm);
        HealthManager.setMaxHealth(hp, p);
    }
}
