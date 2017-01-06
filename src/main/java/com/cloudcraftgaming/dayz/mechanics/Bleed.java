package com.cloudcraftgaming.dayz.mechanics;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class Bleed {
    private static Bleed instance;

    private Bleed() {}

    public static Bleed getInstance() {
        if (instance == null) {
            instance = new Bleed();
        }
        return instance;
    }

    public void init() {
        if (Main.plugin.getConfig().getString("Bleed.Enabled").equalsIgnoreCase("True")) {
            startCheckers();
        }
    }

    private void startCheckers() {
        Integer seconds = Main.plugin.getConfig().getInt("Bleed.Seconds");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                checkPlayers();
                startCheckers();
            }
        }, 20L * seconds);
    }

    private void checkPlayers() {
        if (Main.plugin.getConfig().getString("Bleed.Enabled").equalsIgnoreCase("True")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                    if (PlayerDataManager.isBleeding(p)) {
                        applyDamage(p);
                    }
                }
            }
        }
    }

    private void applyDamage(Player player) {
        if (Main.plugin.getConfig().getString("Bleed.Enabled").equalsIgnoreCase("True")) {
            Double damage = Main.plugin.getConfig().getDouble("Bleed.Damage");
            player.damage(damage);
        }
    }
}