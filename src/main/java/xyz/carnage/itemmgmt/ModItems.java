package xyz.carnage.itemmgmt;

import net.minecraft.util.Rarity;
import xyz.carnage.Carnage;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.carnage.itemmgmt.items.*;

import static xyz.carnage.Carnage.MOD_ID;

public final class ModItems {
    private ModItems() {
        // Prevent instantiation
    }
    public static final Item BLOOD_ESSENCE;
    public static final Item PHANTOMS_KISS;
    public static final Item BLAZERENDER;
    public static final Item BRINEBREAKER;
    public static final Item SURGE;

    static {
        BLOOD_ESSENCE = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "blood_essence"),
                new BloodEssenceItem(new Item.Settings()));

        PHANTOMS_KISS = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "phantoms_kiss"),
                new PhantomsKissItem(ModToolMaterials.PHANTOMS_KISS,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.PHANTOMS_KISS, 5, -3.1f))));

        BLAZERENDER = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "blazerender"),
                new BlazerenderItem(ModToolMaterials.BLAZERENDER,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.BLAZERENDER, 3, -2.3f))));
      
        BRINEBREAKER = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "brinebreaker"),                          // THIUS IS THE LOCAL TEMP CODE WORK WITH THIS :3
                new BrinebreakerItem(
                        new Item.Settings().maxDamage(ModToolMaterials.BRINEBREAKER.getDurability())
                                .rarity(Rarity.RARE)));

        SURGE = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "surge"),
                new SurgeItem(ModToolMaterials.BLAZERENDER,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(ModToolMaterials.SURGE, 3, -2.3f))));
    }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + MOD_ID);
    }
}