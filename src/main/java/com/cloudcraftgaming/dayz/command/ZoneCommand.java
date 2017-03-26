package com.cloudcraftgaming.dayz.command;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.utils.MessageManager;
import com.cloudcraftgaming.dayz.zone.ZoneDataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 3/26/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
public class ZoneCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("zone")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(player.getWorld().getName())) {
                    Integer zone = ZoneDataManager.getZoneFromLocation(player.getLocation());
                    if (zone != 0) {
                        String msgOr = MessageManager.getMessage("Command.Zone.Info");
                        String msg = msgOr.replaceAll("%zone%", String.valueOf(zone));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    } else {
                        player.sendMessage(MessageManager.getMessage("Command.Zone.None"));
                    }
                }
            }
        }
        return false;
    }
}