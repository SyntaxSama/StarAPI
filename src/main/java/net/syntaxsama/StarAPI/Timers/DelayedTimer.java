package net.syntaxsama.StarAPI.Timers;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DelayedTimer {
   public static BukkitTask scheduleDelayedTask(Plugin plugin, long delay, final Runnable task) {
      BukkitTask bukkitTask = (new BukkitRunnable() {
         public void run() {
            task.run();
            ActiveTimers.activeTasks.remove(this);
         }
      }).runTaskLater(plugin, delay);
      ActiveTimers.activeTasks.put(bukkitTask, plugin);
      return bukkitTask;
   }
}
