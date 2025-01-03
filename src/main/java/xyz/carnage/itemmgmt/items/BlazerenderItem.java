package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import xyz.carnage.itemmgmt.ModToolMaterials;
import xyz.carnage.itemmgmt.templates.PushSwordItem;

public class BlazerenderItem extends PushSwordItem {
    private final ToolMaterial material;

    // Constructor updated to accept Settings and ModToolMaterials correctly
    public BlazerenderItem(ModToolMaterials blazerender, Settings settings) {
        super(ModToolMaterials.BLAZERENDER, settings, new PushSwordItem.PushableItemSettings()
                .setCooldownTicks(20)
                .setPushRadius(10.0)
                .setPushStrength(1.5)
                .setUpwardForce(0.5)
                .setParticleType(ParticleTypes.FLAME)
                .setParticleCount(50)
                .setUseSound(SoundEvents.ENTITY_BLAZE_SHOOT)
                .setSoundVolume(0.5f)
                .setSoundPitch(1.2f)
        );
        this.material = ModToolMaterials.BLAZERENDER; // Using the custom material from ModToolMaterials
    }


    public void onItemUse(World world, PlayerEntity player) {
        // When the sword is used, apply push effects as well as sword functionality
        super.use(world, player, player.getActiveHand());
    }

    @Override
    protected void onPushEffectUsed(World world, PlayerEntity player) {
        // Apply a special effect when the push effect is used (e.g., Fire Resistance)
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));
    }

    public boolean postHit(ItemStack stack, LivingEntity target, PlayerEntity player) {
        // Apply the push effect on hit
        World world = player.getWorld();
        onPushEffectUsed(world, player);

        // Now call the parent method with the correct arguments
        return super.postHit(stack, player, target);  // Correct arguments passed
    }

    // Add getter for material if needed
    public ToolMaterial getMaterial() {
        return material;
    }
}
