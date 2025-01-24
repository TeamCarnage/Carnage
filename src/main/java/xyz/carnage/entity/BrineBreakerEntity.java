package xyz.carnage.entity;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.joml.Vector2f;
import xyz.carnage.itemmgmt.ModItems;

// I just followed a tutorial, most of this explains itself, I cant explain it if you dont udnerstand it (might change)
public class BrineBreakerEntity extends TridentEntity {
    private float rotation;
    public Vector2f groundOffset;

    public BrineBreakerEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }

    public BrineBreakerEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
    }

    public static void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.BRINEBREAKER);
    }

    public float getRenderRotation() {
        return 0;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 4);

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            this.discard();
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

    protected float getDragInWater() {
        return 1.0F;
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    private boolean enchanted = false;

    public boolean isEnchanted() {
        return enchanted;
    }
}