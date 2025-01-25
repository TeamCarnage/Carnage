package xyz.carnage.entity.client.entityRenderers;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;
import xyz.carnage.entity.client.entityModels.WardlingEntityModel;
import xyz.carnage.entity.customEntities.WardlingEntity;

import java.awt.*;

import static xyz.carnage.Carnage.MOD_ID;

public class WardlingEntityRenderer extends LivingEntityRenderer<WardlingEntity, WardlingEntityModel> {
    public WardlingEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WardlingEntityModel(context.getPart(WardlingEntityModel.WARDLING)), 0.5f);
    }

    @Override
    public Identifier getTexture(WardlingEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/wardling.png");
    }


}
