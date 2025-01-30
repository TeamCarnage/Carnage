package xyz.carnage.entity.customEntities;


import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import net.minecraft.entity.projectile.TridentEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;

import net.minecraft.world.World;
import org.joml.Vector2f;
import xyz.carnage.Carnage;


public class BrineBreakerEntity extends TridentEntity {


    public Vector2f groundOffset;
    private boolean dealtDamage;
    private World level;

    public BrineBreakerEntity(EntityType<? extends BrineBreakerEntity> entityType, World world) {
        super(entityType, world);

    }

    public BrineBreakerEntity(World world, double x, double y, double z, ItemStack itemStack) {
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
            if (age > 20) { // 20 ticks are 20 sec i think
                this.kill();

            }
        Carnage.LOGGER.info("Age value currently reflects {}", age);
        }
    }



    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 4);

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