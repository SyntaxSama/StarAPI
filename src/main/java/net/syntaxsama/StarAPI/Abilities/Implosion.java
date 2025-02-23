package net.syntaxsama.StarAPI.Abilities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.List;

public class Implosion {
   public static void create(Location center, double radius, double damage) {
      World world = center.getWorld();
      List<Entity> nearbyEntities = (List)world.getNearbyEntities(center, radius, radius, radius);
      Iterator var7 = nearbyEntities.iterator();

      while(var7.hasNext()) {
         Entity entity = (Entity)var7.next();
         if (entity instanceof LivingEntity && !(entity instanceof Player)) {
            ((LivingEntity)entity).damage(damage);
         }
      }

      world.createExplosion(center.getX(), center.getY(), center.getZ(), 0.0F, false, false);
   }
}
