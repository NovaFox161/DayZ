package com.cloudcraftgaming.dayz.mechanics;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class Thirst {
    private static Thirst instance;

    private Thirst() {}

    public static Thirst getInstance() {
        if (instance == null) {
            instance = new Thirst();
        }
        return instance;
    }

    public void init() {
        if (Main.plugin.getConfig().getString("Thirst.Enabled").equalsIgnoreCase("True")) {
            startThirstChecker();
            startThirstDamager();
        }
    }

    private void startThirstChecker() {
        Integer seconds = Main.plugin.getConfig().getInt("Thirst.AutoDrop.Seconds");

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                applyThirst();
                startThirstChecker();
            }
        }, 20L * seconds);
    }

    private void startThirstDamager() {
        Integer seconds = Main.plugin.getConfig().getInt("Thirst.Damage.Seconds");

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                applyDamage();
                startThirstDamager();
            }
        }, 20L * seconds);
    }

    private void applyThirst() {
        if (Main.plugin.getConfig().getString("Thirst.Enabled").equalsIgnoreCase("True")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                    Double thirst = PlayerDataManager.getThirst(p);
                    Double amount = Main.plugin.getConfig().getDouble("Thirst.AutoDrop.Amount");

                    if (thirst - amount <= 0) {
                        PlayerDataManager.setThirst(p, 0.0);
                        //Tell player!
                        p.sendMessage(MessageManager.getMessage("Thirst.None"));
                    } else {
                        PlayerDataManager.setThirst(p, thirst - amount);
                    }
                }
            }
        }
    }

    private void applyDamage() {
        if (Main.plugin.getConfig().getString("Thirst.Enabled").equalsIgnoreCase("True")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                    Double thirst = PlayerDataManager.getThirst(p);
                    if (thirst <= 0) {
                        Double damage = Main.plugin.getConfig().getDouble("Thirst.Damage.Amount");
                        p.damage(damage);
                    }
                }
            }
        }
    }
}