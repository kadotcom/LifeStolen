package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.HealthManager;
import me.kadotcom.lifestolen.Managers.ItemManager;
import me.kadotcom.lifestolen.Utils.UserDataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class ItemEvent implements Listener {
    LifeStolen plugin;
    public static ArrayList<Player> players = new ArrayList<Player>();
    public ItemEvent(LifeStolen ls){
        plugin = ls;
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        UserDataHandler user = new UserDataHandler(plugin, event.getPlayer().getUniqueId());

        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {

                if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                    if(HealthManager.getMaxHealth(event.getPlayer()) != plugin.getConfig().getInt("HP.maxHP")) {
                        if(plugin.getConfig().getBoolean("heart.haveLimitedUses")) {
                            if(user.getUserFile().getInt("User.Config.Item.HeartUses") >= plugin.getConfig().getInt("heart.maxUses")){
                                int uses = user.getUserFile().getInt("User.Config.Item.HeartUses");
                                user.getUserFile().set("User.Config.Item.HeartUses", plugin.getConfig().getInt("heart.maxUses"));
                                user.saveUserFile();
                                event.setCancelled(true);
                                return;
                            }
                        }
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                        Player p = event.getPlayer();
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) + 2.0, p);
                        int uses = user.getUserFile().getInt("User.Config.Item.HeartUses");
                        uses += 1;
                        user.getUserFile().set("User.Config.Item.HeartUses",uses);
                        user.saveUserFile();
                        event.setCancelled(true);
                    }else if(HealthManager.getMaxHealth(event.getPlayer()) >= plugin.getConfig().getInt("HP.maxHP")){
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.maxHP"), event.getPlayer());
                        event.setCancelled(true);
                    }
                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                    if(HealthManager.getMaxHealth(event.getPlayer()) != plugin.getConfig().getInt("HP.maxHP")) {
                        if (event.getItem().getItemMeta().equals(ItemManager.heart.getItemMeta())) {
                            if(HealthManager.getMaxHealth(event.getPlayer()) != plugin.getConfig().getInt("HP.maxHP")) {
                                if(plugin.getConfig().getBoolean("heart.haveLimitedUses")) {
                                    if(user.getUserFile().getInt("User.Config.Item.HeartUses") >= plugin.getConfig().getInt("heart.uses")){
                                        user.getUserFile().set("User.Config.Item.HeartUses", plugin.getConfig().getInt("heart.maxUses"));
                                        user.saveUserFile();
                                        event.setCancelled(true);
                                        return;
                                    }
                                    int uses = user.getUserFile().getInt("User.Config.Item.HeartUses");
                                    uses += 1;
                                    user.getUserFile().set("User.Config.Item.HeartUses",uses);
                                    user.saveUserFile();
                                }
                                event.getItem().setAmount(event.getItem().getAmount() - 1);
                                Player p = event.getPlayer();
                                HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) + 2.0, p);
                                event.setCancelled(true);
                    }else if(HealthManager.getMaxHealth(event.getPlayer()) >= plugin.getConfig().getInt("HP.maxHP")){
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.maxHP"), event.getPlayer());
                        event.setCancelled(true);
                    }
                }
            }
        }else if (event.getItem().getItemMeta().equals(ItemManager.reviver.getItemMeta())) {
                    Player player = event.getPlayer();

                    if(plugin.getConfig().getBoolean("reviver.haveLimitedUses")) {
                        if(user.getUserFile().getInt("User.Config.Item.ReviverUses") >= plugin.getConfig().getInt("reviver.uses")){
                            user.getUserFile().set("User.Config.Item.ReviverUses", plugin.getConfig().getInt("reviver.maxUses"));
                            user.saveUserFile();
                            event.setCancelled(true);
                            return;
                        }
                        int uses = user.getUserFile().getInt("User.Config.Item.ReviverUses");
                        uses += 1;
                        user.getUserFile().set("User.Config.Item.ReviverUses",uses);
                        user.saveUserFile();
                    }
                    if(!players.contains(player)){
                        players.add(player);
                        player.sendMessage("§f[§c" + plugin.getConfig().getString("translation.serverName") + "§f] Put a username in chat");
                        event.getItem().setAmount(event.getItem().getAmount() - 1);

                    }

                }
            }
        }
    }
}
