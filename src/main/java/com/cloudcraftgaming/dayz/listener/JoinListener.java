package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Nova Fox on 12/7/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class JoinListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!PlayerDataManager.hasDataFile(player)) {
            PlayerDataManager.createPlayerDataFile(player);
        } else {
            PlayerDataManager.updatePlayerDataFile(player);
        }
    }
}