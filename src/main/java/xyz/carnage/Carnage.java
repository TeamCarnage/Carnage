package xyz.carnage;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.carnage.manager.ui.UIManager;
import xyz.carnage.manager.particle.CustomParticles;
import xyz.carnage.manager.item.CarnageItems;
import xyz.carnage.manager.item.CarnageItemGroups;
import xyz.carnage.manager.sound.SoundManager;
import xyz.carnage.manager.combo.ComboEventHandler;
import xyz.carnage.manager.ui.actionbar.ActionBarUIManager;

public class Carnage implements ModInitializer {
	public static final String MOD_ID = "carnage";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private CarnageManager carnageManager;

	public Carnage() {
		// No-arg constructor, required by Fabric
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Carnage onInitialize triggered!");

		// Create all dependencies needed for CarnageManager:
		ActionBarUIManager actionBarUIManager = new ActionBarUIManager();
		UIManager uiManager = new UIManager();
		CustomParticles customParticles = new CustomParticles();
		CarnageItems carnageItems = new CarnageItems();
		CarnageItemGroups carnageItemGroups = new CarnageItemGroups();
		SoundManager soundManager = new SoundManager();
		ComboEventHandler comboEventHandler = new ComboEventHandler();

		// Pass them to CarnageManager constructor
		carnageManager = new CarnageManager(
				uiManager,
				customParticles,
				carnageItems,
				carnageItemGroups,
				soundManager,
				comboEventHandler
		);

		carnageManager.initialize();

		LOGGER.info("Carnage initialized successfully.");
	}
}
