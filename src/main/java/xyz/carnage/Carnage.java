package xyz.carnage;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Carnage implements ModInitializer {
	public static final String MOD_ID = "carnage"; // Mod constant ID
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("Carnage onInitialize triggered!");
		CarnageManager.initialize();
		LOGGER.info("Initializing " + MOD_ID);
	}
}