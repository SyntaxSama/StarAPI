package net.syntaxsama.StarAPI.Misc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicInteger;

public class BlockPlacer {
   private final JavaPlugin plugin;
   private Material material;
   private Direction direction;
   private int length;
   private int height;
   private long delayBetweenBlocks;
   private BukkitTask task;

   public BlockPlacer(JavaPlugin plugin) {
      this.plugin = plugin;
   }

   public static BlockPlacer create(JavaPlugin plugin, Material material) {
      BlockPlacer blockPlacer = new BlockPlacer(plugin);
      blockPlacer.material = material;
      return blockPlacer;
   }

   public BlockPlacer setDirection(Direction direction) {
      this.direction = direction;
      return this;
   }

   public BlockPlacer setLength(int length) {
      this.length = length;
      return this;
   }

   public BlockPlacer setHeight(int height) {
      this.height = height;
      return this;
   }

   public BlockPlacer setDelayBetweenBlocks(long delay) {
      this.delayBetweenBlocks = delay;
      return this;
   }

   public void build(Location origin) {
      World world = origin.getWorld();
      AtomicInteger x = new AtomicInteger(origin.getBlockX());
      AtomicInteger y = new AtomicInteger(origin.getBlockY());
      AtomicInteger z = new AtomicInteger(origin.getBlockZ());
      this.task = Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
         for(int j = 0; j < this.height; ++j) {
            Block block = world.getBlockAt(x.get(), y.get() + j, z.get());
            block.setType(this.material);
         }

         switch(this.direction.ordinal()) {
         case 0:
            x.getAndIncrement();
            break;
         case 1:
            x.getAndDecrement();
            break;
         case 2:
            z.getAndIncrement();
            break;
         case 3:
            z.getAndDecrement();
            break;
         case 4:
            y.getAndIncrement();
            break;
         case 5:
            y.getAndDecrement();
            break;
         case 6:
            x.getAndIncrement();
            z.getAndDecrement();
            break;
         case 7:
            x.getAndIncrement();
            z.getAndIncrement();
            break;
         case 8:
            x.getAndDecrement();
            z.getAndIncrement();
            break;
         case 9:
            x.getAndDecrement();
            z.getAndDecrement();
         }

         --this.length;
         if (this.length <= 0) {
            this.task.cancel();
         }

      }, this.delayBetweenBlocks, this.delayBetweenBlocks);
   }

   public static enum Direction {
      EAST,
      WEST,
      SOUTH,
      NORTH,
      UP,
      DOWN,
      NORTHEAST,
      SOUTHEAST,
      SOUTHWEST,
      NORTHWEST;

      // $FF: synthetic method
      private static Direction[] $values() {
         return new Direction[]{EAST, WEST, SOUTH, NORTH, UP, DOWN, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST};
      }
   }
}
