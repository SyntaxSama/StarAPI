package net.syntaxsama.StarAPI.Timers;

import org.bukkit.scheduler.BukkitTask;

public class CancelTimer {
   public static void cancelTask(BukkitTask task) {
      task.cancel();
      ActiveTimers.activeTasks.remove(task);
   }
}
