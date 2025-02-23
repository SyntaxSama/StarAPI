package net.syntaxsama.StarAPI.Abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Transmition {
   public static void create(Player player, double distance) {
      Location playerLocation = player.getLocation();
      World world = player.getWorld();
      Vector direction = playerLocation.getDirection().multiply(distance);
      Location destination = playerLocation.add(direction);
      Location targetLocation = getSafeDestination(destination);
      if (targetLocation != null) {
         player.teleport(targetLocation);
      }

   }

   private static Location getSafeDestination(Location destination) {
      World world = destination.getWorld();
      Vector direction = destination.getDirection();

      for(double i = 0.0D; i < 1.0D; i += 0.1D) {
         Location checkLocation = destination.clone().add(direction.clone().multiply(i));
         if (checkLocation.getBlock().getType() != Material.AIR) {
            return checkLocation.subtract(direction.clone().multiply(0.2D));
         }
      }

      return destination;
   }
}
