package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Nova Fox on 12/7/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class DeathListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
            //Reset everything as they died.
            PlayerDataManager.setThirst(p, 100.0);
            PlayerDataManager.setBleeding(p, false);
            PlayerDataManager.setBoneBroken(p, false);
        }
    }
}