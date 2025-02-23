package net.syntaxsama.StarAPI.MessageUtils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class AutoResponses {
   private final Map<String, String> responses = new HashMap();
   private Sound sound;
   private float volume = 1.0F;
   private float pitch = 1.0F;
   private String title;
   private String subtitle;

   public AutoResponses autoResponses(String trigger, String response) {
      this.responses.put(trigger.toLowerCase(), response);
      return this;
   }

   public AutoResponses playSound(Sound sound, float volume, float pitch) {
      this.sound = sound;
      this.volume = volume;
      this.pitch = pitch;
      return this;
   }

   public AutoResponses sendTitle(String title, String subtitle) {
      this.title = title;
      this.subtitle = subtitle;
      return this;
   }

   public void build(Player player, String message) {
      Iterator var3 = this.responses.entrySet().iterator();

      while(var3.hasNext()) {
         Entry<String, String> entry = (Entry)var3.next();
         if (message.toLowerCase().contains((CharSequence)entry.getKey())) {
            player.sendMessage((String)entry.getValue());
            if (this.sound != null) {
               player.playSound(player.getLocation(), this.sound, this.volume, this.pitch);
            }

            if (this.title != null && this.subtitle != null) {
               player.sendTitle(this.title, this.subtitle, 10, 70, 20);
            }
            break;
         }
      }

   }
}
