package xyz.carnage.combos;

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
                if (!(player instanceof PlayerEntity)) return ActionResult.PASS;

                ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) player);

                if(player.getStackInHand(hand).getItem().toString().contains(MOD_ID)) tracker.hit(); else {
                    tracker.clearHitFlag();
                }
                tracker.clearHitFlag();
                return ActionResult.PASS;
            }
            else return ActionResult.PASS;

        });

        // Resets combo count after set time (comboMaxTime)
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            long currentTimeMillis = System.currentTimeMillis();
            ComboManager.comboTrackers.forEach((uuid, tracker) -> {
                if (currentTimeMillis - tracker.lastHitTime > comboMaxTime) {
                    tracker.reset();
                }
            });
        });
    }
}
