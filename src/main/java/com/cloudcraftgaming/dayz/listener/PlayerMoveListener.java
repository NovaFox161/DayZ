package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.utils.MessageManager;
import com.cloudcraftgaming.dayz.zone.ZoneDataManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Nova Fox on 3/26/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class PlayerMoveListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!event.isCancelled()) {
            Integer zoneFrom = ZoneDataManager.getZoneFromLocation(event.getFrom());
            Integer zoneTo = ZoneDataManager.getZoneFromLocation(event.getTo());
            if (zoneTo != 0) {
                //Within zones, changed zones.
                if (!zoneFrom.equals(zoneTo)) {
                    String msgOr = MessageManager.getMessage("Zone.Change");
                    String msg = msgOr.replaceAll("%zone%", String.valueOf(zoneTo));
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }
}