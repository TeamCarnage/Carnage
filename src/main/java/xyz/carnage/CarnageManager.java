package xyz.carnage;

import xyz.carnage.manager.combo.ComboEventHandler;
import xyz.carnage.manager.combo.ComboManager;
import xyz.carnage.manager.item.CarnageItemGroups;
import xyz.carnage.manager.item.CarnageItems;
import xyz.carnage.manager.particle.CustomParticles;
import xyz.carnage.manager.sound.SoundManager;
import xyz.carnage.manager.ui.UIManager;

public class CarnageManager {
    private final UIManager uiManager;
    private final CustomParticles customParticles;
    private final CarnageItems carnageItems;
    private final CarnageItemGroups carnageItemGroups;
    private final SoundManager soundManager;
    private final ComboEventHandler comboEventHandler;


    public CarnageManager(UIManager uiManager, CustomParticles customParticles, CarnageItems carnageItems, CarnageItemGroups carnageItemGroups, SoundManager soundManager, ComboEventHandler comboEventHandler) {
        this.uiManager = uiManager;
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
        uiManager.initialize();
    }

    public CustomParticles getCustomParticles() {
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
    public UIManager getUiManager() {
        return uiManager;
    }

}