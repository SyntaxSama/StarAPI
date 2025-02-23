package net.syntaxsama.StarAPI.MessageUtils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageColors {
   public static String hex(String message) {
      Pattern hexPattern = Pattern.compile("(#|&#)([a-fA-F0-9]{6})");

      StringBuilder convertedColor;
      for(Matcher matcher = hexPattern.matcher(message); matcher.find(); message = message.replace(matcher.group(), convertedColor.toString())) {
         String hexCode = matcher.group(2);
         convertedColor = new StringBuilder("§x");
         char[] var5 = hexCode.toCharArray();
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            char c = var5[var7];
            convertedColor.append("§").append(c);
         }
      }

      Pattern legacyHexPattern = Pattern.compile("&x(&[a-fA-F0-9]){6}");

      String legacyHex;
      String minecraftHex;
      for(Matcher legacyMatcher = legacyHexPattern.matcher(message); legacyMatcher.find(); message = message.replace(legacyHex, minecraftHex)) {
         legacyHex = legacyMatcher.group(0);
         minecraftHex = legacyHex.replace("&", "§");
      }

      message = ChatColor.translateAlternateColorCodes('&', message);
      message = message.replace('&', '§');
      return message;
   }
}
