package net.syntaxsama.StarAPI.InventoryUtils;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class AnimatedItems {
   public static void addAniToInventory(Inventory inventory, Material[] animationMaterials, int amount, int slot, int animationSpeedTicks, String itemName, String... lore) {
      Material initialMaterial = animationMaterials[0];
      ItemStack itemStack = new ItemStack(initialMaterial, amount);
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

      Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getProvidingPlugin(AnimatedItems.class), () -> {
         Material nextMaterial = animationMaterials[0];
         System.arraycopy(animationMaterials, 1, animationMaterials, 0, animationMaterials.length - 1);
         animationMaterials[animationMaterials.length - 1] = nextMaterial;
         updateAnimatedItem(inventory, slot, nextMaterial, amount, itemName, lore);
      }, 0L, (long)animationSpeedTicks);
   }

   private static void updateAnimatedItem(Inventory inventory, int slot, Material material, int amount, String itemName, String... lore) {
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
      inventory.setItem(slot, itemStack);
   }
}
