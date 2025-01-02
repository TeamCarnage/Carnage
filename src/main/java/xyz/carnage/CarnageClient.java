package xyz.carnage;

import java.lang.reflect.Constructor;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import xyz.carnage.itemmgmt.ModItems;

import static xyz.carnage.Carnage.LOGGER;

public class CarnageClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("CarnageClient onInitializeClient triggered!");
        LOGGER.info("CarnageClient onInitialize triggered!");

        // Model Predicate Registration
        try {
            Constructor<Identifier> constructor = Identifier.class.getDeclaredConstructor(String.class, String.class);
            constructor.setAccessible(true); // Make it accessible
            Identifier identifier = constructor.newInstance("carnage", "hotbar");

            // Directly use Identifier creation
            ModelPredicateProviderRegistry.register(
                    ModItems.PHANTOMS_KISS,
                    new Identifier("carnage", "hotbar"),
                    (stack, world, entity, seed) -> {
                        if (entity instanceof PlayerEntity player) {
                            int slot = player.getInventory().getSlotWithStack(stack);
                            Carnage.LOGGER.info("Hotbar Predicate Check: Slot = " + slot);
                            return (slot >= 0 && slot < 9) ? 1.0F : 0.0F;
                        }
                        return 0.0F;
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
