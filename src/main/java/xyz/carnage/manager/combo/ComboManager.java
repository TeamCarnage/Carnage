package xyz.carnage.manager.combo;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.UUID;

public class ComboManager {
    public static final HashMap<UUID, ComboTracker> comboTrackers = new HashMap<>();

    public static ComboTracker getComboTracker(PlayerEntity player) {
        World world = player.getWorld(); // Get the player's current world
        return comboTrackers.computeIfAbsent(player.getUuid(), uuid -> new ComboTracker(world, player));
    }
}
