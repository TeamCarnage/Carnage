
package xyz.carnage.itemmgmt.items;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.carnage.CustomParticles;
import xyz.carnage.CustomSounds;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;

import static xyz.carnage.Carnage.MOD_ID;
import static xyz.carnage.CustomSounds.SOUND_EVENTS;


public class SurgeItem extends SwordItem {

    public SurgeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            // Generate a random float between 0.0 and 1.0
            World world = attacker.getWorld();

            ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
            if (tracker.getComboCount()/2 >= 15) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 150, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 150, 0)); // this knife can and will zap, maybe cap if you will :3 - tman
                CustomSounds.playSound(null,player,"surge_discharge",SoundCategory.PLAYERS,1.0F,1.0F);
                tracker.reset();
            }
            tracker.clearHitFlag();
            }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            // Set a cooldown for the item
            player.getItemCooldownManager().set(this, 50); // Cooldown: 400 ticks (20 seconds)

            // Add effects to the player
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 300, 0));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 2));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 0));
            world.playSound(null,player.getX(),player.getY(),player.getZ(),SOUND_EVENTS.get(Identifier.of(MOD_ID, "surge_discharge")),SoundCategory.PLAYERS,1.0F,1.0F);

            // Create and spawn a LightningEntity at the player's position
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world); // Create a lightning entity
            if (lightning != null) {
                lightning.setPos(player.getX(), player.getY(), player.getZ()); // Set the lightning's position
                world.spawnEntity(lightning); // Spawn the lightning in the world
            }
        }

        return TypedActionResult.success(stack);

    }
}

// YES i used gpt to finalise this class, YES im too stupid for coding - tfemboy