package xyz.carnage.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;
import xyz.carnage.entity.BrineBreakerEntity;

public class BrinebreakerEntityModel extends EntityModel<BrineBreakerEntity> {
    public static final EntityModelLayer BRINEBREAKER = new EntityModelLayer(Identifier.of(Carnage.MOD_ID, "brinebreaker"), "main");
    private final ModelPart BrineBreakerEntiy;
    public BrinebreakerEntityModel(ModelPart root) {
        this.BrineBreakerEntiy = root.getChild("BrinebreakerEntity");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData BrinebreakerEntity = modelPartData.addChild("BrinebreakerEntity", ModelPartBuilder.create().uv(10, 0).cuboid(0.0F, -14.0F, -1.0F, 1.0F, 14.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-1.0F, -15.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 0).cuboid(-2.0F, -16.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 6).cuboid(2.0F, -18.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 2).cuboid(0.0F, -19.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 9).cuboid(-2.0F, -18.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.0F, -14.0F, -0.5F, 5.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(BrineBreakerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        BrineBreakerEntity.render(matrices, vertexConsumer, light, overlay, color);
    }
}

