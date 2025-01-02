package net.dman.carnage;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public final class CarnageItems {

    private CarnageItems() {}

    // an instance of our new item
    public static final Item BLOOD_ESSENCE = register("blood_essence", new Item(new Item.Settings()));

    public static <T extends Item> T register(String path, T item) {
        // For versions below 1.21, please replace ''Identifier.of'' with ''new Identifier''
        return Registry.register(Registries.ITEM, Identifier.of("Carnage", path), item);
    }

    public static void initialize() {
    }
    public static class Carnage implements ModInitializer {
        @Override
        public void onInitialize() {
            CarnageItems.initialize();
        }
    }
}

// This Code is basically useless but some stuff relies on it ill leave it here cuz I don't know how to safely delete it.