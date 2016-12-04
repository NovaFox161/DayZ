package com.cloudcraftgaming.dayz.utils;

import com.cloudcraftgaming.dayz.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 12/1/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("WeakerAccess")
public class FileManager {
    private static Double conVersion = 1.0;
    static Double msgVersion = 1.0;

    //Functionals
    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");
        if (!file.exists()) {
            Main.plugin.getLogger().info("Generating config.yml...");

            Main.plugin.getConfig().addDefault("DO NOT DELETE.A", "DayZ is developed and managed by Shades161.");
            Main.plugin.getConfig().addDefault("DO NOT DELETE.B", "DayZ is a paid for plugin and may only be used by the original purchaser");

            Main.plugin.getConfig().addDefault("Chest.Refill.Time", 300);

            Main.plugin.getConfig().addDefault("Config Version", conVersion);
            Main.plugin.getConfig().addDefault("Lang", "En");

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }

    public static void createZoneLocationFile() {
        if (!getZoneLocationFile().exists()) {
            YamlConfiguration yml = getZoneLocationYml();
            Main.plugin.getLogger().info("Generating zone location file...");

            yml.addDefault("DO NOT DELETE.A", "DayZ is developed and managed by Shades161.");
            yml.addDefault("DO NOT DELETE.B", "DayZ is a paid for plugin and may only be used by the original purchaser");

            yml.options().copyDefaults(true);
            saveZoneLocationFile(yml);

            yml.options().copyDefaults(true);
            saveZoneLocationFile(yml);
        }
    }

    public static void checkFileVersions() {
        if (Main.plugin.getConfig().getDouble("Config Version") != conVersion) {
            Main.plugin.getLogger().severe("Config.yml outdated!!! Plugin will not work properly!");
            Main.plugin.getLogger().severe("Stop the server, copy your settings, and delete the config then restart the server!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further errors...");
            Main.plugin.getPluginLoader().disablePlugin(Main.plugin);
        }
        /*
        if (MessageManager.getMessageYml().getDouble("Config Version") != msgVersion) {
            Main.plugin.getLogger().severe("Messages file outdated!!! Plugin will not work properly!");
            Main.plugin.getLogger().severe("Stop the server, copy your messages, and delete the messages folder then restart the server!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further errors...");
            Main.plugin.getPluginLoader().disablePlugin(Main.plugin);
        }
        */
    }

    //Getters
    public static File getZoneLocationFile() {
        return new File(Main.plugin.getDataFolder() + "/Locations/Zones.yml");
    }

    public static YamlConfiguration getZoneLocationYml() {
        return YamlConfiguration.loadConfiguration(getZoneLocationFile());
    }

    //Setters

    //Savers
    public static void saveZoneLocationFile(YamlConfiguration yml) {
        try {
            yml.save(getZoneLocationFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}