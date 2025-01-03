package xyz.carnage.combos;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ComboTracker {
    private final World world;
    private final PlayerEntity player;
    private int comboCount;
    long lastHitTime;
    private boolean canProcessHit;  // Flag to ensure we process only one hit per valid attack

    public ComboTracker(World world, PlayerEntity player) {
        this.world = world;
        this.player = player;
        this.comboCount = 0;
        this.lastHitTime = 0;
        this.canProcessHit = true;  // Set flag to allow processing hits initially
    }

    public void hit() {
        long now = System.currentTimeMillis();

        if (now - lastHitTime < 1000) { // 1-second window for combo hits
            if (canProcessHit) {  // Ensure only one increment per hit
                comboCount++;  // Increment combo count
                canProcessHit = false;  // Block further increments within the same tick

                // Display the combo count in the action bar
                Text actionBarMessage = Text.literal(comboCount + "x Combo!").styled(style -> style.withColor(Formatting.BLUE));
                player.sendMessage(actionBarMessage, true);
            }
        } else {
            comboCount = 1;  // Reset combo count if more than 1 second passed
            canProcessHit = false;  // Start new combo and block increment until next hit
        }

        lastHitTime = now;  // Update the last hit time
    }

    public int getComboCount() {
        return comboCount;
    }

    public void reset() {
        comboCount = 0;
        canProcessHit = true;  // Allow processing hits again after reset
    }

    // Method to allow processing new hits after each tick
    public void clearHitFlag() {
        canProcessHit = true;  // Enable the flag to process the next hit
    }
}
