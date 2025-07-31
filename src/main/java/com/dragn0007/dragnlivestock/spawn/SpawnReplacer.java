package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import com.dragn0007.dragnlivestock.entities.bee.OBeeModel;
import com.dragn0007.dragnlivestock.entities.camel.CamelBreed;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.camel.OCamelMarkingLayer;
import com.dragn0007.dragnlivestock.entities.camel.OCamelModel;
import com.dragn0007.dragnlivestock.entities.chicken.ChickenBreed;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.chicken.OChickenMarkingLayer;
import com.dragn0007.dragnlivestock.entities.cod.OCod;
import com.dragn0007.dragnlivestock.entities.cow.CowBreed;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.OCowModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.azalea.AzaleaMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.azalea.AzaleaMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot.BeetrootMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot.BeetrootMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.carrot.CarrotMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.carrot.CarrotMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering.FloweringMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering.FloweringMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.glow_berry.GlowBerryMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.glow_berry.GlowBerryMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.melon.MelonMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.melon.MelonMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.peach.PeachMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.peach.PeachMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.potato.PotatoMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.potato.PotatoMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.pumpkin.PumpkinMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.pumpkin.PumpkinMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.wheat.WheatMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.wheat.WheatMoobloomModel;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroom;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomModel;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomMushroomLayer;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkeyModel;
import com.dragn0007.dragnlivestock.entities.frog.OFrog;
import com.dragn0007.dragnlivestock.entities.frog.OFrogEyeLayer;
import com.dragn0007.dragnlivestock.entities.frog.OFrogMarkingLayer;
import com.dragn0007.dragnlivestock.entities.frog.OFrogModel;
import com.dragn0007.dragnlivestock.entities.mountain_goat.OMountainGoat;
import com.dragn0007.dragnlivestock.entities.mountain_goat.OMountainGoatMarkingLayer;
import com.dragn0007.dragnlivestock.entities.mountain_goat.OMountainGoatModel;
import com.dragn0007.dragnlivestock.entities.horse.HorseBreed;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseDecompLayer;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorseman;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaMarkingLayer;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaModel;
import com.dragn0007.dragnlivestock.entities.marking_layer.BovineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.pig.OPigMarkingLayer;
import com.dragn0007.dragnlivestock.entities.pig.OPigModel;
import com.dragn0007.dragnlivestock.entities.pig.PigBreed;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitMarkingLayer;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitModel;
import com.dragn0007.dragnlivestock.entities.rabbit.RabbitBreed;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmon;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmonModel;
import com.dragn0007.dragnlivestock.entities.sheep.*;
import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
import com.dragn0007.dragnlivestock.entities.unicorn.UnicornHornLayer;
import com.dragn0007.dragnlivestock.entities.unicorn.UnicornModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.dragn0007.dragnlivestock.LivestockOverhaul.MODID;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnReplacer {

    // This class falls under the LGPL license, as stated in the CODE_LICENSE.txt
    // Some of this code was referenced from Realistic Horse Genetics. Please check them out, too! :)
    // https://github.com/sekelsta/horse-colors  |  https://www.curseforge.com/minecraft/mc-mods/realistic-horse-genetics


    //NOTE(EVGLX): The following beehive-specific code should not be used elsewhere, but I'm keeping it public because private functionality is stupid
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

        Random random = new Random();

        //Horse
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && event.getEntity() instanceof Horse vanillaHorse) {

            if (event.getEntity().getClass() == Horse.class && (((!(vanillaHorse.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (event.getLevel().isNight() && event.getLevel().getRandom().nextDouble() < 0.02) {
                    HeadlessHorseman headlessHorseman = EntityTypes.HEADLESS_HORSEMAN_ENTITY.get().create(event.getLevel());

                    if (headlessHorseman != null) {
                        headlessHorseman.copyPosition(vanillaHorse);
                        event.getLevel().addFreshEntity(headlessHorseman);

                        headlessHorseman.setVariant(0);

                        if (event.getLevel().isClientSide) {
                            vanillaHorse.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(headlessHorseman);
                        vanillaHorse.remove(Entity.RemovalReason.DISCARDED);
                    }
                }

                OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(event.getLevel());
                if (oHorse != null) {
                    oHorse.copyPosition(vanillaHorse);

                    //try to take on as many identifiers from the vanilla horse possible
                    oHorse.setCustomName(vanillaHorse.getCustomName());
                    oHorse.setOwnerUUID(vanillaHorse.getOwnerUUID());
                    oHorse.setAge(vanillaHorse.getAge());
                    oHorse.randomizeOHorseAttributes();

                    oHorse.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
                    oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));

                    //spawn breeds except for compat-only ones if the config allows it
                    if (LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            int[] breeds = {10, 13};
                            int randomIndex = new Random().nextInt(breeds.length);
                            oHorse.setBreed(breeds[randomIndex]);
                        } else if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                            int[] breeds = {1, 5, 6, 8, 12};
                            int randomIndex = new Random().nextInt(breeds.length);
                            oHorse.setBreed(breeds[randomIndex]);
                        } else if (!ModList.get().isLoaded("deadlydinos")) {
                            int[] breeds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
                            int randomIndex = new Random().nextInt(breeds.length);
                            oHorse.setBreed(breeds[randomIndex]);
                        } else {
                            oHorse.setBreed(random.nextInt(HorseBreed.values().length));
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
                        oHorse.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    //discard vanilla horse once it's been successfully replaced on client and server
                    if (event.getLevel().isClientSide) {
                        vanillaHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oHorse);
                    vanillaHorse.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla horse with an O-Horse at: " + oHorse.getOnPos());
                    }

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oHorse.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oHorse.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Donkey
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && event.getEntity() instanceof Donkey vanillaDonkey) {

            if (event.getEntity().getClass() == Donkey.class && (((!(vanillaDonkey.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                ODonkey oDonkey = EntityTypes.O_DONKEY_ENTITY.get().create(event.getLevel());
                if (oDonkey != null) {
                    oDonkey.copyPosition(vanillaDonkey);

                    oDonkey.setCustomName(vanillaDonkey.getCustomName());
                    oDonkey.setOwnerUUID(vanillaDonkey.getOwnerUUID());
                    oDonkey.setAge(vanillaDonkey.getAge());
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

                    if (event.getLevel().isClientSide) {
                        vanillaDonkey.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oDonkey);
                    vanillaDonkey.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla donkey with an O-Donkey at: " + oDonkey.getOnPos());
                    }

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oDonkey.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oDonkey.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oDonkey.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Mule
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_MULES.get() && event.getEntity() instanceof Mule vanillaMule) {

            if (event.getEntity().getClass() == Mule.class && (((!(vanillaMule.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OMule oMule = EntityTypes.O_MULE_ENTITY.get().create(event.getLevel());
                if (oMule != null) {
                    oMule.copyPosition(vanillaMule);

                    oMule.setCustomName(vanillaMule.getCustomName());
                    oMule.setOwnerUUID(vanillaMule.getOwnerUUID());
                    oMule.setAge(vanillaMule.getAge());
                    oMule.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oMule.generateRandomMaxHealth());
                    oMule.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oMule.generateRandomSpeed());
                    oMule.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oMule.generateRandomJumpStrength());

                    oMule.setGender(random.nextInt(AbstractOMount.Gender.values().length));

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oMule.setColor();
                        oMule.setMarking();
                        oMule.setFeatheringByBreed();
                    } else {
                        oMule.setVariant(random.nextInt(OMuleModel.Variant.values().length));
                        oMule.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        oMule.setFeathering(random.nextInt(OHorse.Feathering.values().length));
                    }

                    if (event.getLevel().isClientSide) {
                        vanillaMule.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oMule);
                    vanillaMule.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla mule with an O-Mule at: " + oMule.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Cow
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof Cow vanillacow) {

            if (event.getEntity().getClass() == Cow.class && (((!(vanillacow.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get()) {
                    if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.PLAINS)) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            WheatMoobloom moobloom = EntityTypes.WHEAT_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(WheatMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.TAIGA) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SNOWY_TAIGA))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            SweetBerryMoobloom moobloom = EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(SweetBerryMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.FOREST))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            PumpkinMoobloom moobloom = EntityTypes.PUMPKIN_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(PumpkinMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.MEADOW))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            PotatoMoobloom moobloom = EntityTypes.POTATO_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(PotatoMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.JUNGLE) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SPARSE_JUNGLE) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SAVANNA))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            MelonMoobloom moobloom = EntityTypes.MELON_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(MelonMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.LUSH_CAVES) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.DARK_FOREST))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            GlowBerryMoobloom moobloom = EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(GlowBerryMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.FLOWER_FOREST) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SUNFLOWER_PLAINS))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.20) {
                            FloweringMoobloom moobloom = EntityTypes.FLOWERING_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(FloweringMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.PLAINS))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            CarrotMoobloom moobloom = EntityTypes.CARROT_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(CarrotMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.MEADOW))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            BeetrootMoobloom moobloom = EntityTypes.BEETROOT_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(BeetrootMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if ((event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.LUSH_CAVES) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.FOREST) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.BIRCH_FOREST))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            AzaleaMoobloom moobloom = EntityTypes.AZALEA_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(AzaleaMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }

                    if (ModList.get().isLoaded("thatsjustpeachy") && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SAVANNA) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SAVANNA_PLATEAU) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.WINDSWEPT_SAVANNA))) {
                        if (event.getLevel().getRandom().nextDouble() < 0.01) {
                            PeachMoobloom moobloom = EntityTypes.PEACH_MOOBLOOM_ENTITY.get().create(event.getLevel());

                            if (moobloom != null) {
                                moobloom.copyPosition(vanillacow);
                                event.getLevel().addFreshEntity(moobloom);

                                moobloom.setVariant(random.nextInt(PeachMoobloomModel.Variant.values().length));
                                moobloom.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));

                                if (event.getLevel().isClientSide) {
                                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(moobloom);
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }
                        }
                    }
                }

                OCow oCow = EntityTypes.O_COW_ENTITY.get().create(event.getLevel());
                if (oCow != null) {
                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            if (random.nextDouble() < 0.20) {
                                oCow.setBreed(random.nextInt(CowBreed.Breed.values().length));
                            } else {
                                int[] variants = {1, 2, 4, 5};
                                int randomIndex = new Random().nextInt(variants.length);
                                oCow.setBreed(variants[randomIndex]);
                            }
                        } else if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                            if (random.nextDouble() < 0.20) {
                                oCow.setBreed(random.nextInt(CowBreed.Breed.values().length));
                            } else {
                                oCow.setBreed(9);
                            }
                        } else {
                            int[] variants = {0, 6, 7, 8};
                            int randomIndex = new Random().nextInt(variants.length);
                            oCow.setBreed(variants[randomIndex]);
                        }
                    } else {
                        oCow.setBreed(random.nextInt(CowBreed.Breed.values().length));
                    }

                    oCow.copyPosition(vanillacow);
                    oCow.setGender(random.nextInt(OCow.Gender.values().length));

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oCow.setColorByBreed();
                        oCow.setMarkingByBreed();
                        oCow.setHornsByBreed();
                    } else {
                        oCow.setVariant(random.nextInt(OCowModel.Variant.values().length));
                        oCow.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
                        oCow.setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
                    }

                    if (event.getLevel().isClientSide) {
                        vanillacow.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oCow);
                    vanillacow.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla cow with an O-Cow at: " + oCow.getOnPos());
                    }

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oCow.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oCow.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oCow.remove(Entity.RemovalReason.DISCARDED);
                    }


                    event.setCanceled(true);
                }
            }
        }

        //Chicken
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_CHICKENS.get() && event.getEntity() instanceof Chicken vanillachicken) {

            if (event.getEntity().getClass() == Chicken.class && (((!(vanillachicken.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OChicken oChicken = EntityTypes.O_CHICKEN_ENTITY.get().create(event.getLevel());
                if (oChicken != null) {
                    oChicken.copyPosition(vanillachicken);

                    oChicken.setCustomName(vanillachicken.getCustomName());
                    oChicken.setAge(vanillachicken.getAge());

                    oChicken.setBreed(random.nextInt(ChickenBreed.Breed.values().length));
                    oChicken.setGender(random.nextInt(OChicken.Gender.values().length));

                    if (oChicken.isFemale()) {
                        int randomVariant = 6 + event.getLevel().getRandom().nextInt(9);
                        oChicken.setVariant(randomVariant);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 0) {
                        oChicken.setVariant(0);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 1) {
                        oChicken.setVariant(1);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 2) {
                        oChicken.setVariant(2);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 3) {
                        oChicken.setVariant(3);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 4) {
                        oChicken.setVariant(4);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 5) {
                        oChicken.setVariant(5);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 6) {
                        oChicken.setVariant(15); //this isnt supposed to happen, but just in case
                    }

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OChickenMarkingLayer.Overlay.values().length);
                    oChicken.setOverlayVariant(randomOverlayVariant);

                    if (event.getLevel().isClientSide) {
                        vanillachicken.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oChicken);
                    vanillachicken.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oChicken.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oChicken.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oChicken.remove(Entity.RemovalReason.DISCARDED);
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla chicken with an O-Chicken at: " + oChicken.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Salmon
        OSalmon oSalmon = EntityTypes.O_SALMON_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_SALMON.get() && event.getEntity() instanceof Salmon vanillasalmon) {

            if (event.getEntity().getClass() == Salmon.class && (((!(vanillasalmon.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oSalmon != null) {
                    oSalmon.copyPosition(vanillasalmon);

                    oSalmon.setCustomName(vanillasalmon.getCustomName());

                    oSalmon.setVariant(random.nextInt(OSalmonModel.Variant.values().length));

                    if (event.getLevel().isClientSide) {
                        vanillasalmon.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oSalmon);
                    vanillasalmon.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla salmon with an O-Salmon at: " + oSalmon.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Cod
        OCod oCod = EntityTypes.O_COD_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COD.get() && event.getEntity() instanceof Cod vanillacod) {

            if (event.getEntity().getClass() == Cod.class && (((!(vanillacod.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oCod != null) {
                    oCod.copyPosition(vanillacod);

                    oCod.setCustomName(vanillacod.getCustomName());

                    if (event.getLevel().isClientSide) {
                        vanillacod.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oCod);
                    vanillacod.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla cod with an O-Cod at: " + oCod.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Bee
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_BEES.get() &&
                (event.getEntity().getClass() == Bee.class)) {
            Bee bee = (Bee) event.getEntity();
            OBee oBee = EntityTypes.O_BEE_ENTITY.get().create(event.getLevel());
            if(oBee != null) {
                oBee.setCustomName(bee.getCustomName());
                oBee.deserializeNBT(bee.serializeNBT());

                oBee.setVariant(random.nextInt(OBeeModel.Variant.values().length));

                bee.remove(Entity.RemovalReason.DISCARDED);
                event.getLevel().addFreshEntity(oBee);

                if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                    System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla bee with an O-Bee at: " + oBee.getOnPos());
                }

                // we need to handle beehive data and replace it with oBee data
                if(oBee.getHivePos() != null) {
                    BlockEntity blockEntity = event.getLevel().getBlockEntity(oBee.getHivePos());
                    handleBeehiveData(blockEntity);
                }
            }

            event.setCanceled(true);
        }

        //Rabbit
        ORabbit oRabbit = EntityTypes.O_RABBIT_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_RABBITS.get() && event.getEntity() instanceof Rabbit vanillarabbit) {

            if (event.getEntity().getClass() == Rabbit.class && (((!(vanillarabbit.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oRabbit != null) {
                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oRabbit.setBreed(0);
                        oRabbit.setMarkingByWildStatus();
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            int[] variants = {4, 7, 10, 14};
                            int randomIndex = new Random().nextInt(variants.length);
                            oRabbit.setVariant(variants[randomIndex]);
                        } else if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
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

                    oRabbit.copyPosition(vanillarabbit);

                    oRabbit.setCustomName(vanillarabbit.getCustomName());
                    oRabbit.setAge(vanillarabbit.getAge());

                    oRabbit.setGender(random.nextInt(ORabbit.Gender.values().length));
                    oRabbit.setDewlapByGender();

                    if (event.getLevel().isClientSide) {
                        vanillarabbit.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oRabbit);
                    vanillarabbit.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get()) {
                        if (event.getLevel().isClientSide) {
                            oRabbit.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oRabbit.remove(Entity.RemovalReason.DISCARDED);
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla rabbit with an O-Rabbit at: " + oRabbit.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Sheep
        OSheep oSheep = EntityTypes.O_SHEEP_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() && event.getEntity() instanceof Sheep vanillasheep) {

            if (event.getEntity().getClass() == Sheep.class && (((!(vanillasheep.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oSheep != null) {
                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            if (random.nextDouble() < 0.20) {
                                oSheep.setBreed(random.nextInt(SheepBreed.Breed.values().length));
                            } else {
                                int[] variants = {0, 5, 6};
                                int randomIndex = new Random().nextInt(variants.length);
                                oSheep.setBreed(variants[randomIndex]);
                            }
                        } else {
                            if (random.nextDouble() < 0.20) {
                                oSheep.setBreed(random.nextInt(SheepBreed.Breed.values().length));
                            } else {
                                int[] variants = {1, 2, 3, 4};
                                int randomIndex = new Random().nextInt(variants.length);
                                oSheep.setBreed(variants[randomIndex]);
                            }
                        }
                    } else {
                        oSheep.setBreed(random.nextInt(SheepBreed.Breed.values().length));
                    }

                    oSheep.copyPosition(vanillasheep);
                    oSheep.setGender(random.nextInt(OSheep.Gender.values().length));

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oSheep.setColorByBreed();
                        oSheep.setWoolColorByBreed();
                        oSheep.setMarkingByBreed();
                        oSheep.setHornsByBreed();
                    } else {
                        oSheep.setVariant(random.nextInt(OSheepModel.Variant.values().length));
                        oSheep.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
                        oSheep.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
                        oSheep.setHornVariant(random.nextInt(OSheep.BreedHorns.values().length));
                    }

                    event.getLevel().addFreshEntity(oSheep);
                    vanillasheep.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oSheep.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oSheep.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oSheep.remove(Entity.RemovalReason.DISCARDED);
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla sheep with an O-Sheep at: " + oSheep.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Llama
        OLlama oLlama = EntityTypes.O_LLAMA_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_LLAMAS.get() && event.getEntity() instanceof Llama vanillallama) {

            if (event.getEntity().getClass() == Llama.class && (((!(vanillallama.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oLlama != null) {
                    oLlama.copyPosition(vanillallama);

                    oLlama.setCustomName(vanillallama.getCustomName());
                    oLlama.setOwnerUUID(vanillallama.getOwnerUUID());
                    oLlama.setAge(vanillallama.getAge());

                    oLlama.setWooly(random.nextInt(OLlama.Wooly.values().length));
                    oLlama.setVariant(random.nextInt(OLlamaModel.Variant.values().length));
                    oLlama.setOverlayVariant(random.nextInt(OLlamaMarkingLayer.Overlay.values().length));

                    oLlama.setGender(random.nextInt(OPig.Gender.values().length));

                    if (event.getLevel().isClientSide) {
                        vanillallama.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oLlama);
                    vanillallama.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oLlama.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oLlama.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oLlama.remove(Entity.RemovalReason.DISCARDED);
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla llama with an O-Llama at: " + oLlama.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Pig
        OPig oPig = EntityTypes.O_PIG_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_PIGS.get() && event.getEntity() instanceof Pig vanillapig) {

            if (event.getEntity().getClass() == Pig.class && (((!(vanillapig.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oPig != null) {
                    oPig.copyPosition(vanillapig);

                    oPig.setCustomName(vanillapig.getCustomName());
                    oPig.setAge(vanillapig.getAge());

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oPig.setColorByBreed();
                        oPig.setMarkingByBreed();
                    } else {
                        oPig.setVariant(random.nextInt(OPigModel.Variant.values().length));
                        oPig.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
                    }

                    oPig.setBreed(random.nextInt(PigBreed.Breed.values().length));
                    oPig.setGender(random.nextInt(OPig.Gender.values().length));

                    if (event.getLevel().isClientSide) {
                        vanillapig.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oPig);
                    vanillapig.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oPig.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oPig.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oPig.remove(Entity.RemovalReason.DISCARDED);
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla pig with an O-Pig at: " + oPig.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Mooshroom
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof MushroomCow vanillamooshroom) {

            if (event.getEntity().getClass() == MushroomCow.class && (((!(vanillamooshroom.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OMooshroom oMooshroom = EntityTypes.O_MOOSHROOM_ENTITY.get().create(event.getLevel());
                if (oMooshroom != null) {
                    oMooshroom.copyPosition(vanillamooshroom);

                    oMooshroom.setCustomName(vanillamooshroom.getCustomName());
                    oMooshroom.setAge(vanillamooshroom.getAge());

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

                    if (event.getLevel().isClientSide) {
                        vanillamooshroom.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oMooshroom);
                    vanillamooshroom.remove(Entity.RemovalReason.DISCARDED);

                    if (random.nextDouble() <= LivestockOverhaulCommonConfig.SPAWN_PREVENTION_PERCENT.get() && (!(oMooshroom.getSpawnType() == MobSpawnType.SPAWN_EGG))) {
                        if (event.getLevel().isClientSide) {
                            oMooshroom.remove(Entity.RemovalReason.DISCARDED);
                        }
                        oMooshroom.remove(Entity.RemovalReason.DISCARDED);
                    }

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla mooshroom with an O-Mooshroom at: " + oMooshroom.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Camel
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_CAMELS.get() && event.getEntity() instanceof Camel vanillacamel) {

            if (event.getEntity().getClass() == Camel.class && (((!(vanillacamel.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OCamel oCamel = EntityTypes.O_CAMEL_ENTITY.get().create(event.getLevel());
                if (oCamel != null) {
                    oCamel.copyPosition(vanillacamel);

                    oCamel.setCustomName(vanillacamel.getCustomName());
                    oCamel.setAge(vanillacamel.getAge());
                    oCamel.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oCamel.generateRandomMaxHealth());
                    oCamel.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oCamel.generateRandomSpeed());

                    oCamel.setVariant(random.nextInt(OCamelModel.Variant.values().length));
                    oCamel.setOverlayVariant(random.nextInt(OCamelMarkingLayer.Overlay.values().length));
                    oCamel.setBreed(random.nextInt(CamelBreed.Breed.values().length));
                    oCamel.setGender(random.nextInt(AbstractOMount.Gender.values().length));

                    if (event.getLevel().isClientSide) {
                        vanillacamel.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oCamel);
                    vanillacamel.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla camel with an O-Camel at: " + oCamel.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Goat
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_GOATS.get() && event.getEntity() instanceof Goat vanillagoat) {

            if (event.getEntity().getClass() == Goat.class && (((!(vanillagoat.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OMountainGoat oGoat = EntityTypes.O_GOAT_ENTITY.get().create(event.getLevel());
                if (oGoat != null) {
                    oGoat.copyPosition(vanillagoat);

                    oGoat.setCustomName(vanillagoat.getCustomName());
                    oGoat.setAge(vanillagoat.getAge());

                    oGoat.setGender(random.nextInt(OMountainGoat.Gender.values().length));

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oGoat.setColor();
                        oGoat.setMarking();
                    } else {
                        oGoat.setVariant(random.nextInt(OMountainGoatModel.Variant.values().length));
                        oGoat.setOverlayVariant(random.nextInt(OMountainGoatMarkingLayer.Overlay.values().length));
                    }

                    if (event.getLevel().isClientSide) {
                        vanillagoat.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oGoat);
                    vanillagoat.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla goat with an O-Goat at: " + oGoat.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Undead Horse
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_UNDEAD_HORSES.get() && event.getEntity() instanceof SkeletonHorse skeletonHorse) {

            if (event.getEntity().getClass() == SkeletonHorse.class && (((!(skeletonHorse.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(event.getLevel());
                if (oHorse != null) {
                    oHorse.copyPosition(skeletonHorse);

                    oHorse.setCustomName(skeletonHorse.getCustomName());
                    oHorse.setOwnerUUID(skeletonHorse.getOwnerUUID());
                    oHorse.setAge(skeletonHorse.getAge());
                    oHorse.randomizeOHorseAttributes();

                    oHorse.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
                    oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));
                    oHorse.setUndead(true);

                    if (LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
                        if (!ModList.get().isLoaded("deadlydinos")) {
                            int[] breeds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
                            int randomIndex = new Random().nextInt(breeds.length);
                            oHorse.setBreed(breeds[randomIndex]);
                        } else {
                            oHorse.setBreed(random.nextInt(HorseBreed.values().length));
                        }

                        oHorse.setManeType(1 + random.nextInt(4));
                        oHorse.setTailType(1 + random.nextInt(4));
                    } else {
                        oHorse.setBreed(0);
                        oHorse.setManeType(2);
                        oHorse.setTailType(1 + random.nextInt(4));
                    }

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oHorse.setColorByBreed();
                        oHorse.setMarkingByBreed();
                        oHorse.setFeatheringByBreed();
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_NETHER)) {
                            oHorse.setDecompVariant(5);
                        } else {
                            oHorse.setDecompVariant(4);
                        }
                    } else {
                        oHorse.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                        oHorse.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        oHorse.setFeathering(random.nextInt(OHorse.Feathering.values().length));
                        oHorse.setDecompVariant(random.nextInt(OHorseDecompLayer.UndeadStage.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        oHorse.setEyeColorByChance();
                    } else {
                        oHorse.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    if (event.getLevel().isClientSide) {
                        skeletonHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oHorse);
                    skeletonHorse.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla skeleton horse with an O-Undead Horse at: " + oHorse.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_UNDEAD_HORSES.get() && event.getEntity() instanceof ZombieHorse zombieHorse) {

            if (event.getEntity().getClass() == ZombieHorse.class && (((!(zombieHorse.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(event.getLevel());
                if (oHorse != null) {
                    oHorse.copyPosition(zombieHorse);

                    oHorse.setCustomName(zombieHorse.getCustomName());
                    oHorse.setOwnerUUID(zombieHorse.getOwnerUUID());
                    oHorse.setAge(zombieHorse.getAge());
                    oHorse.randomizeOHorseAttributes();

                    oHorse.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
                    oHorse.setGender(random.nextInt(AbstractOMount.Gender.values().length));
                    oHorse.setUndead(true);

                    if (LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
                        if (!ModList.get().isLoaded("deadlydinos")) {
                            int[] breeds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
                            int randomIndex = new Random().nextInt(breeds.length);
                            oHorse.setBreed(breeds[randomIndex]);
                        } else {
                            oHorse.setBreed(random.nextInt(HorseBreed.values().length));
                        }

                        oHorse.setManeType(1 + random.nextInt(4));
                        oHorse.setTailType(1 + random.nextInt(4));
                    } else {
                        oHorse.setBreed(0);
                        oHorse.setManeType(2);
                        oHorse.setTailType(1 + random.nextInt(4));
                    }

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oHorse.setColorByBreed();
                        oHorse.setMarkingByBreed();
                        oHorse.setFeatheringByBreed();
                        if (oHorse.isInWaterOrRain()) {
                            oHorse.setDecompVariant(7);
                        } else if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            oHorse.setDecompVariant(8);
                        } else if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                            oHorse.setDecompVariant(6);
                        } else {
                            int[] decompStage = {1, 2, 3};
                            int decompRandomIndex = new Random().nextInt(decompStage.length);
                            oHorse.setDecompVariant(decompStage[decompRandomIndex]);
                        }
                    } else {
                        oHorse.setVariant(random.nextInt(OHorseModel.Variant.values().length));
                        oHorse.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        oHorse.setFeathering(random.nextInt(OHorse.Feathering.values().length));
                        oHorse.setDecompVariant(random.nextInt(OHorseDecompLayer.UndeadStage.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        oHorse.setEyeColorByChance();
                    } else {
                        oHorse.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    if (event.getLevel().isClientSide) {
                        zombieHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oHorse);
                    zombieHorse.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla zombie horse with an O-Undead Horse at: " + oHorse.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Frog
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_FROGS.get() && event.getEntity() instanceof Frog frog) {

            if (event.getEntity().getClass() == Frog.class && (((!(frog.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                OFrog oFrog = EntityTypes.O_FROG_ENTITY.get().create(event.getLevel());
                if (oFrog != null) {
                    oFrog.copyPosition(frog);

                    oFrog.setCustomName(frog.getCustomName());
                    oFrog.setAge(frog.getAge());

                    oFrog.setVariant(random.nextInt(OFrogModel.Variant.values().length));
                    oFrog.setOverlayVariant(random.nextInt(OFrogMarkingLayer.Overlay.values().length));
                    oFrog.setEyesVariant(random.nextInt(OFrogEyeLayer.Overlay.values().length));

                    if (event.getLevel().isClientSide) {
                        frog.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oFrog);
                    frog.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla frog with an O-Frog at: " + oFrog.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }

        //Unicorn
        Unicorn unicorn = EntityTypes.UNICORN_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.SPAWN_UNICORNS.get()) {

            if (event.getEntity().getClass() == ORabbit.class && random.nextDouble() < 0.008 && event.getLevel().isDay() && event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_PLAINS)) {
                ORabbit rabbit = (ORabbit) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (unicorn != null) {
                    unicorn.copyPosition(rabbit);
                    unicorn.setGender(random.nextInt(Unicorn.Gender.values().length));
                    unicorn.setSpecies(0);

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        unicorn.setColorByBreed();
                        unicorn.setMarkingByBreed();
                        unicorn.setHornByBreed();
                    } else {
                        unicorn.setVariant(random.nextInt(UnicornModel.Variant.values().length));
                        unicorn.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        unicorn.setHornVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        unicorn.setEyeColorByChance();
                    } else {
                        unicorn.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    unicorn.setManeType(1 + random.nextInt(4));

                    event.getLevel().addFreshEntity(unicorn);
                    rabbit.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla rabbit with a Unicorn at: " + unicorn.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }

            if (event.getEntity().getClass() == PiglinBrute.class && random.nextDouble() < 0.02) {
                PiglinBrute brute = (PiglinBrute) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (unicorn != null) {
                    unicorn.copyPosition(brute);
                    unicorn.setGender(random.nextInt(Unicorn.Gender.values().length));
                    unicorn.setSpecies(1);

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        unicorn.setColorByBreed();
                        unicorn.setMarkingByBreed();
                        unicorn.setHornByBreed();
                    } else {
                        unicorn.setVariant(random.nextInt(UnicornModel.Variant.values().length));
                        unicorn.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        unicorn.setHornVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        unicorn.setEyeColorByChance();
                    } else {
                        unicorn.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    unicorn.setManeType(1 + random.nextInt(4));

                    event.getLevel().addFreshEntity(unicorn);
                    brute.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla piglin brute with a Unicorn at: " + unicorn.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }

            if (event.getEntity().getClass() == EnderMan.class && random.nextDouble() < 0.005 && event.getLevel().getBiome(event.getEntity().blockPosition()).is(BiomeTags.IS_END)) {
                EnderMan enderMan = (EnderMan) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (unicorn != null) {
                    unicorn.copyPosition(enderMan);
                    unicorn.setGender(random.nextInt(Unicorn.Gender.values().length));
                    unicorn.setSpecies(2);

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        unicorn.setColorByBreed();
                        unicorn.setMarkingByBreed();
                        unicorn.setHornByBreed();
                    } else {
                        unicorn.setVariant(random.nextInt(UnicornModel.Variant.values().length));
                        unicorn.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
                        unicorn.setHornVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
                    }

                    if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
                        unicorn.setEyeColorByChance();
                    } else {
                        unicorn.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
                    }

                    unicorn.setManeType(1 + random.nextInt(4));

                    event.getLevel().addFreshEntity(unicorn);
                    enderMan.remove(Entity.RemovalReason.DISCARDED);

                    if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                        System.out.println("[DragN's Livestock Overhaul!]: Replaced a vanilla enderman with a Unicorn at: " + unicorn.getOnPos());
                    }

                    event.setCanceled(true);
                }
            }
        }




        //TODO; FAILSAFE O-to-Vanilla Converter
        //Horse
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && event.getEntity() instanceof OHorse) {
            OHorse oHorse1 = (OHorse) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Horse horse = EntityType.HORSE.create(event.getLevel());
            if (horse != null) {
                horse.copyPosition(oHorse1);

                horse.setCustomName(oHorse1.getCustomName());
                horse.setOwnerUUID(oHorse1.getOwnerUUID());
                horse.setAge(oHorse1.getAge());
                horse.setHealth(oHorse1.getHealth());
                horse.setSpeed(oHorse1.getSpeed());

                if (event.getLevel().isClientSide) {
                    oHorse1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(horse);
                oHorse1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Donkey
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && event.getEntity() instanceof ODonkey) {
            ODonkey oDonkey1 = (ODonkey) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Donkey donkey = EntityType.DONKEY.create(event.getLevel());
            if (donkey != null) {
                donkey.copyPosition(oDonkey1);

                donkey.setCustomName(oDonkey1.getCustomName());
                donkey.setOwnerUUID(oDonkey1.getOwnerUUID());
                donkey.setAge(oDonkey1.getAge());
                donkey.setHealth(oDonkey1.getHealth());
                donkey.setSpeed(oDonkey1.getSpeed());

                if (event.getLevel().isClientSide) {
                    oDonkey1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(donkey);
                oDonkey1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Mule
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && event.getEntity() instanceof OMule) {
            OMule oMule1 = (OMule) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Mule mule = EntityType.MULE.create(event.getLevel());
            if (mule != null) {
                mule.copyPosition(oMule1);

                mule.setCustomName(oMule1.getCustomName());
                mule.setOwnerUUID(oMule1.getOwnerUUID());
                mule.setAge(oMule1.getAge());
                mule.setHealth(oMule1.getHealth());
                mule.setSpeed(oMule1.getSpeed());

                if (event.getLevel().isClientSide) {
                    oMule1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(mule);
                oMule1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Cow
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof OCow) {
            OCow oCow1 = (OCow) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Cow cow = EntityType.COW.create(event.getLevel());
            if (cow != null) {
                cow.copyPosition(oCow1);

                cow.setCustomName(oCow1.getCustomName());
                cow.setAge(oCow1.getAge());

                if (event.getLevel().isClientSide) {
                    oCow1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(cow);
                oCow1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Chicken
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_CHICKENS.get() && event.getEntity() instanceof OChicken) {
            OChicken oChicken1 = (OChicken) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Chicken chicken = EntityType.CHICKEN.create(event.getLevel());
            if (chicken != null) {
                chicken.copyPosition(oChicken1);

                chicken.setCustomName(oChicken1.getCustomName());
                chicken.setAge(oChicken1.getAge());

                if (event.getLevel().isClientSide) {
                    oChicken1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(chicken);
                oChicken1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Salmon
        Salmon salmon = EntityType.SALMON.create(event.getLevel());
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_SALMON.get() && event.getEntity() instanceof OSalmon) {
            OSalmon oSalmon1 = (OSalmon) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (salmon != null) {
                salmon.copyPosition(oSalmon1);

                salmon.setCustomName(oSalmon1.getCustomName());

                if (event.getLevel().isClientSide) {
                    oSalmon1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(salmon);
                oSalmon1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Cod
        Cod cod = EntityType.COD.create(event.getLevel());
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_COD.get() && event.getEntity() instanceof OCod) {
            OCod cod1 = (OCod) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (cod != null) {
                cod.copyPosition(cod1);

                cod.setCustomName(cod1.getCustomName());

                if (event.getLevel().isClientSide) {
                    cod1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(cod);
                cod1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Bee
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_BEES.get() && event.getEntity() instanceof OBee oBee1) {
            Bee bee = EntityType.BEE.create(event.getLevel());
            oBee1.remove(Entity.RemovalReason.DISCARDED);
            event.getLevel().addFreshEntity(bee);
            bee.copyPosition(oBee1);
            bee.setCustomName(oBee1.getCustomName());
            event.setCanceled(true);
        }

        //Rabbit
        Rabbit rabbit = EntityType.RABBIT.create(event.getLevel());
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_RABBITS.get() && event.getEntity() instanceof ORabbit) {
            ORabbit oRabbit1 = (ORabbit) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (rabbit != null) {
                rabbit.copyPosition(oRabbit1);

                rabbit.setCustomName(oRabbit1.getCustomName());
                rabbit.setAge(oRabbit1.getAge());

                if (event.getLevel().isClientSide) {
                    oRabbit1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(rabbit);
                oRabbit1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Sheep
        Sheep sheep = EntityType.SHEEP.create(event.getLevel());
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() && event.getEntity() instanceof OSheep) {
            OSheep oSheep1 = (OSheep) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (sheep != null) {
                sheep.copyPosition(oSheep1);

                sheep.setCustomName(oSheep1.getCustomName());
                sheep.setAge(oSheep1.getAge());

                if (event.getLevel().isClientSide) {
                    oSheep1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(sheep);
                oSheep1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Llama
        Llama llama = EntityType.LLAMA.create(event.getLevel());
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_LLAMAS.get() && event.getEntity() instanceof OLlama) {
            OLlama oLlama1 = (OLlama) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (llama != null) {
                llama.copyPosition(oLlama1);

                llama.setCustomName(oLlama1.getCustomName());
                llama.setOwnerUUID(oLlama1.getOwnerUUID());
                llama.setAge(oLlama1.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                llama.setVariant(Llama.Variant.byId(randomVariant));

                if (event.getLevel().isClientSide) {
                    oLlama1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(llama);
                oLlama1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Pig
        Pig pig = EntityType.PIG.create(event.getLevel());
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_PIGS.get() && event.getEntity() instanceof OPig) {
            OPig oPig1 = (OPig) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (pig != null) {
                pig.copyPosition(oPig1);

                pig.setCustomName(oPig1.getCustomName());
                pig.setAge(oPig1.getAge());

                if (event.getLevel().isClientSide) {
                    oPig1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(pig);
                oPig1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Mooshroom
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof OMooshroom) {
            OMooshroom oMooshroom1 = (OMooshroom) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            MushroomCow cow = EntityType.MOOSHROOM.create(event.getLevel());
            if (cow != null) {
                cow.copyPosition(oMooshroom1);

                cow.setCustomName(oMooshroom1.getCustomName());
                cow.setAge(oMooshroom1.getAge());

                if (event.getLevel().isClientSide) {
                    oMooshroom1.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(cow);
                oMooshroom1.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Goat
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_GOATS.get() && event.getEntity() instanceof OMountainGoat) {
            OMountainGoat oGoat = (OMountainGoat) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Goat goat = EntityType.GOAT.create(event.getLevel());
            if (goat != null) {
                goat.copyPosition(oGoat);

                goat.setCustomName(oGoat.getCustomName());
                goat.setAge(oGoat.getAge());

                if (event.getLevel().isClientSide) {
                    oGoat.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(goat);
                oGoat.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Frog
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_FROGS.get() && event.getEntity() instanceof OFrog) {
            OFrog oFrog = (OFrog) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Frog frog = EntityType.FROG.create(event.getLevel());
            if (frog != null) {
                frog.copyPosition(oFrog);

                frog.setCustomName(oFrog.getCustomName());
                frog.setAge(oFrog.getAge());

                if (event.getLevel().isClientSide) {
                    oFrog.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(frog);
                oFrog.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Camel
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_CAMELS.get() && event.getEntity() instanceof OCamel) {
            OCamel oCamel = (OCamel) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Camel camel = EntityType.CAMEL.create(event.getLevel());
            if (camel != null) {
                camel.copyPosition(oCamel);

                camel.setCustomName(oCamel.getCustomName());
                camel.setAge(oCamel.getAge());

                if (event.getLevel().isClientSide) {
                    oCamel.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(camel);
                oCamel.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }


    }

}