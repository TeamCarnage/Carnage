package xyz.carnage.entity.customEntities;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import xyz.carnage.entity.client.entityAnimations.wardling.WardlingAnimationController;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WardlingEntity extends WolfEntity {
    private static final int HOSTILE_RADIUS = 10;
    private final WardlingAnimationController animationController;

    public WardlingEntity(EntityType<? extends WardlingEntity> entityType, World world) {
        super(entityType, world);
//        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED.value().getDefaultValue());
        this.setPathfindingPenalty(PathNodeType.DANGER_OTHER, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_OTHER, 0.0F);
        this.animationController = new WardlingAnimationController(this);
        if (world.isClient) {
            this.animationController.startSpawnAnimation();
            this.animationController.updateAnimations();
        }
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.add(1, new WardlingTargetGoal(this));
    }

    @Override
    public void setCustomNameVisible(boolean visible) {visible = false;}

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
    }

    public WardlingAnimationController getAnimationController() {
        return animationController;
    }

    private class WardlingTargetGoal extends Goal {
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
                    new Box(wardling.getOwner().getPos().add(-HOSTILE_RADIUS, -HOSTILE_RADIUS, -HOSTILE_RADIUS),
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