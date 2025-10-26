package xyz.carnage.manager.entity.wardling.entityAnimations;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import xyz.carnage.manager.entity.wardling.WardlingEntity;

public class WardlingEntityAnimationController {
    private final WardlingEntity wardling;

    public final AnimationState SPAWN = new AnimationState();
    public final AnimationState RUN = new AnimationState();
    public final AnimationState ATTACK = new AnimationState();
    public final AnimationState BOOM = new AnimationState();

    public WardlingEntityAnimationController(WardlingEntity entity) {
        this.wardling = entity;
    }

    public void updateAnimations() {
        // Re-enable spawn animation
        if (wardling.age < 10) {
            SPAWN.startIfNotRunning(wardling.age);
            return; // Prioritize spawn animation first
        }

        Vec3d velocity = wardling.getVelocity();

        // Changed RUN to WALK to match the animation name
        if (velocity.lengthSquared() > 0.01 && wardling.getTarget() == null) {
            RUN.startIfNotRunning(wardling.age);
        } else {
            RUN.stop();
        }

        if (wardling.handSwinging) {
            ATTACK.startIfNotRunning(wardling.age);
        } else {
            ATTACK.stop();
        }

        if (wardling.isSonicBoomActive()) {
            BOOM.startIfNotRunning(wardling.age);
        } else {
            BOOM.stop();
        }
    }

// In WardlingEntity.java



    public void startSpawnAnimation() {
        SPAWN.startIfNotRunning((int) System.currentTimeMillis());
    }
}
