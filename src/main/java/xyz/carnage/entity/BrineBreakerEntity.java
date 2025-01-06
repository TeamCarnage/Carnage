package xyz.carnage.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.ServerWorldAccess;

import java.lang.reflect.Field;
import java.util.List;

public class BrineBreakerEntity extends TridentEntity {
    private static final TrackedData<Byte> LOYALTY = DataTracker.registerData(BrineBreakerEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(BrineBreakerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public BrineBreakerEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }

    public BrineBreakerEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);

        // Set the tracked data values
        this.dataTracker.set(LOYALTY, this.getLoyalty(stack));
        this.dataTracker.set(ENCHANTED, stack.hasGlint());
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);

        // Register the tracked data fields
        builder.add(LOYALTY, (byte) 0);
        builder.add(ENCHANTED, false);
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