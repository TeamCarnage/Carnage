package xyz.carnage.manager.entity.brinebreaker;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.joml.Vector2f;
import xyz.carnage.manager.combo.ComboManager;
import xyz.carnage.manager.combo.ComboTracker;


public class BrinebreakerEntity extends TridentEntity {

    public Vector2f groundOffset;

    public BrinebreakerEntity(EntityType<? extends BrinebreakerEntity> entityType, World world) {
        super(entityType, world);

    }

    public BrinebreakerEntity(World world, double x, double y, double z, ItemStack itemStack) {
        super(world, x, y, z, itemStack);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(Items.AIR);
    }

    private int age = 0;

    @Override
    public void tick() {
        super.tick();
        if (this.inGround) {
            age = age + 1;
            if (age > 20) {
                this.kill();

            }
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);

        if (!this.getWorld().isClient) {
            // sound
            this.getWorld().playSound(
                    null,
                    this.getBlockPos(),
                    SoundEvents.ENTITY_ENDER_EYE_DEATH,
                    net.minecraft.sound.SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );

            // particles
            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.TRIAL_SPAWNER_DETECTION_OMINOUS,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    20,
                    0.5,
                    0.5,
                    0.5,
                    0.1
            );
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        // Apply damage
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 6);

        // Handle combo tracking if the owner is a player
        if (this.getOwner() instanceof PlayerEntity playerEntity) {
            ComboTracker tracker = ComboManager.getComboTracker(playerEntity);
            tracker.hit();  // This is the key line we were missing!

            if (tracker.getComboCount() / 2 >= 5) {
                tracker.reset();
            }
            tracker.clearHitFlag();
        }

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
        }
    }





    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        if (result.getSide() == Direction.SOUTH) {
            groundOffset = new Vector2f(215f, 180f);
        }
        if (result.getSide() == Direction.NORTH) {
            groundOffset = new Vector2f(215f, 0f);
        }
        if (result.getSide() == Direction.EAST) {
            groundOffset = new Vector2f(215f, -90f);
        }
        if (result.getSide() == Direction.WEST) {
            groundOffset = new Vector2f(215f, 90f);
        }

        if (result.getSide() == Direction.DOWN) {
            groundOffset = new Vector2f(115f, 180f);
        }
        if (result.getSide() == Direction.UP) {
            groundOffset = new Vector2f(285f, 180f);
        }
    }
}