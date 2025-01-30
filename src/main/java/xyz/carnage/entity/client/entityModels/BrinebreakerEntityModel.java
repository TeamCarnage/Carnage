package xyz.carnage.entity.client.entityModels;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;
import xyz.carnage.entity.customEntities.BrineBreakerEntity;

public class BrinebreakerEntityModel extends EntityModel<BrineBreakerEntity> {
    public static final EntityModelLayer BRINEBREAKER = new EntityModelLayer(Identifier.of(Carnage.MOD_ID, "brinebreaker"), "main");
    private final ModelPart body;
    private final ModelPart base;
    private final ModelPart left_spike;
    private final ModelPart middle_spike;
    private final ModelPart right_spike;
    public BrinebreakerEntityModel(ModelPart root) {
        this.body = root.getChild("body");
        this.base = root.getChild("base");
        this.left_spike = root.getChild("left_spike");
        this.middle_spike = root.getChild("middle_spike");
        this.right_spike = root.getChild("right_spike");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 6).cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create().uv(4, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData left_spike = modelPartData.addChild("left_spike", ModelPartBuilder.create().uv(4, 3).cuboid(-2.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData middle_spike = modelPartData.addChild("middle_spike", ModelPartBuilder.create().uv(4, 3).cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData right_spike = modelPartData.addChild("right_spike", ModelPartBuilder.create().uv(0, 0).cuboid(1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(BrineBreakerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        body.render(matrices, vertexConsumer, light, overlay, color);
        base.render(matrices, vertexConsumer, light, overlay, color);
        left_spike.render(matrices, vertexConsumer, light, overlay, color);
        middle_spike.render(matrices, vertexConsumer, light, overlay, color);
        right_spike.render(matrices, vertexConsumer, light, overlay, color);
    }

    public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int defaultUv, float v, float v1, float v2, float transparency) {
    }
}