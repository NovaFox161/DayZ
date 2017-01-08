package com.cloudcraftgaming.dayz.utils;

import com.cloudcraftgaming.dayz.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class ItemManager {
    public static ArrayList<ItemStack> getItemsChest(Integer zone) {
        ArrayList<ItemStack> itemsForChest = new ArrayList<>();
        Integer itemsInChest = itemsInChest(zone);
        if (itemsInChest > 0) {
            for (int i = 0; i < itemsInChest + 1; i++) {
                Material itemMat = getNextItemForChest(zone);
                ItemStack item = getItem(itemMat);
                if (!(itemsForChest.contains(item))) {
                    itemsForChest.add(item);
                }
            }
        }
        return itemsForChest;
    }

    private static Material getNextItemForChest(Integer zone) {
        List<String> itemNames = FileManager.getItemYml().getStringList("Chest.Zone." + zone + ".Items.Possible");
        Collections.shuffle(itemNames);
        String itemName = itemNames.get(0);
        return Material.getMaterial(itemName);
    }

    private static Integer itemsInChest(Integer zone) {
        Integer min = FileManager.getItemYml().getInt("Chest.Zone." + zone + ".Items.Min");
        Integer max = FileManager.getItemYml().getInt("Chest.Zone." + zone + ".Items.Max");
        Random rn = new Random();
        return rn.nextInt((max - min) + 1) + min;
    }

    /**
     * Gets the ItemStack to be added to a chest. This has all data of what item and how much (FOR ALL CHESTS).
     * @param itemMat The Material the item should be.
     * @return An ItemStack of the item to be added, with the correct amount.
     */
    private static ItemStack getItem(Material itemMat) {
        YamlConfiguration settings = FileManager.getItemYml();
        if (settings.contains("Items." + itemMat.name())) {
            Integer chance = settings.getInt("Items." + itemMat.name() + ".Chance");
            Random rn = new Random();
            Integer ran = rn.nextInt(100 + 1);
            if (chance <= ran) {
                Integer amount = getItemAmount(itemMat);
                if (amount > 0) {
                    return new ItemStack(itemMat, amount);
                } else {
                    return  new ItemStack(Material.AIR, 1);
                }
            } else {
                return new ItemStack(Material.AIR, 1);
            }
        } else {
            return new ItemStack(Material.AIR, 1);
        }
    }

    /**
     * Gets the amount of the specific item that should be in the chest (FOR ALL CHESTS).
     * @param itemMat The material of the item.
     * @return The number of items of this material that should be in the chest.
     */
    private static Integer getItemAmount(Material itemMat) {
        YamlConfiguration settings = FileManager.getItemYml();
        if (settings.contains("Items." + itemMat.name())) {
            Integer max = settings.getInt("Items." + itemMat.name() + ".Amount.Max");
            Random rn = new Random();
            return rn.nextInt(max + 1);
        } else {
            return 0;
        }
    }

    public static Boolean chestEmpty(Inventory inv) {
        if (Main.plugin.getConfig().getString("Chest.Refill.RequireEmpty").equalsIgnoreCase("True")) {
            for (ItemStack is : inv.getContents()) {
                if (is != null) {
                    if (!is.getType().equals(Material.AIR)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}