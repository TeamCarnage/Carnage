package xyz.carnage.manager.entity.wardling.entityRenderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;
import xyz.carnage.manager.entity.wardling.WardlingEntity;
import xyz.carnage.manager.entity.wardling.entityModels.WardlingEntityModel;

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