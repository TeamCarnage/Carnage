package xyz.carnage.entity;

import net.minecraft.component.Component;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;
import org.joml.Vector2f;
import xyz.carnage.itemmgmt.items.BrinebreakerItem;
import xyz.carnage.combos.ComboTracker;
import xyz.carnage.itemmgmt.ModItems;


public class BrineBreakerEntity extends TridentEntity {


    public Vector2f groundOffset;
    private boolean dealtDamage;

    public BrineBreakerEntity(EntityType<? extends BrineBreakerEntity> entityType, World world) {
        super(entityType, world);
        this.setStack(new ItemStack(ModItems.BRINEBREAKER));

    }

    public ItemStack getComponents() {
        return this.getItemStack(); // Returns the current ItemStack of the entity
    }



    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 4);

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
        }
    }





    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        if (result.getSide() == Direction.SOUTH) {
            groundOffset = new Vector2f(215f, 180f);
        }
        if (result.getSide() == Direction.NORTH) {
            groundOffset = new Vector2f(215f, 0f);
        }
        if (result.getSide() == Direction.EAST) {
            groundOffset = new Vector2f(215f, -90f);
        }
        if (result.getSide() == Direction.WEST) {
            groundOffset = new Vector2f(215f, 90f);
        }

        if (result.getSide() == Direction.DOWN) {
            groundOffset = new Vector2f(115f, 180f);
        }
        if (result.getSide() == Direction.UP) {
            groundOffset = new Vector2f(285f, 180f);
        }
    }

    public void setPreservedStack(ItemStack itemStack) {
    }
}