package com.cloudcraftgaming.dayz.utils;

import com.cloudcraftgaming.dayz.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nova Fox on 12/1/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class FileManager {
    private static Double conVersion = 1.1;
    static Double msgVersion = 1.1;

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
            worlds.add("warzmap");
            worlds.add("weedkits");
            worlds.add("Test");
            worlds.add("world");
            Main.plugin.getConfig().set("Worlds.Enabled", worlds);

            Main.plugin.getConfig().addDefault("Chest.Refill.Enabled", true);
            Main.plugin.getConfig().addDefault("Chest.Refill.RequireEmpty", true);
            Main.plugin.getConfig().addDefault("Chest.Refill.Time", 300);
            Main.plugin.getConfig().addDefault("Chest.Refill.RequireEmpty", false);
            Main.plugin.getConfig().addDefault("Chest.Display.Enabled", true);
            Main.plugin.getConfig().addDefault("Chest.Display.Unit", "SECOND");

            Main.plugin.getConfig().addDefault("Thirst.Enabled", true);
            Main.plugin.getConfig().addDefault("Thirst.AutoDrop.Seconds", 5);
            Main.plugin.getConfig().addDefault("Thirst.AutoDrop.Amount", 2.0);
            Main.plugin.getConfig().addDefault("Thirst.Damage.Enabled", true);
            Main.plugin.getConfig().addDefault("Thirst.Damage.Seconds", 1);
            Main.plugin.getConfig().addDefault("Thirst.Damage.Amount", 1.0);
            Main.plugin.getConfig().addDefault("Thirst.Drink.Amount", 10.0);
            Main.plugin.getConfig().addDefault("Thirst.Pepsi.Amount", 8.0);
            Main.plugin.getConfig().addDefault("Thirst.Display.Enabled", true);

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

    public static void createItemFile() {
        if (!(getItemFile().exists())) {
            Main.plugin.getLogger().info("Generating items.yml...");
            YamlConfiguration items = getItemYml();
            items.addDefault("DO NOT DELETE.A", "DayZ is developed and managed by Shades161.");
            items.addDefault("DO NOT DELETE.B", "DayZ is a paid for plugin and may only be used by the original purchaser");

            items.addDefault("Chest.Zone.1.Items.Min", 0);
            items.addDefault("Chest.Zone.1.Items.Max", 3);
            items.addDefault("Chest.Zone.2.Items.Min", 1);
            items.addDefault("Chest.Zone.2.Items.Max", 5);
            items.addDefault("Chest.Zone.3.Items.Min", 2);
            items.addDefault("Chest.Zone.3.Items.Max", 7);
            items.addDefault("Chest.Zone.4.Items.Min", 3);
            items.addDefault("Chest.Zone.4.Items.Max", 5);
            items.addDefault("Chest.Zone.5.Items.Min", 1);
            items.addDefault("Chest.Zone.5.Items.Max", 6);
            items.addDefault("Chest.Zone.6.Items.Min", 2);
            items.addDefault("Chest.Zone.6.Items.Max", 5);

            //Create lists of possible items for each zone. Yay.... too many lines of code... sorry whoever decides to read this.
            List<String> zone1 = items.getStringList("Zone.1.Possible");
            //Weapons & Tools
            zone1.add(Material.WOOD_SPADE.name());
            zone1.add(Material.WOOD_AXE.name());
            zone1.add(Material.WOOD_PICKAXE.name());
            zone1.add(Material.WOOD_SWORD.name());
            //No armor, skip, on to food.
            zone1.add(Material.MUSHROOM_SOUP.name());
            zone1.add(Material.WHEAT.name());
            //Random
            zone1.add(Material.BOAT.name());
            zone1.add(Material.BONE.name());
            items.set("Zone.1.Possible", zone1);

            List<String> zone2 = items.getStringList("Zone.2.Possible");
            //Weapons & Tools
            zone2.add(Material.STONE_SPADE.name());
            zone2.add(Material.STONE_AXE.name());
            zone2.add(Material.STONE_PICKAXE.name());
            zone2.add(Material.STONE_SWORD.name());
            //No armor, skip, on to food.
            zone2.add(Material.APPLE.name());
            zone2.add(Material.BREAD.name());
            zone2.add(Material.CARROT.name());
            //Random
            zone2.add(Material.FURNACE.name());
            zone2.add(Material.PAPER.name());
            items.set("Zone.2.Possible", zone2);

            List<String> zone3 = items.getStringList("Zone.3.Possible");
            //Weapons & tools
            zone3.add(Material.BOW.name());
            zone3.add(Material.ARROW.name());
            //Armor
            zone3.add(Material.LEATHER_HELMET.name());
            zone3.add(Material.LEATHER_CHESTPLATE.name());
            zone3.add(Material.LEATHER_LEGGINGS.name());
            zone3.add(Material.LEATHER_BOOTS.name());
            //Food
            zone3.add(Material.BAKED_POTATO.name());
            zone3.add(Material.COOKED_BEEF.name());
            zone3.add(Material.RAW_FISH.name());
            //Random
            zone3.add(Material.WORKBENCH.name());
            items.set("Zone.3.Possible", zone3);

            List<String> zone4 = items.getStringList("Zone.4.Possible");
            //Weapons & Tools
            zone4.add(Material.GOLD_SPADE.name());
            zone4.add(Material.GOLD_AXE.name());
            zone4.add(Material.GOLD_PICKAXE.name());
            zone4.add(Material.GOLD_SWORD.name());
            //Armor
            zone4.add(Material.CHAINMAIL_HELMET.name());
            zone4.add(Material.CHAINMAIL_CHESTPLATE.name());
            zone4.add(Material.CHAINMAIL_LEGGINGS.name());
            zone4.add(Material.CHAINMAIL_BOOTS.name());
            //Food
            zone4.add(Material.COOKED_CHICKEN.name());
            zone4.add(Material.COOKED_RABBIT.name());
            zone4.add(Material.CAKE.name());
            //Random
            zone4.add(Material.FLINT_AND_STEEL.name());
            items.set("Zone.4.Possible", zone4);

            List<String> zone5 = items.getStringList("Zone.5.Possible");
            //Weapons & Tools
            zone5.add(Material.IRON_SPADE.name());
            zone5.add(Material.IRON_AXE.name());
            zone5.add(Material.IRON_PICKAXE.name());
            zone5.add(Material.IRON_SWORD.name());
            //Armor
            zone5.add(Material.IRON_HELMET.name());
            zone5.add(Material.IRON_CHESTPLATE.name());
            zone5.add(Material.IRON_LEGGINGS.name());
            zone5.add(Material.IRON_BOOTS.name());
            //Food
            zone5.add(Material.APPLE.name());
            zone5.add(Material.GRILLED_PORK.name());
            zone5.add(Material.COOKED_MUTTON.name());
            //Random
            zone5.add(Material.ANVIL.name());
            items.set("Zone.5.Possible", zone5);

            List<String> zone6 = items.getStringList("Zone.6.Possible");
            //Weapons & Tools
            zone6.add(Material.DIAMOND_SPADE.name());
            zone6.add(Material.DIAMOND_AXE.name());
            zone6.add(Material.DIAMOND_PICKAXE.name());
            zone6.add(Material.DIAMOND_SWORD.name());
            //Armor
            zone6.add(Material.DIAMOND_HELMET.name());
            zone6.add(Material.DIAMOND_CHESTPLATE.name());
            zone6.add(Material.DIAMOND_LEGGINGS.name());
            zone6.add(Material.DIAMOND_BOOTS.name());
            //Food
            zone6.add(Material.GOLDEN_APPLE.name());
            zone6.add(Material.GOLDEN_CARROT.name());
            zone6.add(Material.COOKED_BEEF.name());
            items.set("Zone.6.Possible", zone6);

            //Add item meta - Weapons & Tools First
            items.addDefault("Items." + Material.ARROW.name() + ".Chance", 35);
            items.addDefault("Items." + Material.ARROW.name() + ".Amount.Max", 10);
            items.addDefault("Items." + Material.BOW.name() + ".Chance", 15);
            items.addDefault("Items." + Material.BOW.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.WOOD_SPADE.name() + ".Chance", 45);
            items.addDefault("Items." + Material.WOOD_SPADE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.WOOD_AXE.name() + ".Chance", 45);
            items.addDefault("Items." + Material.WOOD_AXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.WOOD_PICKAXE.name() + ".Chance", 45);
            items.addDefault("Items." + Material.WOOD_PICKAXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.WOOD_SWORD.name() + ".Chance", 45);
            items.addDefault("Items." + Material.WOOD_SWORD.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.STONE_SPADE.name() + ".Chance", 35);
            items.addDefault("Items." + Material.STONE_SPADE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.STONE_AXE.name() + ".Chance", 35);
            items.addDefault("Items." + Material.STONE_AXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.STONE_PICKAXE.name() + ".Chance", 35);
            items.addDefault("Items." + Material.STONE_PICKAXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.STONE_SWORD.name() + ".Chance", 35);
            items.addDefault("Items." + Material.STONE_SWORD.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_SPADE.name() + ".Chance", 40);
            items.addDefault("Items." + Material.GOLD_SPADE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_AXE.name() + ".Chance", 40);
            items.addDefault("Items." + Material.GOLD_AXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_PICKAXE.name() + ".Chance", 40);
            items.addDefault("Items." + Material.GOLD_PICKAXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_SWORD.name() + ".Chance", 40);
            items.addDefault("Items." + Material.GOLD_SWORD.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_SPADE.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_SPADE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_AXE.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_AXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_PICKAXE.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_PICKAXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_SWORD.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_SWORD.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_SPADE.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_SPADE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_AXE.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_AXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_PICKAXE.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_PICKAXE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_SWORD.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_SWORD.name() + ".Amount.Max", 1);
            //Armor next
            items.addDefault("Items." + Material.CHAINMAIL_HELMET.name() + ".Chance", 30);
            items.addDefault("Items." + Material.CHAINMAIL_HELMET.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.CHAINMAIL_CHESTPLATE.name() + ".Chance", 30);
            items.addDefault("Items." + Material.CHAINMAIL_CHESTPLATE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.CHAINMAIL_LEGGINGS.name() + ".Chance", 30);
            items.addDefault("Items." + Material.CHAINMAIL_LEGGINGS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.CHAINMAIL_BOOTS.name() + ".Chance", 30);
            items.addDefault("Items." + Material.CHAINMAIL_BOOTS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.LEATHER_HELMET.name() + ".Chance", 40);
            items.addDefault("Items." + Material.LEATHER_HELMET.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.LEATHER_CHESTPLATE.name() + ".Chance", 40);
            items.addDefault("Items." + Material.LEATHER_CHESTPLATE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.LEATHER_LEGGINGS.name() + ".Chance", 40);
            items.addDefault("Items." + Material.LEATHER_LEGGINGS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.LEATHER_BOOTS.name() + ".Chance", 40);
            items.addDefault("Items." + Material.LEATHER_BOOTS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_HELMET.name() + ".Chance", 20);
            items.addDefault("Items." + Material.GOLD_HELMET.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_CHESTPLATE.name() + ".Chance", 20);
            items.addDefault("Items." + Material.GOLD_CHESTPLATE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_LEGGINGS.name() + ".Chance", 20);
            items.addDefault("Items." + Material.GOLD_LEGGINGS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLD_BOOTS.name() + ".Chance", 20);
            items.addDefault("Items." + Material.GOLD_BOOTS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_HELMET.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_HELMET.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_CHESTPLATE.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_CHESTPLATE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_LEGGINGS.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_LEGGINGS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.IRON_BOOTS.name() + ".Chance", 10);
            items.addDefault("Items." + Material.IRON_BOOTS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_HELMET.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_HELMET.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_CHESTPLATE.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_CHESTPLATE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_LEGGINGS.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_LEGGINGS.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.DIAMOND_BOOTS.name() + ".Chance", 5);
            items.addDefault("Items." + Material.DIAMOND_BOOTS.name() + ".Amount.Max", 5);
            //Food
            items.addDefault("Items." + Material.APPLE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.APPLE.name() + ".Chance", 30);
            items.addDefault("Items." + Material.WHEAT.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.WHEAT.name() + ".Chance", 30);
            items.addDefault("Items." + Material.BREAD.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.BREAD.name() + ".Chance", 30);
            items.addDefault("Items." + Material.COOKED_BEEF.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.COOKED_BEEF.name() + ".Chance", 30);
            items.addDefault("Items." + Material.COOKED_CHICKEN.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.COOKED_CHICKEN.name() + ".Chance", 30);
            items.addDefault("Items." + Material.COOKED_FISH.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.COOKED_FISH.name() + ".Chance", 30);
            items.addDefault("Items." + Material.COOKED_MUTTON.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.COOKED_MUTTON.name() + ".Chance", 30);
            items.addDefault("Items." + Material.COOKED_RABBIT.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.COOKED_RABBIT.name() + ".Chance", 30);
            items.addDefault("Items." + Material.GRILLED_PORK.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GRILLED_PORK.name() + ".Chance", 30);
            items.addDefault("Items." + Material.CAKE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.CAKE.name() + ".Chance", 30);
            items.addDefault("Items." + Material.GOLDEN_APPLE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLDEN_APPLE.name() + ".Chance", 10);
            items.addDefault("Items." + Material.GOLDEN_CARROT.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.GOLDEN_CARROT.name() + ".Chance", 10);
            items.addDefault("Items." + Material.MUSHROOM_SOUP.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.MUSHROOM_SOUP.name() + ".Chance", 30);
            items.addDefault("Items." + Material.POTATO.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.POTATO.name() + ".Chance", 30);
            items.addDefault("Items." + Material.BAKED_POTATO.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.BAKED_POTATO.name() + ".Chance", 30);
            items.addDefault("Items." + Material.CARROT.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.CARROT.name() + ".Chance", 30);
            items.addDefault("Items." + Material.RAW_FISH.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.RAW_FISH.name() + ".Chance", 30);
            //Random
            items.addDefault("Items." + Material.BOAT.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.BOAT.name() + ".Chance", 15);
            items.addDefault("Items." + Material.BONE.name() + ".Amount.Max", 4);
            items.addDefault("Items." + Material.BONE.name() + ".Chance", 20);
            items.addDefault("Items." + Material.FURNACE.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.FURNACE.name() + ".Chance", 15);
            items.addDefault("Items." + Material.PAPER.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.PAPER.name() + ".Chance", 15);
            items.addDefault("Items." + Material.WORKBENCH.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.WORKBENCH.name() + ".Chance", 15);
            items.addDefault("Items." + Material.FLINT_AND_STEEL.name() + ".Chance", 15);
            items.addDefault("Items." + Material.ANVIL.name() + ".Amount.Max", 1);
            items.addDefault("Items." + Material.ANVIL.name() + ".Chance", 15);

            items.options().copyDefaults(true);
            saveItemFile(items);

            items.options().copyDefaults(true);
            saveItemFile(items);
        }
    }

    public static void checkFileVersions() {
        if (Main.plugin.getConfig().getDouble("Config Version") != conVersion) {
            Main.plugin.getLogger().severe("Config.yml outdated!!! Plugin will not work properly!");
            Main.plugin.getLogger().severe("Stop the server, copy your settings, and delete the config then restart the server!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further errors...");
            Main.plugin.getPluginLoader().disablePlugin(Main.plugin);
        }
        if (MessageManager.getMessageYml().getDouble("Messages Version") != msgVersion) {
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
    private static File getZoneLocationFile() {
        return new File(Main.plugin.getDataFolder() + "/Locations/Zones.yml");
    }

    public static YamlConfiguration getZoneLocationYml() {
        return YamlConfiguration.loadConfiguration(getZoneLocationFile());
    }

    private static File getItemFile() {
        return new File(Main.plugin.getDataFolder() + "/items.yml");
    }

    static YamlConfiguration getItemYml() {
        File file = getItemFile();
        return YamlConfiguration.loadConfiguration(file);
    }

    //Savers
    public static void saveZoneLocationFile(YamlConfiguration yml) {
        try {
            yml.save(getZoneLocationFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveItemFile(YamlConfiguration yml) {
        try {
            yml.save(getItemFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}