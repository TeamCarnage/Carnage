package xyz.carnage.itemmgmt.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PhantomsKissItem extends SwordItem {

    private static final Map<Integer, Task> scheduledTasks = new HashMap<>();

    public PhantomsKissItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient && world.getServer() != null) {
            // Immediate effects
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100, 0)); // Invisibility
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 1));   // Resistance

            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS,
                    0.5f, 1.2f);

            // Schedule velocity update and command

                // Update velocity
                Vec3d currentVelocity = player.getVelocity();
                player.setVelocity(currentVelocity.x, 1.0, currentVelocity.z);

                // Force client sync
                player.velocityModified = true;

                // Execute command
                world.getServer().getCommandManager().executeWithPrefix(
                        player.getCommandSource(),
                        "say pluh"
                );
            }
        world.getServer().getCommandManager().executeWithPrefix(
                player.getCommandSource(),
                "/summon tnt ~ ~ ~ {HasVisualFire:1b,fuse:1,explosion_power:2,block_state:{Name:\"minecraft:bone_block\"}}",


        // Immediate sound effect
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_PHANTOM_BITE, SoundCategory.PLAYERS,
                0.5f, 1.2f),

        // Set item cooldown
        player.getItemCooldownManager().set(this, 200);

        return TypedActionResult.success(stack);
    }

    private void scheduleDelayedTask(MinecraftServer server, int delayTicks, Runnable task) {
        int targetTick = server.getTicks() + delayTicks;
        scheduledTasks.put(targetTick, new Task(server, task));
    }

    public static void onServerTick(MinecraftServer server) {
        int currentTick = server.getTicks();
        Iterator<Map.Entry<Integer, Task>> iterator = scheduledTasks.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            if (entry.getKey() <= currentTick) {
                Task task = entry.getValue();
                if (task != null) {
                    task.run();
                }
                iterator.remove();
            }
        }
    }

    private static class Task {
        private final MinecraftServer server;
        private final Runnable action;

        public Task(MinecraftServer server, Runnable action) {
            this.server = server;
            this.action = action;
        }

        public void run() {
            server.execute(action);
        }
    }
}
