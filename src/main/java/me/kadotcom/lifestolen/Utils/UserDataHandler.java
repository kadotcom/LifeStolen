package me.kadotcom.lifestolen.Utils;

import me.kadotcom.lifestolen.LifeStolen;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class UserDataHandler {
    LifeStolen p;
    UUID u;
    File userFile;
    File location;
    FileConfiguration userConfig;

    public UserDataHandler(LifeStolen pl, UUID u){
        this.p = pl;

        this.u = u;

        location = new File(p.getDataFolder(), "users");

        if(!location.exists()) {
            location.mkdirs();
        }

        userFile = new File(location, u + ".yml");



        userConfig = YamlConfiguration.loadConfiguration(userFile);

    }
    public void createUser(final Player player){

        if ( !(userFile.exists()) ) {
            try {


                YamlConfiguration UserConfig = YamlConfiguration.loadConfiguration(userFile);

                UserConfig.set("User.Info.PreviousName", player.getName());
                UserConfig.set("User.Info.UniqueID", player.getUniqueId().toString());
                UserConfig.set("User.Config.Item.HeartUses",0);
                UserConfig.set("User.Config.Item.ReviverUses",0);
                UserConfig.save(userFile);



            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }

    public FileConfiguration getUserFile(){

        return userConfig;

    }


    public void saveUserFile() {

        try {

            getUserFile().save(userFile);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    }
