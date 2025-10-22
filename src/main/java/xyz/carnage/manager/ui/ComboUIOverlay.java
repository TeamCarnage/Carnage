package xyz.carnage.manager.ui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import static xyz.carnage.Carnage.MOD_ID;

public class ComboUIOverlay implements HudRenderCallback {
    private static final Identifier comboIcon = Identifier.of(MOD_ID, "/textures/ui/combo.png");
    
    private static int comboCount = 0;
    
    private static boolean visible = false;
    private static float animationProgress = 0.0f; // 0.0 = hidden, 1.0 = fully shown
    private static boolean appearing = false;
    private static boolean disappearing = false;
    
    private static final float appearSpeed = 0.05f;
    private static final float disappearSpeed = 0.04f;

    public static void setComboCount(int value) {
        comboCount = value;
    }
    
    public static void show() {
        visible = true;
        appearing = true;
        disappearing = false;
    }
    
    public static void hide() {
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

        int screenWidth = context.getScaledWindowWidth();
        int counterImgWidth = 100;
        int counterImgHeight = 22;
        int x = (screenWidth / 2) - (counterImgWidth / 2);
        int y = 10;
        
        float alpha = animationProgress;
        float scale = 0.8f + (0.2f * animationProgress);

        context.getMatrices().push();
        context.getMatrices().translate(screenWidth / 2.0, y + counterImgHeight / 2.0, 0);
        context.getMatrices().scale(scale, scale, 1);
        context.getMatrices().translate((counterImgWidth * -1) / 2.0, (counterImgHeight * -1) / 2.0, 0);
        context.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        context.drawTexture(comboIcon, 0, 0, 0, 0, counterImgWidth, counterImgHeight, counterImgWidth, counterImgHeight);
        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        context.getMatrices().pop();
        
        String comboText = "Combo: " + comboCount;
        int textWidth = client.textRenderer.getWidth(comboText);
        int textX = (screenWidth / 2) - (textWidth / 2);
        int textY = y + (y / 2) + 2;

        int color = ((int)(alpha * 255) << 24) | 0xFFFFFF;
        context.drawText(client.textRenderer, comboText, textX, textY, color, true);
    }
}
