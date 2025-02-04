package xyz.carnage.manager.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;
import xyz.carnage.manager.entity.brinebreaker.BrinebreakerEntity;
import xyz.carnage.manager.entity.wardling.WardlingEntity;
import static xyz.carnage.Carnage.MOD_ID;

public class EntitiesRegistry {
    public static final EntityType<BrinebreakerEntity> BRINEBREAKER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Carnage.MOD_ID, "brinebreaker"),
            EntityType.Builder.<BrinebreakerEntity>create(BrinebreakerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static final EntityType<WardlingEntity> WARDLING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "wardling"),
            EntityType.Builder.create(WardlingEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.5f, 1.15f).build());

    private static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(WARDLING,
                WolfEntity.createWolfAttributes()
        );
    }

    public static void initialize() {
        Carnage.LOGGER.info("Initialising Entities!");
        registerEntityAttributes();
    }
}