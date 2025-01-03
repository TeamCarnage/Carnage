package xyz.carnage.itemmgmt;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    PHANTOMS_KISS(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3096, 9.0F, 5.0F, 15,
            () -> Ingredient.ofItems(Items.PHANTOM_MEMBRANE)),
    BLAZERENDER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 10.0F, 4.0F, 20,
            () -> Ingredient.ofItems(Items.BLAZE_ROD));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(
            TagKey<Block> inverseTag,
            int itemDurability,
            float miningSpeed,
            float attackDamage,
            int enchantability,
            Supplier<Ingredient> repairIngredient
    ) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get); // Memoize for performance
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }


    //public int getEnchantability() {
    //    return this.enchantability;
    //}

    public int getEnchantability() {
        return this.enchantability;
    }


    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public int getItemDurability() {
        return itemDurability;
    }
}
