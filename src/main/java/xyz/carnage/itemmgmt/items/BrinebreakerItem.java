package xyz.carnage.itemmgmt.items;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import xyz.carnage.Carnage;
import xyz.carnage.combos.ComboManager;
import xyz.carnage.combos.ComboTracker;
import xyz.carnage.entity.BrineBreakerEntity;
import xyz.carnage.entity.EntitiesRegistry;
import xyz.carnage.itemmgmt.ModItems;

import static net.minecraft.datafixer.TypeReferences.ITEM_STACK;

public class BrinebreakerItem extends TridentItem {

    public BrinebreakerItem(Item.Settings settings) {
        super(settings);
    }


    public ItemStack createBrinebreakerStack() {
        ItemStack stack = new ItemStack(ModItems.BRINEBREAKER);

        stack.set(DataComponentTypes.DAMAGE, 5);
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.literal("Brinebreaker"));
        return stack;
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
                BrineBreakerEntity tridentEntity = new BrineBreakerEntity(EntitiesRegistry.BRINEBREAKER, world);
                tridentEntity.setOwner(user);

                tridentEntity.setPreservedStack(new ItemStack(ModItems.BRINEBREAKER.asItem(), stack.getComponents().size()));



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
            Carnage.LOGGER.error("Brinebreaker FAILED to throw <3");
            e.printStackTrace();
        }
        stack.decrementUnlessCreative(1, playerEntity);
    }



    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount()/2 >= 5) {
            tracker.reset();
        }
        tracker.clearHitFlag();
        return super.postHit(stack, target, attacker);
    }
}