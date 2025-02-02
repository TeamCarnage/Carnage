package xyz.carnage.manager.item.customItem.brineBreaker;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import xyz.carnage.Carnage;
import xyz.carnage.manager.combo.ComboManager;
import xyz.carnage.manager.combo.ComboTracker;
import xyz.carnage.manager.entity.brinebreaker.BrinebreakerEntity;
import xyz.carnage.manager.entity.EntitiesRegistry;

public class BrinebreakerItem extends TridentItem {

    public BrinebreakerItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        var brineTridentEntity = new BrinebreakerEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
        brineTridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
        return brineTridentEntity;
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
                BrinebreakerEntity tridentEntity = new BrinebreakerEntity(EntitiesRegistry.BRINEBREAKER, world);
                tridentEntity.setOwner(user);
                tridentEntity.setPosition(playerEntity.getX(), playerEntity.getEyeY() - 0.1, playerEntity.getZ());
                float power = Math.min(i / 20.0F, 1.0F);
                float speed = 2.5F * power;
                tridentEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, speed, 1.0F);
                if (world.spawnEntity(tridentEntity)) {
                    world.playSoundFromEntity(null, tridentEntity, SoundEvents.ITEM_TRIDENT_THROW.value(), SoundCategory.PLAYERS,
                            1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
                    );
                }
            }
        } catch (Exception e) {
            Carnage.LOGGER.error("Brinebreaker FAILED to throw <3");
            // this will stay forever
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker){
        ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        if (tracker.getComboCount() / 2 >= 5) {
            tracker.reset();
        }

        tracker.clearHitFlag();
        return super.postHit(stack, target, attacker);
    }
}
