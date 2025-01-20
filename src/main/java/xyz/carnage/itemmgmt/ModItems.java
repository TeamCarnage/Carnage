package xyz.carnage.itemmgmt;

import xyz.carnage.Carnage;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import xyz.carnage.itemmgmt.items.*;

public final class ModItems {
    private ModItems() {
        // Prevent instantiation
    }

    public static final String MOD_ID = "carnage";

    public static final Item BLOOD_ESSENCE;
    public static final Item PHANTOMS_KISS;
    public static final Item BLAZERENDER;
    public static final Item BRINEBREAKER;
    public static final Item JUGGERNAUT;

    static {
        BLOOD_ESSENCE = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blood_essence"),
                new BloodEssenceItem(new Item.Settings()));

//        PHANTOMS_KISS = Registry.register(Registries.ITEM,
//                Identifier.of(Carnage.MOD_ID, "phantoms_kiss"),
//                new PhantomsKissItem(ModToolMaterials.PHANTOMS_KISS, new Item.Settings()));

        PHANTOMS_KISS = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "phantoms_kiss"),
                new PhantomsKissItem(ModToolMaterials.PHANTOMS_KISS,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.PHANTOMS_KISS, 5, -3.1f))));

        BLAZERENDER = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blazerender"),
                new BlazerenderItem(ModToolMaterials.BLAZERENDER,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.BLAZERENDER, 3, -2.3f))));
      
        BRINEBREAKER = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "brinebreaker"),                          // THIUS IS THE LOCAL TEMP CODE WORK WITH THIS :3
                new BrinebreakerItem(ModToolMaterials.BRINEBREAKER,
                        SwordItem.createAttributeModifiers(ModToolMaterials.BRINEBREAKER, 5, -2.0f)));

        JUGGERNAUT = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "juggernaut"),
                new JuggernautItem(ModToolMaterials.JUGGERNAUT,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.JUGGERNAUT, 0, -2.1f))));
                            }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }
}