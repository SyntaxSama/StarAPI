package net.syntaxsama.StarAPI.Abilities;

import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MultiShot {
   public static void create(Player player, int arrowAmount, double damage, boolean isCritical) {
      arrowAmount = Math.max(0, arrowAmount);
      ItemStack arrowStack = new ItemStack(Material.ARROW, arrowAmount);
      if (player.getInventory().containsAtLeast(arrowStack, arrowAmount)) {
         player.getInventory().removeItem(new ItemStack[]{arrowStack});

         for(int i = 0; i < arrowAmount; ++i) {
            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, player.getLocation().getDirection());
            arrow.setPickupStatus(PickupStatus.DISALLOWED);
            arrow.setDamage(damage);
            arrow.setCritical(isCritical);
         }

      }
   }
}
