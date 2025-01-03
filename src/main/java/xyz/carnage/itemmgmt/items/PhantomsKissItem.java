package xyz.carnage.itemmgmt.items;

import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import xyz.carnage.CustomSounds;

public class PhantomsKissItem extends SwordItem {

    public PhantomsKissItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            // Play the sound effect
            World world = attacker.getWorld();
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, // custom sound effect!
                    0.5f, 1.0f);
            // Send the message
            player.sendMessage(Text.literal("You hit " + target.getName().getString() + " with the sword!"), true);
        }

        return super.postHit(stack, target, attacker);
    }
}