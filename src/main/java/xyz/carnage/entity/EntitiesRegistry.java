package xyz.carnage.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;

public class EntitiesRegistry {
    public static final EntityType<BrineBreakerEntity> BRINEBREAKER;

    static {
        BRINEBREAKER = Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of("carnage", "brinebreaker"),
                FabricEntityTypeBuilder.<BrineBreakerEntity>create(SpawnGroup.MISC, BrineBreakerEntity::new)
                        .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                        .trackRangeBlocks(4)
                        .trackedUpdateRate(20)
                        .build()
        );
    }

    public static void init() {
        Carnage.LOGGER.info("Initialising Entities!");
    }
}