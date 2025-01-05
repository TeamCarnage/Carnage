package xyz.carnage.entity;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BrineBreakerEntity extends TridentEntity {
    public BrineBreakerEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }

    public BrineBreakerEntity(World world, LivingEntity owner, ItemStack stack) {
        super(EntityType.TRIDENT, world);
        this.setOwner(owner);
        this.setStack(stack);
    }

    public static final EntityType<BrineBreakerEntity> BRINE_BREAKER_ENTITY_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("carnage", "brinebreaker"),
            FabricEntityTypeBuilder.<BrineBreakerEntity>create(SpawnGroup.MISC, BrineBreakerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .trackRangeBlocks(4).trackedUpdateRate(20)
                    .build()
    );

}