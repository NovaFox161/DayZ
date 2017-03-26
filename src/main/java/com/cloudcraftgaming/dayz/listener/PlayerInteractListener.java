package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.mechanics.BoneBreak;
import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import org.bukkit.GameMode;
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
                if (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
                    if (event.getItem() != null && event.getItem().getType() != Material.AIR) {
                        if (event.getItem().getType() == Material.BONE) {
                            if (Main.plugin.getConfig().getString("BoneBreak.Enabled").equalsIgnoreCase("True")) {
                                if (PlayerDataManager.hasBrokenBone(p)) {
                                    PlayerDataManager.setBoneBroken(p, false);
                                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                                    BoneBreak.getInstance().mendBreak(p);

                                    //Send message
                                    p.sendMessage(MessageManager.getMessage("Bone.Mend"));
                                }
                            }
                        } else if (event.getItem().getType() == Material.PAPER) {
                            if (Main.plugin.getConfig().getString("Bleed.Enabled").equalsIgnoreCase("True")) {
                                if (PlayerDataManager.isBleeding(p)) {
                                    PlayerDataManager.setBleeding(p, false);
                                    event.getItem().setAmount(event.getItem().getAmount() - 1);

                                    //Send message
                                    p.sendMessage(MessageManager.getMessage("Bleed.Patch"));
                                }
                            }
                        } else if (event.getItem().getType() == Material.INK_SACK) {
                            if (event.getItem().getDurability() == 14) {
                                //'pepsi'
                                if (Main.plugin.getConfig().getString("Thirst.Enabled").equalsIgnoreCase("True")) {
                                    if (PlayerDataManager.getThirst(p) < 100) {
                                        Double fill = Main.plugin.getConfig().getDouble("Thirst.Pepsi.Amount");

                                        if (PlayerDataManager.getThirst(p) + fill >= 100) {
                                            PlayerDataManager.setThirst(p, 100d);
                                            event.getItem().setAmount(event.getItem().getAmount() - 1);
                                            p.sendMessage(MessageManager.getMessage("Thirst.Full"));
                                        } else {
                                            PlayerDataManager.setThirst(p, fill);
                                            event.getItem().setAmount(event.getItem().getAmount() - 1);
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnPlayerZoneInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
            if (event.getItem() != null && event.getItem().getType() == Material.STICK) {
                if (PlayerDataManager.zoneToolEnabled(p)) {
                    if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        PlayerDataManager.setLocationOne(p, event.getClickedBlock().getLocation());
                        p.sendMessage(MessageManager.getMessage("Set.Location.One"));
                        checkLocations(p);
                    } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        PlayerDataManager.setLocationTwo(p, event.getClickedBlock().getLocation());
                        p.sendMessage(MessageManager.getMessage("Set.Location.Two"));
                        checkLocations(p);
                    }
                }
            }
        }
    }

    private void checkLocations(Player p) {
        //Implement the checking and messages here.
        if (PlayerDataManager.hasLocationOne(p) && !PlayerDataManager.hasLocationTwo(p)) {
            //Location one only.
            p.sendMessage(MessageManager.getMessage("Set.Location.Check.OneOnly"));
        } else if (PlayerDataManager.hasLocationOne(p) && PlayerDataManager.hasLocationTwo(p)) {
            //Both locations one and two set.
            p.sendMessage(MessageManager.getMessage("Set.Location.Check.Both"));
        } else if (!PlayerDataManager.hasLocationOne(p) && PlayerDataManager.hasLocationTwo(p)) {
            //Location two only.
            p.sendMessage(MessageManager.getMessage("Set.Location.Check.TwoOnly"));
        }
    }
}