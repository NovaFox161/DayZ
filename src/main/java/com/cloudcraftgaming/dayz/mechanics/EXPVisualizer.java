package com.cloudcraftgaming.dayz.mechanics;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class EXPVisualizer {

    public static Boolean changeThirstIndicator(Player player) {
        if (Main.plugin.getConfig().getString("Thirst.Enabled").equalsIgnoreCase("True")) {
            if (Main.plugin.getConfig().getString("Thirst.Display.Enabled").equalsIgnoreCase("True")) {
                if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(player.getWorld().getName())) {
                    if (player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.SURVIVAL) {
                        Double thirstPercent = PlayerDataManager.getThirst(player);
                        Double thirst = thirstPercent / 100;
                        player.setExp(thirst.floatValue());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void changeChestRefillInticator() {
        if (Main.plugin.getConfig().getString("Chest.Refill.Enabled").equalsIgnoreCase("True")) {
            if (Main.plugin.getConfig().getString("Chest.Display.Enabled").equalsIgnoreCase("True")) {
                Integer lvl;
                if (Main.plugin.getConfig().getString("Chest.Display.Unit").equalsIgnoreCase("MINUTE")) {
                    lvl = ChestRefill.getInstance().getMinutesRemaining();
                } else {
                    lvl = ChestRefill.getInstance().getSecondsRemaining();
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                        if (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
                            p.setLevel(lvl);
                        }
                    }
                }
            }
        }
    }
}