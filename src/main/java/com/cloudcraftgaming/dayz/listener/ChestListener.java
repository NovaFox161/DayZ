package com.cloudcraftgaming.dayz.listener;

import com.cloudcraftgaming.dayz.Main;
import com.cloudcraftgaming.dayz.mechanics.ChestRefill;
import com.cloudcraftgaming.dayz.utils.ItemManager;
import com.cloudcraftgaming.dayz.zone.ZoneDataManager;
import org.bukkit.GameMode;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Nova Fox on 1/6/2017.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public class ChestListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChestOpen(InventoryOpenEvent event) {
        Player p = (Player)event.getPlayer();
        if (Main.plugin.getConfig().getStringList("Worlds.Enabled").contains(p.getWorld().getName())) {
            if (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
                if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof Chest) {
                    Chest chest = (Chest) event.getInventory().getHolder();
                    if (Main.plugin.getConfig().getString("Chest.Refill.RequireEmpty").equalsIgnoreCase("False") || ItemManager.chestEmpty(chest.getInventory())) {
                        Integer zoneNumber = ZoneDataManager.getZoneFromLocation(chest.getLocation());
                        if (zoneNumber > 0 && zoneNumber < 7) {
                            if (!ChestRefill.getInstance().hasBeenRefilled(chest.getLocation())) {
                                ChestRefill.getInstance().addLocation(chest.getLocation());
                                int slot = 0;
                                for (ItemStack itemStack : ItemManager.getItemsChest(zoneNumber)) {
                                    chest.getInventory().setItem(slot, itemStack);
                                    slot++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}