package me.kadotcom.lifestolen;

import me.kadotcom.lifestolen.Commands.*;
import me.kadotcom.lifestolen.Events.ChatEvent;
import me.kadotcom.lifestolen.Events.GUIEvent;
import me.kadotcom.lifestolen.Events.ItemEvent;
import me.kadotcom.lifestolen.Events.LifeStealEvent;
import me.kadotcom.lifestolen.Managers.ItemManager;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.kadotcom.lifestolen.Utils.HTTP;

import java.util.logging.Logger;

public final class LifeStolen extends JavaPlugin {
    private Logger log;

    @Override
    public void onEnable() {
        log = getLogger();

        // Events
        getServer().getPluginManager().registerEvents(new ItemEvent(this), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new GUIEvent(this), this);
        getServer().getPluginManager().registerEvents(new LifeStealEvent(this), this);
        // Commands
        getCommand("giveheart").setExecutor(new GiveHeart(this));
        getCommand("health").setExecutor(new Health(this));
        getCommand("resethp").setExecutor(new ResetHP(this));
        getCommand("sethealth").setExecutor(new SetHealth(this));
        getCommand("withdraw").setExecutor(new Withdraw(this));
        getCommand("addhealth").setExecutor(new AddHealth(this));
        getCommand("removehealth").setExecutor(new RemoveHealth(this));
        getCommand("reloadls").setExecutor(new ReloadLifeStolen(this));
        getCommand("lshelp").setExecutor(new LifeStolenHelp(this));
        getCommand("vercheck").setExecutor(new VerCheck(this));
        getCommand("viewrecipes").setExecutor(new ViewRecipes(this));

        // Plugin startup logic
        if(!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220").equalsIgnoreCase(this.getDescription().getVersion()) && !this.getDescription().getVersion().contains("Tested") && !this.getConfig().getBoolean("disableVersionMessage")){
            log.info("♡ LifeStolen ♡ \nVersion: " + this.getDescription().getVersion() + " \nPlugin by: KadotCom\n\n(NOTE: This version is outdated)");
        }else {
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
