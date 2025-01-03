package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BloodEssenceItem extends Item {
    public BloodEssenceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, // hypixel ding sound effect!
                0.5f, 1.0f);

        stack.decrementUnlessCreative(1.0f); // trying to decrease blood by 1 every use

        player.getItemCooldownManager().set(this, 20); // 20 TICKS, which is 1 second at 20tps

        return TypedActionResult.success(stack);
    }
}