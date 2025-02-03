package xyz.carnage.manager.entity.wardling;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import xyz.carnage.manager.entity.wardling.entityAnimations.WardlingEntityAnimationController;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class WardlingEntity extends WolfEntity {
    private final float HEALTH = 50.0F;
    private static final int HOSTILE_RADIUS = 10;
    private static final int SONIC_BOOM_COOLDOWN = 200; // 15 seconds (20 ticks per second)
    private final WardlingEntityAnimationController animationController;
    private int sonicBoomCooldown = SONIC_BOOM_COOLDOWN;

    public WardlingEntity(EntityType<? extends WardlingEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.DANGER_OTHER, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_OTHER, 0.0F);
        this.setCustomNameVisible(false);
        this.animationController = new WardlingEntityAnimationController(this);
        if (world.isClient) {
            this.animationController.startSpawnAnimation();
            this.animationController.updateAnimations();
        }
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.5D, true));
        this.targetSelector.add(1, new WardlingTargetGoal(this));
    }
    @Override
    public boolean isTeammate(Entity other) {
        return Objects.equals(this.getOwner(), other);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            animationController.updateAnimations();
        }

        if (!this.getWorld().isClient() && this.getTarget() != null) {
            if (--sonicBoomCooldown <= 0) {
                unleashSonicBoom();
                sonicBoomCooldown = SONIC_BOOM_COOLDOWN;
            }
        }
    }

    private void unleashSonicBoom() {
        LivingEntity target = this.getTarget();
        if (target == null) return;

        Vec3d wardlingPos = this.getPos();
        Vec3d targetPos = target.getPos();
        Vec3d direction = targetPos.subtract(wardlingPos).normalize();

        double range = 10.0;

        List<LivingEntity> entitiesHit = this.getWorld().getEntitiesByClass(LivingEntity.class,
                new Box(wardlingPos.add(-range, -range, -range), wardlingPos.add(range, range, range)),
                entity -> entity != this && entity.squaredDistanceTo(wardlingPos) <= range * range);

        for (LivingEntity entity : entitiesHit) {
            Vec3d toEntity = entity.getPos().subtract(wardlingPos).normalize();
            if (toEntity.dotProduct(direction) > 0.9) {
                entity.damage(this.getDamageSources().magic(), 6.0F);
                entity.takeKnockback(1.0, -direction.x, -direction.z);
            }
        }

        this.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 1.0F, 1.0F);
    }

    public WardlingEntityAnimationController getAnimationController() {
        return animationController;
    };


    @Override
    public boolean hasArmor() {
        return false;
    }

    @Override
    public boolean isInLove() {
        return false;
    }

    @Override
    public void setBegging(boolean begging) {}


    public boolean showNameTags(){
        return false;
    }

    @Override
    public void lovePlayer(@Nullable PlayerEntity player) {
    }


    @Override
    public WolfEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        return null;
    }


    @Override
    public boolean canUseSlot(EquipmentSlot slot) {
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public void setSitting(boolean sitting) {
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public boolean isSitting() {
        return false;
    }




    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isBreedingItem(itemStack) || player.isSneaking()) {
            return ActionResult.FAIL;
        }

        return super.interactMob(player, hand);
    }

    private static class WardlingTargetGoal extends Goal {
        private final WardlingEntity wardling;
        private LivingEntity target;
        private int updateCountdownTicks = 0;

        public WardlingTargetGoal(WardlingEntity wardling) {
            this.wardling = wardling;
            this.setControls(EnumSet.of(Goal.Control.TARGET));
        }

        @Override
        public boolean canStart() {
            if (!wardling.isTamed() || wardling.getOwner() == null) {
                return false;
            }
            return --updateCountdownTicks <= 0;
        }

        @Override
        public boolean shouldContinue() {
            return target != null && target.isAlive() && wardling.getOwner() != null &&
                    isWithinRadius(target) && !target.equals(wardling.getOwner());
        }

        @Override
        public void start() {
            List<LivingEntity> nearbyEntities = wardling.getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    new Box(Objects.requireNonNull(wardling.getOwner()).getPos().add(-HOSTILE_RADIUS, -HOSTILE_RADIUS, -HOSTILE_RADIUS),
                            wardling.getOwner().getPos().add(HOSTILE_RADIUS, HOSTILE_RADIUS, HOSTILE_RADIUS)),
                    entity -> !entity.equals(wardling.getOwner()) && !entity.equals(wardling) &&
                            !(entity instanceof WardlingEntity && ((WardlingEntity) entity).getOwner() != null &&
                                    ((WardlingEntity) entity).getOwner().equals(wardling.getOwner()))
            );

            if (!nearbyEntities.isEmpty()) {
                target = nearbyEntities.get(wardling.getRandom().nextInt(nearbyEntities.size()));
                wardling.setTarget(target);
            }

            updateCountdownTicks = 10;
        }

        @Override
        public void stop() {
            target = null;
            wardling.setTarget(null);
        }

        private boolean isWithinRadius(Entity entity) {
            return wardling.getOwner() != null &&
                    entity.squaredDistanceTo(wardling.getOwner()) <= HOSTILE_RADIUS * HOSTILE_RADIUS;
        }
    }
}
