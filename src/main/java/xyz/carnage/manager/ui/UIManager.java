package xyz.carnage.manager.ui;

import xyz.carnage.CarnageManager;
import xyz.carnage.manager.ui.actionbar.ActionBarUIManager;
import xyz.carnage.manager.ui.actionbar.ComboUIManager;

public class UIManager {
    private final ActionBarUIManager actionBarUIManager;
    private final CarnageManager carnageManager;
    private final ComboUIManager comboUIManager;

    public UIManager(CarnageManager carnageManager) {
        this.actionBarUIManager = new ActionBarUIManager();
        this.comboUIManager = new ComboUIManager();
        this.carnageManager = new CarnageManager(this, carnageManager.getCustomParticles(), carnageManager.getCarnageItems(), carnageManager.getCarnageItemGroups(), carnageManager.getSoundManager(), carnageManager.getComboEventHandler());
    }

    public void initialize() {
        actionBarUIManager.initialise();
    }

}