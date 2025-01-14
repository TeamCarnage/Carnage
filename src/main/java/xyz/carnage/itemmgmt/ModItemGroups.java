package xyz.carnage.itemmgmt;

import xyz.carnage.Carnage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> CARNAGE_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(Carnage.MOD_ID, "carnage_items")
    );

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, CARNAGE_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModItems.BLOOD_ESSENCE))
                .displayName(Text.translatable("itemgroup.carnage"))
                .entries((context, entries) -> {
                    entries.add(ModItems.BLOOD_ESSENCE);
                    entries.add(ModItems.PHANTOMS_KISS);
                    entries.add(ModItems.BLAZERENDER);
                    entries.add(ModItems.BRINEBREAKER);
                    entries.add(ModItems.JUGGERNAUT);
                //  entries.add(ModItems.ITEM);
                //  Add other items here...
                })
                .build()
        );
    }
}