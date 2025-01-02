package xyz.carnage.itemmgmt;

import xyz.carnage.Carnage;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import xyz.carnage.itemmgmt.items.BlazerenderItem;
import xyz.carnage.itemmgmt.items.BloodEssenceItem;
import xyz.carnage.itemmgmt.items.PhantomsKissItem;

public final class ModItems {
    private ModItems() {
        // return null;
    }

    public static final Item BLOOD_ESSENCE; // Declare
    public static final Item PHANTOMS_KISS; // Declare
    public static final Item BLAZERENDER;

    static { // Register all items in ONE static block
        BLOOD_ESSENCE = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blood_essence"),
                new BloodEssenceItem(new Item.Settings()));

        PHANTOMS_KISS = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "phantoms_kiss"),
                new PhantomsKissItem(ModToolMaterials.PHANTOMS_KISS, new Item.Settings()));

        BLAZERENDER = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blazerender"),
                new BlazerenderItem(ModToolMaterials.BLAZERENDER, new Item.Settings()));
    }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }

}
