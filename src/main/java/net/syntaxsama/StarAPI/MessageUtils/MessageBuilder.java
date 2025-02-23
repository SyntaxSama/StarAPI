package net.syntaxsama.StarAPI.MessageUtils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.EntityType;

public class MessageBuilder {
   public static TextComponent buildHoverText(TextComponent baseComponent, String hoverText) {
      baseComponent.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new TextComponent[]{new TextComponent(hoverText)}));
      return baseComponent;
   }

   public static TextComponent buildCommandText(String text, String clickCommand) {
      TextComponent component = new TextComponent(text);
      component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickCommand));
      return component;
   }

   public static TextComponent buildEntityText(String text, EntityType entityType) {
      TextComponent component = new TextComponent(text);
      component.setHoverEvent(new HoverEvent(Action.SHOW_ENTITY, new BaseComponent[]{new TextComponent("{id:\"minecraft:" + entityType.getKey().getKey() + "\"}")}));
      return component;
   }

   public static TextComponent buildOpenUrlText(String text, String url) {
      TextComponent component = new TextComponent(text);
      component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
      return component;
   }
}
