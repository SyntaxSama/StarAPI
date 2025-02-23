package net.syntaxsama.StarAPI.Misc;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Rarities {

    public enum Rarity {
        COMMON(MessageColors.hex("&f&lCOMMON")),
        UNCOMMON(MessageColors.hex("&a&lUNCOMMON")),
        RARE(MessageColors.hex("&9&lRARE")),
        EPIC(MessageColors.hex("&5&lEPIC")),
        LEGENDARY(MessageColors.hex("&6&lLEGENDARY")),
        MYTHIC(MessageColors.hex("&d&lMYTHIC")),
        DIVINE(MessageColors.hex("&b&lDIVINE")),
        COSMIC(MessageColors.hex("&#C444BB&lC&#B744C9&lO&#AA44D6&lS&#9E45E4&lM&#9145F1&lI&#8445FF&lC"));

        private final String displayName;

        Rarity(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public static void addRarityToItem(ItemStack item, Rarity rarity) {
        if (item == null || !item.hasItemMeta()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.getLore();

            if (lore == null) {
                lore = new ArrayList<>();
            }

            lore.removeIf(line -> line.startsWith("§7Rarity:"));

            lore.add("");
            lore.add(rarity.getDisplayName());

            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }
}
