package net.dman.carnage.item;

import net.dman.carnage.Carnage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ModItems {
    private ModItems() {}

    // Declare the item but don't register it yet
    public static final Item PHANTOMS_KISS;

    static {
        // Register items in static block
        PHANTOMS_KISS = Registry.register(
                Registries.ITEM,
                Identifier.of(Carnage.MOD_ID, "phantoms_kiss"),
                new Item(new Item.Settings())
        );
    }


    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);

    }
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Carnage.MOD_ID, "carnage_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.PHANTOMS_KISS))
            .displayName(Text.translatable("itemGroup.carnage"))
            .build();
}