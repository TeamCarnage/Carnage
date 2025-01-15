package xyz.carnage.itemmgmt.items;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;

public class JuggernautItem extends SwordItem {

    public JuggernautItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            // Generate a random float between 0.0 and 1.0
            if (ThreadLocalRandom.current().nextDouble() <= 0.05) { // 5% chance
                // Play the sound effect
                World world = attacker.getWorld();
                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.PLAYERS, // custom sound effect!
                        0.5f, 1.0f);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 200, 0)); //this hammer can and will lobotomise
            }
        }

        return super.postHit(stack, target, attacker);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient) {
            player.c

        }
        return null;
    }}