package net.syntaxsama.StarAPI.Builders;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ArmorBuilder {
   private ItemStack armorPiece;

   public ArmorBuilder(Material armorType) {
      this.armorPiece = new ItemStack(armorType);
   }

   public ArmorBuilder displayName(String displayName) {
      this.setItemDisplayName(displayName);
      return this;
   }

   public ArmorBuilder lore(String... lore) {
      this.setItemLore(lore);
      return this;
   }

   public ArmorBuilder amount(int amount) {
      this.setItemAmount(amount);
      return this;
   }

   public ArmorBuilder enchant(Enchantment enchantment, int level) {
      this.enchantItem(enchantment, level);
      return this;
   }

   public ArmorBuilder attribute(Attribute attribute, AttributeModifier modifier) {
      this.setItemAttribute(attribute, modifier);
      return this;
   }

   public ArmorBuilder flags(ItemFlag... flags) {
      this.setItemFlags(flags);
      return this;
   }

   public ArmorBuilder leatherColor(Color color) {
      if (this.armorPiece.getType() == Material.LEATHER_HELMET || this.armorPiece.getType() == Material.LEATHER_CHESTPLATE || this.armorPiece.getType() == Material.LEATHER_LEGGINGS || this.armorPiece.getType() == Material.LEATHER_BOOTS) {
         LeatherArmorMeta leatherMeta = (LeatherArmorMeta)this.getItemMeta();
         leatherMeta.setColor(color);
         this.armorPiece.setItemMeta(leatherMeta);
      }

      return this;
   }

   public ItemStack build() {
      return this.armorPiece;
   }

   private void setItemDisplayName(String displayName) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.setDisplayName(displayName);
      this.armorPiece.setItemMeta(itemMeta);
   }

   private void setItemLore(String... lore) {
      ItemMeta itemMeta = this.getItemMeta();
      List<String> loreList = new ArrayList();
      String[] var4 = lore;
      int var5 = lore.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String line = var4[var6];
         loreList.add(line);
      }

      itemMeta.setLore(loreList);
      this.armorPiece.setItemMeta(itemMeta);
   }

   private void setItemAmount(int amount) {
      this.armorPiece.setAmount(amount);
   }

   private void enchantItem(Enchantment enchantment, int level) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.addEnchant(enchantment, level, true);
      this.armorPiece.setItemMeta(itemMeta);
   }

   private void setItemAttribute(Attribute attribute, AttributeModifier modifier) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.addAttributeModifier(attribute, modifier);
      this.armorPiece.setItemMeta(itemMeta);
   }

   private void setItemFlags(ItemFlag... flags) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.addItemFlags(flags);
      this.armorPiece.setItemMeta(itemMeta);
   }

   private ItemMeta getItemMeta() {
      ItemMeta itemMeta = this.armorPiece.getItemMeta();
      if (itemMeta == null) {
         itemMeta = Bukkit.getItemFactory().getItemMeta(this.armorPiece.getType());
         this.armorPiece.setItemMeta(itemMeta);
      }

      return itemMeta;
   }
}
