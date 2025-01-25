package xyz.carnage.entity.client.entityAnimations.wardling;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.entity.AnimationState;
import xyz.carnage.entity.customEntities.WardlingEntity;
import xyz.carnage.entity.client.entityAnimations.wardling.WardlingAnimations;

public class WardlingAnimationController {
    private final WardlingEntity wardling;
    public final AnimationState spawnState = new AnimationState();
    public final AnimationState attackState = new AnimationState();
    public final AnimationState boomState = new AnimationState();

    public WardlingAnimationController(WardlingEntity entity) {
        this.wardling = entity;
    }

    public void updateAnimations() {
        if (wardling.age < 10) {
            spawnState.startIfNotRunning(wardling.age * 50);
        }
    }

    public void startSpawnAnimation() {
        spawnState.startIfNotRunning((int) System.currentTimeMillis());
    }
}