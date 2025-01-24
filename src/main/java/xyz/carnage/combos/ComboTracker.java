package xyz.carnage.combos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ComboTracker {

    private final PlayerEntity player;
    private int comboCount;
    long lastHitTime;
    private boolean canProcessHit;
    private Map<Integer, Consumer<PlayerEntity>> comboActions;

    public ComboTracker(World world, PlayerEntity player) {
        this.player = player;
        this.comboCount = 0;
        this.lastHitTime = 0;
        this.canProcessHit = true;
        this.comboActions = new HashMap<>();
    }

    public void registerComboAction(int comboCount, Consumer<PlayerEntity> action) {
        comboActions.put(comboCount, action);
    }

    public void hit() {
        long now = System.currentTimeMillis();
        if (canProcessHit) {
            if (now - lastHitTime < 1000) {
                comboCount++;
                // Check for and execute registered combo actions
                Consumer<PlayerEntity> action = comboActions.get(comboCount);
                if (action != null) {
                    action.accept(player);
                }
            } else {
                comboCount = 1;
            }

            Text actionBarMessage = Text.literal(comboCount/2 + "x Combo!")
                    .styled(style -> style.withColor(Formatting.BLUE));
            player.sendMessage(actionBarMessage, true);

            canProcessHit = false;
        }
        lastHitTime = now;
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