package net.syntaxsama.StarAPI.InventoryUtils;

import org.bukkit.inventory.InventoryView;

public class InventoryName {
   public static String clickedInvName(InventoryView inventoryView) {
      String inventoryName = inventoryView.getTitle();
      return inventoryName;
   }
}
