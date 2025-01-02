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

    static {
        // Register items in static block
        BLOOD_ESSENCE = Registry.register(
                Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blood_essence"),
                new Item(new Item.Settings())
        );
    }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }

}
