package xyz.carnage;

import xyz.carnage.manager.combo.ComboEventHandler;
import xyz.carnage.manager.item.CarnageItemGroups;
import xyz.carnage.manager.item.CarnageItems;
import xyz.carnage.manager.particle.CustomParticles;
import xyz.carnage.manager.sound.SoundManager;

public class CarnageManager {
    private final CustomParticles customParticles;
    private final CarnageItems carnageItems;
    private final CarnageItemGroups carnageItemGroups;
    private final SoundManager soundManager;
    private final ComboEventHandler comboEventHandler;


    public CarnageManager(CustomParticles customParticles, CarnageItems carnageItems, CarnageItemGroups carnageItemGroups, SoundManager soundManager, ComboEventHandler comboEventHandler) {
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

}