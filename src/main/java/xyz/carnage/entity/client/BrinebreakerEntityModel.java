package xyz.carnage.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import xyz.carnage.Carnage;
import xyz.carnage.entity.BrineBreakerEntity;

public class BrinebreakerEntityModel extends EntityModel<BrineBreakerEntity> {
    public static final EntityModelLayer BRINEBREAKER = new EntityModelLayer(Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreakernew"), "main");
    private final ModelPart brinebreaker;
    private final ModelPart bb_main;
    public BrinebreakerEntityModel(ModelPart root) {
        this.brinebreaker = root.getChild("brinebreaker");
        this.bb_main = root.getChild("bb_main");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData brinebreaker = modelPartData.addChild("brinebreaker", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(4, 7).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 7).cuboid(-0.5F, -16.0F, -0.5F, 1.0F, 14.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(16, 25).cuboid(-1.0F, -5.0F, -1.0F, 8.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -8.0F, 1.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.0F, -1.0F, 8.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -16.0F, 1.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(4, 11).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.75F, -15.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(BrineBreakerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        brinebreaker.render(matrices, vertexConsumer, light, overlay, color);
        bb_main.render(matrices, vertexConsumer, light, overlay, color);
    }
    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT;
    }
}