package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemEvent implements Listener {
    LifeStolen plugin;
    public ItemEvent(LifeStolen ls){
        plugin = ls;
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {

                if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                    if(HealthManager.getMaxHealth(event.getPlayer()) != plugin.getConfig().getInt("MaxHP")){
                        Player p = event.getPlayer();
                        p.setMaxHealth(p.getMaxHealth() + 2.0);
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    }else if(HealthManager.getMaxHealth(event.getPlayer()) >= plugin.getConfig().getInt("MaxHP")){
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("MaxHP"), event.getPlayer());
                    }

                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                    if(HealthManager.getMaxHealth(event.getPlayer()) != plugin.getConfig().getInt("MaxHP")){
                        Player p = event.getPlayer();
                        p.setMaxHealth(p.getMaxHealth() + 2.0);
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    }else if(HealthManager.getMaxHealth(event.getPlayer()) >= plugin.getConfig().getInt("MaxHP")){
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("MaxHP"), event.getPlayer());
                    }
                }
            }
        }
    }
}
