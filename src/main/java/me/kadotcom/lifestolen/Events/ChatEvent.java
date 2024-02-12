package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.ReviveManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatEvent implements Listener {

    LifeStolen plugin;
    public ChatEvent(LifeStolen ls){
        plugin = ls;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if (ItemEvent.players.contains(event.getPlayer())) {
            String playerName = event.getMessage();
            ReviveManager.revivePlayer(event.getPlayer(), playerName);

            ItemEvent.players.remove(event.getPlayer());
            event.setCancelled(true);
        }
    }
}
