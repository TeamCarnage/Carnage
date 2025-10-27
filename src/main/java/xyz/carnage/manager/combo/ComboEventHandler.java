package xyz.carnage.manager.combo;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import static xyz.carnage.Carnage.MOD_ID;

public class ComboEventHandler {
    public static final int comboMaxTime = 3000;

    public static void registerEvents() {

        // Triggers after an entity takes damage (handles hits)
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, amount, taken, blocked) -> {
            if (blocked || taken <= 0) return;

            if (source.getAttacker() instanceof PlayerEntity player) {
                if (player.getMainHandStack().getItem().toString().contains(MOD_ID)) {
                    ComboTracker tracker = ComboManager.getComboTracker(player);
                    tracker.hit();
                }
            }
        });

        // Triggers after an entity dies (AFTER_DAMAGE doesn't trigger when the entity dies in the same hit)
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            if (source.getAttacker() instanceof PlayerEntity player) {
                if (player.getMainHandStack().getItem().toString().contains(MOD_ID)) {
                    ComboTracker tracker = ComboManager.getComboTracker(player);
                    tracker.hit();
                }
            }
        });

        // Triggers every server tick (handles combo cancelling after time)
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            long now = System.currentTimeMillis();

            ComboManager.comboTrackers.forEach((uuid, tracker) -> {
                tracker.clearHitFlag();

                if (now - tracker.lastHitTime > comboMaxTime) {
                    tracker.reset();
                }
            });
        });
    }

    public void initialize() {
        registerEvents();
    }
}
