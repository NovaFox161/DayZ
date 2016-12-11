package com.cloudcraftgaming.dayz.utils;

import com.cloudcraftgaming.dayz.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 12/11/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class MessageManager {

    private static File getMessageFile() {
        String fileName = Main.plugin.getConfig().getString("Lang");
        return new File(Main.plugin.getDataFolder() + "/Messages/" + fileName + ".yml");
    }

    public static FileConfiguration getMessageYml() {
        File messageFile = getMessageFile();
        return YamlConfiguration.loadConfiguration(messageFile);
    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', getMessageYml().getString(path));
    }

    public static void createEnglishMessagesFile() {
        File enFile = new File(Main.plugin.getDataFolder() + "/Messages/En.yml");
        if (!(enFile.exists())) {
            Main.plugin.getLogger().info("Generating En.yml messages file...");
            YamlConfiguration en = YamlConfiguration.loadConfiguration(enFile);
            en.addDefault("DO NOT DELETE.A", "DayZ is developed and managed by Shades161");
            en.addDefault("DO NOT DELETE.B", "DayZ is a paid product and may only be used by the original purchaser!");
            en.addDefault("Messages Version", FileManager.msgVersion);

            en.addDefault("Bone.Break", "&4Your bone is broken! Heal it by right clicking a bone!");

            en.addDefault("Bleed.Bleeding", "&4You're bleeding! Apply a bandage before you bleed out!");

            en.options().copyDefaults(true);
            saveLangFile(en);

            en.options().copyDefaults(true);
            saveLangFile(en);
        }
    }

    private static void saveLangFile(YamlConfiguration lang) {
        try {
            lang.save(getMessageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}