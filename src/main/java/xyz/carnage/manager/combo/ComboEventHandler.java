package xyz.carnage.manager.combo;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import xyz.carnage.manager.item.CarnageItemGroups;

public class ComboEventHandler {
    public static final int comboMaxTime = 3000;

    public static void registerEvents() {

        // Triggers after an entity takes damage (handles hits)
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, amount, taken, blocked) -> {
            if (blocked || taken <= 0) return;

            if (source.getAttacker() instanceof PlayerEntity player) {
                if (CarnageItemGroups.isItemTriggerable(source.getWeaponStack().getItem()) || CarnageItemGroups.isItemTriggerable(source.getWeaponStack().getItem())) {
                    ComboTracker tracker = ComboManager.getComboTracker(player);
                    tracker.hit();
                }
            }
        });

        // Triggers after an entity dies (AFTER_DAMAGE doesn't trigger when the entity dies in the same hit)
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            if (source.getAttacker() instanceof PlayerEntity player) {
                if (CarnageItemGroups.isItemTriggerable(source.getWeaponStack().getItem()) || CarnageItemGroups.isItemTriggerable(source.getWeaponStack().getItem())) {
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
