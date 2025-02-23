package net.syntaxsama.StarAPI.Timers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Timer {
   public static BukkitTask timer(long delay, long period, final Runnable task) {
      return (new BukkitRunnable() {
         public void run() {
            task.run();
         }
      }).runTaskTimer(JavaPlugin.getProvidingPlugin(Timer.class), delay, period);
   }
}
