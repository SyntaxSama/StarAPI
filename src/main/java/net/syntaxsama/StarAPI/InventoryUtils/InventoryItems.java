package net.syntaxsama.StarAPI.InventoryUtils;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryItems {

   public static void addItemToInventory(Inventory inventory, Material material, int amount, int slot, String itemName, String... lore) {
      ItemStack itemStack = new ItemStack(material, amount);
      ItemMeta itemMeta = itemStack.getItemMeta();
      if (itemName != null) {
         itemMeta.setDisplayName(itemName);
      }

      if (lore != null && lore.length > 0) {
         for(int i = 0; i < lore.length; ++i) {
            lore[i] = MessageColors.hex(lore[i]);
         }

         itemMeta.setLore(Arrays.asList(lore));
      }

      itemStack.setItemMeta(itemMeta);
      if (slot >= 0 && slot < inventory.getSize()) {
         inventory.setItem(slot, itemStack);
      } else {
         inventory.addItem(new ItemStack[]{itemStack});
      }

   }

   public static void addShinyItemToInventory(Inventory inventory, Material material, int amount, int slot, boolean isShiny, String itemName, String... lore) {
      ItemStack itemStack = new ItemStack(material, amount);
      ItemMeta itemMeta = itemStack.getItemMeta();

      if (itemName != null) {
         itemMeta.setDisplayName(itemName);
      }

      if (lore != null && lore.length > 0) {
         for (int i = 0; i < lore.length; ++i) {
            lore[i] = MessageColors.hex(lore[i]);
         }
         itemMeta.setLore(Arrays.asList(lore));
      }

      if (isShiny) {
         itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
         itemMeta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
      }

      itemStack.setItemMeta(itemMeta);

      if (slot >= 0 && slot < inventory.getSize()) {
         inventory.setItem(slot, itemStack);
      } else {
         inventory.addItem(itemStack);
      }
   }

}
