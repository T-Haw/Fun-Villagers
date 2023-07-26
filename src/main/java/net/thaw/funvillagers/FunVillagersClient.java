package net.thaw.funvillagers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.thaw.funvillagers.entity.custom.EntityCustomVillager;
import net.thaw.funvillagers.entity.custom.PickUpPlayerGoal;
import net.thaw.funvillagers.entity.custom.ThrowPlayerGoal;

public class FunVillagersClient implements ModInitializer {

    public static EntityType<EntityCustomVillager> CUSTOM_VILLAGER_TYPE;

    @Override
    public void onInitialize() {
        // Create and register your custom Villager entity.
        CUSTOM_VILLAGER_TYPE = Registry.register(
                Registry.ENTITY_VILLAGER,
                new Identifier("funvillagers", "custom_villager"),
                FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, EntityCustomVillager::new)
                        .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build()
        );

        // After registering, clear the goals for your custom Villager
        CUSTOM_VILLAGER_TYPE.getSpawnGroup().clear();
        // Add custom goals (e.g., PickUpPlayerGoal and ThrowPlayerGoal) here
        CUSTOM_VILLAGER_TYPE.getSpawnGroup().add(0, new PickUpPlayerGoal(CUSTOM_VILLAGER_TYPE));
        CUSTOM_VILLAGER_TYPE.getSpawnGroup().add(1, new ThrowPlayerGoal(CUSTOM_VILLAGER_TYPE));
    }
}
