package com.cloudcraftgaming.dayz;

import com.cloudcraftgaming.dayz.listener.DeathListener;
import com.cloudcraftgaming.dayz.listener.JoinListener;
import com.cloudcraftgaming.dayz.listener.PlayerDamageListener;
import com.cloudcraftgaming.dayz.utils.FileManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import com.cloudcraftgaming.dayz.utils.TimeManager;
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
        FileManager.createZoneLocationFile();
        MessageManager.createEnglishMessagesFile();

        //Register commands

        //Register events
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        //Finish up
        FileManager.checkFileVersions();

        TimeManager.getManager().startChestRefillTimer();
    }
}