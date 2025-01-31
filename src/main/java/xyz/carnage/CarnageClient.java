package xyz.carnage;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import xyz.carnage.manager.entity.EntitiesRegistry;
import xyz.carnage.manager.entity.brinebreaker.entityModels.BrinebreakerEntityModel;
import xyz.carnage.manager.entity.wardling.entityModels.WardlingEntityModel;
import xyz.carnage.manager.entity.brinebreaker.entityRenderers.BrinebreakerEntityRenderer;
import xyz.carnage.manager.entity.wardling.entityRenderers.WardlingEntityRenderer;

import static xyz.carnage.Carnage.LOGGER;

public class CarnageClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LOGGER.info("CarnageClient onInitialize triggered!");
        EntitiesRegistry.initialize();
        EntityModelLayerRegistry.registerModelLayer(BrinebreakerEntityModel.BRINEBREAKER, BrinebreakerEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(EntitiesRegistry.BRINEBREAKER, BrinebreakerEntityRenderer::new);


        EntityModelLayerRegistry.registerModelLayer(WardlingEntityModel.WARDLING, WardlingEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(EntitiesRegistry.WARDLING, WardlingEntityRenderer::new);
    }
}



