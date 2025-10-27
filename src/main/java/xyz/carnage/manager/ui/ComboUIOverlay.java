package xyz.carnage.manager.ui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static xyz.carnage.Carnage.MOD_ID;

public class ComboUIOverlay implements HudRenderCallback {
    private static final Identifier comboIcon = Identifier.of(MOD_ID, "/textures/ui/combo.png");
    
    private static int comboCount = 0;
    
    private static boolean visible = false;
    private static float animationProgress = 0.0f; // 0.0 = hidden, 1.0 = fully shown
    private static boolean appearing = false;
    private static boolean disappearing = false;
    
    private static final float appearSpeed = 0.03f;
    private static final float disappearSpeed = 0.02f;

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

        float alpha = animationProgress;
        int comboCountColour = ((int)(alpha * 255) << 24) | 0xFFFFFF;
        int comboTextColour = ((int)(alpha * 255) << 24) | 0x00FA64;

        String countText = "x" + comboCount;
        Text comboText = Text.literal("Combo").formatted(Formatting.BOLD);

        int countTextX = 6;
        int countTextY = 6;
        int comboTextX = (client.textRenderer.getWidth(countText) + countTextX);
        int comboTextY = (client.textRenderer.fontHeight * 2);

        float scale = 1.5f;

        context.getMatrices().push();
        context.getMatrices().translate(countTextX, countTextY, 0);
        context.getMatrices().scale(scale, scale, 1.0F);
        context.getMatrices().translate(-countTextX, -countTextY, 0);

        context.drawText(client.textRenderer, countText, countTextX, countTextY, comboCountColour, true);

        context.getMatrices().pop();

        context.drawText(client.textRenderer, comboText, comboTextX, comboTextY, comboTextColour, true);
    }
}
