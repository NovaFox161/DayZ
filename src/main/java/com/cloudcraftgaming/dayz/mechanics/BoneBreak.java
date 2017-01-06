package com.cloudcraftgaming.dayz.mechanics;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class BoneBreak {
    private static BoneBreak instance;

    private BoneBreak() {}

    public static BoneBreak getInstance() {
        if (instance == null) {
            instance = new BoneBreak();
        }
        return instance;
    }

    public void init() {
        startCheckers();
    }

    private void startCheckers() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                checkPlayers();
                startCheckers();
            }
        }, 20L * 5);
    }

    private void checkPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                if (PlayerDataManager.hasBrokenBone(p)) {
                    applyBreak(p);
                }
            }
        }
    }

    //Functionals
    public Boolean applyBreak(Player player) {
        Integer level = Main.plugin.getConfig().getInt("BoneBreak.Level");
        Boolean ambient = Main.plugin.getConfig().getString("BoneBreak.Ambient").equalsIgnoreCase("True");
        Boolean particles = Main.plugin.getConfig().getString("BoneBreak.Particles").equalsIgnoreCase("True");
        Boolean force = Main.plugin.getConfig().getString("BoneBreak.Force").equalsIgnoreCase("True");

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, 999999999, level, ambient, particles);

        return player.addPotionEffect(potionEffect, force);
    }

    public void mendBreak(Player player) {
        player.removePotionEffect(PotionEffectType.SLOW);
    }
}