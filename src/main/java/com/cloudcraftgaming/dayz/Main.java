package com.cloudcraftgaming.dayz;

import com.cloudcraftgaming.dayz.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Nova Fox on 11/26/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class Main extends JavaPlugin {
    public static Main plugin;

    public void onDisable() {}

    public void onEnable() {
        plugin = this;

        //Create files
        FileManager.createConfig();

        //Register commands

        //Register events

        //Finish up
        FileManager.checkFileVersions();
    }
}