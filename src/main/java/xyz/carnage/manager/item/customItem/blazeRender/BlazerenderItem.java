package xyz.carnage.manager.item.customItem.blazeRender;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.slf4j.Logger;
import xyz.carnage.Carnage;
import xyz.carnage.manager.item.example.PushSwordItem;
import xyz.carnage.manager.combo.ComboManager;
import xyz.carnage.manager.combo.ComboTracker;

public class BlazerenderItem extends PushSwordItem {
    private static PlayerEntity comboPlayer;
    static boolean Polar;
    private static final PushableItemSettings pushableItemSettings = createPushableItemSettings();

    public BlazerenderItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings, pushableItemSettings);
    }

    private static PushableItemSettings createPushableItemSettings() {
        PushableItemSettings settings = new PushableItemSettings();

        if (Polar) {
            settings.setCooldownTicks(10)
                    .setPushRadius(10.0)
                    .setPushStrength(-1.5)
                    .setUpwardForce(-0.5)
                    .setParticleType(ParticleTypes.SOUL_FIRE_FLAME)
                    .setParticleCount(50)
                    .setUseSound(SoundEvents.ENTITY_WITHER_HURT)
                    .setSoundVolume(0.5f)
                    .setSoundPitch(1.2f);
        } else {
            settings.setCooldownTicks(10)
                    .setPushRadius(10.0)
                    .setPushStrength(1.5)
                    .setUpwardForce(0.5)
                    .setParticleType(ParticleTypes.FLAME)
                    .setParticleCount(50)
                    .setUseSound(SoundEvents.ENTITY_BLAZE_SHOOT)
                    .setSoundVolume(0.5f)
                    .setSoundPitch(1.2f);
        }

        return settings;
    }



    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount()/2 >= 5) {
            if (!Polar) {
                boolean Polar = true;
            }

            if (Polar) {
                boolean Polar = false;}
            tracker.reset();
        }
        tracker.clearHitFlag();
        return super.postHit(stack, target, attacker);
    }

}