package xyz.carnage.manager.entity.wardling.entityAnimations;


import net.minecraft.entity.AnimationState;
import xyz.carnage.manager.entity.wardling.WardlingEntity;

public class WardlingEntityAnimationController {
    private final WardlingEntity wardling;
    public final AnimationState spawnState = new AnimationState();
    public final AnimationState attackState = new AnimationState();
    public final AnimationState boomState = new AnimationState();

    public WardlingEntityAnimationController(WardlingEntity entity) {
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
