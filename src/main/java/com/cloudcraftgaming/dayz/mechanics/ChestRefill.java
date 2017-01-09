package com.cloudcraftgaming.dayz.mechanics;

import com.cloudcraftgaming.dayz.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class ChestRefill {
    private static ChestRefill instance;

    private final ArrayList<Location> usedLocations = new ArrayList<>();

    private Integer secondsRemaining;

    private ChestRefill() {}

    public static ChestRefill getInstance() {
        if (instance == null) {
            instance = new ChestRefill();
        }
        return instance;
    }

    public void init() {
        secondsRemaining = Main.plugin.getConfig().getInt("Chest.Refill.Time");
        startChestRefillTimer();
    }

    private void startChestRefillTimer() {
        if (Main.plugin.getConfig().getString("Chest.Refill.Enabled").equalsIgnoreCase("True")) {
            EXPVisualizer.changeChestRefillIndicator();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (secondsRemaining - 1 <= 0) {
                        refillChests();
                    } else {
                        secondsRemaining--;
                        startChestRefillTimer();
                    }
                }
            }, 20L);
        }
    }

    private void refillChests() {
        usedLocations.clear();

        secondsRemaining = Main.plugin.getConfig().getInt("Chest.Refill.Time");
        startChestRefillTimer();
    }

    //Booleans/Checkers
    public Boolean hasBeenRefilled(Location loc) {
        return usedLocations.contains(loc);
    }

    //Public getters
    Integer getSecondsRemaining() {
        return secondsRemaining;
    }

    Integer getMinutesRemaining() {
        return secondsRemaining / 60;
    }

    //Public setters
    public Boolean addLocation(Location loc) {
        if (!usedLocations.contains(loc)) {
            usedLocations.add(loc);
            return true;
        }
        return false;
    }
}