package xyz.carnage.manager.item;

import net.minecraft.util.Rarity;
import xyz.carnage.Carnage;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.carnage.manager.item.customItem.blazeRender.BlazerenderItem;
import xyz.carnage.manager.item.customItem.bloodEssence.BloodEssenceItem;
import xyz.carnage.manager.item.customItem.echoingTwinBlade.EchoingTwinbladeItem;
import xyz.carnage.manager.item.customItem.phantomsKiss.PhantomsKissItem;
import xyz.carnage.manager.item.customItem.surge.SurgeItem;
import xyz.carnage.manager.item.customItem.brineBreaker.BrinebreakerItem;

import static xyz.carnage.Carnage.MOD_ID;

public final class CarnageItems {
    public CarnageItems() {
        // Prevent instantiation
    }
    public static final Item BLOOD_ESSENCE;
    public static final Item PHANTOMS_KISS;
    public static final Item BLAZERENDER;
    public static final Item BRINEBREAKER;
    public static final Item SURGE;
    public static final Item ECHOING_TWINBLADE;

    static {
        BLOOD_ESSENCE = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "blood_essence"),
                new BloodEssenceItem(new Item.Settings()));

        PHANTOMS_KISS = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "phantoms_kiss"),
                new PhantomsKissItem(CarnageToolMaterials.PHANTOMS_KISS,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(CarnageToolMaterials.PHANTOMS_KISS, 5, -3.1f))));

        BLAZERENDER = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "blazerender"),
                new BlazerenderItem(CarnageToolMaterials.BLAZERENDER,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(CarnageToolMaterials.BLAZERENDER, 3, -2.3f))));

        BRINEBREAKER = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "brinebreaker"),
                new BrinebreakerItem(
                        new Item.Settings().maxDamage(CarnageToolMaterials.BRINEBREAKER.getDurability())
                                .rarity(Rarity.RARE)));

        SURGE = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "surge"),
                new SurgeItem(CarnageToolMaterials.SURGE,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(CarnageToolMaterials.SURGE, 1, -1.8f))));

        ECHOING_TWINBLADE = Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "echoing_twinblade"),
                new EchoingTwinbladeItem(CarnageToolMaterials.ECHOING_TWINBLADE,
                        new Item.Settings().attributeModifiers(
                                SwordItem.createAttributeModifiers(CarnageToolMaterials.ECHOING_TWINBLADE, 3, -2.4f))));
    }

    public void initialize() {
        Carnage.LOGGER.info("Registering items for " + MOD_ID);
    }
}