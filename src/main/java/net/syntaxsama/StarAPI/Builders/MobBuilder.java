package net.syntaxsama.StarAPI.Builders;

import net.syntaxsama.StarAPI.MessageUtils.MessageColors;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MobBuilder {
   private EntityType type;
   private Location location;
   private String customName;
   private double health;
   private List<ItemStack> armorContents = new ArrayList();
   private ItemStack heldItem;
   private List<PotionEffect> potionEffects = new ArrayList();
   private boolean showParticles = true;
   private boolean isBaby;

   private MobBuilder() {
   }

   public static MobBuilder create() {
      return new MobBuilder();
   }

   public MobBuilder type(EntityType type) {
      this.type = type;
      return this;
   }

   public MobBuilder location(Location location) {
      this.location = location;
      return this;
   }

   public MobBuilder customName(String customName) {
      this.customName = MessageColors.hex(customName);
      return this;
   }

   public MobBuilder health(double health) {
      return this;
   }

   public MobBuilder armorContents(Material... materials) {
      Material[] var2 = materials;
      int var3 = materials.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Material material = var2[var4];
         this.armorContents.add(new ItemStack(material));
      }

      return this;
   }

   public MobBuilder heldItem(Material material) {
      this.heldItem = new ItemStack(material);
      return this;
   }

   public MobBuilder showParticles(boolean showParticles) {
      this.showParticles = showParticles;
      return this;
   }

   public MobBuilder potionEffect(PotionEffectType type, int durationSeconds, int amplifier) {
      PotionEffect potionEffect = new PotionEffect(type, durationSeconds * 20, amplifier, false, this.showParticles);
      this.potionEffects.add(potionEffect);
      return this;
   }

   public MobBuilder isBaby(boolean isBaby) {
      this.isBaby = isBaby;
      return this;
   }

   public LivingEntity spawn() {
      this.validate();
      Entity spawnedEntity = this.location.getWorld().spawnEntity(this.location, this.type);
      if (!(spawnedEntity instanceof LivingEntity)) {
         throw new IllegalStateException("Spawned entity is not a LivingEntity");
      } else {
         LivingEntity mob = (LivingEntity)spawnedEntity;
         if (this.customName != null) {
            mob.setCustomName(this.customName);
            mob.setCustomNameVisible(true);
         }

         if (this.health > 0.0D) {
            mob.setHealth(this.health);
         }

         EntityEquipment equipment = mob.getEquipment();
         if (equipment != null) {
            int i = 0;

            for(Iterator var5 = this.armorContents.iterator(); var5.hasNext(); ++i) {
               ItemStack armor = (ItemStack)var5.next();
               equipment.setArmorContents((ItemStack[])this.armorContents.toArray(new ItemStack[0]));
            }
         }

         if (this.heldItem != null) {
            equipment.setItemInMainHand(this.heldItem);
         }

         Iterator var7 = this.potionEffects.iterator();

         while(var7.hasNext()) {
            PotionEffect effect = (PotionEffect)var7.next();
            mob.addPotionEffect(effect);
         }

         if (mob instanceof Ageable) {
            Ageable ageable = (Ageable)mob;
            ageable.setAdult();
            if (this.isBaby) {
               ageable.setBaby();
            }
         }

         return mob;
      }
   }

   private void validate() {
      if (this.type == null || this.location == null) {
         throw new IllegalStateException("Type and location must be set before spawning the mob.");
      }
   }
}
