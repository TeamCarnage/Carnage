package xyz.carnage.manager.entity.brinebreaker.entityModels;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import xyz.carnage.manager.entity.brinebreaker.BrinebreakerEntity;

import static xyz.carnage.Carnage.MOD_ID;

public class BrinebreakerEntityModel extends EntityModel<BrinebreakerEntity> {
    public static final EntityModelLayer BRINEBREAKER = new EntityModelLayer(Identifier.of(MOD_ID, "brinebreaker"), "main");
    private final ModelPart bone;
    public BrinebreakerEntityModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(30, 42).cuboid(8.3134F, -1.0F, -13.1528F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(12, 44).cuboid(-9.6866F, -1.0F, -29.1528F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(20, 44).cuboid(-8.6866F, -1.0F, -30.1528F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(48, 3).cuboid(-8.6866F, -1.0F, -27.1528F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(-7.6866F, -1.0F, -29.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(24, 6).cuboid(-6.6866F, -1.0F, -28.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(24, 12).cuboid(-5.6866F, -1.0F, -27.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(24, 18).cuboid(-4.6866F, -1.0F, -26.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(22, 24).cuboid(-3.6866F, -1.0F, -25.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(22, 30).cuboid(-2.6866F, -1.0F, -24.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(34, 24).cuboid(-1.6866F, -1.0F, -23.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(34, 30).cuboid(-0.6866F, -1.0F, -22.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 35).cuboid(4.3134F, -1.0F, -17.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(36, 0).cuboid(3.3134F, -1.0F, -18.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(36, 6).cuboid(2.3134F, -1.0F, -19.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(12, 36).cuboid(1.3134F, -1.0F, -20.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(36, 12).cuboid(0.3134F, -1.0F, -21.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 24).cuboid(9.3134F, -1.0F, -12.1528F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(36, 18).cuboid(7.3134F, -1.0F, -14.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(24, 36).cuboid(6.3134F, -1.0F, -15.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(36, 36).cuboid(5.3134F, -1.0F, -16.1528F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(8, 48).cuboid(9.3134F, -1.0F, -13.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 8).cuboid(6.3134F, -1.0F, -10.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 10).cuboid(8.3134F, -1.0F, -9.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(10.3134F, -1.0F, -12.1528F, 1.0F, 1.0F, 11.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(11.3134F, -1.0F, -11.1528F, 1.0F, 1.0F, 11.0F, new Dilation(0.0F))
                .uv(40, 42).cuboid(8.3134F, -1.0F, -8.1528F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(12, 48).cuboid(7.3134F, -1.0F, -9.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 42).cuboid(12.3134F, -1.0F, -8.1528F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 44).cuboid(12.3134F, -1.0F, -10.1528F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 42).cuboid(12.3134F, -1.0F, -9.1528F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 30).cuboid(12.3134F, -1.0F, -11.1528F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 32).cuboid(16.3134F, -1.0F, -7.1528F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 34).cuboid(17.3134F, -1.0F, -6.1528F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 46).cuboid(12.3134F, -1.0F, -7.1528F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 24).cuboid(14.3134F, -1.0F, -5.1528F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(48, 12).cuboid(14.3134F, -1.0F, -6.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 14).cuboid(13.3134F, -1.0F, -5.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 27).cuboid(12.3134F, -1.0F, -2.1528F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 48).cuboid(12.3134F, -1.0F, -3.1528F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-14.0F, -7.0F, 0.0F, 1.5708F, 0.0F, -0.7854F));

        ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(48, 6).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-11.6866F, 0.0F, -30.1528F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r2 = bone.addChild("cube_r2", ModelPartBuilder.create().uv(8, 46).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.6866F, 0.0F, -32.1528F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r3 = bone.addChild("cube_r3", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-8.6866F, 0.0F, -31.1528F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r4 = bone.addChild("cube_r4", ModelPartBuilder.create().uv(46, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-10.6866F, 0.0F, -32.1528F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r5 = bone.addChild("cube_r5", ModelPartBuilder.create().uv(40, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.6866F, 0.0F, -31.1528F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r6 = bone.addChild("cube_r6", ModelPartBuilder.create().uv(34, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.6866F, 0.0F, -29.1528F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r7 = bone.addChild("cube_r7", ModelPartBuilder.create().uv(28, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-10.6866F, 0.0F, -28.1528F, 0.0F, 1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(BrinebreakerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bone.render(matrices, vertexConsumer, light, overlay, color);
    }
}