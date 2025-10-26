package xyz.carnage.manager.combo;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

import static xyz.carnage.Carnage.MOD_ID;

public class ComboEventHandler {
    public static final int comboMaxTime = 2000;

    public static void registerEvents() {
        // Monitor Player Attacks
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if(player.getAttackCooldownProgress(1f) == 1.0f) {
                ComboTracker tracker = ComboManager.getComboTracker(player);
                if(player.getStackInHand(hand).getItem().toString().contains(MOD_ID)) {
                    if (entity.isAlive()) {
                        tracker.hit();
                    }
                }
            }
            return ActionResult.PASS;
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            ComboManager.comboTrackers.forEach((uuid, tracker) -> {
                if (System.currentTimeMillis() - tracker.lastHitTime > comboMaxTime) {
                    tracker.reset();
                }
            });
        });
    }

    public void initialize() {
        registerEvents();
    }
}
