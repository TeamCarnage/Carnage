package xyz.carnage;

import xyz.carnage.manager.combo.ComboEventHandler;
import xyz.carnage.manager.item.CarnageItemGroups;
import xyz.carnage.manager.item.CarnageItems;
import xyz.carnage.manager.particle.CustomParticles;
import xyz.carnage.manager.sound.SoundManager;

public class CarnageManager {
    public static void initialize() {
        CustomParticles.initialize();
        CarnageItems.initialize();
        CarnageItemGroups.initialize();
        SoundManager.initialize();
        ComboEventHandler.initialize();
    }
}
