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
    public static final EntityType<BrineBreakerEntity> BRINEBREAKER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Carnage.MOD_ID, "brinebreaker"),
            EntityType.Builder.<BrineBreakerEntity>create(BrineBreakerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static void init() {
        Carnage.LOGGER.info("Initialising Entities!");
    }
}