package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import com.dragn0007.dragnlivestock.entities.bee.OBeeModel;
import com.dragn0007.dragnlivestock.entities.camel.CamelBreed;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.camel.OCamelBodyLayer;
import com.dragn0007.dragnlivestock.entities.camel.OCamelModel;
import com.dragn0007.dragnlivestock.entities.chicken.ChickenBreed;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.chicken.OChickenModel;
import com.dragn0007.dragnlivestock.entities.chicken.OChickenRenderLayer;
import com.dragn0007.dragnlivestock.entities.cod.OCod;
import com.dragn0007.dragnlivestock.entities.cow.CowBreed;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.OCowModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.Moobloom;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroom;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomModel;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomMushroomLayer;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkeyModel;
import com.dragn0007.dragnlivestock.entities.frog.OFrog;
import com.dragn0007.dragnlivestock.entities.frog.OFrogModel;
import com.dragn0007.dragnlivestock.entities.frog.OFrogRenderLayer;
import com.dragn0007.dragnlivestock.entities.goat.OGoat;
import com.dragn0007.dragnlivestock.entities.goat.OGoatBodyLayer;
import com.dragn0007.dragnlivestock.entities.goat.OGoatModel;
import com.dragn0007.dragnlivestock.entities.horse.HorseBreed;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorseman;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaMarkingLayer;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaModel;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.pig.OPigModel;
import com.dragn0007.dragnlivestock.entities.pig.OPigRenderLayer;
import com.dragn0007.dragnlivestock.entities.pig.PigBreed;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitMarkingLayer;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitModel;
import com.dragn0007.dragnlivestock.entities.rabbit.RabbitBreed;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmon;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmonModel;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.entities.sheep.OSheepModel;
import com.dragn0007.dragnlivestock.entities.sheep.OSheepRenderLayer;
import com.dragn0007.dragnlivestock.entities.sheep.SheepBreed;
import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
import com.dragn0007.dragnlivestock.entities.unicorn.UnicornBodyLayer;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.BovineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LONetwork;
import com.dragn0007.dragnlivestock.util.LOUtils;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static com.dragn0007.dragnlivestock.LivestockOverhaul.MODID;

