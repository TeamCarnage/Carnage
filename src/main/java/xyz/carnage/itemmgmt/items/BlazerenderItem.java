package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import xyz.carnage.CustomParticles;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;
import xyz.carnage.itemmgmt.ModToolMaterials;
import xyz.carnage.itemmgmt.templates.PushSwordItem;

public class BlazerenderItem extends PushSwordItem {
    private final ToolMaterial material;
    private static PlayerEntity comboPlayer;

    public BlazerenderItem(ModToolMaterials blazerender, Settings settings) {
        super(ModToolMaterials.BLAZERENDER, settings, new PushSwordItem.PushableItemSettings()
                .setCooldownTicks(20)
                .setPushRadius(10.0)
        );
        this.material = ModToolMaterials.BLAZERENDER;
    }

    public BlazerenderItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings, new PushSwordItem.PushableItemSettings()
                .setCooldownTicks(10)
                .setPushRadius(10.0)
                .setPushStrength(1.5)
                .setUpwardForce(0.5)
                .setParticleType(CustomParticles.HELLFIRE)
                .setParticleCount(50)
                .setUseSound(SoundEvents.ENTITY_BLAZE_SHOOT)
                .setSoundVolume(0.5f)
                .setSoundPitch(1.2f)
        );
        this.material = toolMaterial;
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        onPushEffectUsed(world, (PlayerEntity) attacker);

        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount()/2 >= 5) {
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));
            tracker.reset();
        }
        tracker.clearHitFlag();

        return super.postHit(stack, target, attacker);
    }

    public ToolMaterial getMaterial() {
        return material;
    }
}