package com.cloudcraftgaming.dayz.player;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.mechanics.EXPVisualizer;
import com.cloudcraftgaming.dayz.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

            data.addDefault("Zone.Tool.Enabled", false);

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
            data.set("Zone.Tool", false);

            savePlayerDataFile(player, data);
            deleteLocationOne(player);
            deleteLocationTwo(player);
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

    public static boolean hasLocationOne(Player player) {
        return getPlayerDataYml(player).contains("Locations.loc1");
    }

    public static boolean hasLocationTwo(Player player) {
        return getPlayerDataYml(player).contains("Locations.loc2");
    }

    public static boolean zoneToolEnabled(Player player) {
        return getPlayerDataYml(player).getString("Zone.Tool").equalsIgnoreCase("True");
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

    public static Location getLocationOne(Player player) {
        YamlConfiguration yml = getPlayerDataYml(player);
        World w = Bukkit.getWorld(yml.getString("Locations.loc1.world"));
        Double x = yml.getDouble("Locations.loc1.x");
        Double y = yml.getDouble("Locations.loc1.y");
        Double z = yml.getDouble("Locations.loc1.z");
        return new Location(w, x, y, z);
    }

    public static Location getLocationTwo(Player player) {
        YamlConfiguration yml = getPlayerDataYml(player);
        World w = Bukkit.getWorld(yml.getString("Locations.loc2.world"));
        Double x = yml.getDouble("Locations.loc2.x");
        Double y = yml.getDouble("Locations.loc2.y");
        Double z = yml.getDouble("Locations.loc2.z");
        return new Location(w, x, y, z);
    }

    //Setters
    public static boolean setThirst(Player player, Double percent) {
        if (hasDataFile(player)) {
            YamlConfiguration yml = getPlayerDataYml(player);
            yml.set("Thirst.Percent", percent);
            savePlayerDataFile(player, yml);
            EXPVisualizer.changeThirstIndicator(player);
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

    public static void setLocationOne(Player player, Location loc) {
        YamlConfiguration yml = getPlayerDataYml(player);
        yml.set("Locations.loc1.world", loc.getWorld().getName());
        yml.set("Locations.loc1.x", loc.getX());
        yml.set("Locations.loc1.y", loc.getY());
        yml.set("Locations.loc1.z", loc.getZ());
        savePlayerDataFile(player, yml);
    }

    public static void setLocationTwo(Player player, Location loc) {
        YamlConfiguration yml = getPlayerDataYml(player);
        yml.set("Locations.loc2.world", loc.getWorld().getName());
        yml.set("Locations.loc2.x", loc.getX());
        yml.set("Locations.loc2.y", loc.getY());
        yml.set("Locations.loc2.z", loc.getZ());
        savePlayerDataFile(player, yml);
    }

    public static void deleteLocationOne(Player player) {
        if (hasLocationOne(player)) {
            YamlConfiguration yml = getPlayerDataYml(player);
            yml.set("Locations.loc1", null);
            savePlayerDataFile(player, yml);
        }
    }

    public static void deleteLocationTwo(Player player) {
        if (hasLocationTwo(player)) {
            YamlConfiguration yml = getPlayerDataYml(player);
            yml.set("Locations.loc2", null);
            savePlayerDataFile(player, yml);
        }
    }

    public static void setZoneTool(Player player, Boolean _enabled) {
        YamlConfiguration yml = getPlayerDataYml(player);
        yml.set("Zone.Tool", _enabled);
        savePlayerDataFile(player, yml);
    }
}