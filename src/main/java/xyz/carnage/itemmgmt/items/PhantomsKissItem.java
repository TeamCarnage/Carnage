package xyz.carnage.itemmgmt.items;

import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import xyz.carnage.CustomSounds;
import xyz.carnage.itemmgmt.ModItems;
import xyz.carnage.combos.ComboEventHandler;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;

import java.util.List;

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
            player.sendMessage(Text.literal("You hit " + target.getName().getString() + " with the sword!"), true); // waffles about what you killed above your hotbar
        }

        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount()/2 >= 5) {
            //attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0)); <- Example
            tracker.reset();
        }
        tracker.clearHitFlag();

        return super.postHit(stack, target, attacker);
    }


}