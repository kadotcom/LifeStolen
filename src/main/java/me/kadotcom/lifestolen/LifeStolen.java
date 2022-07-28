package me.kadotcom.lifestolen;

import me.kadotcom.lifestolen.Commands.*;
import me.kadotcom.lifestolen.Events.ChatEvent;
import me.kadotcom.lifestolen.Events.ItemEvent;
import me.kadotcom.lifestolen.Events.LifeStealEvent;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeStolen extends JavaPlugin {


    @Override
    public void onEnable() {
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

        System.out.println("♡ LifeStolen \nver: " + this.getDescription().getVersion() + " \nPlugin by: KadotCom");
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
