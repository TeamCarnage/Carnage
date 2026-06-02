package xyz.carnage.manager.ui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import net.minecraft.util.Identifier;

public class UIManager implements HudRenderCallback {
    private static boolean visible = false;

    private static float animationProgress = 0.0f;
    private static boolean appearing = false;
    private static boolean disappearing = false;

    private static final float appearSpeed = 0.05f;
    private static final float disappearSpeed = 0.05f;

    private static Identifier uiTexture;



    public static void triggerUseUI(Identifier uiTexturePath) {
        uiTexture = uiTexturePath;
        visible = true;
        appearing = true;
        disappearing = false;
    }

    public void hideUI() {
        disappearing = true;
        appearing = false;
    }


    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        if (appearing) {
            animationProgress += appearSpeed;
            if (animationProgress >= 1.0f) {
                animationProgress = 1.0f;
                appearing = false;
            }
        } else if (disappearing) {
            animationProgress -= disappearSpeed;
            if (animationProgress <= 0.0f) {
                animationProgress = 0.0f;
                disappearing = false;
                visible = false;
            }
        }
        if (!visible && animationProgress <= 0.0f) return;

        float alpha = animationProgress;

        int textureX = 0;
        int textureY = 0;

        int textureWidth = 16;
        int textureHeight = 16;

        float scale = 2f;

        context.getMatrices().push();
        context.getMatrices().translate(textureX, textureY, 0);
        context.getMatrices().scale(scale, scale, 1.0F);
        context.getMatrices().translate(-textureX, -textureY, 0);
        context.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        context.drawTexture(uiTexture, 0,0,0,0, textureWidth, textureHeight, textureWidth, textureHeight);
        context.getMatrices().pop();
    }

}
