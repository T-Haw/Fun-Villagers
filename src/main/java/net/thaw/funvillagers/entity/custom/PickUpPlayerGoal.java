package net.thaw.funvillagers.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class PickUpPlayerGoal extends Goal {
    private final EntityCustomVillager villager;
    private final MobEntity entity;
    private PlayerEntity targetPlayer;

    public PickUpPlayerGoal(EntityCustomVillager villager, MobEntity entity) {
        this.villager = villager;
        this.entity = entity;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (villager.getTarget() instanceof PlayerEntity) {
            this.targetPlayer = (PlayerEntity) villager.getTarget();
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return targetPlayer != null && targetPlayer.isAlive() && villager.squaredDistanceTo(targetPlayer.getX(), targetPlayer.getY(), targetPlayer.getZ()) <= 2.0;
    }

    @Override
    public void start() {
        villager.getNavigation().stop();
        villager.setAttacking(true);
    }

    @Override
    public void stop() {
        villager.setAttacking(false);
    }

    @Override
    public void tick() {
        if (targetPlayer != null && !targetPlayer.isSpectator() && !targetPlayer.isInvulnerable()) {
            // The actual behavior for picking up the player goes here.
            // For example, you can set the player's position to be slightly above the Villager.
            double playerX = villager.getX();
            double playerY = villager.getY() + villager.getHeight() + 0.2;
            double playerZ = villager.getZ();
            targetPlayer.updatePosition(playerX, playerY, playerZ);
        }
    }
}
