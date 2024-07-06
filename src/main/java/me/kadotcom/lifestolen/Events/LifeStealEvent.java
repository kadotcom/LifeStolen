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
import org.bukkit.entity.EntityType;
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

        String dm = "";

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

                } else if (HealthManager.getMaxHealth(p) <= 2.0) {
                    System.out.println("Player " + e.getName() + " Killed " + p.getName());
                    if(event.getEntityType() != EntityType.PLAYER && !event.getEntityType().equals(EntityType.TNT)  && !event.getEntityType().equals(EntityType.TNT_MINECART) && event.getEntityType() != EntityType.CREEPER  && event.getEntityType() != EntityType.SKELETON  && event.getEntityType() != EntityType.ARROW  && event.getEntityType() != EntityType.SPECTRAL_ARROW) {
                        dm = plugin.getConfig().getString("translation.deathMessages.generic").replace("&", "§").replace("${player}", p.getName()).replace("${attacker}", event.getEntityType().name());
                        event.setDeathMessage(dm);
                    }else if(event.getEntityType() == EntityType.PLAYER){
                        dm = plugin.getConfig().getString("translation.deathMessages.generic").replace("&", "§").replace("${player}", p.getName()).replace("${attacker}", e.getName());
                        event.setDeathMessage(dm);
                    }else if (event.getEntityType() == EntityType.CREEPER || event.getEntityType().equals(EntityType.TNT) || event.getEntityType().equals(EntityType.TNT_MINECART)) {
                        dm = plugin.getConfig().getString("translation.deathMessages.explosion").replace("&", "§").replace("${player}", p.getName()).replace("${attacker}", event.getEntityType().name());
                        event.setDeathMessage(dm);
                    }else if(event.getEntityType() == EntityType.SKELETON || event.getEntityType() == EntityType.ARROW || event.getEntityType() == EntityType.SPECTRAL_ARROW) {
                        dm = plugin.getConfig().getString("translation.deathMessages.shot").replace("&", "§").replace("${player}", p.getName()).replace("${attacker}", event.getEntityType().name());
                        event.setDeathMessage(dm);
                    }else{
                        event.setDeathMessage(event.getDeathMessage());
                    }
                    if (HealthManager.getMaxHealth((Player) e) != plugin.getConfig().getInt("HP.maxHP")) {
                        HealthManager.setMaxHealth(HealthManager.getMaxHealth((Player) e) + 2.0, (Player) e);
                    } else if (HealthManager.getMaxHealth((Player) e) >= plugin.getConfig().getInt("HP.maxHP")) {
                        HealthManager.setMaxHealth(plugin.getConfig().getInt("HP.maxHP"), (Player) e);
                    }
                }
            }
        if (HealthManager.getMaxHealth(p) == 1.0) {
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                target.sendMessage(plugin.getConfig().getString("death.fullDeathAnnouncement").replace("&", "§").replace("${player}",p.getName()));
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

        if(event.getPlayer().isOp() || event.getPlayer().hasPermission(plugin.getConfig().getString("permissions.messages.outdatedPermissionMessage"))){
            String version = HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=99220");
            if(!version.equalsIgnoreCase(plugin.getDescription().getVersion()) && !plugin.getDescription().getVersion().contains("Tested")){
                event.getPlayer().sendMessage("§f[§cLifeStolen§f] There is a new version of LifeStolen.\nYou are on "+ plugin.getDescription().getVersion() +" while the newest version is " + version + ".\nYou can get the newest version here. https://www.spigotmc.org/resources/lifestolen.99220/");
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
            if(plugin.getConfig().getBoolean("death.runCommandsOnDeath")){
                for(int i = 0; plugin.getConfig().getStringList("commands.commandsToRun").size() > i; i++){
                    String item = plugin.getConfig().getStringList("commands.commandsToRun").get(i).replace("&", "§").replace("${player}", event.getPlayer().getName());

                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), item);
                }
            }
            if(plugin.getConfig().getBoolean("death.teleportOnDeath")){
                Location Location = new Location(p.getWorld(), plugin.getConfig().getDouble("teleporting.xCord"),plugin.getConfig().getDouble("teleporting.yCord"),plugin.getConfig().getDouble("teleporting.zCord"));
                event.setRespawnLocation(Location);
                if(!p.isDead()){
                    Location l = p.getLocation();
                    l.setX(plugin.getConfig().getDouble("teleporting.xCord"));
                    l.setY(plugin.getConfig().getDouble("teleporting.yCord"));
                    l.setZ(plugin.getConfig().getDouble("teleporting.zCord"));
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
