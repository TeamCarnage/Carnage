package xyz.carnage.entity.client;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import xyz.carnage.entity.BrineBreakerEntity;

public class BrinebreakerEntityRenderer extends EntityRenderer<BrineBreakerEntity> {
    public BrinebreakerEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(BrineBreakerEntity entity) {
        return new Identifier("xyz.carnage", "textures/entity/brinebreaker_thrown.png");
    }
}