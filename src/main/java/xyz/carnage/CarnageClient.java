package xyz.carnage;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import xyz.carnage.entity.EntitiesRegistry;
import xyz.carnage.entity.client.entityModels.BrinebreakerEntityModel;
import xyz.carnage.entity.client.entityModels.WardlingEntityModel;
import xyz.carnage.entity.client.entityRenderers.BrinebreakerEntityRenderer;
import xyz.carnage.entity.client.entityRenderers.WardlingEntityRenderer;
import xyz.carnage.itemmgmt.ModItems;

import static xyz.carnage.Carnage.LOGGER;

public class CarnageClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LOGGER.info("CarnageClient onInitialize triggered!");

        EntitiesRegistry.init();
        EntityModelLayerRegistry.registerModelLayer(BrinebreakerEntityModel.BRINEBREAKER, BrinebreakerEntityModel::getTexturedModelData);;
        EntityRendererRegistry.register(EntitiesRegistry.BRINEBREAKER, BrinebreakerEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(WardlingEntityModel.WARDLING, WardlingEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(EntitiesRegistry.WARDLING, WardlingEntityRenderer::new);


        // Model Predicate Registration
        try {
            // Register hotbar predicate for the PhantomsKiss item
            ModelPredicateProviderRegistry.register(
                    ModItems.PHANTOMS_KISS,
                    Identifier.of("carnage", "hotbar"),
                    (stack, world, entity, seed) -> {
                        if (entity instanceof PlayerEntity player) {
                            int slot = player.getInventory().getSlotWithStack(stack);
                            return (slot >= 0 && slot < 9) ? 1.0F : 0.0F;
                        }
                        return 0.0F;
                    }
            );


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
