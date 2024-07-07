package me.kadotcom.lifestolen.Utils;

import org.bukkit.event.inventory.InventoryEvent;

public class InventoryUtil {

    public static String getInventoryTitle(InventoryEvent e){
        return e.getView().getTitle();
    }
}
