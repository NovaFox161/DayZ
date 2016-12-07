package com.cloudcraftgaming.dayz.player;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.utils.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 12/7/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("WeakerAccess")
public class PlayerDataManager {
    //Functionals
    public static void createPlayerDataFile(Player player) {
        if (!hasDataFile(player)) {
            if (FileManager.verbose()) {
                Main.plugin.getLogger().info("Generating player data file for player: " + player.getName());
            }

            YamlConfiguration data = getPlayerDataYml(player);

            data.addDefault("Player.Name", player.getName());
            data.addDefault("Thirst.Percent", 100.0);
            data.addDefault("Bleed.Bleeding", false);
            data.addDefault("Bone.Broken", false);

            data.options().copyDefaults(true);
            savePlayerDataFile(player, data);

            data.options().copyDefaults(true);
            savePlayerDataFile(player, data);
        } else {
            updatePlayerDataFile(player);
        }
    }

    public static void updatePlayerDataFile(Player player) {
        if (hasDataFile(player)) {
            if (FileManager.verbose()) {
                Main.plugin.getLogger().info("Updating player data file for player: " + player.getName());
            }

            YamlConfiguration data = getPlayerDataYml(player);

            data.set("Player.Name", player.getName());

            savePlayerDataFile(player, data);
        }
    }

    //Savers
    public static void savePlayerDataFile(Player player, YamlConfiguration yml) {
        try {
            yml.save(getPlayerDataFile(player));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Booleans/Checkers
    public static boolean hasDataFile(Player player) {
        return getPlayerDataFile(player).exists();
    }

    public static boolean isBleeding(Player player) {
        return getPlayerDataYml(player).getString("Bleed.Bleeding").equalsIgnoreCase("True");
    }

    public static boolean hasBrokenBone(Player player) {
        return getPlayerDataYml(player).getString("Bone.Broken").equalsIgnoreCase("True");
    }

    //Getters
    public static File getPlayerDataFile(Player player) {
        return new File(Main.plugin.getDataFolder() + "/Players/" + player.getUniqueId() + ".yml");
    }

    public static YamlConfiguration getPlayerDataYml(Player player) {
        return YamlConfiguration.loadConfiguration(getPlayerDataFile(player));
    }

    public static Double getThirst(Player player) {
        return getPlayerDataYml(player).getDouble("Thirst.Percent");
    }

    //Setters
    public static boolean setThirst(Player player, Double percent) {
        if (hasDataFile(player)) {
            YamlConfiguration yml = getPlayerDataYml(player);
            yml.set("Thirst.Percent", percent);
            savePlayerDataFile(player, yml);
            return true;
        }
        return false;
    }

    public static boolean setBleeding(Player player, Boolean _bleeding) {
        if (hasDataFile(player)) {
            YamlConfiguration yml = getPlayerDataYml(player);
            yml.set("Bleed.Bleeding", _bleeding);
            savePlayerDataFile(player, yml);
            return true;
        }
        return false;
    }

    public static boolean setBoneBroken(Player player, Boolean _boneBroken) {
        if (hasDataFile(player)) {
            YamlConfiguration yml = getPlayerDataYml(player);
            yml.set("Bone.Broken", _boneBroken);
            savePlayerDataFile(player, yml);
            return true;
        }
        return false;
    }
}