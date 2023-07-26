package net.thaw.funvillagers.entity.custom;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class ThrowPlayerGoal extends Goal {
    private final EntityCustomVillager villager;
    private PlayerEntity targetPlayer;

    public ThrowPlayerGoal(EntityCustomVillager villager) {
        this.villager = villager;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        targetPlayer = villager.getTarget() instanceof PlayerEntity ? (PlayerEntity) villager.getTarget() : null;
        return targetPlayer != null && targetPlayer.isAlive() && villager.isHoldingPlayer(targetPlayer);
    }

    @Override
    public boolean shouldContinue() {
        return targetPlayer != null && targetPlayer.isAlive() && villager.isHoldingPlayer(targetPlayer);
    }

    @Override
    public void start() {
        villager.setAttacking(true);
    }

    @Override
    public void stop() {
        villager.setAttacking(false);
        villager.releasePlayer(targetPlayer);
    }

    @Override
    public void tick() {
        if (targetPlayer != null && !targetPlayer.isSpectator() && !targetPlayer.isInvulnerable()) {
            // The actual behavior for throwing the player goes here.
            // For example, you can set the player's motion to simulate a throw.
            double motionX =0 /* Calculate motion X */;
            double motionY =0 /* Calculate motion Y */;
            double motionZ =0/* Calculate motion Z */;
            targetPlayer.addVelocity(motionX, motionY, motionZ);
        }
    }
}
