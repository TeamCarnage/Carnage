package xyz.carnage.manager.combo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.carnage.manager.ui.ComboUIOverlay;

import static xyz.carnage.manager.combo.ComboEventHandler.comboMaxTime;

public class ComboTracker {

    private final PlayerEntity player;
    private final World world;
    private int comboCount;
    public long lastHitTime;
    public boolean canProcessHit;
    private final Map<Integer, Consumer<PlayerEntity>> comboActions;

    public ComboTracker(World world, PlayerEntity player) {
        this.player = player;
        this.world = world;
        this.comboCount = 0;
        this.lastHitTime = 0;
        canProcessHit = true;
        this.comboActions = new HashMap<>();
    }

    public void registerComboAction(int comboCount, Consumer<PlayerEntity> action) {
        comboActions.put(comboCount, action);
    }

    public void hit() {
        long currentTimeMillis = System.currentTimeMillis();
        if (canProcessHit) {
            canProcessHit = false;
            if (currentTimeMillis - lastHitTime < comboMaxTime) {
                comboCount++;
                // Check for and execute registered combo actions
                Consumer<PlayerEntity> action = comboActions.get(comboCount);
                if (action != null) {
                    action.accept(player);
                }
            } else {
                comboCount = 1;
            }
            ComboUIOverlay.setComboCount(comboCount);
            ComboUIOverlay.show();
            lastHitTime = currentTimeMillis;
        }

    }

    public int getComboCount() {
        return comboCount;
    }

    public void reset() {
        comboCount = 0;
        canProcessHit = true;
        ComboUIOverlay.hide();
    }

    public void clearHitFlag() {
        canProcessHit = true;
    }
}