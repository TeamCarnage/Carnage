package xyz.carnage.manager.ui;

import xyz.carnage.CarnageManager;
import xyz.carnage.manager.ui.actionbar.ActionBarUIManager;

public class UIManager {
    private final ActionBarUIManager actionBarUIManager;
    private final CarnageManager carnageManager;

    public UIManager(ActionBarUIManager actionBarUIManager, CarnageManager carnageManager) {
        this.actionBarUIManager = actionBarUIManager;
        this.carnageManager = carnageManager;
    }

    public void initialize() {
        actionBarUIManager.initialise();
    }

}
