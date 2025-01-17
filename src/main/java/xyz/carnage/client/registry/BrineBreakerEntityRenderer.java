package xyz.carnage.client.registry;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import xyz.carnage.Carnage;
import xyz.carnage.entity.BrineBreakerEntity;

public class BrineBreakerEntityRenderer extends EntityRenderer<BrineBreakerEntity> {
    public static final Identifier TEXTURE = Identifier.of(Carnage.MOD_ID, "textures/entity/brinebreaker_thrown.png");
    private final TridentEntityModel model;

    public BrineBreakerEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new TridentEntityModel(context.getPart(EntityModelLayers.TRIDENT));
    }

    @Override
    public Identifier getTexture(BrineBreakerEntity entity) {
        return TEXTURE;
    }
    public static void init() {
        Carnage.LOGGER.info("Initializing renderererererer");
    }

}

