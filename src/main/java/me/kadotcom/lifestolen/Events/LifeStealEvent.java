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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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


        if(HealthManager.getMaxHealth(p) > 2.0 && plugin.getConfig().getBoolean("death.anyDeathRemovesHearts")){
            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 2.0, p);
            if(plugin.getConfig().getBoolean("death.dropHeartOnDeath")){
                assert e != null;
                Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                dropitem.setVelocity(dropitem.getVelocity().zero());

            }
        }else if(HealthManager.getMaxHealth(p) <= 2.0 && plugin.getConfig().getBoolean("death.anyDeathRemovesHearts")){
            HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 1.0, p);
            if(plugin.getConfig().getBoolean("death.dropHeartOnDeath")){
                assert e != null;
                Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                dropitem.setVelocity(dropitem.getVelocity().zero());

            }
        }

        if (e instanceof Player) {
                if (HealthManager.getMaxHealth(p) > 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());

                    if(HealthManager.getMaxHealth(p) > 2.0 && !plugin.getConfig().getBoolean("death.anyDeathRemovesHearts")){
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 2.0, p);
                        if(plugin.getConfig().getBoolean("death.dropHeartOnDeath")){
                            Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                            dropitem.setVelocity(dropitem.getVelocity().zero());

                        }
                    }else if(HealthManager.getMaxHealth(p) <= 2.0 && !plugin.getConfig().getBoolean("death.anyDeathRemovesHearts")){
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth(p) - 1.0, p);
                        if(plugin.getConfig().getBoolean("death.dropHeartOnDeath")){
                            Item dropitem = p.getWorld().dropItem(p.getLocation(), ItemManager.heart);
                            dropitem.setVelocity(dropitem.getVelocity().zero());

                        }
                    }

                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("HP.maxHP")) {
                        if(!plugin.getConfig().getBoolean("death.dropHeartOnDeath")){
                            HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                        }

                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("HP.maxHP")) {
                        if(!plugin.getConfig().getBoolean("death.dropHeartOnDeath")){
                            HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.maxHP"), (Player) e);
                        }

                    }

                    //((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                    //p.setMaxHealth(p.getMaxHealth() - 2.0);
                } else if (HealthManager.getMaxHealth(p) <= 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());
                    //((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                    //p.setMaxHealth(p.getMaxHealth() - 1.0);

                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("HP.maxHP")) {
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("HP.maxHP")) {
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.maxHP"), (Player) e);
                    }
                }


            }

        if (HealthManager.getMaxHealth(p) == 1.0) {
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                target.sendMessage(plugin.getConfig().getString("death.publicDeathAnnouncement").replace("&", "§").replace("${player}",p.getName()));
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

        if(!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220").equalsIgnoreCase(plugin.getDescription().getVersion()) && !plugin.getDescription().getVersion().contains("Tested")){
            if(event.getPlayer().isOp() || event.getPlayer().hasPermission(plugin.getConfig().getString("permissions.messages.outdatedPermissionMessage"))){
                event.getPlayer().sendMessage("§f[§cLifeStolen§f] There is a new version of LifeStolen.\nYou are on "+ plugin.getDescription().getVersion() +" while the newest version is " + HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220") + ".\nYou can get the newest version here. https://www.spigotmc.org/resources/lifestolen.99220/");
            }
        }

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player p = event.getPlayer();

        if (HealthManager.getMaxHealth(p) == 1.0) {

            if(plugin.getConfig().getBoolean("death.clearItemsOnFullDeath")) {
                p.getInventory().clear();
            }

            if(plugin.getConfig().getBoolean("death.teleportOnDeath")){

                Location Location = new Location(p.getWorld(), (double) plugin.getConfig().getDouble("teleporting.xCoord"),(double) plugin.getConfig().getDouble("teleporting.yCoord"),(double) plugin.getConfig().getDouble("teleporting.zCoord"));
                event.setRespawnLocation(Location);
                if(!p.isDead()){
                    Location l = p.getLocation();
                    l.setX((double) plugin.getConfig().getDouble("teleporting.xCoord"));
                    l.setY((double) plugin.getConfig().getDouble("teleporting.yCoord"));
                    l.setZ((double) plugin.getConfig().getDouble("teleporting.zCoord"));
                }

                if(plugin.getConfig().getInt("teleporting.gamemode") == 0){

                    if(plugin.getConfig().getBoolean("teleporting.giveDefaultHP")){
                        GameModeManager.setGamemodeAndHealth(GameMode.SURVIVAL,plugin.getConfig().getInt("HP.returnHP"),p);

                    }else{
                        GameModeManager.setGamemode(GameMode.SURVIVAL,p);
                    }

                }else if(plugin.getConfig().getInt("teleporting.gamemode") == 1){

                    if(plugin.getConfig().getBoolean("teleporting.giveDefaultHP")){
                        GameModeManager.setGamemodeAndHealth(GameMode.CREATIVE,plugin.getConfig().getInt("HP.returnHP"),p);

                    }else{
                        GameModeManager.setGamemode(GameMode.CREATIVE,p);
                    }

                }else if(plugin.getConfig().getInt("teleporting.gamemode") == 2){

                    if(plugin.getConfig().getBoolean("teleporting.giveDefaultHP")){
                        GameModeManager.setGamemodeAndHealth(GameMode.ADVENTURE,plugin.getConfig().getInt("HP.returnHP"),p);

                    }else{
                        GameModeManager.setGamemode(GameMode.ADVENTURE,p);
                    }

                }else {

                    if(plugin.getConfig().getBoolean("teleporting.giveDefaultHP")){
                        GameModeManager.setGamemodeAndHealth(GameMode.SPECTATOR,plugin.getConfig().getInt("HP.returnHP"),p);

                    }else{
                        GameModeManager.setGamemode(GameMode.SPECTATOR,p);
                    }

                }


            }else if(plugin.getConfig().getBoolean("death.banOnDeath")) {
                if(!plugin.getConfig().getBoolean("banning.permBan")){
                    BanManager.ban(p, plugin.getConfig().getInt("banning.banTime"), plugin.getConfig().getInt("HP.returnHP"), plugin.getConfig().getString("banning.banReason").replace("&", "§"), plugin.getConfig().getString("banning.kickMessage").replace("&", "§"));
                }else{
                    BanManager.banPerm(p, plugin.getConfig().getInt("HP.returnHP"), plugin.getConfig().getString("banning.banReason").replace("&", "§"), plugin.getConfig().getString("banning.kickMessage").replace("&", "§"));
                }
            }else{
                GameModeManager.setGamemodeAndHealth(GameMode.SPECTATOR, plugin.getConfig().getInt("HP.returnHP"), p);
            }
        }

    }
}
