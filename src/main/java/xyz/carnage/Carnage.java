package xyz.carnage;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import xyz.carnage.itemmgmt.ModItemGroups;
import xyz.carnage.itemmgmt.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.carnage.combos.ComboEventHandler;
import xyz.carnage.itemmgmt.ModItemGroups;
import xyz.carnage.itemmgmt.ModItems;

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
		LOGGER.info("Initializing " + MOD_ID);

		// Register Combo Event Handlers
		ComboEventHandler.registerEvents();
	}
}