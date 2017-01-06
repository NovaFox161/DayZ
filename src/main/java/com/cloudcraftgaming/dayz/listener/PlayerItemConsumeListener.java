package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class PlayerItemConsumeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemConsume(PlayerItemConsumeEvent event) {
        if (!event.isCancelled()) {
            Player p = event.getPlayer();
            if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                if (event.getItem() != null || event.getItem().getType() != Material.AIR) {
                    if (event.getItem().getType() == Material.POTION) {
                        if (event.getItem().getDurability() == 0) {
                            //Water bottle.
                            Double amount = Main.plugin.getConfig().getDouble("Thirst.Drink.Amount");
                            Double thirst = PlayerDataManager.getThirst(p);
                            if (thirst + amount >= 100.0) {
                                PlayerDataManager.setThirst(p, 100.0);
                                //Send message
                                p.sendMessage(MessageManager.getMessage("Thirst.Full"));
                            } else {
                                PlayerDataManager.setThirst(p, thirst + amount);
                            }
                        }
                    }
                }
            }
        }
    }
}