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
    private boolean canProcessHit;
    boolean canCombo;

    public ComboTracker(World world, PlayerEntity player) {
        this.world = world;
        this.player = player;
        this.comboCount = 0;
        this.lastHitTime = 0;
        this.canProcessHit = true;
        this.canCombo = true;
    }

    public void hit() {
        long now = System.currentTimeMillis();
        if (canProcessHit) {
            if (now - lastHitTime < 5000) comboCount++; else comboCount = 1;  // Start new combo at 1, not 2

            // Display the combo count in the action bar
            Text actionBarMessage = Text.literal(comboCount / 2 + "x Combo!").styled(style -> style.withColor(Formatting.BLUE));
            player.sendMessage(actionBarMessage, true);

            canProcessHit = false;  // Block further increments until next tick
        }
        lastHitTime = now;  // Update the last hit time
    }

    public int getComboCount() {
        return comboCount;
    }

    public void reset() {
        comboCount = 0;
        canProcessHit = true;
    }

    public void clearHitFlag() {
        canProcessHit = true;
    }
}