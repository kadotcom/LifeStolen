package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.Managers.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemEvent implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                    Player p = event.getPlayer();
                    p.setMaxHealth(p.getMaxHealth() + 2.0);
                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                    Player p = event.getPlayer();
                    p.setMaxHealth(p.getMaxHealth() + 2.0);
                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                }
            }
        }
    }
}
