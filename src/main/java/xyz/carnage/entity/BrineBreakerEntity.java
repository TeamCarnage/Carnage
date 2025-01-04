package xyz.carnage.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.world.World;
import xyz.carnage.entity.templates.ModTridentEntity;

public class BrineBreakerEntity extends ModTridentEntity {
    public BrineBreakerEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }
}