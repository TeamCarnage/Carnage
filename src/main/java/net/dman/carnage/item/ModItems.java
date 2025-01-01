package net.dman.carnage.item;

import net.dman.carnage.Carnage;
import net.dman.carnage.CarnageItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BLOOD_ESSENCE = registerItem("blood_essence", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Carnage.MOD_ID, name), item);
    }

    public static  void  registerModItems() {
        Carnage.LOGGER.info("Registering Items" + Carnage.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BLOOD_ESSENCE);
        });
    }

    public static void initialize() {
    }
}
