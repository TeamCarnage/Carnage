package xyz.carnage.items;

import xyz.carnage.Carnage;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItems {
    private ModItems() {
    }

    // Declare the item but don't register it yet
    public static final Item BLOOD_ESSENCE;

    static {BLOOD_ESSENCE = Registry.register(Registries.ITEM, Identifier.of(Carnage.MOD_ID, "blood_essence"), new Item(new Item.Settings()));}

    public static final Item PHANTOMS_KISS;

    static {PHANTOMS_KISS = Registry.register(Registries.ITEM, Identifier.of(Carnage.MOD_ID, "phantoms_kiss"),
             new SwordItem(ModToolMaterials.PHANTOMS_KISS, new Item.Settings()
                     .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PHANTOMS_KISS, 5, -3.1f))));}

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }

}
