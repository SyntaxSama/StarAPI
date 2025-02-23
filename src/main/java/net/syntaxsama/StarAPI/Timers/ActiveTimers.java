package net.syntaxsama.StarAPI.Timers;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class ActiveTimers {
   static final Map<BukkitTask, Plugin> activeTasks = new HashMap();

   public static boolean isTaskActive(BukkitTask task) {
      return activeTasks.containsKey(task);
   }
}
