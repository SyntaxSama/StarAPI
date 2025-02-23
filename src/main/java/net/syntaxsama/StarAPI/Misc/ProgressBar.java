package net.syntaxsama.StarAPI.Misc;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;

public class ProgressBar {
   public static String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor, ChatColor notCompletedColor) {
      float percent = (float)current / (float)max;
      int progressBars = (int)((float)totalBars * percent);
      String var10000 = Strings.repeat(String.valueOf(completedColor) + symbol, progressBars);
      return var10000 + Strings.repeat(String.valueOf(notCompletedColor) + symbol, totalBars - progressBars);
   }
}
