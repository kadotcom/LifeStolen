package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.BanManager;
import me.kadotcom.lifestolen.Managers.GameModeManager;
import me.kadotcom.lifestolen.Managers.HealthManager;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Objects;

public class ChatEvent implements Listener {

    LifeStolen plugin;
    public ChatEvent(LifeStolen ls){
        plugin = ls;
    }


    @EventHandler
    public void onChat(PlayerChatEvent event){
        if(ItemEvent.players.contains(event.getPlayer())){

            OfflinePlayer sName = Bukkit.getOfflinePlayer(event.getMessage());
            Player sName2 = Bukkit.getPlayer(event.getMessage());

            if(sName != null || sName.hasPlayedBefore()){
                if(plugin.getConfig().getBoolean("death.banOnDeath")){
                    if(Bukkit.getBanList(BanList.Type.NAME).isBanned(sName.getName())){
                        BanManager.unban(sName);
                        ItemEvent.players.remove(event.getPlayer());
                        event.getPlayer().sendMessage("You revived " + sName.getName() + ".");
                    }else {
                        event.getPlayer().sendMessage("Player you mentioned isn't banned.");
                        ItemEvent.players.remove(event.getPlayer());

                    }
                }


            }else {
                ItemEvent.players.remove(event.getPlayer());
                event.getPlayer().sendMessage("Mention a player that has joined.");
            }

            if(sName2 != null){
                if(!plugin.getConfig().getBoolean("death.banOnDeath")){
                    if(sName2.getGameMode() == GameMode.SPECTATOR){
                        ItemEvent.players.remove(event.getPlayer());
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String command = "gamemode survival " + sName2.getName();
                        String command2 = "tp " + sName2.getName() + " " + Bukkit.getServer().getWorlds().get(0).getSpawnLocation().getX() + " " + Bukkit.getServer().getWorlds().get(0).getSpawnLocation().getY() + " " + Bukkit.getServer().getWorlds().get(0).getSpawnLocation().getZ();

                        Bukkit.dispatchCommand(console, command);
                        Bukkit.dispatchCommand(console, command2);

                    }else {
                        ItemEvent.players.remove(event.getPlayer());
                        event.getPlayer().sendMessage("Mention a player that is dead.");
                    }
                }
            }

            event.setCancelled(true);
        }
    }
}
