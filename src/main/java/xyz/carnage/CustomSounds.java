package xyz.carnage;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class CustomSounds {
    private CustomSounds() {
        // private empty constructor to avoid accidental instantiation
    }

    // ITEM_METAL_WHISTLE is the name of the custom sound event
    // and is called in the mod to use the custom sound
    public static final SoundEvent PHANTOMS_KISS_HIT = registerSound("phantoms_kiss_hit");
    public static final SoundEvent  JUGGERNAUT_DISCHARGE = registerSound("JuggernautDischarge");

    // actual registration of all the custom SoundEvents
    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(Carnage.MOD_ID, id); // Currently this shits useless because it wont work inside of my Item but well just keep it
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    // This static method starts class initialization, which then initializes
    // the static class variables (e.g. ITEM_METAL_WHISTLE).
    public static void initialize() {
        Carnage.LOGGER.info("Registering " + Carnage.MOD_ID + " Sounds");
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
                    // these are some amazing GPT comments, deamoz.  -DiaDuck
    }
}