package xyz.carnage;

import xyz.carnage.entity.EntitiesRegistry;
import xyz.carnage.itemmgmt.ModItemGroups;
import xyz.carnage.itemmgmt.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.carnage.combos.ComboEventHandler;

public class Carnage implements ModInitializer {
	public static final String MOD_ID = "carnage"; // Mod constant ID
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID); // Setup logger
	@Override
	public void onInitialize() {
		System.out.println("Carnage onInitialize triggered!");
		LOGGER.info("Carnage onInitialize triggered!");

		CustomParticles.registerParticles();
		ModItems.initialize();
		ModItemGroups.registerItemGroups();
		CustomSounds.initialize();
		EntitiesRegistry.init(); // Wohooo mod entities :D
		LOGGER.info("Initializing " + MOD_ID);

		// Register Combo Event Handlers
		ComboEventHandler.registerEvents();
	}
}