package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.mechanics.BoneBreak;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class PlayerInteractListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player p = event.getPlayer();
            if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
               if (event.getItem() != null && event.getItem().getType() != Material.AIR) {
                   if (event.getItem().getType() == Material.BONE) {
                       if (PlayerDataManager.hasBrokenBone(p)) {
                           PlayerDataManager.setBoneBroken(p, false);
                           event.getItem().setAmount(event.getItem().getAmount() - 1);
                           BoneBreak.getInstance().mendBreak(p);

                           //Send message
                           p.sendMessage(MessageManager.getMessage("Bone.Mend"));
                       }
                   } else if (event.getItem().getType() == Material.PAPER) {
                       if (PlayerDataManager.isBleeding(p)) {
                           PlayerDataManager.setBleeding(p, false);
                           event.getItem().setAmount(event.getItem().getAmount() - 1);

                           //Send message
                           p.sendMessage(MessageManager.getMessage("Bleed.Patch"));
                       }
                   }
               }
            }
        }
    }
}