package me.kadotcom.lifestolen;

import me.kadotcom.lifestolen.Commands.GiveHeart;
import me.kadotcom.lifestolen.Commands.Health;
import me.kadotcom.lifestolen.Commands.ResetHP;
import me.kadotcom.lifestolen.Events.ItemEvent;
import me.kadotcom.lifestolen.Events.LifeStealEvent;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeStolen extends JavaPlugin {



    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemEvent(), this);
        getServer().getPluginManager().registerEvents(new LifeStealEvent(), this);
        getCommand("giveheart").setExecutor(new GiveHeart());
        getCommand("health").setExecutor(new Health());
        getCommand("resethp").setExecutor(new ResetHP());

        System.out.println("♡ LifeStolen ver: " + this.getDescription().getVersion() + " \nPlugin by: KadotCom");
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
