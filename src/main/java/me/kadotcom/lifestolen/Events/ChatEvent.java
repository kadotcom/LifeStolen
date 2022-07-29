package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.BanManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    LifeStolen plugin;
    public ChatEvent(LifeStolen ls){
        plugin = ls;
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(ItemEvent.players.contains(event.getPlayer())){

            OfflinePlayer sName = Bukkit.getOfflinePlayer(event.getMessage());

            if(sName != null){
                if(Bukkit.getBanList(BanList.Type.NAME).isBanned(sName.getName())){
                    BanManager.unban(sName);
                    ItemEvent.players.remove(event.getPlayer());
                    event.getPlayer().sendMessage("You revived " + sName.getName() + ".");
                }else {
                    event.getPlayer().sendMessage("Player you mentioned isn't banned.");
                    ItemEvent.players.remove(event.getPlayer());

                }

            }else {
                ItemEvent.players.remove(event.getPlayer());
                event.getPlayer().sendMessage("Mention a player that has joined.");
            }

            event.setCancelled(true);
        }
    }
}
