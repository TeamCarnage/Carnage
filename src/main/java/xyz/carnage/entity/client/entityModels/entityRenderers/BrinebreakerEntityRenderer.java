package xyz.carnage.entity.client.entityModels.entityRenderers;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import xyz.carnage.Carnage;
import xyz.carnage.entity.customEntities.BrineBreakerEntity;
import xyz.carnage.entity.client.entityModels.BrinebreakerEntityModel;

public class BrinebreakerEntityRenderer extends EntityRenderer<BrineBreakerEntity> {
    protected BrinebreakerEntityModel model;

    public BrinebreakerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new BrinebreakerEntityModel(ctx.getPart(BrinebreakerEntityModel.BRINEBREAKER));
    }

    public void render(BrineBreakerEntity BrineBreakerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, BrineBreakerEntity.prevYaw, BrineBreakerEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, BrineBreakerEntity.prevPitch, BrineBreakerEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider, this.model.getLayer(Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreakernew.png")), false, BrineBreakerEntity.isEnchanted());
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        super.render(BrineBreakerEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }



    @Override
    public Identifier getTexture(BrineBreakerEntity entity) {
        return Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreakernew.png");
    }
}


