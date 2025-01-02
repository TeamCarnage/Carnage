package xyz.carnage.items;

import xyz.carnage.Carnage;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItems {
    private ModItems() {
    }

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

    // Are you sure you want to put every item into one file?
    // Why not make multiple class files in the xyz.carnage.items package?
    // -DiaDuck

    public class PhantomToolMaterial implements ToolMaterial {

        public int getDurability() { return 4096; }
        //            Durability = 4096
        public int getEnchantability() { return 15; }
        //            Enchantability = 15

        public float getMiningSpeedMultiplier() { return 0.0f; }
        //              Mining Speed Multiplier = 0.0
        public float getAttackDamage() { return 10.0f; }
        //              Attack Damage = 10.0

        // nulls:
        public TagKey<Block> getInverseTag() { return null; }
        public Ingredient getRepairIngredient() { return null; }

    }

    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);
    }

}
