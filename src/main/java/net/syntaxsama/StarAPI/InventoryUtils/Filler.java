package net.syntaxsama.StarAPI.InventoryUtils;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Filler {
   public static void addFillerToInventory(Inventory inventory, Material fillerMaterial) {
      ItemStack fillerItem = new ItemStack(fillerMaterial);
      ItemMeta fillerMeta = fillerItem.getItemMeta();
      fillerMeta.setDisplayName(MessageColors.hex("&7"));
      fillerMeta.setLore((List)null);
      fillerItem.setItemMeta(fillerMeta);

      for(int slot = 0; slot < inventory.getSize(); ++slot) {
         if (inventory.getItem(slot) == null || inventory.getItem(slot).getType() == Material.AIR) {
            inventory.setItem(slot, fillerItem);
         }
      }

   }
}
