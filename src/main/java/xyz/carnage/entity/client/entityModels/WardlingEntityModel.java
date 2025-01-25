package xyz.carnage.entity.client.entityModels;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import xyz.carnage.entity.customEntities.WardlingEntity;

import static xyz.carnage.Carnage.MOD_ID;

public class WardlingEntityModel extends SinglePartEntityModel<WardlingEntity> {
    public static final EntityModelLayer WARDLING = new EntityModelLayer(Identifier.of(MOD_ID, "wardling"), "main");

    private final ModelPart root;
    private final ModelPart body2;
    private final ModelPart rightarm;
    private final ModelPart leftarm;
    private final ModelPart body;
    private final ModelPart leftkeg;
    private final ModelPart rightleg;
    private final ModelPart head;

    public WardlingEntityModel(ModelPart root) {
        this.root = root;
        this.body2 = root.getChild("body2");
        this.rightarm = this.body2.getChild("rightarm");
        this.leftarm = this.body2.getChild("leftarm");
        this.body = this.body2.getChild("body");
        this.leftkeg = this.body2.getChild("leftkeg");
        this.rightleg = this.body2.getChild("rightleg");
        this.head = this.body2.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body2 = modelPartData.addChild("body2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 25.0F, 0.0F));
        ModelPartData rightarm = body2.addChild("rightarm", ModelPartBuilder.create().uv(32, 0).cuboid(-7.0F, -13.0F, -2.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData leftarm = body2.addChild("leftarm", ModelPartBuilder.create().uv(44, 0).cuboid(5.0F, -13.0F, -2.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData body = body2.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -15.0F, -3.0F, 10.0F, 12.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData leftkeg = body2.addChild("leftkeg", ModelPartBuilder.create().uv(28, 18).cuboid(1.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData rightleg = body2.addChild("rightleg", ModelPartBuilder.create().uv(28, 18).cuboid(-8.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, 0.0F));
        ModelPartData head = body2.addChild("head", ModelPartBuilder.create().uv(0, 52).cuboid(-9.0F, -27.0F, -1.0F, 18.0F, 12.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 27).cuboid(-4.0F, -21.0F, -2.0F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(WardlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        getPart().render(matrices, vertices, light, overlay, color);
    }
}