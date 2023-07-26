package net.thaw.funvillagers.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class EntityCustomVillager extends VillagerEntity {

    private PlayerEntity heldPlayer;
    private final MobEntity entity;

    public EntityCustomVillager(EntityType<? extends VillagerEntity> entityType, World world, MobEntity entity) {
        super(entityType, world);
        this.entity = entity;

        // Clear existing goals
        clearGoals();

        // Add custom goals and other AI tasks here
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new PickUpPlayerGoal(this, this.entity));
        goalSelector.add(2, new ThrowPlayerGoal(this));
        goalSelector.add(3, new WanderAroundFarGoal(this, 0.3D));
        goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.add(5, new LookAroundGoal(this));
    }

    private void clearGoals() {
        goalSelector.clear((goal) -> true);
        targetSelector.clear((goal) -> true);
    }

    public EntityCustomVillager(EntityType<EntityCustomVillager> entityType, World world) {
        super(entityType, world);
        this.entity = null;
    }

    public boolean isHoldingPlayer(PlayerEntity player) {
        // Check if the Villager is holding the specified player.
        return heldPlayer != null && heldPlayer == player;
    }

    public void grabPlayer(PlayerEntity player) {
        // Called when the Villager picks up the player.
        heldPlayer = player;
    }

    public void releasePlayer(PlayerEntity player) {
        // Release the player from the Villager's grasp.
        if (heldPlayer == player) {
            heldPlayer = null;
        }
    }
}