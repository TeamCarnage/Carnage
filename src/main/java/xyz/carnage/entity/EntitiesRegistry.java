package xyz.carnage.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;
import xyz.carnage.entity.customEntities.BrineBreakerEntity;
import xyz.carnage.entity.customEntities.WardlingEntity;

import java.util.jar.Attributes;

import static xyz.carnage.Carnage.MOD_ID;

public class EntitiesRegistry {
    public static final EntityType<BrineBreakerEntity> BRINEBREAKER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "brinebreaker"),
            EntityType.Builder.create(BrineBreakerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static final EntityType<WardlingEntity> WARDLING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "wardling"),
            EntityType.Builder.create(WardlingEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    private static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(WARDLING,
                WolfEntity.createWolfAttributes()
        );
    }

    public static void init() {
        Carnage.LOGGER.info("Initialising Entities!");
        registerEntityAttributes();
    }
}