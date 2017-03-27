package com.cloudcraftgaming.dayz.command;

import com.cloudcraftgaming.dayz.player.PlayerDataManager;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import com.cloudcraftgaming.dayz.zone.Zone;
import com.cloudcraftgaming.dayz.zone.ZoneDataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class DayZCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("dayz")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (player.hasPermission("DayZ.admin")) {
                    if (args.length < 1) {
                        player.sendMessage(MessageManager.getMessage("Notification.Args.TooFew"));
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("help")) {
                            sendHelpMessages(player);
                        } else if (args[0].equalsIgnoreCase("tool")) {
                            PlayerDataManager.setZoneTool(player, !PlayerDataManager.zoneToolEnabled(player));
                            //Send message.
                            if (PlayerDataManager.zoneToolEnabled(player)) {
                                player.sendMessage(MessageManager.getMessage("Command.Tool.Enabled"));
                            } else {
                                player.sendMessage(MessageManager.getMessage("Command.Tool.Disabled"));
                            }
                        } else {
                            player.sendMessage(MessageManager.getMessage("Notification.Args.Invalid"));
                        }
                    } else if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("SetZone")) {
                            String zoneNumString = args[1];
                            try {
                                Integer zoneNum = Integer.valueOf(zoneNumString);
                                Integer subZone = Integer.valueOf(args[2]);
                                if (Zone.isValid(zoneNum)) {
                                    setZone(player, zoneNum, subZone);
                                } else {
                                    player.sendMessage(MessageManager.getMessage("Notification.ZoneNum.Integer"));
                                }
                            } catch (NumberFormatException e) {
                                player.sendMessage(MessageManager.getMessage("Notification.ZoneNum.Integer"));
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("DeleteZone")) {
                        String zoneNumString = args[1];
                        try {
                            Integer zoneNum = Integer.valueOf(zoneNumString);
                            Integer subZone = Integer.valueOf(args[2]);
                            if (Zone.isValid(zoneNum)) {
                                if (ZoneDataManager.deleteSubZone(Zone.fromValue(zoneNum), subZone)) {
                                    //Deleted
                                    player.sendMessage(MessageManager.getMessage("Delete.Success"));
                                } else {
                                    //Failed, does not exist?
                                    player.sendMessage(MessageManager.getMessage("Delete.Failure"));
                                }
                            } else {
                                player.sendMessage(MessageManager.getMessage("Notification.ZoneNum.Integer"));
                            }
                        } catch (NumberFormatException e) {
                            player.sendMessage(MessageManager.getMessage("Notification.ZoneNum.Integer"));
                        }
                    } else {
                        player.sendMessage(MessageManager.getMessage("Notification.Args.TooMany"));
                    }
                } else {
                    player.sendMessage(MessageManager.getMessage("Notification.NoPerm"));
                }
            } else {
                sender.sendMessage(MessageManager.getMessage("Notification.PlayerOnly"));
            }
        }
        return false;
    }

    private void sendHelpMessages(Player p) {
        p.sendMessage(ChatColor.GOLD + "-~- DayZ Help -~-");
        p.sendMessage(ChatColor.GREEN + "/dayz help" + ChatColor.LIGHT_PURPLE + " - Displays info on DayZ commands.");
        p.sendMessage(ChatColor.GREEN + "/zone" + ChatColor.LIGHT_PURPLE + " - Displays what zone you are in.");
        p.sendMessage(ChatColor.GREEN + "/dayz tool" + ChatColor.LIGHT_PURPLE + " - Enables/Disables the zone selection tool.");
        p.sendMessage(ChatColor.GREEN + "/dayz setZone <zone #>" + ChatColor.LIGHT_PURPLE + " - Sets the area for the specified zone.");
        p.sendMessage(ChatColor.GOLD + "End of DayZ Help.");
    }

    private void setZone(Player p, Integer zoneNum, Integer subZone) {
       if (PlayerDataManager.hasLocationOne(p) && !PlayerDataManager.hasLocationTwo(p)) {
           //Only loc one set.
           p.sendMessage(MessageManager.getMessage("Set.Location.Check.OneOnly"));
       } else if (PlayerDataManager.hasLocationOne(p) && PlayerDataManager.hasLocationTwo(p)) {
           //Both locations set.
           Zone zone = Zone.fromValue(zoneNum);
           ZoneDataManager.saveLocationOneForZone(zone, subZone, PlayerDataManager.getLocationOne(p));
           ZoneDataManager.saveLocationTwoForZone(zone, subZone, PlayerDataManager.getLocationTwo(p));
           PlayerDataManager.deleteLocationOne(p);
           PlayerDataManager.deleteLocationTwo(p);

           String msgOr = MessageManager.getMessageYml().getString("Command.Set.Zone");
           String msg = msgOr.replaceAll("%number%", String.valueOf(zoneNum));
           p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
       } else if (!PlayerDataManager.hasLocationOne(p) && PlayerDataManager.hasLocationOne(p)) {
           //Only loc two set.
           p.sendMessage(MessageManager.getMessage("Set.Location.Check.TwoOnly"));
       } else {
           //No locations set.
           p.sendMessage(MessageManager.getMessage("Set.Location.Check.None"));
       }
    }
}