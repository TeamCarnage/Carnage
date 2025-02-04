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
      //  if (wardling.age < 10) {
       //    SPAWN.startIfNotRunning(wardling.age * 50);
       //    return; // Prioritize spawn animation first
       // }
        Vec3d velocity = wardling.getVelocity();

        // RUN animation: Triggers when moving
        if (velocity.lengthSquared() > 0.01 && wardling.getTarget() == null) {
            RUN.startIfNotRunning(wardling.age);
        } else {
            RUN.stop(); // Stop running animation when not moving
        }

        // ATTACK animation: Uses handSwinging field to detect melee attacks
        if (wardling.handSwinging) {
            ATTACK.startIfNotRunning(wardling.age);
        } else {
            ATTACK.stop(); // Stop attack animation after swinging ends
        }

        // BOOM animation: Uses a new cooldown check to ensure it stops
        if (wardling.isSonicBoomActive()) {
            BOOM.startIfNotRunning(wardling.age);
        } else {
            BOOM.stop(); // Ensure BOOM stops when the ability ends
        }
    }

    public void startSpawnAnimation() {
        SPAWN.startIfNotRunning((int) System.currentTimeMillis());
    }
}
