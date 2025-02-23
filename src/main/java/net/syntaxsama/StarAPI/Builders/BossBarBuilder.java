package net.syntaxsama.StarAPI.Builders;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BossBarBuilder {
   private final JavaPlugin plugin;
   private final Map<UUID, BossBar> bossBars;
   private String title;
   private BarColor color;
   private BarStyle style;
   private float progress;
   private BarFlag[] flags;

   public BossBarBuilder(JavaPlugin plugin) {
      this.plugin = plugin;
      this.bossBars = new HashMap();
   }

   public BossBarBuilder setTitle(String title) {
      this.title = title;
      return this;
   }

   public BossBarBuilder setColor(BarColor color) {
      this.color = color;
      return this;
   }

   public BossBarBuilder setStyle(BarStyle style) {
      this.style = style;
      return this;
   }

   public BossBarBuilder setProgress(float progress) {
      this.progress = progress;
      return this;
   }

   public BossBarBuilder setFlags(BarFlag... flags) {
      this.flags = flags;
      return this;
   }

   public BossBarBuilder create(Player player) {
      BossBar bossBar = Bukkit.createBossBar(MessageColors.hex(this.title), this.color, this.style != null ? this.style : BarStyle.SOLID, this.flags);
      bossBar.setProgress((double)this.progress);
      this.bossBars.put(player.getUniqueId(), bossBar);
      bossBar.addPlayer(player);
      return this;
   }

   public BossBarBuilder update(Player player) {
      BossBar bossBar = (BossBar)this.bossBars.get(player.getUniqueId());
      if (bossBar != null) {
         bossBar.setTitle(MessageColors.hex(this.title));
         bossBar.setColor(this.color);
         bossBar.setStyle(this.style);
         bossBar.setProgress((double)this.progress);
         bossBar.removeAll();
         bossBar.addPlayer(player);
      }

      return this;
   }
}
