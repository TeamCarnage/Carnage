package xyz.carnage.itemmgmt;

import xyz.carnage.Carnage;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import xyz.carnage.itemmgmt.items.BlazerenderItem;
import xyz.carnage.itemmgmt.items.BloodEssenceItem;
import xyz.carnage.itemmgmt.items.BrinebreakerItem;
import xyz.carnage.itemmgmt.items.PhantomsKissItem;

public final class ModItems {
    private ModItems() {
        // Prevent instantiation
    }

    public static final String MOD_ID = "carnage";

    public static final Item BLOOD_ESSENCE;
    public static final Item PHANTOMS_KISS;
    public static final Item BLAZERENDER;
    public static final Item BRINEBREAKER;

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
                                SwordItem.createAttributeModifiers(ModToolMaterials.BLAZERENDER,3,-2.3f))));
      
// start of origin:binebreaker
      
        //BRINEBREAKER = Registry.register(Registries.ITEM,
                //Identifier.of(Carnage.MOD_ID, "brinebreaker"),            // this is mine and tmans bodged code! 
                //new BrinebreakerItem(ModToolMaterials.BRINEBREAKER,       // commenting to keep it if needed.
                        //new Item.Settings().attributeModifiers(           //    -diaduck
                                //TridentItem.createAttributeModifiers())));

      
      //BRINEBREAKER = Registry.register(Registries.ITEM,
               //Identifier.of(Carnage.MOD_ID, "brinebreaker"),             // this is your original code!! uncomment to work on it :)
                //new BrinebreakerItem(ModToolMaterials.BRINEBREAKER,       //   -diaduck
                        //SwordItem.createAttributeModifiers(ModToolMaterials.BRINEBREAKER, 5, -2.0f)));

      
// end of origin:binebreaker
    }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }
}