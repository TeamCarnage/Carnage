package xyz.carnage.combos;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class ComboEventHandler {
    public static void registerEvents() {
        // Monitor Player Attacks
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!(player instanceof PlayerEntity)) return ActionResult.PASS;

            ComboTracker tracker = ComboManager.getComboTracker((PlayerEntity) player);
            tracker.hit();  // Process the hit for combo counting

            // Reset combo after 10 hits
            if (tracker.getComboCount() == 10) {
                world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                tracker.reset();  // Reset after triggering
            }

            // Ensure the hit flag is cleared after processing the hit
            tracker.clearHitFlag();

            return ActionResult.PASS;
        });

        // Reset Combos Periodically
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            long now = System.currentTimeMillis();
            ComboManager.comboTrackers.forEach((uuid, tracker) -> {
                // Reset combo if more than 2 seconds have passed since last hit
                if (now - tracker.lastHitTime > 2000) {
                    tracker.reset();
                }
            });
        });
    }
//    public static void registerEvents() {
//        AttackEntityCallback.EVENT.register(((playerEntity, world, hand, entity, entityHitResult) -> {
//            if(!(playerEntity instanceof PlayerEntity)) return ActionResult.PASS;
//
//            final ComboTracker tracker = new ComboTracker(world, playerEntity);
//            tracker.hit();
//
//
//        }));
//    }
}
