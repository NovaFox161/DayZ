package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.mechanics.BoneBreak;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Nova Fox on 12/7/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class PlayerDamageListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                //Player in enabled world, see what caused issue and do stuff.
                if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    if (Main.plugin.getConfig().getString("BoneBreak.Enabled").equalsIgnoreCase("True")) {
                        if (!PlayerDataManager.hasBrokenBone(p)) {
                            PlayerDataManager.setBoneBroken(p, true);
                            BoneBreak.getInstance().applyBreak(p);
                            //Tell player.
                            p.sendMessage(MessageManager.getMessage("Bone.Break"));
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamageFromEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
                if (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
                    if (Main.plugin.getConfig().getString("Bleed.Enabled").equalsIgnoreCase("True")) {
                        //Player in enabled world, see what caused issue and do stuff.
                        if (event.getDamager() instanceof Zombie) {
                            //Zombie hit player, make them bleed.
                            if (!PlayerDataManager.isBleeding(p)) {
                                PlayerDataManager.setBleeding(p, true);
                                //Tell player.
                                p.sendMessage(MessageManager.getMessage("Bleed.Bleeding"));
                            }
                        } else if (event.getDamager() instanceof Arrow) {
                            if (!PlayerDataManager.isBleeding(p)) {
                                PlayerDataManager.setBleeding(p, true);
                                //Tell player.
                                p.sendMessage(MessageManager.getMessage("Bleed.Bleeding"));
                            }
                        } else if (event.getDamager() instanceof Player) {
                            Player damager = (Player) event.getDamager();
                            if (damager.getItemInHand() != null || !damager.getItemInHand().getType().equals(Material.AIR)) {
                                Material itemType = damager.getItemInHand().getType();
                                if (itemType.equals(Material.WOOD_SWORD) || itemType.equals(Material.STONE_SWORD) || itemType.equals(Material.IRON_SWORD)
                                        || itemType.equals(Material.GOLD_SWORD) || itemType.equals(Material.DIAMOND_SWORD)) {
                                    //Hit by sword, make them bleed.
                                    if (!PlayerDataManager.isBleeding(p)) {
                                        PlayerDataManager.setBleeding(p, true);
                                        //Tell player.
                                        p.sendMessage(MessageManager.getMessage("Bleed.Bleeding"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}