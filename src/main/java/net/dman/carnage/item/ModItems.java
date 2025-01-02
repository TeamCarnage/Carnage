package net.dman.carnage.item;

import net.dman.carnage.Carnage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
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



    public class PhantomToolMaterial implements ToolMaterial {
        @Override
        public int getDurability() {
            return 4096;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 10.0f;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return null;
        }

        @Override
        public int getEnchantability() {
            return 15;
        }
        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    }


    public static void initialize() {
        Carnage.LOGGER.info("Registering items for " + Carnage.MOD_ID);

    }


        }
