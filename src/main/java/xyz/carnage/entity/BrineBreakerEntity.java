package xyz.carnage.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.ServerWorldAccess;
import xyz.carnage.entity.templates.ModTridentEntity;

import java.lang.reflect.Field;
import java.util.List;

public class BrineBreakerEntity extends TridentEntity {
    public BrineBreakerEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }
    public void setTridentAttributes(ItemStack stack) {
    }

    public BrineBreakerEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ()); // Add proper positioning
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    private byte getLoyalty(ItemStack stack) {
        World world = this.getWorld();
        if (world instanceof ServerWorld) {
            return (byte) MathHelper.clamp(
                    EnchantmentHelper.getTridentReturnAcceleration((ServerWorld) world, stack, this),
                    0,
                    127
            );
        }
        return 0;
    }
}