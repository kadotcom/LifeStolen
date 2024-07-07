package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Utils.InventoryUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIEvent implements Listener {

    LifeStolen plugin;
    public GUIEvent(LifeStolen ls){
        plugin = ls;
    }

    @EventHandler
    public void ClickEvent(InventoryClickEvent e){
        if(InventoryUtil.getInventoryTitle(e).equalsIgnoreCase(plugin.getConfig().getString("translation.serverName") + " Crafting View")){
            e.setCancelled(true);
        }
    }
}
