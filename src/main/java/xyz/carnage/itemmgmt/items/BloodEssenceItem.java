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
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 150, 0));
        }

        //
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, // hypixel ding sound effect!
                SoundCategory.PLAYERS,
                0.5f,
                1.0f
        );

        stack.decrement(1); //decreases by one

        stack.decrement(1); // decrease blood by 1 every use

        player.getItemCooldownManager().set(this, 100); // 100 TICKS, which is 5 seconds at 20tps

        return TypedActionResult.success(stack);
    }
}