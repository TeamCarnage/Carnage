package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import xyz.carnage.CustomSounds;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;

public class PhantomsKissItem extends SwordItem {

    public PhantomsKissItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            // Play the sound effect
            CustomSounds.playItemSound("hit", stack, attacker.getWorld(), player);
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