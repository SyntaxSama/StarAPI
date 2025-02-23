package net.syntaxsama.StarAPI.Builders;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class CommandBuilder {
   private final JavaPlugin plugin;
   private String commandName;
   private final List<String> aliases;
   private int cooldown;
   private String permission;
   private String[] argumentNames;
   private Function<String[], String> argumentMapper;
   private Function<CommandSender, Void> commandFunction;
   private String[] messages;

   private CommandBuilder(JavaPlugin plugin) {
      this.plugin = plugin;
      this.aliases = new ArrayList();
   }

   public static CommandBuilder create(JavaPlugin plugin, String commandName) {
      return (new CommandBuilder(plugin)).setCommandName(commandName);
   }

   public CommandBuilder setCooldown(int cooldown) {
      this.cooldown = cooldown;
      return this;
   }

   public CommandBuilder setPermission(String permission) {
      this.permission = permission;
      return this;
   }

   public CommandBuilder setArguments(String... argumentNames) {
      this.argumentNames = argumentNames;
      return this;
   }

   public CommandBuilder setArgumentMapper(Function<String[], String> argumentMapper) {
      this.argumentMapper = argumentMapper;
      return this;
   }

   public CommandBuilder setCommandFunction(Function<CommandSender, Void> commandFunction) {
      this.commandFunction = commandFunction;
      return this;
   }

   public CommandBuilder setMessages(String... messages) {
      this.messages = messages;
      return this;
   }

   public CommandBuilder addAliases(String... aliases) {
      String[] var2 = aliases;
      int var3 = aliases.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String alias = var2[var4];
         this.aliases.add(alias);
      }

      return this;
   }

   public void build() {
      this.plugin.getCommand(this.commandName).setExecutor(this.createExecutor());
      Iterator var1 = this.aliases.iterator();

      while(var1.hasNext()) {
         String alias = (String)var1.next();
         this.plugin.getCommand(alias).setExecutor(this.createExecutor());
      }

   }

   private CommandExecutor createExecutor() {
      return new CommandExecutor() {
         public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (CommandBuilder.this.permission != null && !sender.hasPermission(CommandBuilder.this.permission)) {
               sender.sendMessage(CommandBuilder.this.messages[1]);
               return true;
            } else {
               if (CommandBuilder.this.cooldown > 0 && sender instanceof Player) {
                  Player player = (Player)sender;
                  if (player.hasMetadata("cooldown_" + CommandBuilder.this.commandName)) {
                     long lastExecutionTime = ((MetadataValue)player.getMetadata("cooldown_" + CommandBuilder.this.commandName).get(0)).asLong();
                     long currentTime = System.currentTimeMillis();
                     long timeDifference = currentTime - lastExecutionTime;
                     if (timeDifference < (long)(CommandBuilder.this.cooldown * 1000)) {
                        long remainingCooldown = (long)CommandBuilder.this.cooldown - timeDifference / 1000L;
                        sender.sendMessage(CommandBuilder.this.messages[0].replace("<time>", String.valueOf(remainingCooldown)));
                        return true;
                     }
                  }

                  player.setMetadata("cooldown_" + CommandBuilder.this.commandName, new FixedMetadataValue(CommandBuilder.this.plugin, System.currentTimeMillis()));
               }

               String result = CommandBuilder.this.argumentMapper != null ? (String)CommandBuilder.this.argumentMapper.apply(args) : null;
               if (result != null) {
                  sender.sendMessage(result.replace("<sender>", sender.getName()));
               }

               if (CommandBuilder.this.commandFunction != null) {
                  CommandBuilder.this.commandFunction.apply(sender);
               }

               return true;
            }
         }
      };
   }

   private CommandBuilder setCommandName(String commandName) {
      this.commandName = commandName;
      return this;
   }
}
