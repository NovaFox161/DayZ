package com.cloudcraftgaming.dayz.utils;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.zone.Zone;
import com.cloudcraftgaming.dayz.zone.ZoneDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Nova Fox on 12/3/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class TimeManager {
    private static TimeManager instance;

    //Instance handling.
    private TimeManager() {} //Prevent initialization.

    public static TimeManager getManager() {
        if (instance == null) {
            instance = new TimeManager();
        }
        return instance;
    }

    //Functionals
    public void startChestRefillTimer() {
        Integer time = Main.plugin.getConfig().getInt("Chest.Refill.Time");

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                //Loop zones
                for (int i = 1; i < 7; i++) {
                    Zone zone = Zone.fromValue(i);
                    Location loc1 = ZoneDataManager.getLocationOneForZone(zone);
                    Location loc2 = ZoneDataManager.getLocationTwoForZone(zone);

                    //Refill chests.
                }
                startChestRefillTimer();
            }
        }, 20L * time);
    }
}