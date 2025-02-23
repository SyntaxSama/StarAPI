package net.syntaxsama.StarAPI.Builders;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class PotionBuilder {

    private final ItemStack potion;
    private final PotionMeta meta;
    private Consumer<Player> customEffect;
    private static final Map<UUID, Consumer<Player>> potionEffects = new HashMap<>();

    public PotionBuilder() {
        this.potion = new ItemStack(Material.POTION);
        this.meta = (PotionMeta) potion.getItemMeta();
    }

    public PotionBuilder setName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public PotionBuilder setColor(Color color) {
        meta.setColor(color);
        return this;
    }

    public PotionBuilder addEffect(PotionEffectType type, int duration, int amplifier) {
        meta.addCustomEffect(new PotionEffect(type, duration * 20, amplifier), true);
        return this;
    }

    public PotionBuilder setCustomEffect(Consumer<Player> effect) {
        this.customEffect = effect;
        return this;
    }

    public ItemStack build(Player player) {
        potion.setItemMeta(meta);

        if (customEffect != null) {
            registerPotion(player, customEffect);
        }

        return potion;
    }

    public static void registerPotion(Player player, Consumer<Player> effect) {
        potionEffects.put(player.getUniqueId(), effect);
    }

    public static void applyEffectIfDrank(Player player) {
        Consumer<Player> effect = potionEffects.remove(player.getUniqueId());
        if (effect != null) {
            effect.accept(player);
        }
    }
}
