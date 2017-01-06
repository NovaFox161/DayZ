package com.cloudcraftgaming.dayz.utils;

import com.cloudcraftgaming.dayz.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
            Main.plugin.getConfig().addDefault("Config Version", conVersion);
            Main.plugin.getConfig().addDefault("Console.Verbose", true);

            List<String> worlds = Main.plugin.getConfig().getStringList("Worlds.Enabled");
            worlds.add("world");
            worlds.add("ExampleWorld");
            Main.plugin.getConfig().set("Worlds.Enabled", worlds);

            Main.plugin.getConfig().addDefault("Chest.Refill.Time", 300);

            Main.plugin.getConfig().addDefault("Thirst.AutoDrop.Enabled", true);
            Main.plugin.getConfig().addDefault("Thirst.AutoDrop.Time", 5);
            Main.plugin.getConfig().addDefault("Thirst.AutoDrop.Amount", 2);
            Main.plugin.getConfig().addDefault("Thirst.Damage.Enabled", true);
            Main.plugin.getConfig().addDefault("Thirst.Damage.Time", 1);
            Main.plugin.getConfig().addDefault("Thirst.Damage.Amount", 1);
            Main.plugin.getConfig().addDefault("Thirst.Drink.Enabled", true);
            Main.plugin.getConfig().addDefault("Thirst.Drink.Amount", 10);

            Main.plugin.getConfig().addDefault("BoneBreak.Enabled", true);
            Main.plugin.getConfig().addDefault("BoneBreak.Level", 2);
            Main.plugin.getConfig().addDefault("BoneBreak.Ambient", false);
            Main.plugin.getConfig().addDefault("BoneBreak.Particles", true);
            Main.plugin.getConfig().addDefault("BoneBreak.Force", true);

            Main.plugin.getConfig().addDefault("Bleed.Enabled", true);
            Main.plugin.getConfig().addDefault("Bleed.Seconds", 4);
            Main.plugin.getConfig().addDefault("Bleed.Damage", 1.0);

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
        if (MessageManager.getMessageYml().getDouble("Config Version") != msgVersion) {
            Main.plugin.getLogger().severe("Messages file outdated!!! Plugin will not work properly!");
            Main.plugin.getLogger().severe("Stop the server, copy your messages, and delete the messages folder then restart the server!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further errors...");
            Main.plugin.getPluginLoader().disablePlugin(Main.plugin);
        }
    }

    //Booleans/Checkers
    public static boolean verbose() {
        return Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True");
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