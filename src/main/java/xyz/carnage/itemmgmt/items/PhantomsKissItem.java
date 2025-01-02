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


            player.setVelocity(player.getVelocity().x, 1.0, player.getVelocity().z); // initial velocity application
            player.velocityModified = true;  // Required to apply velocity changes

            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS, // beacon sound effect
                    0.5f, 1.2f);

            world.getServer().getCommandManager().executeWithPrefix(
                    player.getCommandSource(),
                    "/summon tnt ~ ~ ~ {NoGravity:1b,fuse:1,explosion_power:1,Motion:[0.0,-1.0,0.0]}"  // replace with your desired command
            );
        }


        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_PHANTOM_BITE, SoundCategory.PLAYERS, // phantom sound effect
                0.5f, 1.2f);

        // Play sound for only the player who clicked
        //player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.2f);
        //                             enderman sound effect        half volume    sharper, faster tone

        player.getItemCooldownManager().set(this, 200); // 200 TICKS, which is 10 seconds at 20tps

        return TypedActionResult.success(stack);
    }
}
