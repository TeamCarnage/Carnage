package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class BlazerenderItem extends SwordItem {
    private static final double PUSH_RADIUS = 10.0; // Blocks around
    private static final double PUSH_STRENGTH = 1.5;
    private static final int PARTICLE_COUNT = 50; // Number of particles to spawn

    public BlazerenderItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        // Spawn particles in a circle
        if (world.isClient) {
            spawnRadiusParticles(world, player);
        }

        if (!world.isClient) {
            // Add original effects
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));

            // Create a box around the player to find nearby entities
            Box searchBox = new Box(
                    player.getX() - PUSH_RADIUS,
                    player.getY() - PUSH_RADIUS,
                    player.getZ() - PUSH_RADIUS,
                    player.getX() + PUSH_RADIUS,
                    player.getY() + PUSH_RADIUS,
                    player.getZ() + PUSH_RADIUS
            );

            // Get all entities within the radius
            List<Entity> nearbyEntities = world.getEntitiesByClass(
                    Entity.class,
                    searchBox,
                    entity -> entity != player
            );

            // Push each entity away from the player
            for (Entity entity : nearbyEntities) {
                Vec3d pushDirection = entity.getPos().subtract(player.getPos()).normalize();
                entity.addVelocity(
                        pushDirection.x * PUSH_STRENGTH,
                        0.5,
                        pushDirection.z * PUSH_STRENGTH
                );
                entity.velocityModified = true;
            }
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS,
                0.5f, 1.2f);

        // Set Cooldown
        //player.getItemCooldownManager().set(this, 400);

        return TypedActionResult.success(stack);
    }

    private void spawnRadiusParticles(World world, PlayerEntity player) {
        // Spawn particles in a circle around the player
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            double angle = (2 * Math.PI * i) / PARTICLE_COUNT;
            double x = player.getX() + PUSH_RADIUS * Math.cos(angle);
            double z = player.getZ() + PUSH_RADIUS * Math.sin(angle);

            // Spawn particles at different heights
            for (double y = 0; y < 2; y += 0.5) {
                world.addParticle(
                        ParticleTypes.FLAME, // You can change this to other particle types
                        x,
                        player.getY() + y,
                        z,
                        0.0, // X velocity
                        0.0, // Y velocity
                        0.0  // Z velocity
                );
            }
        }
    }
}