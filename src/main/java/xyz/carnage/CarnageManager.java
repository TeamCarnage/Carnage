package xyz.carnage;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import xyz.carnage.manager.combo.ComboEventHandler;
import xyz.carnage.manager.item.CarnageItemGroups;
import xyz.carnage.manager.item.CarnageItems;
import xyz.carnage.manager.particle.CarnageParticles;
import xyz.carnage.manager.sound.SoundManager;
import xyz.carnage.manager.ui.UIManager;

public class CarnageManager {
    private final CarnageParticles customParticles;
    private final CarnageItems carnageItems;
    private final CarnageItemGroups carnageItemGroups;
    private final SoundManager soundManager;
    private final ComboEventHandler comboEventHandler;


    public CarnageManager(CarnageParticles customParticles, CarnageItems carnageItems, CarnageItemGroups carnageItemGroups, SoundManager soundManager, ComboEventHandler comboEventHandler) {
        this.customParticles = customParticles;
        this.carnageItems = carnageItems;
        this.carnageItemGroups = carnageItemGroups;
        this.soundManager = soundManager;
        this.comboEventHandler = comboEventHandler;
    }

    public void initialize() {
        customParticles.initialize();
        carnageItems.initialize();
        carnageItemGroups.initialize();
        soundManager.initialize();
        comboEventHandler.initialize();
        HudRenderCallback.EVENT.register(new UIManager());
    }

    public CarnageParticles getCustomParticles() {
        return customParticles;
    }
    public CarnageItems getCarnageItems() {
        return carnageItems;
    }
    public CarnageItemGroups getCarnageItemGroups() {
        return carnageItemGroups;
    }
    public SoundManager getSoundManager() {
        return soundManager;
    }
    public ComboEventHandler getComboEventHandler() {
        return comboEventHandler;
    }

}