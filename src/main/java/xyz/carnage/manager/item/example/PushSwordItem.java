package xyz.carnage.manager.item.example;

import foundry.veil.api.quasar.data.module.force.PointAttractorForceData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.carnage.manager.sound.SoundManager;
import xyz.carnage.manager.item.CoolDownManager;

import java.util.List;

public abstract class PushSwordItem extends SwordItem {
    private final double pushRadius;
    private final double pushStrength;
    private final double upwardForce;
    private final int cooldownTicks;
    private final int particleCount;
    private final ParticleEffect particleType;
    private final SoundEvent useSound;
    private final float soundVolume;
    private final float soundPitch;

    // The constructor now accepts a ToolMaterial to allow enchantments
    protected PushSwordItem(ToolMaterial toolMaterial, Settings settings, PushableItemSettings pushSettings) {
        super(toolMaterial, settings);  // Only pass ToolMaterial and Settings
        this.pushRadius = pushSettings.pushRadius;
        this.pushStrength = pushSettings.pushStrength;
        this.upwardForce = pushSettings.upwardForce;
        this.cooldownTicks = pushSettings.cooldownTicks;
        this.particleCount = pushSettings.particleCount;
        this.particleType = pushSettings.particleType;
        this.useSound = pushSettings.useSound;
        this.soundVolume = pushSettings.soundVolume;
        this.soundPitch = pushSettings.soundPitch;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient) spawnRadiusParticles(world, player);
        if (!world.isClient) handlePushEffect(world, player);

        // Play the item's sound effect
        SoundManager.playVanillaSound(useSound, player, SoundCategory.PLAYERS, soundVolume, soundPitch);

        // Sets the cooldown for the item
        CoolDownManager.setItemCooldown(player, this, cooldownTicks);

        return TypedActionResult.success(stack);
    }

    private void handlePushEffect(World world, PlayerEntity player) {
        Box searchBox = new Box(
                player.getX() - pushRadius,
                player.getY() - pushRadius,
                player.getZ() - pushRadius,
                player.getX() + pushRadius,
                player.getY() + pushRadius,
                player.getZ() + pushRadius
        );

        List<Entity> nearbyEntities = world.getEntitiesByClass(
                Entity.class,
                searchBox,
                entity -> entity != player
        );

        for (Entity entity : nearbyEntities) {
            Vec3d pushDirection = entity.getPos().subtract(player.getPos()).normalize();
            entity.addVelocity(
                    pushDirection.x * pushStrength,
                    upwardForce,
                    pushDirection.z * pushStrength
            );
            entity.velocityModified = true;
        }
    }

    private void spawnRadiusParticles(World world, PlayerEntity player) {
        for (int i = 0; i < particleCount; i++) {
            double angle = (2 * Math.PI * i) / particleCount;
            double x = player.getX() + pushRadius * Math.cos(angle);
            double z = player.getZ() + pushRadius * Math.sin(angle);

            for (double y = 0; y < 2; y += 0.5) {
                world.addParticle(
                        particleType,
                        x,
                        player.getY() + y,
                        z,
                        0.0,
                        0.0,
                        0.0
                );
            }
        }
    }

    public static class PushableItemSettings {
        private double pushRadius = 5.0;
        private double pushStrength = 1.5;
        private double upwardForce = 0.5;
        private int cooldownTicks = 400;
        private int particleCount = 50;
        private ParticleEffect particleType = ParticleTypes.FLAME;
        private SoundEvent useSound = SoundEvents.ENTITY_BLAZE_SHOOT;
        private float soundVolume = 0.5f;
        private float soundPitch = 1.2f;

        // Setters for builder pattern
        public PushableItemSettings setPushRadius(double radius) {
            this.pushRadius = radius;
            return this;
        }

        public PushableItemSettings setPushStrength(double strength) {
            this.pushStrength = strength;
            return this;
        }

        public PushableItemSettings setUpwardForce(double force) {
            this.upwardForce = force;
            return this;
        }

        public PushableItemSettings setCooldownTicks(int ticks) {
            this.cooldownTicks = ticks;
            return this;
        }

        public PushableItemSettings setParticleCount(int count) {
            this.particleCount = count;
            return this;
        }

        public PushableItemSettings setParticleType(ParticleEffect type) {
            this.particleType = type;
            return this;
        }

        public PushableItemSettings setUseSound(SoundEvent sound) {
            this.useSound = sound;
            return this;
        }

        public PushableItemSettings setSoundVolume(float volume) {
            this.soundVolume = volume;
            return this;
        }

        public PushableItemSettings setSoundPitch(float pitch) {
            this.soundPitch = pitch;
            return this;
        }


    }
}
