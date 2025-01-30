package xyz.carnage.mixin;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    protected abstract void addModel(ModelIdentifier modelId);
    // NOTHING FUCKING WORKS
    // -diaduck
}