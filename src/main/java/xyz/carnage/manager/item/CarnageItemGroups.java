package xyz.carnage.manager.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;

import java.util.ArrayList;
import java.util.List;

import static xyz.carnage.Carnage.MOD_ID;

public class CarnageItemGroups {
    public static List<Item> vanillaWeapons = new ArrayList<>();

    public static final RegistryKey<ItemGroup> CARNAGE_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(MOD_ID, "carnage_items")
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

    public static void initialiseTriggerableItems() {
            for (Item item : Registries.ITEM) {
                if (item instanceof SwordItem ||
                    item instanceof AxeItem ||
                    item instanceof BowItem ||
                    item instanceof CrossbowItem ||
                    item instanceof TridentItem ||
                    item instanceof ShieldItem ||
                    item.getName().contains(Text.of(Carnage.MOD_ID)))
                {
                    vanillaWeapons.add(item);
                }
                if (item instanceof MaceItem) {
                    vanillaWeapons.add(item);
                }
            }

    }

    public static List<Item> getTriggerableItems() {
        return vanillaWeapons;
    }
    public static Boolean isItemTriggerable(Item item) {
        return getTriggerableItems().contains(item);
    }

    public void initialize() {
        registerItemGroups();
        initialiseTriggerableItems();
    }
}