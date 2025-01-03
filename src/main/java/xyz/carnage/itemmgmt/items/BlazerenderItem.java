package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import xyz.carnage.itemmgmt.templates.PushItem;

public class BlazerenderItem extends PushItem {
    private final ToolMaterial material;

    public BlazerenderItem(ToolMaterial toolMaterial, Settings settings) {
        super(settings, new PushItem.PushableItemSettings()
                // Cooldown
//                .setCooldownTicks(400)
                // Pushing Mobs Away
                .setPushRadius(10.0) // in blocks
                .setPushStrength(1.5)
                .setUpwardForce(0.5)
                // Particle
                .setParticleType(ParticleTypes.FLAME)
                .setParticleCount(50)
                // Sounds
                .setUseSound(SoundEvents.ENTITY_BLAZE_SHOOT)
                .setSoundVolume(0.5f)
                .setSoundPitch(1.2f)
        );
        this.material = toolMaterial;
    }

    @Override
    protected void onPushEffectUsed(World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));
    }

    // for any sword-specific functionality, add it here
    public ToolMaterial getMaterial() {
        return material;
    }
}