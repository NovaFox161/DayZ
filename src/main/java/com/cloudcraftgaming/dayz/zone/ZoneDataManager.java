package com.cloudcraftgaming.dayz.zone;

import com.cloudcraftgaming.dayz.utils.FileManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Created by Nova Fox on 12/3/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class ZoneDataManager {
    public boolean saveLocationOneForZone(Zone zone, Location location) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        yml.set("Zones." + zone.name() + ".loc1.world", location.getWorld().getName());
        yml.set("Zones." + zone.name() + ".loc1.x", location.getX());
        yml.set("Zones." + zone.name() + ".loc1.y", location.getY());
        yml.set("Zones." + zone.name() + ".loc1.z", location.getZ());
        FileManager.saveZoneLocationFile(yml);
        return true;
    }

    public boolean saveLocationTwoForZone(Zone zone, Location location) {
        YamlConfiguration yml = FileManager.getZoneLocationYml();
        yml.set("Zones." + zone.name() + ".loc2.world", location.getWorld().getName());
        yml.set("Zones." + zone.name() + ".loc2.x", location.getX());
        yml.set("Zones." + zone.name() + ".loc2.y", location.getY());
        yml.set("Zones." + zone.name() + ".loc2.z", location.getZ());
        FileManager.saveZoneLocationFile(yml);
        return true;
    }
}