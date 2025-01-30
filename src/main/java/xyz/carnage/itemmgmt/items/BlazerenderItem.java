package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import xyz.carnage.CustomParticles;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;
import xyz.carnage.itemmgmt.ModToolMaterials;
import xyz.carnage.itemmgmt.templates.PushSwordItem;

public class BlazerenderItem extends PushSwordItem {
    private static PlayerEntity comboPlayer;
    public boolean Polar;

    public BlazerenderItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    private PushableItemSettings createPushableItemSettings() {
        PushableItemSettings settings = new PushableItemSettings();

        if (Polar == true) {
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
        onPushEffectUsed(world, (PlayerEntity) attacker);

        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount()/2 >= 15) {
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));
            tracker.reset();
        }
        tracker.clearHitFlag();

        return super.postHit(stack, target, attacker);
    }

    public ToolMaterial getMaterial() {
        return getMaterial();
    }

    public boolean isPolar() {
        return Polar;
    }

    public void setPolar(boolean polar) {
        Polar = polar;
    }
}
