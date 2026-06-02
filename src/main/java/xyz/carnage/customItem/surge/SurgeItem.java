package xyz.carnage.customItem.surge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import xyz.carnage.Carnage;
import xyz.carnage.manager.sound.SoundManager;
import xyz.carnage.manager.combo.ComboManager;
import xyz.carnage.manager.combo.ComboTracker;

import java.util.List;

import static xyz.carnage.Carnage.MOD_ID;
import static xyz.carnage.manager.sound.SoundManager.SOUND_EVENTS;


public class SurgeItem extends SwordItem {

    public SurgeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            World world = attacker.getWorld();
            }

        ComboTracker tracker = null;
        if (attacker instanceof PlayerEntity) {
            tracker = ComboManager.getComboTracker((PlayerEntity) attacker);
        }
        PlayerEntity player = (PlayerEntity) attacker;
        if (tracker.getComboCount()/2 >= 15) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 150, 1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 150, 0)); // this knife can and will zap, maybe cap if you will :3 - tman
            SoundManager.playCustomSound("carnage:surge_crit", player); // Plays a sound after 15 crits
            tracker.reset();
        }
        tracker.clearHitFlag();
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient) {
            player.getItemCooldownManager().set(this, 50);
            Entity targetPos = entityLookingAt(player);
            if (targetPos != null) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 300, 0));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 0));
                world.playSound(null,player.getX(),player.getY(),player.getZ(),SOUND_EVENTS.get(Identifier.of(MOD_ID, "surge_discharge")),SoundCategory.PLAYERS,1.0F,1.0F);
                SoundManager.playCustomSound("carnage:surge_use", player);
                createLightningWave(player);
            }
        }
        return TypedActionResult.success(stack);

    }

    public Entity entityLookingAt(PlayerEntity player) {
        // Raycast :D
        World world = player.getWorld();
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d direction = player.getRotationVec(1.0F);
        double maxDistance = 100.0;
        
        

        Entity closestEntity = null;
        double closestDistance = Double.MAX_VALUE;

        // Checks for all entities in the path of the raycast
        for (Entity entity : world.getOtherEntities(player, player.getBoundingBox().stretch(direction.multiply(maxDistance)).expand(10))) {
            if (entity.isSpectator() || !entity.isAttackable()) {
                continue;
            }

            Vec3d entityPos = entity.getPos();
            Vec3d toEntity = entityPos.subtract(start);
            double t = toEntity.dotProduct(direction);

            // Checks for if the entity is within range
            if (t < 0 || t > maxDistance) {
                continue;
            }

            // get the entity closest to the raycast (within 2 blocks of looking distance - literal aimbot)
            Vec3d closestPoint = start.add(direction.multiply(t));
            double distToRay = entityPos.distanceTo(closestPoint);

            if (distToRay < 2.0 && t < closestDistance) {
                // Check if theres a block between the player and targetted entity
                Vec3d endPoint = closestPoint;
                HitResult blockHit = world.raycast(new RaycastContext(start, endPoint, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
                if (blockHit.getType() == HitResult.Type.BLOCK) {
                    Carnage.LOGGER.info("Block there :D");
                    continue;
                }

                closestDistance = t;
                closestEntity = entity;
            }
        }
        if (closestEntity == null) closestEntity = player;
        return closestEntity;
    }

    // Gets all entities within a range of the entity that is passed as a parameter
    public List<Entity> getEntitiesWithinRange(Entity entity, double range) {
        World world = entity.getWorld();
        Vec3d center = entity.getPos();
        List<Entity> otherEntities = world.getOtherEntities(entity, entity.getBoundingBox().expand(range), e -> e.isAttackable());
        otherEntities.add(entity);
        return otherEntities;
    }

    // Creates lightning bolts on all entities within set range of the entity that the player was looking at
    public void createLightningWave(PlayerEntity player) {
        Entity entity = entityLookingAt(player);
        List<Entity> entities = getEntitiesWithinRange(entity, 5.0);
        if (entities.contains(player)) entities.remove(player);
        if (entities.isEmpty()) entities.add(player);
        for (Entity target : entities) {
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(player.getWorld());
            if (lightning != null) {
                lightning.setPos(target.getX() + 0.5, target.getY(), target.getZ() + 0.5);
                player.getWorld().spawnEntity(lightning);
            }
        }
    }
}