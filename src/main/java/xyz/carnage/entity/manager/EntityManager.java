package xyz.carnage.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityManager {
    private final String MOD_ID;

    public EntityManager(String modId) {
        this.MOD_ID = modId;
    }

    /**
     * Creates a new entity type builder
     */
    public <T extends Entity> FabricEntityTypeBuilder<T> createEntityType(
            EntityType.EntityFactory<T> factory,
            SpawnGroup spawnGroup,
            float width,
            float height,
            int trackRange,
            int updateRate
    ) {
        return FabricEntityTypeBuilder
                .<T>create(spawnGroup, factory)
                .dimensions(EntityDimensions.fixed(width, height))
                .trackRangeBlocks(trackRange)
                .trackedUpdateRate(updateRate);
    }

    /**
     * Registers an entity type using the provided builder
     */
    public <T extends Entity> EntityType<T> register(
            String path,
            FabricEntityTypeBuilder<T> builder
    ) {
        return Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of(MOD_ID, path),
                builder.build()
        );
    }
}