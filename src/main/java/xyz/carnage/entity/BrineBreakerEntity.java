package xyz.carnage.entity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.TridentEntity;

import net.minecraft.world.World;
import xyz.carnage.entity.templates.ModTridentEntity;

public class BrineBreakerEntity extends ModTridentEntity {

    //public BrineBreakerEntity(EntityType<TridentEntity> entityType, World world) {

    public BrineBreakerEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }
}