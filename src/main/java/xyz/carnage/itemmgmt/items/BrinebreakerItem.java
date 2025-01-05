package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import xyz.carnage.Carnage;
import xyz.carnage.entity.BrineBreakerEntity;
import xyz.carnage.itemmgmt.ModToolMaterials;

public class BrinebreakerItem extends TridentItem {
    private final ModToolMaterials material;

    public BrinebreakerItem(ModToolMaterials material, Item.Settings settings) {
        super(settings.maxDamage(material.getDurability()));
        this.material = material;
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
//                stack.damage(1, playerEntity);

                BrineBreakerEntity tridentEntity = new BrineBreakerEntity(world, playerEntity, stack.copy());

                // Calculate the position
                tridentEntity.setPosition(
                        playerEntity.getX(),
                        playerEntity.getEyeY() - 0.1,
                        playerEntity.getZ()
                );

                // Set velocity with pitch and yaw
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

                // Add to world
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
    }
}