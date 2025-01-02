package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PhantomsKissItem extends SwordItem {
    public PhantomsKissItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100, 0)); // 5 seconds of Invisibility
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1)); // 5 seconds of Speed 2
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, // enderman sound effect
                0.5f, 1.2f);

        player.getItemCooldownManager().set(this, 200); // 200 TICKS, which is 10 seconds at 20tps

        return TypedActionResult.success(stack);
    }
}
