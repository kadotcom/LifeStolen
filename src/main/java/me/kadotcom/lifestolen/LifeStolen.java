package me.kadotcom.lifestolen;

import me.kadotcom.lifestolen.Commands.*;
import me.kadotcom.lifestolen.Events.ChatEvent;
import me.kadotcom.lifestolen.Events.ItemEvent;
import me.kadotcom.lifestolen.Events.LifeStealEvent;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.kadotcom.lifestolen.Utils.HTTP;

import java.util.logging.Logger;

public final class LifeStolen extends JavaPlugin {
    private Logger log;

    @Override
    public void onEnable() {
        log = getLogger();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemEvent(this), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new LifeStealEvent(this), this);
        getCommand("giveheart").setExecutor(new GiveHeart(this));
        getCommand("health").setExecutor(new Health());
        getCommand("resethp").setExecutor(new ResetHP(this));
        getCommand("sethealth").setExecutor(new SetHealth(this));
        getCommand("withdraw").setExecutor(new Withdraw(this));
        getCommand("addhealth").setExecutor(new AddHealth(this));
        getCommand("removehealth").setExecutor(new RemoveHealth(this));
        getCommand("reloadls").setExecutor(new ReloadLifeStolen(this));
        getCommand("lshelp").setExecutor(new LifeStolenHelp());

        if(!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220").equalsIgnoreCase(this.getDescription().getVersion())){
            log.info("♡ LifeStolen ♡ \nVersion: " + this.getDescription().getVersion() + " \nPlugin by: KadotCom\n\n(NOTE: This version is outdated)");
        }else{
            log.info("♡ LifeStolen ♡ \nVersion: " + this.getDescription().getVersion() + " \nPlugin by: KadotCom");
        }
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
