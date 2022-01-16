package me.kadotcom.lifestolen;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public final class LifeStolen extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        
        // Do not remove this line
        System.out.println("♡ LifeStolen \n Original Plugin by: KadotCom");


        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Entity e = event.getEntity().getKiller();
        if (e instanceof Player) {

            if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() > 2.0) {
                System.out.println("Player " + e.getName() + " Killed " + p.getName());
                ((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                p.setMaxHealth(p.getMaxHealth() - 2.0);
            } else if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() <= 2.0) {
                System.out.println("Player " + e.getName() + " Killed " + p.getName());
                ((Player) e).setMaxHealth(((Player) e).getMaxHealth() + 2.0);
                p.setMaxHealth(p.getMaxHealth() - 1.0);

            }

            if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == 1.0 && getConfig().getBoolean("kickWhenPlayerIsAtTheMinHP")) {
                for (Player target : getServer().getOnlinePlayers()) {
                    target.playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    target.sendMessage(ChatColor.RED + p.getName() + " has ran out of hearts...");
                    p.kickPlayer("You have ran out of hearts...");
                }
            } else if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == 1.0 && !getConfig().getBoolean("kickWhenPlayerIsAtTheMinHP")) {
                for (Player target : getServer().getOnlinePlayers()) {
                    target.playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    target.sendMessage(p.getName() + " has ran out of hearts...");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() == 1.0 && getConfig().getBoolean("kickWhenPlayerIsAtTheMinHP"))
            event.getPlayer().kickPlayer("You have ran out of hearts...");
        if (!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().setMaxHealth(getConfig().getDouble("maxHealth"));
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("resetHealth")) {
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                target.setMaxHealth(20.0);
                target.playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                target.sendMessage(ChatColor.RED + "Your hearts has been resetted.");
            }
        }

        if (command.getName().equalsIgnoreCase("health")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.AQUA + "Your exact health is " + p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + "");
                p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

            }
        }

        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

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

