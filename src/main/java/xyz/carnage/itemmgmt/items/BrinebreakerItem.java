package xyz.carnage.itemmgmt.items;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import xyz.carnage.Carnage;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;
import xyz.carnage.entity.BrineBreakerEntity;
import xyz.carnage.itemmgmt.ModToolMaterials;

public class BrinebreakerItem extends TridentItem {

        public BrinebreakerItem( Item.Settings settings) {
            super(settings); //apparantly trident item doesnt like it if you have tool materials
        }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return;
        }

        int i = 72000 - remainingUseTicks;
        if (i < 10) {
            return;
        }

        try {
            if (!world.isClient) {

                BrineBreakerEntity tridentEntity;
                tridentEntity = new BrineBreakerEntity(world, (PlayerEntity) user);

                tridentEntity.setPosition(
                        playerEntity.getX(),
                        playerEntity.getEyeY() - 0.1,
                        playerEntity.getZ()
                );

                float power = Math.min(i / 20.0F, 1.0F);
                float speed = 2.5F * power;

                tridentEntity.setVelocity(
                        playerEntity,
                        playerEntity.getPitch(),
                        playerEntity.getYaw(),
                        0.0F,
                        speed,
                        1.0F
                );

                if (world.spawnEntity(tridentEntity)) {
                    world.playSoundFromEntity(
                            null,
                            tridentEntity,
                            SoundEvents.ITEM_TRIDENT_THROW.value(),
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
                    );
                }
            }
        } catch (Exception e) {
            // Log the actual error
            Carnage.LOGGER.error("Brinebreaker FAILED to throw <3");
            e.printStackTrace();
        }
        stack.decrementUnlessCreative(1, playerEntity);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount()/2 >= 5) {
            //attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0)); <- Example
            tracker.reset();
        }
        tracker.clearHitFlag();
        return super.postHit(stack, target, attacker);
    }
}