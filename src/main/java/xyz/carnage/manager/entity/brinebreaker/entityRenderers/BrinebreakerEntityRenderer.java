package xyz.carnage.manager.entity.brinebreaker.entityRenderers;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import xyz.carnage.Carnage;
import xyz.carnage.manager.entity.brinebreaker.BrinebreakerEntity;
import xyz.carnage.manager.entity.brinebreaker.entityModels.BrinebreakerEntityModel;

public class BrinebreakerEntityRenderer extends EntityRenderer<BrinebreakerEntity> {
    protected BrinebreakerEntityModel model;

    public BrinebreakerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new BrinebreakerEntityModel(ctx.getPart(BrinebreakerEntityModel.BRINEBREAKER));
    }



    public void render(BrinebreakerEntity BrinebreakerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, BrinebreakerEntity.prevYaw, BrinebreakerEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, BrinebreakerEntity.prevPitch, BrinebreakerEntity.getPitch()) + 90.0F));

        // this does  veil transparency stuff yay
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(
                RenderLayer.getEntityTranslucent(Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreakerentity.png"))
        );

        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        super.render(BrinebreakerEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BrinebreakerEntity entity) {
        return Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreakerentity.png");
    }
}