package net.syntaxsama.StarAPI.Builders;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public class AniSBBuilder {
   private Scoreboard scoreboard;
   private Objective objective;
   private List<String> lines;
   private int interval;
   private int scrollingSpeed = 1;
   private int currentLineIndex = 0;
   private boolean scrollingEnabled = false;
   private boolean titleScrollingEnabled = false;
   private boolean typeWriterEnabled = false;
   private boolean flowWriterEnabled = false;
   private boolean boldTitleEnabled = false;
   private String title;
   private ChatColor defaultColor;
   private ChatColor flowColor;
   private boolean letterWaveEnabled;
   private int letterWaveStep;
   private boolean colorTransitionEnabled;
   private boolean pulsingEnabled;
   private boolean slidingEnabled;
   private ChatColor[] colorCycle;

   public AniSBBuilder(String title) {
      this.defaultColor = ChatColor.WHITE;
      this.flowColor = ChatColor.AQUA;
      this.letterWaveEnabled = false;
      this.letterWaveStep = 0;
      this.colorTransitionEnabled = false;
      this.pulsingEnabled = false;
      this.slidingEnabled = false;
      this.colorCycle = new ChatColor[]{ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.AQUA, ChatColor.LIGHT_PURPLE};
      this.title = MessageColors.hex(title);
      ScoreboardManager manager = Bukkit.getScoreboardManager();
      this.scoreboard = manager.getNewScoreboard();
      this.objective = this.scoreboard.registerNewObjective("aniSB", "dummy", this.title);
      this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
   }

   public AniSBBuilder setLines(List<String> lines) {
      this.lines = lines;
      return this;
   }

   public AniSBBuilder setInterval(int interval) {
      this.interval = Math.max(1, interval);
      return this;
   }

   public AniSBBuilder setScrollingSpeed(int speed) {
      this.scrollingSpeed = Math.max(1, speed);
      return this;
   }

   public AniSBBuilder enableScrolling(boolean enable) {
      this.scrollingEnabled = enable;
      return this;
   }

   public AniSBBuilder enableTitleScrolling(boolean enable) {
      this.titleScrollingEnabled = enable;
      return this;
   }

   public AniSBBuilder enableTypeWriter(boolean enable) {
      this.typeWriterEnabled = enable;
      return this;
   }

   public AniSBBuilder enableFlowWriter(boolean enable) {
      this.flowWriterEnabled = enable;
      return this;
   }

   public AniSBBuilder enableColorTransition(boolean enable) {
      this.colorTransitionEnabled = enable;
      return this;
   }

   public AniSBBuilder enablePulsing(boolean enable) {
      this.pulsingEnabled = enable;
      return this;
   }

   public AniSBBuilder enableSliding(boolean enable) {
      this.slidingEnabled = enable;
      return this;
   }

   public AniSBBuilder setDefaultColor(ChatColor color) {
      this.defaultColor = color;
      return this;
   }

   public AniSBBuilder setFlowColor(ChatColor color) {
      this.flowColor = color;
      return this;
   }

   public AniSBBuilder setBoldTitle(boolean bold) {
      this.boldTitleEnabled = bold;
      return this;
   }

   public AniSBBuilder enableLetterWave(boolean enable) {
      this.letterWaveEnabled = enable;
      return this;
   }

   public void build(final Player player, Plugin plugin) {
      (new BukkitRunnable() {
         int colorIndex = 0;
         int pulseStep = 0;
         int slideStep = 0;
         int typeWriterStep = 0;
         int flowWriterStep = 0;

         public void run() {
            if (AniSBBuilder.this.titleScrollingEnabled) {
               this.updateTitleScrolling();
            } else if (AniSBBuilder.this.colorTransitionEnabled) {
               this.updateColorTransition();
            } else if (AniSBBuilder.this.pulsingEnabled) {
               this.updatePulsing();
            } else if (AniSBBuilder.this.slidingEnabled) {
               this.updateSliding();
            } else if (AniSBBuilder.this.typeWriterEnabled) {
               this.updateTypeWriter();
            } else if (AniSBBuilder.this.flowWriterEnabled) {
               this.updateFlowWriter();
            } else if (AniSBBuilder.this.letterWaveEnabled) {
               this.updateLetterWave();
            }

            if (AniSBBuilder.this.scrollingEnabled) {
               AniSBBuilder.this.updateScrolling();
            } else {
               AniSBBuilder.this.updateStatic();
            }

            player.setScoreboard(AniSBBuilder.this.scoreboard);
         }

         private void updateLetterWave() {
            String strippedTitle = ChatColor.stripColor(AniSBBuilder.this.title);
            StringBuilder formattedTitle = new StringBuilder();

            for(int i = 0; i < strippedTitle.length(); ++i) {
               if (i == AniSBBuilder.this.letterWaveStep) {
                  formattedTitle.append(AniSBBuilder.this.flowColor);
               } else {
                  formattedTitle.append(AniSBBuilder.this.defaultColor);
               }

               if (AniSBBuilder.this.boldTitleEnabled) {
                  formattedTitle.append(ChatColor.BOLD);
               }

               formattedTitle.append(strippedTitle.charAt(i));
            }

            AniSBBuilder.this.objective.setDisplayName(formattedTitle.toString());
            ++AniSBBuilder.this.letterWaveStep;
            if (AniSBBuilder.this.letterWaveStep >= strippedTitle.length()) {
               AniSBBuilder.this.letterWaveStep = 0;
            }

         }

         private void updateTitleScrolling() {
            for(int i = 0; i < AniSBBuilder.this.scrollingSpeed; ++i) {
               AniSBBuilder var10000 = AniSBBuilder.this;
               String var10001 = AniSBBuilder.this.title.substring(1);
               var10000.title = var10001 + AniSBBuilder.this.title.charAt(0);
            }

            AniSBBuilder.this.objective.setDisplayName(AniSBBuilder.this.title);
         }

         private void updateColorTransition() {
            Objective var10000 = AniSBBuilder.this.objective;
            String var10001 = String.valueOf(AniSBBuilder.this.colorCycle[this.colorIndex]);
            var10000.setDisplayName(var10001 + ChatColor.stripColor(AniSBBuilder.this.title));
            this.colorIndex = (this.colorIndex + 1) % AniSBBuilder.this.colorCycle.length;
         }

         private void updatePulsing() {
            int brightness = 160 + (int)(95.0D * Math.sin((double)this.pulseStep * 0.1D));
            String hexColor = String.format("#%02x%02x%02x", brightness, brightness, brightness);
            Objective var10000 = AniSBBuilder.this.objective;
            String var10001 = String.valueOf(ChatColor.valueOf(hexColor));
            var10000.setDisplayName(var10001 + ChatColor.stripColor(AniSBBuilder.this.title));
            ++this.pulseStep;
         }

         private void updateSliding() {
            int maxLength = 16;
            if (this.slideStep > AniSBBuilder.this.title.length()) {
               this.slideStep = 0;
            }

            String var10000 = " ".repeat(Math.max(0, this.slideStep));
            String slidingTitle = var10000 + AniSBBuilder.this.title;
            if (slidingTitle.length() > maxLength) {
               slidingTitle = slidingTitle.substring(0, maxLength);
            }

            AniSBBuilder.this.objective.setDisplayName(slidingTitle);
            ++this.slideStep;
         }

         private void updateTypeWriter() {
            String partialTitle = AniSBBuilder.this.title.substring(0, Math.min(this.typeWriterStep, AniSBBuilder.this.title.length()));
            AniSBBuilder.this.objective.setDisplayName(partialTitle);
            ++this.typeWriterStep;
            if (this.typeWriterStep > AniSBBuilder.this.title.length()) {
               this.typeWriterStep = 0;
            }

         }

         private void updateFlowWriter() {
            String strippedTitle = ChatColor.stripColor(AniSBBuilder.this.title);
            String partialTitle = strippedTitle.substring(0, Math.min(this.flowWriterStep, strippedTitle.length()));
            String remainingTitle = strippedTitle.substring(this.flowWriterStep);
            StringBuilder formattedTitle = new StringBuilder();
            if (AniSBBuilder.this.boldTitleEnabled) {
               formattedTitle.append(ChatColor.BOLD);
            }

            formattedTitle.append(AniSBBuilder.this.flowColor);
            if (AniSBBuilder.this.boldTitleEnabled) {
               formattedTitle.append(ChatColor.BOLD);
            }

            formattedTitle.append(partialTitle);
            formattedTitle.append(AniSBBuilder.this.defaultColor);
            if (AniSBBuilder.this.boldTitleEnabled) {
               formattedTitle.append(ChatColor.BOLD);
            }

            formattedTitle.append(remainingTitle);
            AniSBBuilder.this.objective.setDisplayName(formattedTitle.toString());
            ++this.flowWriterStep;
            if (this.flowWriterStep > strippedTitle.length()) {
               this.flowWriterStep = 0;
            }

         }
      }).runTaskTimerAsynchronously(plugin, 0L, (long)this.interval);
   }

   private void updateScrolling() {
      if (!this.lines.isEmpty()) {
         int maxLines = Math.min(15, this.lines.size());

         for(int i = 0; i < maxLines; ++i) {
            int lineIndex = (this.currentLineIndex + i) % this.lines.size();
            String line = ChatColor.translateAlternateColorCodes('&', (String)this.lines.get(lineIndex));
            this.objective.getScore(line).setScore(maxLines - i);
         }

         ++this.currentLineIndex;
      }
   }

   private void updateStatic() {
      int maxLines = Math.min(15, this.lines.size());

      for(int i = 0; i < maxLines; ++i) {
         String line = ChatColor.translateAlternateColorCodes('&', (String)this.lines.get(i));
         this.objective.getScore(line).setScore(maxLines - i);
      }

   }
}
