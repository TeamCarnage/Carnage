package xyz.carnage.manager.item.customItem.echoingTwinBlade;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.carnage.manager.combo.ComboManager;
import xyz.carnage.manager.combo.ComboTracker;
import xyz.carnage.manager.entity.EntitiesRegistry;
import xyz.carnage.manager.entity.wardling.WardlingEntity;

import java.util.Random;

public class EchoingTwinbladeItem extends SwordItem {
    public EchoingTwinbladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            // Generate a random float between 0.0 and 1.0
            World world = attacker.getWorld();

            ComboTracker tracker = ComboManager.getComboTracker(player);
            if (tracker.getComboCount() / 2 >= 4) { //SET COMBO TO 15 IN FINAL
                spawnZombieNearAttacker(world, attacker.getPos(), player);

                tracker.reset();
            }
            tracker.clearHitFlag();
        }

        return super.postHit(stack, target, attacker);
    }


    private void spawnZombieNearAttacker(World world, Vec3d attackerPos, PlayerEntity player) {
        Random random = new Random();

        double offsetX = random.nextInt(7) - 5;
        double offsetZ = random.nextInt(7) - 5;
        double offsetY = 0;

        BlockPos spawnPos = new BlockPos(
                (int) (attackerPos.x + offsetX),
                (int) (attackerPos.y + offsetY),
                (int) (attackerPos.z + offsetZ)
        );

        // Creating and spawning the wardling entity
        WardlingEntity wardling = new WardlingEntity(EntitiesRegistry.WARDLING, world);
        wardling.refreshPositionAndAngles(spawnPos, 0, 0);
        wardling.setOwner(player); // Correct way to set the owner
        world.spawnEntity(wardling);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        player.getItemCooldownManager().set (this, 50);

        if (!world.isClient) {
            Vec3d playerPos = player.getPos();


            world.playSound(null, playerPos.x, playerPos.y, playerPos.z,
                    SoundEvents.ENTITY_WARDEN_SONIC_BOOM,
                    SoundCategory.PLAYERS,
                    0.5F, 1.0F);

            double range =2.5;
            world.getEntitiesByClass(LivingEntity.class,
                    player.getBoundingBox().expand(range),
                    entity -> entity != player).forEach(entity -> {
                // Apply damage
                entity.damage(player.getDamageSources().magic(), 5.0F);
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,200));


                Vec3d knockbackDirection = entity.getPos().subtract(player.getPos()).normalize();
                entity.setVelocity(knockbackDirection.multiply(3.25));
                entity.velocityModified = true;
            });
        }

        return TypedActionResult.success(stack);
    }
}