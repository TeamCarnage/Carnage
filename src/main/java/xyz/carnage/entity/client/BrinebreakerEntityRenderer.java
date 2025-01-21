package xyz.carnage.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderPhase;
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
import xyz.carnage.entity.BrineBreakerEntity;

public class BrinebreakerEntityRenderer extends EntityRenderer<BrineBreakerEntity> {
    protected BrinebreakerEntityModel model;

    public BrinebreakerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new BrinebreakerEntityModel(ctx.getPart(BrinebreakerEntityModel.BRINEBREAKER));
    }

    @Override
    public void render(BrineBreakerEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumer, int light) {
        matrices.push();

        if(!entity.isGrounded()) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getRenderRotation() * 5f));
            matrices.translate(0, -1.0f, 0);
        } else {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.groundOffset.y()));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.groundOffset.x()));
            matrices.translate(0, -1.0f, 0);
        }

        VertexConsumer vertexConsumer1 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumer, this.model.getLayer
                (Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreaker_entity.png")), false, false);
        this.model.render(matrices, vertexConsumer1, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumer, light);
    }

    @Override
    public Identifier getTexture(BrineBreakerEntity entity) {
        return Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreaker_entity.png");
    }
}
