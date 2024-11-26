package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCompatSpawnReplacer {

    /*
    Current Compatiblities:
    -TerraFirmaCraft
     */

    @SubscribeEvent
    public static void onModdedSpawn(EntityJoinLevelEvent event) {

        // TerraFirmaCraft Horse -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_HORSES.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "horse"))) {

            Entity tfcHorse = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Horse horse = EntityType.HORSE.create(event.getLevel());
            if (horse != null) {
                horse.copyPosition(tfcHorse);
                horse.setCustomName(tfcHorse.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcHorse.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(horse);
                tfcHorse.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Donkey -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "donkey"))) {

            Entity tfcDonkey = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Donkey donkey = EntityType.DONKEY.create(event.getLevel());
            if (donkey != null) {
                donkey.copyPosition(tfcDonkey);
                donkey.setCustomName(tfcDonkey.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcDonkey.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(donkey);
                tfcDonkey.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Mule -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_MULES.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "mule"))) {

            Entity tfcMule = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Mule mule = EntityType.MULE.create(event.getLevel());
            if (mule != null) {
                mule.copyPosition(tfcMule);
                mule.setCustomName(tfcMule.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcMule.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(mule);
                tfcMule.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Pig -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_PIGS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "pig"))) {

            Entity tfcPig = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Pig pig = EntityType.PIG.create(event.getLevel());
            if (pig != null) {
                pig.copyPosition(tfcPig);
                pig.setCustomName(tfcPig.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcPig.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(pig);
                tfcPig.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Sheep -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "sheep"))) {

            Entity tfcSheep = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Sheep sheep = EntityType.SHEEP.create(event.getLevel());
            if (sheep != null) {
                sheep.copyPosition(tfcSheep);
                sheep.setCustomName(tfcSheep.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcSheep.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(sheep);
                tfcSheep.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Cow -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_COWS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "cow"))) {

            Entity tfcCow = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Cow cow = EntityType.COW.create(event.getLevel());
            if (cow != null) {
                cow.copyPosition(tfcCow);
                cow.setCustomName(tfcCow.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcCow.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(cow);
                tfcCow.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Goat -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_GOATS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "goat"))) {

            Entity tfcGoat = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Goat goat = EntityType.GOAT.create(event.getLevel());
            if (goat != null) {
                goat.copyPosition(tfcGoat);
                goat.setCustomName(tfcGoat.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcGoat.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(goat);
                tfcGoat.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Frog -> Vanilla (so it can be converted into an O-Variant)
        if (LivestockOverhaulCommonConfig.REPLACE_FROGS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "frog"))) {

            Entity tfcFrog = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Frog frog = EntityType.FROG.create(event.getLevel());
            if (frog != null) {
                frog.copyPosition(tfcFrog);
                frog.setCustomName(tfcFrog.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcFrog.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(frog);
                tfcFrog.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

    }
}