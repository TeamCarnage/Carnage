package xyz.carnage;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

import static xyz.carnage.Carnage.MOD_ID;

public class CustomSounds {
    private static final Map<Identifier, SoundEvent> SOUND_EVENTS = new HashMap<>();
    private static final Map<String, Map<String, Identifier>> ITEM_SOUNDS = new HashMap<>();

    // Registers the sound (used in registerSounds)
    private static void registerSound(String modId, String soundId) {
        SoundEvent soundEvent = SoundEvent.of(Identifier.of(modId, soundId));
        SOUND_EVENTS.put(Identifier.of(modId, soundId), soundEvent);
        Registry.register(Registries.SOUND_EVENT, Identifier.of(modId, soundId), soundEvent);
    }

    // Registers the itemsound (used in registerSounds)
    private static void registerItemSound(String identifier, String eventType, String soundId, String modId) {
        ITEM_SOUNDS.computeIfAbsent(identifier, k -> new HashMap<>())
                .put(eventType, Identifier.of(modId, soundId));
    }

    // Plays a sound, used by the other functions in this class, uses any eventType (make sure to use one of the pre-defined three.).
    public static void playItemSound(String eventType, ItemStack stack, World world, PlayerEntity player) {
        String itemId = Registries.ITEM.getId(stack.getItem()).toString();
        Identifier soundId = ITEM_SOUNDS.getOrDefault(itemId, new HashMap<>()).get(eventType);


        if (soundId != null && SOUND_EVENTS.containsKey(soundId)) {
            SoundEvent soundEvent = SOUND_EVENTS.get(soundId);
            world.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    soundEvent,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
    }
    public static void playItemSound(String eventType, String soundName, World world, PlayerEntity player) {
        String itemId = soundName;
        Identifier soundId = ITEM_SOUNDS.getOrDefault(itemId, new HashMap<>()).get(eventType);


        if (soundId != null && SOUND_EVENTS.containsKey(soundId)) {
            SoundEvent soundEvent = SOUND_EVENTS.get(soundId);
            world.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    soundEvent,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
    }

    // Does nothing rn :D
    public static void onItemBreak(ItemStack stack, World world, PlayerEntity player) {
        playItemSound("break", stack, world, player); // Does nothing atm - i'll do something with it eventually lmao
    }


    // Called whenever the item hits an entity - must be called within each item class.
    public static void onItemHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            playItemSound("hit", stack, attacker.getWorld(), (PlayerEntity) attacker);
        }
    }

    // Plays a sound though the custom_sound eventType.
    public static void playCustomSound(String identifier, PlayerEntity player) {
            playItemSound("custom_sound", identifier, player.getWorld(), player);
    }

    public static void registerSounds(String modId) {
        // Register Sounds (not for the events).

        // Usage: registerSound(modId, "item ID - same as in ModItems")
        registerSound(modId, "surge_discharge");
        registerSound(modId, "phantoms_kiss_hit");

        // Register sound events (theres three types of event - hit, break and custom_sound - they're pretty self-explanitory) - dont use "break" eventType, it does nothing atm.

        // USAGE: registerItemSound("mod_id:item_name", "eventType", "sound file name", modId);
        registerItemSound("carnage:phantoms_kiss", "hit", "phantoms_kiss_hit", modId);
        registerItemSound("carnage:surge_crit", "custom_sound", "surge_discharge", modId);
        registerItemSound("carnage:surge_use", "custom_sound", "surge_discharge", modId);
    }

    public static void initialise() {
        Carnage.LOGGER.info("Initialising Carnage Sounds.");
        registerSounds(MOD_ID);
    }
}