/*
This class falls under the LGPL 3 license, as some code was referenced from
the Realistic Horse Genetics repository, though has since been heavily modified.
https://github.com/sekelsta/horse-colors/tree/1.20.1
*/

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnReplacer {
    //NOTE (EVGLX): The following beehive-specific code should not be used elsewhere, but I'm keeping it public because private functionality is stupid
    public static Map<BeehiveBlockEntity, CompoundTag> beehivesToUpdate = new HashMap<>();
    public static void handleBeehiveData(BlockEntity blockEntity) {
        if(blockEntity instanceof BeehiveBlockEntity beehiveBlockEntity) {
            CompoundTag beehiveData = beehiveBlockEntity.serializeNBT();
            ListTag stored = beehiveData.getList("Bees", 10);
            for (Tag beeData : stored) {
                CompoundTag entityData = ((CompoundTag) beeData).getCompound("EntityData");
                String id = entityData.getString("id");
                if (id.equals("minecraft:bee")) {
                    entityData.putString("id", MODID + ":o_bee"); /** see {@link Entity#getEncodeId()}  */
                }
            }
            beehivesToUpdate.put(beehiveBlockEntity, beehiveData);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            if(!beehivesToUpdate.isEmpty()) {
                // Beehives which need updates are added to beehivesToUpdate, then updated as the final step of the server tick in which they were marked.
                // This prevents updating the beehive mid-tick basically. <Arraylist>.clear() cannot be used while the beehive is iterating, only add/remove.
                beehivesToUpdate.forEach(IForgeBlockEntity::deserializeNBT);
                beehivesToUpdate.clear();
            }
        }
    }

    @SubscribeEvent
    public static void onSpawn(EntityJoinLevelEvent event) {
        //TODO: Vanilla animals spawn wrongly when replace spawn egg animals is false
        Random random = new Random();
        Level level = event.getLevel();
        Entity eventEntity = event.getEntity();
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();

        if (level.isClientSide) return;

        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get()) {
            //Horse
            if (LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && eventEntity instanceof Horse animal) {
                if (eventEntity.getClass() == Horse.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                    //spawn Headless Horseman
                    if (((month == Month.OCTOBER && (day == 31)) || (month == Month.NOVEMBER && (day == 1 || day == 2)) && LivestockOverhaulCommonConfig.ALLOW_HOLIDAY_EVENTS.get() && level.isNight() && level.getRandom().nextDouble() <= 0.12) ||
                            ((level.isNight() && level.getRandom().nextDouble() <= 0.02))) {
                        HeadlessHorseman headlessHorseman = EntityTypes.HEADLESS_HORSEMAN_ENTITY.get().create(level);
                        OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(level);
                        if (headlessHorseman != null) {
                            level.addFreshEntity(oHorse);
                            level.addFreshEntity(headlessHorseman);
                            oHorse.copyPosition(animal);
                            oHorse.setCustomName(Component.nullToEmpty("Daredevil"));
                            oHorse.setAge(animal.getAge());
                            oHorse.randomizeOHorseAttributes();
                            oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));
                            oHorse.setBreed(5);
                            oHorse.setVariant(2);
                            oHorse.setOverlayVariant(0);
                            oHorse.setFeathering(2);
                            oHorse.setManeType(1);
                            oHorse.setTailType(2);
                            oHorse.setEyeVariant(10);
                            oHorse.setSaddleItem(new ItemStack(LOItems.BLACK_LIGHT_SADDLE.get()));
                            oHorse.setDecorItem(new ItemStack(LOItems.MODERN_BLANKETS.get(DyeColor.BLACK).get()));
                            oHorse.updateContainerEquipment();
                            oHorse.setTamed(true);
                            oHorse.setHallow(true);
                            headlessHorseman.startRiding(oHorse);
                            if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                            animal.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }

                    //spawn normal horse
                    OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(level);
                    if (oHorse != null) {
                        //try to take on as many identifiers from the vanilla horse possible
                        oHorse.copyPosition(animal);
                        oHorse.setCustomName(animal.getCustomName());
                        oHorse.setOwnerUUID(animal.getOwnerUUID());
                        oHorse.setAge(animal.getAge());
                        oHorse.randomizeOHorseAttributes();
                        oHorse.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
                        oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));

                        //spawn breeds except for compat-only ones if the config allows it
                        if (LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
                            if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                                int[] breeds = {10, 13};
                                int randomIndex = new Random().nextInt(breeds.length);
                                oHorse.setBreed(breeds[randomIndex]);
                            } else if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                                int[] breeds = {1, 5, 6, 8, 12, 15, 16, 17, 19, 21, 22};
                                int randomIndex = new Random().nextInt(breeds.length);
                                oHorse.setBreed(breeds[randomIndex]);
                            } else if (ModList.get().isLoaded("deadlydinos")) {
                                oHorse.setBreed(random.nextInt(HorseBreed.values().length));
                            } else {
                                do oHorse.setBreed(random.nextInt(HorseBreed.values().length));
                                while (oHorse.getBreed() == 14);
                            }

                            oHorse.setManeType(1 + random.nextInt(4));
                            oHorse.setTailType(1 + random.nextInt(4));
                        } else {
                            oHorse.setBreed(0);
                            oHorse.setManeType(2);
                            oHorse.setTailType(1 + random.nextInt(4));
                        }

                        //spawn markings and colors by breed if the config allows it
                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            oHorse.setColorByBreed();
                            oHorse.setMarkingByBreed();
                            oHorse.setFeatheringByBreed();
                        } else {
                            oHorse.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                            oHorse.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                            oHorse.setFeathering(random.nextInt(OHorse.Feathering.values().length));
                        }

                        if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                            oHorse.setEyeColorByChance();
                        } else {
                            do oHorse.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                            while (oHorse.getEyeVariant() == 10);
                        }

                        if (LivestockOverhaulCommonConfig.DYNAMIC_RESOURCE_SPAWNS.get() && random.nextDouble() <= LivestockOverhaulCommonConfig.DYNAMIC_RESOURCE_SPAWN_PERCENT.get()) {
                            if (!(oHorse.level().getServer()).isSingleplayer()) {
                                int id = oHorse.getId();
                                String folderName = "base_colors/horse";
                                boolean useServerCoats = false;
                                if (oHorse.level().getServer() instanceof DedicatedServer dedicatedServer) {
                                    MinecraftServer.ServerResourcePackInfo serverResourcePackInfo = dedicatedServer.getProperties().serverResourcePackInfo.orElse(null);
                                    if (serverResourcePackInfo != null) {
                                        useServerCoats = !serverResourcePackInfo.url().isEmpty();
                                    }
                                }
                                LONetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> oHorse), new LONetwork.TextureRequest(id, folderName, useServerCoats));
                            } else {
                                oHorse.setVariantTexture(String.valueOf(LOUtils.getRandomClientCoat("base_colors/horse")));
                                oHorse.setOverlayVariantTexture(String.valueOf(LOUtils.getRandomClientPattern("pattern_overlays/horse")));
                            }

                            if (oHorse.getTextureResource().contains("missingno"))
                                oHorse.setVariantTexture(OHorseModel.Variant.variantFromOrdinal(oHorse.getVariant()).resourceLocation.toString());
                            if (oHorse.getOverlayLocation().contains("missingno"))
                                oHorse.setOverlayVariantTexture(EquineMarkingOverlay.overlayFromOrdinal(oHorse.getOverlayVariant()).resourceLocation.toString());
                        }

                        //discard vanilla horse once it's been successfully replaced on client and server
                        if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                        animal.remove(Entity.RemovalReason.DISCARDED);

                        //add new o-horse
                        level.addFreshEntity(oHorse);
                        if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                            LivestockOverhaul.LOGGER.debug("Replaced a vanilla horse at: " + oHorse.getOnPos());

                        //remove a certain percentage based on configs
                        if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oHorse.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                            if (level.isClientSide) oHorse.remove(Entity.RemovalReason.DISCARDED);
                            oHorse.remove(Entity.RemovalReason.DISCARDED);
                        }

                        //cancel the event when done
                        event.setCanceled(true);
                    }
                }
            }

            //Donkey
            if (LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && eventEntity instanceof Donkey animal) {
                if (eventEntity.getClass() == Donkey.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                    ODonkey oDonkey = EntityTypes.O_DONKEY_ENTITY.get().create(level);
                    if (oDonkey != null) {
                        oDonkey.copyPosition(animal);
                        oDonkey.setCustomName(animal.getCustomName());
                        oDonkey.setOwnerUUID(animal.getOwnerUUID());
                        oDonkey.setAge(animal.getAge());
                        oDonkey.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oDonkey.generateRandomMaxHealth());
                        oDonkey.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oDonkey.generateRandomSpeed());
                        oDonkey.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oDonkey.generateRandomJumpStrength());
                        oDonkey.setGender(random.nextInt(AbstractOMount.Gender.values().length));

                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            oDonkey.setColor();
                            oDonkey.setMarking();
                        } else {
                            oDonkey.setVariant(random.nextInt(ODonkeyModel.Variant.values().length));
                            oDonkey.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        }

                        level.addFreshEntity(oDonkey);
                        animal.remove(Entity.RemovalReason.DISCARDED);
                        if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                            LivestockOverhaul.LOGGER.debug("Replaced a vanilla donkey at: " + oDonkey.getOnPos());
                        if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oDonkey.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                            if (level.isClientSide) oDonkey.remove(Entity.RemovalReason.DISCARDED);
                            oDonkey.remove(Entity.RemovalReason.DISCARDED);
                        }
                        event.setCanceled(true);
                    }
                }
            }

            //Mule
            if (LivestockOverhaulCommonConfig.REPLACE_MULES.get() && eventEntity instanceof Mule animal) {
                if (eventEntity.getClass() == Mule.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                    OMule oMule = EntityTypes.O_MULE_ENTITY.get().create(level);
                    if (oMule != null) {
                        oMule.copyPosition(animal);
                        oMule.setCustomName(animal.getCustomName());
                        oMule.setOwnerUUID(animal.getOwnerUUID());
                        oMule.setAge(animal.getAge());
                        oMule.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oMule.generateRandomMaxHealth());
                        oMule.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oMule.generateRandomSpeed());
                        oMule.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oMule.generateRandomJumpStrength());
                        oMule.setGender(random.nextInt(AbstractOMount.Gender.values().length));

                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            oMule.setColor();
                            oMule.setMarking();
                            oMule.setFeatheringByBreed();
                        } else {
                            oMule.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                            oMule.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                            oMule.setFeathering(random.nextInt(OHorse.Feathering.values().length));
                        }

                        if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                        level.addFreshEntity(oMule);
                        animal.remove(Entity.RemovalReason.DISCARDED);
                        if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                            LivestockOverhaul.LOGGER.debug("Replaced a vanilla mule at: " + oMule.getOnPos());
                        event.setCanceled(true);
                    }
                }
            }

            //Cow
            if (LivestockOverhaulCommonConfig.REPLACE_COWS.get() && eventEntity instanceof Cow animal) {
                if (eventEntity.getClass() == Cow.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                    if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get()) {
                        if (level.getRandom().nextDouble() < 0.02) {
                            Moobloom moobloom = EntityTypes.MOOBLOOM_ENTITY.get().create(level);
                            if (moobloom != null) {
                                moobloom.copyPosition(animal);
                                level.addFreshEntity(moobloom);

                                if (level.getBiome(eventEntity.blockPosition()).is(Biomes.PLAINS)) {
                                    int[] variants = {12, 13, 14, 15, 16, 17, 18};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    moobloom.setPlantType(variants[randomIndex]);
                                } else if (level.getBiome(eventEntity.blockPosition()).is(Biomes.SUNFLOWER_PLAINS)) {
                                    int[] variants = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    moobloom.setPlantType(variants[randomIndex]);
                                } else if (level.getBiome(eventEntity.blockPosition()).is(Biomes.TAIGA)) {
                                    int[] variants = {19, 20};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    moobloom.setPlantType(variants[randomIndex]);
                                } else if (level.getBiome(eventEntity.blockPosition()).is(Biomes.SAVANNA)) {
                                    if (ModList.get().isLoaded("thatsjustpeachy")) moobloom.setPlantType(21);
                                }

                                moobloom.setVariant(moobloom.getPlantType());
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
                                moobloom.setBreed(0);
                                moobloom.setGender(0);
                                moobloom.setQuality(100);
                                if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                                level.addFreshEntity(moobloom);
                                animal.remove(Entity.RemovalReason.DISCARDED);
                                event.setCanceled(true);
                            }
                        }
                    }

                    OCow oCow = EntityTypes.O_COW_ENTITY.get().create(level);
                    if (oCow != null) {
                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                                if (random.nextDouble() < 0.20) {
                                    do oCow.setBreed(random.nextInt(CowBreed.Breed.values().length));
                                    while (oCow.getBreed() == 10);
                                } else {
                                    int[] variants = {1, 2, 4, 5};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oCow.setBreed(variants[randomIndex]);
                                }
                            } else if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                                if (random.nextDouble() < 0.20) {
                                    do oCow.setBreed(random.nextInt(CowBreed.Breed.values().length));
                                    while (oCow.getBreed() == 10);
                                } else {
                                    oCow.setBreed(9);
                                }
                            } else {
                                int[] variants = {0, 6, 7, 8};
                                int randomIndex = new Random().nextInt(variants.length);
                                oCow.setBreed(variants[randomIndex]);
                            }
                        } else {
                            do oCow.setBreed(random.nextInt(CowBreed.Breed.values().length));
                            while (oCow.getBreed() == 10);
                        }

                        oCow.copyPosition(animal);
                        if (oCow.getBreed() == 10) oCow.setGender(1);
                        else oCow.setGender(random.nextInt(OCow.Gender.values().length));
                        if (LivestockOverhaulCommonConfig.QUALITY.get()) oCow.setQuality(random.nextInt(30));

                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            oCow.setColorByBreed();
                            oCow.setMarkingByBreed();
                            oCow.setHornsByBreed();
                        } else {
                            oCow.setVariant(random.nextInt(OCowModel.Variant.values().length));
                            oCow.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
                            oCow.setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
                        }

                        if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                        level.addFreshEntity(oCow);
                        animal.remove(Entity.RemovalReason.DISCARDED);
                        if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oCow.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                            if (level.isClientSide) oCow.remove(Entity.RemovalReason.DISCARDED);
                            oCow.remove(Entity.RemovalReason.DISCARDED);
                        }
                        event.setCanceled(true);
                    }
                }
            }

            //Chicken
            if (LivestockOverhaulCommonConfig.REPLACE_CHICKENS.get() && eventEntity instanceof Chicken animal) {
                if (eventEntity.getClass() == Chicken.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                    OChicken oChicken = EntityTypes.O_CHICKEN_ENTITY.get().create(level);
                    if (oChicken != null) {
                        oChicken.copyPosition(animal);
                        oChicken.setCustomName(animal.getCustomName());
                        oChicken.setAge(animal.getAge());
                        oChicken.setGender(random.nextInt(OChicken.Gender.values().length));
                        oChicken.setChickenJockey(animal.isChickenJockey());

//                        if (vanillachicken.isChickenJockey()) {
//                            List<Entity> passengers = vanillachicken.getPassengers().stream().collect(Collectors.toList());
//                            if (oChicken != null && passengers != null) {
//                                for (Entity passenger : passengers) {
//                                    passenger.startRiding(oChicken, true);
//                                }
//                            }
//                        }

                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                                if (random.nextDouble() < 0.15) {
                                    int[] variants = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oChicken.setBreed(variants[randomIndex]);
                                } else {
                                    int[] variants = {2, 4, 10};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oChicken.setBreed(variants[randomIndex]);
                                }
                            } else if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                                if (random.nextDouble() < 0.15) {
                                    int[] variants = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oChicken.setBreed(variants[randomIndex]);
                                } else {
                                    int[] variants = {3, 7, 9};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oChicken.setBreed(variants[randomIndex]);
                                }
                            } else {
                                if (random.nextDouble() < 0.15) {
                                    int[] variants = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oChicken.setBreed(variants[randomIndex]);
                                } else {
                                    int[] variants = {0, 1, 5, 8};
                                    int randomIndex = new Random().nextInt(variants.length);
                                    oChicken.setBreed(variants[randomIndex]);
                                }
                            }
                        } else {
                            oChicken.setBreed(random.nextInt(ChickenBreed.Breed.values().length));
                        }

                        if (LivestockOverhaulCommonConfig.QUALITY.get()) oChicken.setQuality(random.nextInt(30));

                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            oChicken.setColorByBreed();
                            oChicken.setMarkingByBreed();
                        } else {
                            oChicken.setVariant(random.nextInt(OChickenModel.Variant.values().length));
                            oChicken.setOverlayVariant(random.nextInt(OChickenRenderLayer.Marking.values().length));
                        }

                        if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                        level.addFreshEntity(oChicken);
                        animal.remove(Entity.RemovalReason.DISCARDED);
                        if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oChicken.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                            if (level.isClientSide) oChicken.remove(Entity.RemovalReason.DISCARDED);
                            oChicken.remove(Entity.RemovalReason.DISCARDED);
                        }
                        if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                            LivestockOverhaul.LOGGER.debug("Replaced a vanilla chicken at: " + oChicken.getOnPos());
                        event.setCanceled(true);

                    }
                }
            }
        }

        //Salmon
        OSalmon oSalmon = EntityTypes.O_SALMON_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.REPLACE_SALMON.get() && eventEntity instanceof Salmon animal) {
            if (eventEntity.getClass() == Salmon.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                if (oSalmon != null) {
                    oSalmon.copyPosition(animal);
                    oSalmon.setCustomName(animal.getCustomName());
                    oSalmon.setVariant(random.nextInt(OSalmonModel.Variant.values().length));
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oSalmon);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla salmon at: " + oSalmon.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Cod
        OCod oCod = EntityTypes.O_COD_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.REPLACE_COD.get() && eventEntity instanceof Cod animal) {
            if (eventEntity.getClass() == Cod.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                if (oCod != null) {
                    oCod.copyPosition(animal);
                    oCod.setCustomName(animal.getCustomName());
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oCod);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla cod at: " + oCod.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Bee
        if (LivestockOverhaulCommonConfig.REPLACE_BEES.get() && (eventEntity.getClass() == Bee.class)) {
            Bee animal = (Bee) eventEntity;
            if (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) {
                OBee oBee = EntityTypes.O_BEE_ENTITY.get().create(level);
                if (oBee != null) {
                    oBee.setCustomName(animal.getCustomName());
                    oBee.deserializeNBT(animal.serializeNBT());
                    oBee.setVariant(random.nextInt(OBeeModel.Variant.values().length));
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oBee);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla bee at: " + oBee.getOnPos());
                    if (oBee.getHivePos() != null) {
                        BlockEntity blockEntity = level.getBlockEntity(oBee.getHivePos());
                        handleBeehiveData(blockEntity);
                    }
                }
                event.setCanceled(true);
            }
        }

        //Rabbit
        ORabbit oRabbit = EntityTypes.O_RABBIT_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.REPLACE_RABBITS.get() && eventEntity instanceof Rabbit animal) {
            if (eventEntity.getClass() == Rabbit.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                if (oRabbit != null) {
                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oRabbit.setBreedByWildStatus();
                        oRabbit.setMarkingByWildStatus();
                        if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            int[] variants = {4, 7, 10, 14};
                            int randomIndex = new Random().nextInt(variants.length);
                            oRabbit.setVariant(variants[randomIndex]);
                        } else if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                            int[] variants = {1, 9, 11};
                            int randomIndex = new Random().nextInt(variants.length);
                            oRabbit.setVariant(variants[randomIndex]);
                        } else {
                            oRabbit.setColorByWildStatus();
                        }
                    } else {
                        oRabbit.setBreed(random.nextInt(RabbitBreed.Breed.values().length));
                        oRabbit.setVariant(random.nextInt(ORabbitModel.Variant.values().length));
                        oRabbit.setOverlayVariant(random.nextInt(ORabbitMarkingLayer.Overlay.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.QUALITY.get()) oRabbit.setQuality(random.nextInt(30));
                    oRabbit.copyPosition(animal);
                    oRabbit.setCustomName(animal.getCustomName());
                    oRabbit.setAge(animal.getAge());
                    oRabbit.setGender(random.nextInt(ORabbit.Gender.values().length));
                    oRabbit.setDewlapByGender();
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oRabbit);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oRabbit.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (level.isClientSide) oRabbit.remove(Entity.RemovalReason.DISCARDED);
                        oRabbit.remove(Entity.RemovalReason.DISCARDED);
                    }
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla rabbit at: " + oRabbit.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Sheep
        OSheep oSheep = EntityTypes.O_SHEEP_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() && eventEntity instanceof Sheep animal) {
            if (eventEntity.getClass() == Sheep.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                if (oSheep != null) {
                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            if (random.nextDouble() < 0.20) {
                                oSheep.setBreed(random.nextInt(SheepBreed.Breed.values().length));
                            } else {
                                int[] variants = {0, 5, 6, 8};
                                int randomIndex = new Random().nextInt(variants.length);
                                oSheep.setBreed(variants[randomIndex]);
                            }
                        } else {
                            if (random.nextDouble() < 0.20) {
                                oSheep.setBreed(random.nextInt(SheepBreed.Breed.values().length));
                            } else {
                                int[] variants = {1, 2, 3, 4, 7};
                                int randomIndex = new Random().nextInt(variants.length);
                                oSheep.setBreed(variants[randomIndex]);
                            }
                        }
                    } else {
                        oSheep.setBreed(random.nextInt(SheepBreed.Breed.values().length));
                    }

                    oSheep.copyPosition(animal);
                    oSheep.setGender(random.nextInt(OSheep.Gender.values().length));
                    if (LivestockOverhaulCommonConfig.QUALITY.get()) oSheep.setQuality(random.nextInt(30));

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oSheep.setColorByBreed();
                        oSheep.setWoolColorByBreed();
                        oSheep.setMarkingByBreed();
                        oSheep.setHornsByBreed();
                    } else {
                        oSheep.setVariant(random.nextInt(OSheepModel.Variant.values().length));
                        oSheep.setOverlayVariant(random.nextInt(OSheepRenderLayer.Marking.values().length));
                        oSheep.setWoolVariant(random.nextInt(OSheepRenderLayer.WoolColor.values().length));
                        oSheep.setHornVariant(random.nextInt(OSheep.BreedHorns.values().length));
                    }

                    if (random.nextFloat() <= 0.0164) {
                        oSheep.setDyed(true);
                        oSheep.setWoolDyeVariant(12);
                    } else {
                        oSheep.setDyed(false);
                        oSheep.setWoolDyeVariant(0);
                    }

                    level.addFreshEntity(oSheep);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oSheep.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (level.isClientSide) oSheep.remove(Entity.RemovalReason.DISCARDED);
                        oSheep.remove(Entity.RemovalReason.DISCARDED);
                    }
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla sheep at: " + oSheep.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Llama
        OLlama oLlama = EntityTypes.O_LLAMA_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.REPLACE_LLAMAS.get() && eventEntity instanceof Llama animal) {
            if (((eventEntity.getClass() == Llama.class) || (eventEntity.getClass() == TraderLlama.class)) && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                if (oLlama != null) {
                    oLlama.copyPosition(animal);
                    oLlama.setCustomName(animal.getCustomName());
                    oLlama.setOwnerUUID(animal.getOwnerUUID());
                    oLlama.setAge(animal.getAge());

                    Entity leashHolder = animal.getLeashHolder();
                    if (leashHolder != null) {
                        oLlama.setLeashedTo(leashHolder, true);
                        oLlama.setDelayedLeashHolderId(leashHolder.getId());
                    }

                    if (animal instanceof TraderLlama) oLlama.setChested(true);
                    oLlama.setWooly(random.nextInt(OLlama.Wooly.values().length));
                    oLlama.setVariant(random.nextInt(OLlamaModel.Variant.values().length));
                    oLlama.setOverlayVariant(random.nextInt(OLlamaMarkingLayer.Overlay.values().length));
                    oLlama.setGender(random.nextInt(OLlama.Gender.values().length));
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oLlama);
                    animal.remove(Entity.RemovalReason.DISCARDED);

                    if (!(animal instanceof TraderLlama)) {
                        if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oLlama.getSpawnType() == MobSpawnType.SPAWN_EGG)) && (!(animal.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                            if (level.isClientSide) oLlama.remove(Entity.RemovalReason.DISCARDED);
                            oLlama.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla llama at: " + oLlama.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Pig
        OPig oPig = EntityTypes.O_PIG_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.REPLACE_PIGS.get() && eventEntity instanceof Pig animal) {
            if (eventEntity.getClass() == Pig.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                if (oPig != null) {
                    oPig.copyPosition(animal);
                    oPig.setCustomName(animal.getCustomName());
                    oPig.setAge(animal.getAge());

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oPig.setColorByBreed();
                        oPig.setMarkingByBreed();
                    } else {
                        oPig.setVariant(random.nextInt(OPigModel.Variant.values().length));
                        oPig.setOverlayVariant(random.nextInt(OPigRenderLayer.Overlay.values().length));
                    }

                    oPig.setBreed(random.nextInt(PigBreed.Breed.values().length));
                    oPig.setGender(random.nextInt(OPig.Gender.values().length));
                    if (LivestockOverhaulCommonConfig.QUALITY.get()) oPig.setQuality(random.nextInt(30));
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oPig);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oPig.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (level.isClientSide) oPig.remove(Entity.RemovalReason.DISCARDED);
                        oPig.remove(Entity.RemovalReason.DISCARDED);
                    }
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla pig at: " + oPig.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Mooshroom
        if (LivestockOverhaulCommonConfig.REPLACE_COWS.get() && eventEntity instanceof MushroomCow animal) {
            if (eventEntity.getClass() == MushroomCow.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                OMooshroom oMooshroom = EntityTypes.O_MOOSHROOM_ENTITY.get().create(level);
                if (oMooshroom != null) {
                    oMooshroom.copyPosition(animal);
                    oMooshroom.setCustomName(animal.getCustomName());
                    oMooshroom.setAge(animal.getAge());
                    oMooshroom.setGender(random.nextInt(OCow.Gender.values().length));
                    oMooshroom.setBreed(random.nextInt(CowBreed.Breed.values().length));
                    oMooshroom.setMushroomVariant(OMooshroomMushroomLayer.MushroomOverlay.values().length);

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oMooshroom.setColorByBreed();
                        oMooshroom.setMarkingByBreed();
                        oMooshroom.setHornsByBreed();
                    } else {
                        oMooshroom.setVariant(random.nextInt(OMooshroomModel.Variant.values().length));
                        oMooshroom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
                        oMooshroom.setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
                    }

                    if (level.isClientSide)
                        animal.remove(Entity.RemovalReason.DISCARDED);

                    level.addFreshEntity(oMooshroom);
                    animal.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oMooshroom.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (level.isClientSide) oMooshroom.remove(Entity.RemovalReason.DISCARDED);
                        oMooshroom.remove(Entity.RemovalReason.DISCARDED);
                    }
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla mooshroom at: " + oMooshroom.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Camel
        if (LivestockOverhaulCommonConfig.REPLACE_CAMELS.get() && eventEntity instanceof Camel animal) {
            if (eventEntity.getClass() == Camel.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                OCamel oCamel = EntityTypes.O_CAMEL_ENTITY.get().create(level);
                if (oCamel != null) {
                    oCamel.copyPosition(animal);
                    oCamel.setCustomName(animal.getCustomName());
                    oCamel.setAge(animal.getAge());
                    oCamel.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oCamel.generateRandomMaxHealth());
                    oCamel.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oCamel.generateRandomSpeed());
                    oCamel.setVariant(random.nextInt(OCamelModel.Variant.values().length));
                    oCamel.setOverlayVariant(random.nextInt(OCamelBodyLayer.Marking.values().length));
                    oCamel.setBreed(random.nextInt(CamelBreed.Breed.values().length));
                    oCamel.setGender(random.nextInt(AbstractOMount.Gender.values().length));
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oCamel);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla camel at: " + oCamel.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Goat
        if (LivestockOverhaulCommonConfig.REPLACE_GOATS.get() && eventEntity instanceof Goat animal) {
            if (eventEntity.getClass() == Goat.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                OGoat oGoat = EntityTypes.O_GOAT_ENTITY.get().create(level);
                if (oGoat != null) {
                    oGoat.copyPosition(animal);
                    oGoat.setCustomName(animal.getCustomName());
                    oGoat.setAge(animal.getAge());
                    oGoat.setGender(random.nextInt(OGoat.Gender.values().length));

                    if (LivestockOverhaulCommonConfig.QUALITY.get()) {
                        oGoat.setQuality(random.nextInt(30));
                    }

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oGoat.setColor();
                        oGoat.setMarking();
                    } else {
                        oGoat.setVariant(random.nextInt(OGoatModel.Variant.values().length));
                        oGoat.setOverlayVariant(random.nextInt(OGoatBodyLayer.Overlay.values().length));
                    }

                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oGoat);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla goat at: " + oGoat.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Undead Horse
        if (LivestockOverhaulCommonConfig.REPLACE_UNDEAD_HORSES.get() && eventEntity instanceof SkeletonHorse animal) {
            if (eventEntity.getClass() == SkeletonHorse.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(level);
                if (oHorse != null) {
                    oHorse.copyPosition(animal);
                    oHorse.setCustomName(animal.getCustomName());
                    oHorse.setOwnerUUID(animal.getOwnerUUID());
                    oHorse.setAge(animal.getAge());
                    oHorse.randomizeOHorseAttributes();
                    oHorse.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
                    oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));
                    oHorse.setUndead(true);
                    oHorse.setBreed(0);
                    oHorse.setManeType(2);
                    oHorse.setTailType(1 + random.nextInt(4));

                    oHorse.setColorByBreed();
                    oHorse.setMarkingByBreed();
                    oHorse.setFeatheringByBreed();
                    if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_NETHER)) {
                        oHorse.setDecompVariant(6);
                    } else if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                        oHorse.setDecompVariant(5);
                    } else {
                        oHorse.setDecompVariant(4);
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        oHorse.setEyeColorByChance();
                    } else {
                        oHorse.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oHorse);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla skeleton horse at: " + oHorse.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        if (LivestockOverhaulCommonConfig.REPLACE_UNDEAD_HORSES.get() && eventEntity instanceof ZombieHorse animal) {
            if (eventEntity.getClass() == ZombieHorse.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(level);
                if (oHorse != null) {
                    oHorse.copyPosition(animal);
                    oHorse.setCustomName(animal.getCustomName());
                    oHorse.setOwnerUUID(animal.getOwnerUUID());
                    oHorse.setAge(animal.getAge());
                    oHorse.randomizeOHorseAttributes();
                    oHorse.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
                    oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));
                    oHorse.setUndead(true);
                    oHorse.setBreed(0);
                    oHorse.setManeType(2);
                    oHorse.setTailType(1 + random.nextInt(4));

                    oHorse.setColorByBreed();
                    oHorse.setMarkingByBreed();
                    oHorse.setFeatheringByBreed();
                    if (oHorse.isInWaterOrRain()) {
                        oHorse.setDecompVariant(2);
                    } else if (level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                        oHorse.setDecompVariant(3);
                    } else {
                        oHorse.setDecompVariant(0);
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        oHorse.setEyeColorByChance();
                    } else {
                        oHorse.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oHorse);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla zombie horse at: " + oHorse.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Frog
        if (LivestockOverhaulCommonConfig.REPLACE_FROGS.get() && eventEntity instanceof Frog animal) {
            if (eventEntity.getClass() == Frog.class && (animal.getSpawnType() != MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {
                OFrog oFrog = EntityTypes.O_FROG_ENTITY.get().create(level);
                if (oFrog != null) {
                    oFrog.copyPosition(animal);
                    oFrog.setCustomName(animal.getCustomName());
                    oFrog.setAge(animal.getAge());
                    oFrog.setVariant(random.nextInt(OFrogModel.Variant.values().length));
                    oFrog.setOverlayVariant(random.nextInt(OFrogRenderLayer.Marking.values().length));
                    oFrog.setEyesVariant(random.nextInt(OFrogRenderLayer.EyeColor.values().length));
                    if (level.isClientSide) animal.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(oFrog);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Replaced a vanilla frog at: " + oFrog.getOnPos());
                    event.setCanceled(true);
                }
            }
        }

        //Unicorn
        Unicorn unicorn = EntityTypes.UNICORN_ENTITY.get().create(level);
        if (LivestockOverhaulCommonConfig.SPAWN_UNICORNS.get()) {
            if (eventEntity.getClass() == Rabbit.class && random.nextDouble() < 0.0012 && level.isDay() && level.getBiome(eventEntity.blockPosition()).is(Tags.Biomes.IS_PLAINS)) {
                Rabbit animal = (Rabbit) eventEntity;
                if (unicorn != null) {
                    unicorn.copyPosition(animal);
                    unicorn.setGender(random.nextInt(Unicorn.Gender.values().length));
                    unicorn.setSpecies(0);
                    unicorn.randomizeUnicornAttributes();

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        unicorn.setColorByBreed();
                        unicorn.setMarkingByBreed();
                        unicorn.setHornByBreed();
                    } else {
                        unicorn.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                        unicorn.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        unicorn.setHornVariant(random.nextInt(UnicornBodyLayer.HornType.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        unicorn.setEyeColorByChance();
                    } else {
                        unicorn.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    unicorn.setManeType(1 + random.nextInt(4));
                    level.addFreshEntity(unicorn);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Added a unicorn at: " + unicorn.getOnPos());
                    event.setCanceled(true);
                }
            }

            if (eventEntity.getClass() == PiglinBrute.class && random.nextDouble() < 0.007) {
                PiglinBrute animal = (PiglinBrute) eventEntity;
                if (unicorn != null) {
                    unicorn.copyPosition(animal);
                    unicorn.setGender(random.nextInt(Unicorn.Gender.values().length));
                    unicorn.setSpecies(1);
                    unicorn.randomizeUnicornAttributes();

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        unicorn.setColorByBreed();
                        unicorn.setMarkingByBreed();
                        unicorn.setHornByBreed();
                    } else {
                        unicorn.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                        unicorn.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        unicorn.setHornVariant(random.nextInt(UnicornBodyLayer.HornType.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        unicorn.setEyeColorByChance();
                    } else {
                        unicorn.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    unicorn.setManeType(1 + random.nextInt(4));
                    level.addFreshEntity(unicorn);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Added a unicorn at: " + unicorn.getOnPos());
                    event.setCanceled(true);
                }
            }

            if (eventEntity.getClass() == EnderMan.class && random.nextDouble() < 0.002 && level.getBiome(eventEntity.blockPosition()).is(BiomeTags.IS_END)) {
                EnderMan animal = (EnderMan) eventEntity;
                if (unicorn != null) {
                    unicorn.copyPosition(animal);
                    unicorn.setGender(random.nextInt(Unicorn.Gender.values().length));
                    unicorn.setSpecies(2);
                    unicorn.randomizeUnicornAttributes();

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        unicorn.setColorByBreed();
                        unicorn.setMarkingByBreed();
                        unicorn.setHornByBreed();
                    } else {
                        unicorn.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                        unicorn.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        unicorn.setHornVariant(random.nextInt(UnicornBodyLayer.HornType.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        unicorn.setEyeColorByChance();
                    } else {
                        unicorn.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    unicorn.setManeType(1 + random.nextInt(4));
                    level.addFreshEntity(unicorn);
                    animal.remove(Entity.RemovalReason.DISCARDED);
                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get())
                        LivestockOverhaul.LOGGER.debug("Added a unicorn at: " + unicorn.getOnPos());
                    event.setCanceled(true);
                }
            }
        }
        
        //FAILSAFE O-to-Vanilla Converter
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get()) {
            //Horse
            if (!LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && eventEntity instanceof OHorse) {
                OHorse oHorse1 = (OHorse) eventEntity;
                Horse horse = EntityType.HORSE.create(level);
                if (horse != null) {
                    horse.copyPosition(oHorse1);
                    horse.setCustomName(oHorse1.getCustomName());
                    horse.setOwnerUUID(oHorse1.getOwnerUUID());
                    horse.setAge(oHorse1.getAge());
                    horse.setHealth(oHorse1.getHealth());
                    horse.setSpeed(oHorse1.getSpeed());
                    if (level.isClientSide) oHorse1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(horse);
                    oHorse1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Donkey
            if (!LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && eventEntity instanceof ODonkey) {
                ODonkey oDonkey1 = (ODonkey) eventEntity;
                Donkey donkey = EntityType.DONKEY.create(level);
                if (donkey != null) {
                    donkey.copyPosition(oDonkey1);
                    donkey.setCustomName(oDonkey1.getCustomName());
                    donkey.setOwnerUUID(oDonkey1.getOwnerUUID());
                    donkey.setAge(oDonkey1.getAge());
                    donkey.setHealth(oDonkey1.getHealth());
                    donkey.setSpeed(oDonkey1.getSpeed());
                    if (level.isClientSide) oDonkey1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(donkey);
                    oDonkey1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Mule
            if (!LivestockOverhaulCommonConfig.REPLACE_MULES.get() && eventEntity instanceof OMule) {
                OMule oMule1 = (OMule) eventEntity;
                Mule mule = EntityType.MULE.create(level);
                if (mule != null) {
                    mule.copyPosition(oMule1);
                    mule.setCustomName(oMule1.getCustomName());
                    mule.setOwnerUUID(oMule1.getOwnerUUID());
                    mule.setAge(oMule1.getAge());
                    mule.setHealth(oMule1.getHealth());
                    mule.setSpeed(oMule1.getSpeed());
                    if (level.isClientSide) oMule1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(mule);
                    oMule1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Cow
            if (!LivestockOverhaulCommonConfig.REPLACE_COWS.get() && eventEntity instanceof OCow) {
                OCow oCow1 = (OCow) eventEntity;
                Cow cow = EntityType.COW.create(level);
                if (cow != null) {
                    cow.copyPosition(oCow1);
                    cow.setCustomName(oCow1.getCustomName());
                    cow.setAge(oCow1.getAge());
                    if (level.isClientSide) oCow1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(cow);
                    oCow1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Chicken
            if (!LivestockOverhaulCommonConfig.REPLACE_CHICKENS.get() && eventEntity instanceof OChicken) {
                OChicken oChicken1 = (OChicken) eventEntity;
                Chicken chicken = EntityType.CHICKEN.create(level);
                if (chicken != null) {
                    chicken.copyPosition(oChicken1);
                    chicken.setCustomName(oChicken1.getCustomName());
                    chicken.setAge(oChicken1.getAge());
                    if (level.isClientSide) oChicken1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(chicken);
                    oChicken1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Salmon
            Salmon salmon = EntityType.SALMON.create(level);
            if (!LivestockOverhaulCommonConfig.REPLACE_SALMON.get() && eventEntity instanceof OSalmon) {
                OSalmon oSalmon1 = (OSalmon) eventEntity;
                if (salmon != null) {
                    salmon.copyPosition(oSalmon1);
                    salmon.setCustomName(oSalmon1.getCustomName());
                    if (level.isClientSide) oSalmon1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(salmon);
                    oSalmon1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Cod
            Cod cod = EntityType.COD.create(level);
            if (!LivestockOverhaulCommonConfig.REPLACE_COD.get() && eventEntity instanceof OCod) {
                OCod cod1 = (OCod) eventEntity;
                if (cod != null) {
                    cod.copyPosition(cod1);
                    cod.setCustomName(cod1.getCustomName());
                    if (level.isClientSide) cod1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(cod);
                    cod1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Bee
            if (!LivestockOverhaulCommonConfig.REPLACE_BEES.get() && eventEntity instanceof OBee oBee1) {
                Bee bee = EntityType.BEE.create(level);
                oBee1.remove(Entity.RemovalReason.DISCARDED);
                level.addFreshEntity(bee);
                bee.copyPosition(oBee1);
                bee.setCustomName(oBee1.getCustomName());
                event.setCanceled(true);
            }

            //Rabbit
            Rabbit rabbit = EntityType.RABBIT.create(level);
            if (!LivestockOverhaulCommonConfig.REPLACE_RABBITS.get() && eventEntity instanceof ORabbit) {
                ORabbit oRabbit1 = (ORabbit) eventEntity;
                if (rabbit != null) {
                    rabbit.copyPosition(oRabbit1);
                    rabbit.setCustomName(oRabbit1.getCustomName());
                    rabbit.setAge(oRabbit1.getAge());
                    if (level.isClientSide) oRabbit1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(rabbit);
                    oRabbit1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Sheep
            Sheep sheep = EntityType.SHEEP.create(level);
            if (!LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() && eventEntity instanceof OSheep) {
                OSheep oSheep1 = (OSheep) eventEntity;
                if (sheep != null) {
                    sheep.copyPosition(oSheep1);
                    sheep.setCustomName(oSheep1.getCustomName());
                    sheep.setAge(oSheep1.getAge());
                    if (level.isClientSide) oSheep1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(sheep);
                    oSheep1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Llama
            Llama llama = EntityType.LLAMA.create(level);
            if (!LivestockOverhaulCommonConfig.REPLACE_LLAMAS.get() && eventEntity instanceof OLlama) {
                OLlama oLlama1 = (OLlama) eventEntity;
                if (llama != null) {
                    llama.copyPosition(oLlama1);
                    llama.setCustomName(oLlama1.getCustomName());
                    llama.setOwnerUUID(oLlama1.getOwnerUUID());
                    llama.setAge(oLlama1.getAge());
                    int randomVariant = level.getRandom().nextInt(23);
                    llama.setVariant(Llama.Variant.byId(randomVariant));
                    if (level.isClientSide) oLlama1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(llama);
                    oLlama1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Pig
            Pig pig = EntityType.PIG.create(level);
            if (!LivestockOverhaulCommonConfig.REPLACE_PIGS.get() && eventEntity instanceof OPig) {
                OPig oPig1 = (OPig) eventEntity;
                if (pig != null) {
                    pig.copyPosition(oPig1);
                    pig.setCustomName(oPig1.getCustomName());
                    pig.setAge(oPig1.getAge());
                    if (level.isClientSide) oPig1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(pig);
                    oPig1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Mooshroom
            if (!LivestockOverhaulCommonConfig.REPLACE_COWS.get() && eventEntity instanceof OMooshroom) {
                OMooshroom oMooshroom1 = (OMooshroom) eventEntity;
                MushroomCow cow = EntityType.MOOSHROOM.create(level);
                if (cow != null) {
                    cow.copyPosition(oMooshroom1);
                    cow.setCustomName(oMooshroom1.getCustomName());
                    cow.setAge(oMooshroom1.getAge());
                    if (level.isClientSide) oMooshroom1.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(cow);
                    oMooshroom1.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Goat
            if (!LivestockOverhaulCommonConfig.REPLACE_GOATS.get() && eventEntity instanceof OGoat) {
                OGoat oGoat = (OGoat) eventEntity;
                Goat goat = EntityType.GOAT.create(level);
                if (goat != null) {
                    goat.copyPosition(oGoat);
                    goat.setCustomName(oGoat.getCustomName());
                    goat.setAge(oGoat.getAge());
                    if (level.isClientSide) oGoat.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(goat);
                    oGoat.remove(Entity.RemovalReason.DISCARDED);

                    event.setCanceled(true);
                }
            }

            //Frog
            if (!LivestockOverhaulCommonConfig.REPLACE_FROGS.get() && eventEntity instanceof OFrog) {
                OFrog oFrog = (OFrog) eventEntity;
                Frog frog = EntityType.FROG.create(level);
                if (frog != null) {
                    frog.copyPosition(oFrog);
                    frog.setCustomName(oFrog.getCustomName());
                    frog.setAge(oFrog.getAge());
                    if (level.isClientSide) oFrog.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(frog);
                    oFrog.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }

            //Camel
            if (!LivestockOverhaulCommonConfig.REPLACE_CAMELS.get() && eventEntity instanceof OCamel) {
                OCamel oCamel = (OCamel) eventEntity;
                Camel camel = EntityType.CAMEL.create(level);
                if (camel != null) {
                    camel.copyPosition(oCamel);
                    camel.setCustomName(oCamel.getCustomName());
                    camel.setAge(oCamel.getAge());
                    if (level.isClientSide) oCamel.remove(Entity.RemovalReason.DISCARDED);
                    level.addFreshEntity(camel);
                    oCamel.remove(Entity.RemovalReason.DISCARDED);
                    event.setCanceled(true);
                }
            }
        }
        
    }
}