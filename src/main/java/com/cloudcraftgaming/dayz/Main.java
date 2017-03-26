package com.cloudcraftgaming.dayz;

import com.cloudcraftgaming.dayz.command.DayZCommand;
import com.cloudcraftgaming.dayz.command.ZoneCommand;
import com.cloudcraftgaming.dayz.listener.*;
import com.cloudcraftgaming.dayz.mechanics.Bleed;
import com.cloudcraftgaming.dayz.mechanics.BoneBreak;
import com.cloudcraftgaming.dayz.mechanics.ChestRefill;
import com.cloudcraftgaming.dayz.mechanics.Thirst;
import com.cloudcraftgaming.dayz.utils.FileManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
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
        FileManager.createItemFile();
        MessageManager.createEnglishMessagesFile();

        //Register commands
        getCommand("dayz").setExecutor(new DayZCommand());
        getCommand("zone").setExecutor(new ZoneCommand());

        //Register events
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(), this);
        getServer().getPluginManager().registerEvents(new ChestListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);

        //Finish up
        FileManager.checkFileVersions();

        //Initiate new mechanics
        BoneBreak.getInstance().init();
        Bleed.getInstance().init();
        Thirst.getInstance().init();
        ChestRefill.getInstance().init();
    }
}