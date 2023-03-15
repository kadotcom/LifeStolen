package me.kadotcom.lifestolen.Events;

import me.kadotcom.lifestolen.LifeStolen;
import me.kadotcom.lifestolen.Managers.BanManager;
import me.kadotcom.lifestolen.Managers.GameModeManager;
import me.kadotcom.lifestolen.Managers.HealthManager;
import me.kadotcom.lifestolen.Managers.ItemManager;
import me.kadotcom.lifestolen.Utils.HTTP;
import me.kadotcom.lifestolen.Utils.UserDataHandler;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public class LifeStealEvent implements Listener {

    LifeStolen plugin;
    public LifeStealEvent(LifeStolen ls){
        plugin = ls;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Entity e = event.getEntity().getKiller();


        if(HealthManager.getMaxHealth(p) > 2.0 && plugin.getConfig().getBoolean("anyDeathRemovesHearts")){
            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 2.0, p);
            if(plugin.getConfig().getBoolean("dropHeartOnDeath")){
                assert e != null;
                Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                dropitem.setVelocity(dropitem.getVelocity().zero());

            }
        }else if(HealthManager.getMaxHealth(p) <= 2.0 && plugin.getConfig().getBoolean("anyDeathRemovesHearts")){
            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 1.0, p);
            if(plugin.getConfig().getBoolean("dropHeartOnDeath")){
                assert e != null;
                Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                dropitem.setVelocity(dropitem.getVelocity().zero());

            }
        }

        if (e instanceof Player) {
                if (HealthManager.getMaxHealth(p) > 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());

                    if(HealthManager.getMaxHealth(p) > 2.0 && !plugin.getConfig().getBoolean("anyDeathRemovesHearts")){
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 2.0, p);
                        if(plugin.getConfig().getBoolean("dropHeartOnDeath")){
                            Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                            dropitem.setVelocity(dropitem.getVelocity().zero());

                        }
                    }else if(HealthManager.getMaxHealth(p) <= 2.0 && !plugin.getConfig().getBoolean("anyDeathRemovesHearts")){
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 1.0, p);
                        if(plugin.getConfig().getBoolean("dropHeartOnDeath")){
                            Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                            dropitem.setVelocity(dropitem.getVelocity().zero());

                        }
                    }

                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("MaxHP")) {
                        if(!plugin.getConfig().getBoolean("dropHeartOnDeath")){
                            HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                        }

                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("MaxHP")) {
                        if(!plugin.getConfig().getBoolean("dropHeartOnDeath")){
                            HealthManager.setMaxHealth(plugin.getConfig().getInt("MaxHP"), (Player) e);
                        }

                    }

                    //((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                    //p.setMaxHealth(p.getMaxHealth() - 2.0);
                } else if (HealthManager.getMaxHealth(p) <= 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());
                    //((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                    //p.setMaxHealth(p.getMaxHealth() - 1.0);

                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("MaxHP")) {
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("MaxHP")) {
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("MaxHP"), (Player) e);
                    }
                }


            }

                if (HealthManager.getMaxHealth(p) == 1.0) {

                     if(plugin.getConfig().getBoolean("clearItemsOnFullDeath")) {
                         p.getInventory().clear();
                     }
                         if(plugin.getConfig().getBoolean("banOnDeath")) {
                             if(!plugin.getConfig().getBoolean("permBan")){
                                 BanManager.ban(p, plugin.getConfig().getInt("banTime"), plugin.getConfig().getInt("HP.returnHP"), plugin.getConfig().getString("banReason").replace("&", "§"), plugin.getConfig().getString("kickMessage").replace("&", "§"));
                             }else{
                                 BanManager.banPerm(p, plugin.getConfig().getInt("HP.returnHP"), plugin.getConfig().getString("banReason").replace("&", "§"), plugin.getConfig().getString("kickMessage").replace("&", "§"));
                             }
                         }else{
                             GameModeManager.setGamemodeAndHealth(GameMode.SPECTATOR, plugin.getConfig().getInt("HP.returnHP"), p);
                         }

                         for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                             target.sendMessage(plugin.getConfig().getString("publicDeathAnnouncement").replace("&", "§").replace("${player}",p.getName()));
                }

            }


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UserDataHandler user = new UserDataHandler(plugin, event.getPlayer().getUniqueId());
        user.createUser(event.getPlayer());

        if (!event.getPlayer().hasPlayedBefore()) {
            HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.startHP"), event.getPlayer());
        }

        if(!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220").equalsIgnoreCase(plugin.getDescription().getVersion())){
            if(event.getPlayer().isOp() || event.getPlayer().hasPermission(plugin.getConfig().getString("permissions.messages.outdatedPermissionMessage"))){
                event.getPlayer().sendMessage("§f[§cLifeStolen§f] There is a new version of LifeStolen.\nYou are on "+ plugin.getDescription().getVersion() +" while the newest version is " + HTTP.get("https://pastebin.com/raw/s87JX0Xf") + ".\nYou can get the newest version here. https://www.spigotmc.org/resources/lifestolen.99220/");
            }
        }

    }
}
