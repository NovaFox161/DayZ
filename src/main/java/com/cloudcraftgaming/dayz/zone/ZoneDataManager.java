package com.cloudcraftgaming.dayz.zone;

import com.cloudcraftgaming.dayz.utils.Cuboid;
import com.cloudcraftgaming.dayz.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Created by Nova Fox on 12/3/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class ZoneDataManager {

    //Setters
    public static boolean saveLocationOneForZone(Zone zone, Location location) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        yml.set("Zones." + zone.name() + ".loc1.world", location.getWorld().getName());
        yml.set("Zones." + zone.name() + ".loc1.x", location.getX());
        yml.set("Zones." + zone.name() + ".loc1.y", location.getY());
        yml.set("Zones." + zone.name() + ".loc1.z", location.getZ());
        FileManager.saveZoneLocationFile(yml);
        return true;
    }

    public static boolean saveLocationTwoForZone(Zone zone, Location location) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        yml.set("Zones." + zone.name() + ".loc2.world", location.getWorld().getName());
        yml.set("Zones." + zone.name() + ".loc2.x", location.getX());
        yml.set("Zones." + zone.name() + ".loc2.y", location.getY());
        yml.set("Zones." + zone.name() + ".loc2.z", location.getZ());
        FileManager.saveZoneLocationFile(yml);
        return true;
    }

    //Getters
    public static Location getLocationOneForZone(Zone zone) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        World w = Bukkit.getWorld(yml.getString("Zones." + zone.name() + ".loc1.world"));
        Double x = yml.getDouble("Zones." + zone.name() + ".loc1.x");
        Double y = yml.getDouble("Zones." + zone.name() + ".loc1.y");
        Double z = yml.getDouble("Zones." + zone.name() + ".loc1.z");
        return new Location(w, x, y, z);
    }

    public static Location getLocationTwoForZone(Zone zone) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        World w = Bukkit.getWorld(yml.getString("Zones." + zone.name() + ".loc2.world"));
        Double x = yml.getDouble("Zones." + zone.name() + ".loc2.x");
        Double y = yml.getDouble("Zones." + zone.name() + ".loc2.y");
        Double z = yml.getDouble("Zones." + zone.name() + ".loc2.z");
        return new Location(w, x, y, z);
    }

    public static Integer getZoneFromLocation(Location loc) {
        for (int i = 1; i < 7; i++) {
            Zone zone = Zone.fromValue(i);
            if (zoneSaved(zone)) {
                Cuboid c = new Cuboid(getLocationOneForZone(zone), getLocationTwoForZone(zone));
                if (c.contains(loc)) {
                    return i;
                }
            }
        }
        return 0;
    }

    //Booleans/Checkers
    public static Boolean zoneSaved(Zone zone) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        return yml.contains("Zones." + zone.name() + ".loc1") && yml.contains("Zones." + zone.name() + ".loc2");
    }
}