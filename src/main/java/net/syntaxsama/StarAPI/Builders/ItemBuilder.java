package net.syntaxsama.StarAPI.Builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
   private ItemStack itemStack;

   public ItemBuilder(Material material) {
      this.itemStack = new ItemStack(material);
   }

   public ItemBuilder amount(int amount) {
      this.itemStack.setAmount(amount);
      return this;
   }

   public ItemBuilder displayName(String displayName) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.setDisplayName(displayName);
      this.itemStack.setItemMeta(itemMeta);
      return this;
   }

   public ItemBuilder lore(String... lore) {
      ItemMeta itemMeta = this.getItemMeta();
      List<String> loreList = new ArrayList();
      String[] var4 = lore;
      int var5 = lore.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String line = var4[var6];
         loreList.add(line);
      }

      itemMeta.setLore(loreList);
      this.itemStack.setItemMeta(itemMeta);
      return this;
   }

   public ItemBuilder enchant(Enchantment enchantment, int level) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.addEnchant(enchantment, level, true);
      this.itemStack.setItemMeta(itemMeta);
      return this;
   }

   public ItemBuilder attribute(Attribute attribute, AttributeModifier modifier) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.addAttributeModifier(attribute, modifier);
      this.itemStack.setItemMeta(itemMeta);
      return this;
   }

   public ItemBuilder flags(ItemFlag... flags) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.addItemFlags(flags);
      this.itemStack.setItemMeta(itemMeta);
      return this;
   }

   public ItemStack build() {
      return this.itemStack;
   }

   private ItemMeta getItemMeta() {
      ItemMeta itemMeta = this.getItemStack().getItemMeta();
      if (itemMeta == null) {
         itemMeta = Bukkit.getItemFactory().getItemMeta(this.getItemStack().getType());
         this.getItemStack().setItemMeta(itemMeta);
      }

      return itemMeta;
   }

   private ItemStack getItemStack() {
      return this.itemStack;
   }
}
