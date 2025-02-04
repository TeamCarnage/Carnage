package xyz.carnage.manager.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;

public class CarnageItemGroups {

    public static final RegistryKey<ItemGroup> CARNAGE_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(Carnage.MOD_ID, "carnage_items")
    );

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, CARNAGE_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(CarnageItems.BLOOD_ESSENCE))
                .displayName(Text.translatable("itemgroup.carnage"))
                .entries((context, entries) -> {
                    entries.add(CarnageItems.BLOOD_ESSENCE);
                    entries.add(CarnageItems.PHANTOMS_KISS);
                    entries.add(CarnageItems.BLAZERENDER);
                    entries.add(CarnageItems.BRINEBREAKER);
                    entries.add(CarnageItems.SURGE);
                    entries.add(CarnageItems.ECHOING_TWINBLADE);
                    //  entries.add(CarnageItems.ITEM);
                    //  Add other items here...
                })
                .build()
        );
    }

    public void initialize() {
        registerItemGroups();
    }
}