package net.syntaxsama.StarAPI.Misc;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

public class SkullUtils {

   public static void giveSkullToPlayer(Player player, String url, int amount, String itemName, String... lore) {
      ItemStack skullStack = new ItemStack(Material.PLAYER_HEAD, amount);
      SkullMeta skullMeta = (SkullMeta)skullStack.getItemMeta();
      PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID());
      PlayerTextures textures = profile.getTextures();

      try {
         textures.setSkin(new URL("http://textures.minecraft.net/texture/" + url));
      } catch (MalformedURLException var10) {
         var10.printStackTrace();
      }

      profile.setTextures(textures);
      skullMeta.setOwnerProfile(profile);
      if (itemName != null) {
         skullMeta.setDisplayName(itemName);
      }

      if (lore != null && lore.length > 0) {
         for(int i = 0; i < lore.length; ++i) {
            lore[i] = MessageColors.hex(lore[i]);
         }

         skullMeta.setLore(Arrays.asList(lore));
      }

      skullStack.setItemMeta(skullMeta);
      player.getInventory().addItem(new ItemStack[]{skullStack});
   }

   public static ItemStack giveSkull(Player player, String playerName, int amount, String itemName, String... lore) {
      ItemStack skullStack = new ItemStack(Material.PLAYER_HEAD, amount);
      SkullMeta skullMeta = (SkullMeta)skullStack.getItemMeta();
      if (skullMeta != null) {
         skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
         if (itemName != null) {
            skullMeta.setDisplayName(itemName);
         }

         if (lore != null && lore.length > 0) {
            for(int i = 0; i < lore.length; ++i) {
               lore[i] = MessageColors.hex(lore[i]);
            }

            skullMeta.setLore(Arrays.asList(lore));
         }

         skullStack.setItemMeta(skullMeta);
         player.getInventory().addItem(new ItemStack[]{skullStack});
      }

      return skullStack;
   }
}
