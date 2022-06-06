package me.kadotcom.lifestolen;

import me.kadotcom.lifestolen.Commands.*;
import me.kadotcom.lifestolen.Events.ItemEvent;
import me.kadotcom.lifestolen.Events.LifeStealEvent;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeStolen extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemEvent(this), this);
        getServer().getPluginManager().registerEvents(new LifeStealEvent(this), this);
        getCommand("giveheart").setExecutor(new GiveHeart());
        getCommand("health").setExecutor(new Health());
        getCommand("resethp").setExecutor(new ResetHP());
        getCommand("sethealth").setExecutor(new SetHealth());
        getCommand("withdraw").setExecutor(new Withdraw());

        System.out.println("♡ LifeStolen ver: " + this.getDescription().getVersion() + " \nPlugin by: KadotCom\nOpen-Source");


        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
