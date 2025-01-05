package xyz.carnage.entity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.util.Rarity;
import xyz.carnage.itemmgmt.items.BrinebreakerItem;

import static xyz.carnage.Carnage.MOD_ID;

public class EntityManager implements {

    private static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(MOD_ID, "main")
    );


    public void initialiseEntities() {
        // Register item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.add((ItemConvertible) BrineBreakerEntity.BRINE_BREAKER_ENTITY_ENTITY_TYPE);
        });
        // Register the entity attributes
        Entity BrineBreakerEntity = new BrineBreakerEntity()
        FabricDefaultAttributeRegistry.register(, createTridentAttributes());
    }

    private static DefaultAttributeContainer createTridentAttributes() {
        return DefaultAttributeContainer.builder()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .build();
    }
}
