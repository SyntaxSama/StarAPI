package net.syntaxsama.StarAPI.Recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Furnace {
   private Plugin plugin;

   public Furnace(Plugin plugin) {
      this.plugin = plugin;
   }

   public Furnace registerRecipe(Material input, ItemStack output, String name, List<String> lore, Map<Enchantment, Integer> enchantments) {
      ItemStack result = new ItemStack(output.getType());
      ItemMeta meta = result.getItemMeta();
      if (meta != null) {
         if (name != null) {
            meta.setDisplayName(name);
         }

         if (lore != null) {
            meta.setLore(lore);
         }

         if (enchantments != null) {
            Iterator var8 = enchantments.entrySet().iterator();

            while(var8.hasNext()) {
               Entry<Enchantment, Integer> entry = (Entry)var8.next();
               meta.addEnchant((Enchantment)entry.getKey(), (Integer)entry.getValue(), true);
            }
         }

         result.setItemMeta(meta);
      }

      FurnaceRecipe recipe = new FurnaceRecipe(result, input);
      this.plugin.getServer().addRecipe(recipe);
      return null;
   }
}
