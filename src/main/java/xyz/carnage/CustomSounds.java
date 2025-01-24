package xyz.carnage;

import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static xyz.carnage.Carnage.MOD_ID;

public class CustomSounds {
    public static final Map<Identifier, SoundEvent> SOUND_EVENTS = new HashMap<>();
    public static final Map<String, Map<String, Identifier>> ITEM_SOUNDS = new HashMap<>();

    public static void registerSound(String modId, String soundId) {
        SoundEvent soundEvent = SoundEvent.of(Identifier.of(modId, soundId));
        SOUND_EVENTS.put(Identifier.of(modId, soundId), soundEvent);
        Registry.register(Registries.SOUND_EVENT, Identifier.of(modId, soundId), soundEvent);
    }

    public static void registerItemSound(String itemId, String eventType, String soundId, String modId) {
        ITEM_SOUNDS.computeIfAbsent(itemId, k -> new HashMap<>())
                .put(eventType, Identifier.of(modId, soundId));
    }

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

    public static void onItemBreak(ItemStack stack, World world, PlayerEntity player) {
        playItemSound("break", stack, world, player); // Does nothing atm - i'll do something with it eventually lmao
    }

    public static void onItemHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            playItemSound("hit", stack, attacker.getWorld(), (PlayerEntity) attacker);
        }
    }

    public static void playSound(Entity source, @NotNull PlayerEntity player, String soundName, SoundCategory soundCategory, Float volume, Float pitch) {

        /*
        *   USAGE:
        *   source - is usually null.
        *   player - sets the location that the sound comes from, use the player entity that you want to hear the sound.
        *   soundName - is the file name of the sound. Use the name of the sound file e.g "phantoms_kiss_hit".
        *   soundCategory - is the sound category you want the sound to play, adjustable by the players in the settings menu. e.g SoundCategory.PLAYER
        *   volume - is how loud the sound is, usually set to 1.
        *   pitch - is the pitch of the sound (duh) usually set to 1.
        *
        *   Example: playSound(null, player, "soundName", SoundCategory.PLAYER, 1.0F, 1.0F);
        *
        * */

        SoundEvent soundEvent = SOUND_EVENTS.get(Identifier.of(MOD_ID, soundName));
        player.getWorld().playSound(
                source,
                player.getBlockPos(),
                soundEvent,
                soundCategory,
                volume,
                pitch
        );
    }

    public static void registerSounds(String modId) {
        // Register Sounds (not for the events).

        // Usage: registerSound(modId, "item ID - same as in ModItems")
        registerSound(modId, "phantoms_kiss_hit");
        registerSound(modId, "surge_discharge");

        // Register sound events (theres two types of event - hit and break - they're pretty self-explanitory) - dont use "break" eventType, it does nothing atm.

        // USAGE: registerItemSound("mod_id:item_name", "eventType", "sound file name", modId);
        registerItemSound("carnage:phantoms_kiss", "hit", "phantoms_kiss_hit", modId);
        registerItemSound("carnage:surge", "hit", "surge_discharge", modId);
    }

    public static void initialise() {
        Carnage.LOGGER.info("Initialising Carnage Sounds.");
        registerSounds(MOD_ID);
    }
}
