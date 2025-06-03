package xyz.carnage.manager.ui;

import xyz.carnage.CarnageManager;
import xyz.carnage.manager.ui.actionbar.ActionBarUIManager;
import xyz.carnage.manager.ui.actionbar.ComboUIManager;

public class UIManager {
    private final ActionBarUIManager actionBarUIManager;

    public UIManager() {
        this.actionBarUIManager = new ActionBarUIManager();
    }

    public void initialize() {
        actionBarUIManager.initialise();
    }

}