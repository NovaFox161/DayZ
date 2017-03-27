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
            en.addDefault("Bone.Mend", "&6Your bone is no longer broken!");

            en.addDefault("Bleed.Bleeding", "&4You're bleeding! Apply a bandage before you bleed out!");
            en.addDefault("Bleed.Patch", "&6You have patched your wound and are no longer bleeding!");

            en.addDefault("Thirst.None", "&4You need to drink water or you will die!");
            en.addDefault("Thirst.Full", "&6Thirst full!");

            en.addDefault("Chest.Refill", "&6Chests have been refilled!");

            en.addDefault("Zone.Change", "&7You have entered zone %zone%!");

            en.addDefault("Set.Location.One", "&6Location one set!");
            en.addDefault("Set.Location.Two", "&6Location two set!");
            en.addDefault("Set.Location.Check.OneOnly", "&6Set location two by right clicking a block!");
            en.addDefault("Set.Location.Check.TwoOnly", "&6Set location one by left clicking a block!");
            en.addDefault("Set.Location.Check.Both", "&6Both locations set! Use the appropriate command!");
            en.addDefault("Set.Location.Check.None", "&6Set locations via the zone tool (stick)!");
            en.addDefault("Delete.Success", "&4Successfully deleted the specified sub-Zone!");
            en.addDefault("Delete.Failure", "&4Failed to delete sub zone! Are you sure it exists?");

            en.addDefault("Command.Tool.Enabled", "&6Zone tool (stick) now enabled!");
            en.addDefault("Command.Tool.Disabled", "&6Zone tool (stick) now disabled!");
            en.addDefault("Command.Set.Zone", "&6Zone %number% locations set!");

            en.addDefault("Command.Zone.Info", "&6You are in zone %zone%!");
            en.addDefault("Command.Zone.None", "&6You are not in a zone!");

            en.addDefault("Notification.PlayerOnly", "&4That command can only be used by a player!");
            en.addDefault("Notification.NoPerm", "&4You do not have permission to do that!");
            en.addDefault("Notification.Args.TooFew", "&4Too few arguments! Use /dayz help!");
            en.addDefault("Notification.Args.TooMany", "&4Too many arguments! Use /dayz help!");
            en.addDefault("Notification.Args.Invalid", "&4Invalid arguments! Use /dayz help!");
            en.addDefault("Notification.ZoneNum.Integer", "&4Zone number invalid! It must be between 1 and 6!");

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