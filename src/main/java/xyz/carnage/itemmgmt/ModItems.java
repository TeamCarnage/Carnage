package xyz.carnage.itemmgmt;

import net.minecraft.util.Rarity;
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
    public static final Item SURGE;

    static {
        BLOOD_ESSENCE = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blood_essence"),
                new BloodEssenceItem(new Item.Settings()));

        PHANTOMS_KISS = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "phantoms_kiss"),
                new PhantomsKissItem(ModToolMaterials.PHANTOMS_KISS,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.PHANTOMS_KISS, 5, -3.1f))));
        new Item.Settings().rarity(Rarity.EPIC);

        BLAZERENDER = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "blazerender"),
                new BlazerenderItem(ModToolMaterials.BLAZERENDER,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.BLAZERENDER, 3, -2.3f))));
        new Item.Settings().rarity(Rarity.EPIC);
      
        BRINEBREAKER = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "brinebreaker"),                          // THIUS IS THE LOCAL TEMP CODE WORK WITH THIS :3
                new BrinebreakerItem(ModToolMaterials.BRINEBREAKER,
                        SwordItem.createAttributeModifiers(ModToolMaterials.BRINEBREAKER, 5, -2.0f)));

        SURGE = Registry.register(Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "surge"),
                new SurgeItem(ModToolMaterials.SURGE,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.SURGE, 0, -2.1f))));
//        new Item.Settings().rarity(Rarity.EPIC); <- Sets rarity to EPIC (Like the ender dragon egg)
    }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }
}