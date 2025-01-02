package xyz.carnage;

import xyz.carnage.items.ModItemGroups;
import xyz.carnage.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Carnage implements ModInitializer {
	// Define mod ID constant - must match your fabric.mod.json
	public static final String MOD_ID = "carnage";

	// Create a logger for your mod
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModItemGroups.registerItemGroups();

		LOGGER.info("Initializing " + MOD_ID);
	}
}