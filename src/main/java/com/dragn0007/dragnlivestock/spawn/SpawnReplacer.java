package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.camel.OCamelMarkingLayer;
import com.dragn0007.dragnlivestock.entities.camel.OCamelModel;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.chicken.OChickenMarkingLayer;
import com.dragn0007.dragnlivestock.entities.cod.OCod;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.OCowHornLayer;
import com.dragn0007.dragnlivestock.entities.cow.OCowMarkingLayer;
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
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.*;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkeyModel;
import com.dragn0007.dragnlivestock.entities.frog.OFrog;
import com.dragn0007.dragnlivestock.entities.frog.OFrogEyeLayer;
import com.dragn0007.dragnlivestock.entities.frog.OFrogMarkingLayer;
import com.dragn0007.dragnlivestock.entities.frog.OFrogModel;
import com.dragn0007.dragnlivestock.entities.goat.OGoat;
import com.dragn0007.dragnlivestock.entities.goat.OGoatModel;
import com.dragn0007.dragnlivestock.entities.horse.HorseBreedModel;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseMarkingLayer;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorseman;
import com.dragn0007.dragnlivestock.entities.horse.undead_horse.OUndeadHorse;
import com.dragn0007.dragnlivestock.entities.horse.undead_horse.OUndeadHorseModel;
import com.dragn0007.dragnlivestock.entities.horse.undead_horse.OUndeadHorseSkeletalLayer;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaModel;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.pig.OPigMarkingLayer;
import com.dragn0007.dragnlivestock.entities.pig.OPigModel;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitMarkingLayer;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitModel;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmon;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmonModel;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.entities.sheep.OSheepModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnReplacer {

    // This class falls under the LGPL license, as stated in the CODE_LICENSE.txt
    // Some of this code was referenced from Realistic Horse Genetics. Please check them out, too! :)
    // https://github.com/sekelsta/horse-colors  |  https://www.curseforge.com/minecraft/mc-mods/realistic-horse-genetics

    @SubscribeEvent
    public static void onSpawn(EntityJoinLevelEvent event) {


        //Horse
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && event.getEntity() instanceof Horse) {

            if (event.getEntity().getClass() == Horse.class) {
                Horse vanillaHorse = (Horse) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (event.getLevel().isNight()) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
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
                }

                if (event.getLevel().isNight()) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        OUndeadHorse undeadHorse = EntityTypes.O_UNDEAD_HORSE_ENTITY.get().create(event.getLevel());

                        if (undeadHorse != null) {
                            undeadHorse.copyPosition(vanillaHorse);
                            event.getLevel().addFreshEntity(undeadHorse);

                            int randomVariant = event.getLevel().getRandom().nextInt(OUndeadHorseModel.Variant.values().length);
                            undeadHorse.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OUndeadHorseSkeletalLayer.Overlay.values().length);
                            undeadHorse.setOverlayVariant(randomOverlayVariant);

                            if (event.getLevel().isClientSide) {
                                vanillaHorse.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(undeadHorse);
                            vanillaHorse.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                OHorse oHorse = EntityTypes.O_HORSE_ENTITY.get().create(event.getLevel());
                if (oHorse != null) {
                    oHorse.copyPosition(vanillaHorse);

                    //try to take on as many identifiers from the vanilla horse possible
                    oHorse.setCustomName(vanillaHorse.getCustomName());
                    oHorse.setOwnerUUID(vanillaHorse.getOwnerUUID());
                    oHorse.setAge(vanillaHorse.getAge());
                    oHorse.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oHorse.generateRandomMaxHealth());
                    oHorse.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oHorse.generateRandomSpeed());
                    oHorse.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oHorse.generateRandomJumpStrength());

                    //set random variants on-spawn
                    int randomVariant = event.getLevel().getRandom().nextInt(OHorseModel.Variant.values().length);
                    oHorse.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OHorseMarkingLayer.Overlay.values().length);
                    oHorse.setOverlayVariant(randomOverlayVariant);

                    int randomGender = event.getLevel().getRandom().nextInt(AbstractOMount.Gender.values().length);
                    oHorse.setGender(randomGender);

                    int randomChristmasVariant = event.getLevel().getRandom().nextInt(OHorseModel.ReindeerVariant.values().length);
                    oHorse.setReindeerVariant(randomChristmasVariant);

                    if (LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
                        int randomBreedIfConfigured = event.getLevel().getRandom().nextInt(HorseBreedModel.values().length);
                        oHorse.setBreed(randomBreedIfConfigured);
                    } else {
                        oHorse.setBreed(0);
                    }

                    //tfc compat
                    CompoundTag tfcTag = new CompoundTag();

                    if (ModList.get().isLoaded("tfc")) {
                        oHorse.setTamed(true);
                    }

                    //discard vanilla horse once it's been successfully replaced on client and server
                    if (event.getLevel().isClientSide) {
                        vanillaHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oHorse);
                    vanillaHorse.remove(Entity.RemovalReason.DISCARDED);

                    //debug only. annoying to see it spam the console
//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla horse with an O-Horse!");

                    event.setCanceled(true);
                }
            }
        }

        //Donkey
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && event.getEntity() instanceof Donkey) {

            if (event.getEntity().getClass() == Donkey.class) {
                Donkey vanillaDonkey = (Donkey) event.getEntity();

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

                    int randomVariant = event.getLevel().getRandom().nextInt(ODonkeyModel.Variant.values().length);
                    oDonkey.setVariant(randomVariant);

                    int randomGender = event.getLevel().getRandom().nextInt(AbstractOMount.Gender.values().length);
                    oDonkey.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillaDonkey.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oDonkey);
                    vanillaDonkey.remove(Entity.RemovalReason.DISCARDED);

                    //debug only. annoying to see it spam the console
//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla donkey with an O-Donkey!");

                    event.setCanceled(true);
                }
            }
        }

        //Mule
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_MULES.get() && event.getEntity() instanceof Mule) {

            if (event.getEntity().getClass() == Mule.class) {
                Mule vanillaMule = (Mule) event.getEntity();

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

                    int randomVariant = event.getLevel().getRandom().nextInt(OMuleModel.Variant.values().length);
                    oMule.setVariant(randomVariant);

                    int randomGender = event.getLevel().getRandom().nextInt(AbstractOMount.Gender.values().length);
                    oMule.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillaMule.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oMule);
                    vanillaMule.remove(Entity.RemovalReason.DISCARDED);

                    //debug only. annoying to see it spam the console
//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla mule with an O-Mule!");

                    event.setCanceled(true);
                }
            }
        }

        //Cow
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof Cow) {

            if (event.getEntity().getClass() == Cow.class) {
                Cow vanillacow = (Cow) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.PLAINS))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        WheatMoobloom moobloom = EntityTypes.WHEAT_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(WheatMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.TAIGA) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SNOWY_TAIGA))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        SweetBerryMoobloom moobloom = EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(SweetBerryMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.FOREST))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        PumpkinMoobloom moobloom = EntityTypes.PUMPKIN_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(PumpkinMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.MEADOW))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        PotatoMoobloom moobloom = EntityTypes.POTATO_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(PotatoMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.JUNGLE) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SPARSE_JUNGLE) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SAVANNA))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        MelonMoobloom moobloom = EntityTypes.MELON_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(MelonMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.LUSH_CAVES) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.DARK_FOREST))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        GlowBerryMoobloom moobloom = EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(GlowBerryMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.FLOWER_FOREST) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SUNFLOWER_PLAINS))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.30) {
                        FloweringMoobloom moobloom = EntityTypes.FLOWERING_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(FloweringMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.PLAINS))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        CarrotMoobloom moobloom = EntityTypes.CARROT_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(CarrotMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.MEADOW))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        BeetrootMoobloom moobloom = EntityTypes.BEETROOT_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(BeetrootMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.LUSH_CAVES) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.FOREST) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.BIRCH_FOREST))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        AzaleaMoobloom moobloom = EntityTypes.AZALEA_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(AzaleaMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                if (ModList.get().isLoaded("thatsjustpeachy") && LivestockOverhaulCommonConfig.SPAWN_MOOBLOOMS.get() && (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SAVANNA) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SAVANNA_PLATEAU) || event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.WINDSWEPT_SAVANNA))) {
                    if (event.getLevel().getRandom().nextDouble() < 0.02) {
                        PeachMoobloom moobloom = EntityTypes.PEACH_MOOBLOOM_ENTITY.get().create(event.getLevel());

                        if (moobloom != null) {
                            moobloom.copyPosition(vanillacow);
                            event.getLevel().addFreshEntity(moobloom);

                            int randomVariant = event.getLevel().getRandom().nextInt(PeachMoobloomModel.Variant.values().length);
                            moobloom.setVariant(randomVariant);

                            int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                            moobloom.setOverlayVariant(randomOverlayVariant);

                            int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                            moobloom.setHornVariant(randomHorns);

                            if (event.getLevel().isClientSide) {
                                vanillacow.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(moobloom);
                            vanillacow.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                }

                OCow oCow = EntityTypes.O_COW_ENTITY.get().create(event.getLevel());
                if (oCow != null) {
                    oCow.copyPosition(vanillacow);

                    oCow.setCustomName(vanillacow.getCustomName());
                    oCow.setAge(vanillacow.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OCowModel.Variant.values().length);
                    oCow.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCowMarkingLayer.Overlay.values().length);
                    oCow.setOverlayVariant(randomOverlayVariant);

                    int randomHorns = event.getLevel().getRandom().nextInt(OCowHornLayer.HornOverlay.values().length);
                    oCow.setHornVariant(randomHorns);

                    int randomGender = event.getLevel().getRandom().nextInt(OCow.Gender.values().length);
                    oCow.setGender(randomGender);

                    int randomBreed = event.getLevel().getRandom().nextInt(OCow.Breed.values().length);
                    oCow.setBreed(randomBreed);

                    if (event.getLevel().isClientSide) {
                        vanillacow.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oCow);
                    vanillacow.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla cow with an O-Cow!");

                    event.setCanceled(true);
                }
            }
        }

        //Chicken
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_CHICKENS.get() && event.getEntity() instanceof Chicken) {

            if (event.getEntity().getClass() == Chicken.class) {
                Chicken vanillachicken = (Chicken) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OChicken oChicken = EntityTypes.O_CHICKEN_ENTITY.get().create(event.getLevel());
                if (oChicken != null) {
                    oChicken.copyPosition(vanillachicken);

                    oChicken.setCustomName(vanillachicken.getCustomName());
                    oChicken.setAge(vanillachicken.getAge());

                    int randomBreed = event.getLevel().getRandom().nextInt(OChicken.Breed.values().length);
                    oChicken.setBreed(randomBreed);

                    int randomGender = event.getLevel().getRandom().nextInt(OChicken.Gender.values().length);
                    oChicken.setGender(randomGender);

                    if (oChicken.isFemale()) {
                        int randomVariant = 6 + event.getLevel().getRandom().nextInt(9);
                        oChicken.setVariant(randomVariant);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 0) {
                        oChicken.setVariant(0);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 1) {
                        oChicken.setVariant(10);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 2) {
                        oChicken.setVariant(11);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 3) {
                        oChicken.setVariant(12);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 4) {
                        oChicken.setVariant(13);
                    }

                    if (oChicken.isMale() && oChicken.getBreed() == 5) {
                        oChicken.setVariant(14);
                    }

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OChickenMarkingLayer.Overlay.values().length);
                    oChicken.setOverlayVariant(randomOverlayVariant);

                    if (event.getLevel().isClientSide) {
                        vanillachicken.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oChicken);
                    vanillachicken.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla chicken with an O-Chicken!");

                    event.setCanceled(true);
                }
            }
        }

        //Salmon
        OSalmon oSalmon = EntityTypes.O_SALMON_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_SALMON.get() && event.getEntity() instanceof Salmon) {

            if (event.getEntity().getClass() == Salmon.class) {
                Salmon vanillasalmon = (Salmon) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oSalmon != null) {
                    oSalmon.copyPosition(vanillasalmon);

                    oSalmon.setCustomName(vanillasalmon.getCustomName());

                    int randomVariant = event.getLevel().getRandom().nextInt(OSalmonModel.Variant.values().length);
                    oSalmon.setVariant(randomVariant);

                    if (event.getLevel().isClientSide) {
                        vanillasalmon.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oSalmon);
                    vanillasalmon.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla salmon with an O-Salmon!");

                    event.setCanceled(true);
                }
            }
        }

        //Cod
        OCod oCod = EntityTypes.O_COD_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COD.get() && event.getEntity() instanceof Cod) {

            if (event.getEntity().getClass() == Cod.class) {
                Cod vanillacod = (Cod) event.getEntity();

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

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla cod with an O-Cod!");

                    event.setCanceled(true);
                }
            }
        }

        //Bee
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_BEES.get() && (event.getEntity() instanceof Bee bee && !(bee instanceof OBee))) {

            OBee oBee = EntityTypes.O_BEE_ENTITY.get().create(event.getLevel());

            bee.remove(Entity.RemovalReason.DISCARDED);
            event.getLevel().addFreshEntity(oBee);
            oBee.copyPosition(bee);
            oBee.setCustomName(bee.getCustomName());

            System.out.println("[Livestock Overhaul]: Replaced a vanilla bee with an O-Bee!");

            event.setCanceled(true);
        }

        //Rabbit
        ORabbit oRabbit = EntityTypes.O_RABBIT_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_RABBITS.get() && event.getEntity() instanceof Rabbit) {

            if (event.getEntity().getClass() == Rabbit.class) {
                Rabbit vanillarabbit = (Rabbit) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oRabbit != null) {
                    oRabbit.copyPosition(vanillarabbit);

                    oRabbit.setCustomName(vanillarabbit.getCustomName());
                    oRabbit.setAge(vanillarabbit.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(ORabbitModel.Variant.values().length);
                    oRabbit.setVariant(randomVariant);

                    int randomOverlay = event.getLevel().getRandom().nextInt(ORabbitMarkingLayer.Overlay.values().length);
                    oRabbit.setOverlayVariant(randomOverlay);

                    int randomGender = event.getLevel().getRandom().nextInt(ORabbit.Gender.values().length);
                    oRabbit.setGender(randomGender);

                    int randomBreed = event.getLevel().getRandom().nextInt(ORabbit.Breed.values().length);
                    oRabbit.setBreed(randomBreed);

                    if (event.getLevel().isClientSide) {
                        vanillarabbit.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oRabbit);
                    vanillarabbit.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla rabbit with an O-Rabbit!");

                    event.setCanceled(true);
                }
            }
        }

        //Sheep
        OSheep oSheep = EntityTypes.O_SHEEP_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() && event.getEntity() instanceof Sheep) {

            if (event.getEntity().getClass() == Sheep.class) {
                Sheep vanillasheep = (Sheep) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oSheep != null) {
                    oSheep.copyPosition(vanillasheep);

                    oSheep.setCustomName(vanillasheep.getCustomName());
                    oSheep.setAge(vanillasheep.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OSheepModel.Variant.values().length);
                    oSheep.setVariant(randomVariant);

                    int randomBreed = event.getLevel().getRandom().nextInt(OSheep.Breed.values().length);
                    oSheep.setBreed(randomBreed);

                    int randomGender = event.getLevel().getRandom().nextInt(OSheep.Gender.values().length);
                    oSheep.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillasheep.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oSheep);
                    vanillasheep.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla sheep with an O-Sheep!");

                    event.setCanceled(true);
                }
            }
        }

        //Llama
        OLlama oLlama = EntityTypes.O_LLAMA_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_LLAMAS.get() && event.getEntity() instanceof Llama) {

            if (event.getEntity().getClass() == Llama.class) {
                Llama vanillallama = (Llama) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oLlama != null) {
                    oLlama.copyPosition(vanillallama);

                    oLlama.setCustomName(vanillallama.getCustomName());
                    oLlama.setOwnerUUID(vanillallama.getOwnerUUID());
                    oLlama.setAge(vanillallama.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OLlamaModel.Variant.values().length);
                    oLlama.setVariant(randomVariant);

                    int randomOverlay = event.getLevel().getRandom().nextInt(OPigMarkingLayer.Overlay.values().length);
                    oLlama.setOverlayVariant(randomOverlay);

                    int randomGender = event.getLevel().getRandom().nextInt(OLlama.Gender.values().length);
                    oLlama.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillallama.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oLlama);
                    vanillallama.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla llama with an O-Llama!");

                    event.setCanceled(true);
                }
            }
        }

        //Pig
        OPig oPig = EntityTypes.O_PIG_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_PIGS.get() && event.getEntity() instanceof Pig) {

            if (event.getEntity().getClass() == Pig.class) {
                Pig vanillapig = (Pig) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oPig != null) {
                    oPig.copyPosition(vanillapig);

                    oPig.setCustomName(vanillapig.getCustomName());
                    oPig.setAge(vanillapig.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OPigModel.Variant.values().length);
                    oPig.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OPigMarkingLayer.Overlay.values().length);
                    oPig.setOverlayVariant(randomOverlayVariant);

                    int randomGender = event.getLevel().getRandom().nextInt(OPig.Gender.values().length);
                    oPig.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillapig.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oPig);
                    vanillapig.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla pig with an O-Llama!");

                    event.setCanceled(true);
                }
            }
        }

        //Mooshroom
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof MushroomCow) {

            if (event.getEntity().getClass() == MushroomCow.class) {
                MushroomCow vanillamooshroom = (MushroomCow) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OMooshroom oMooshroom = EntityTypes.O_MOOSHROOM_ENTITY.get().create(event.getLevel());
                if (oMooshroom != null) {
                    oMooshroom.copyPosition(vanillamooshroom);

                    oMooshroom.setCustomName(vanillamooshroom.getCustomName());
                    oMooshroom.setAge(vanillamooshroom.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OMooshroomModel.Variant.values().length);
                    oMooshroom.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OMooshroomMarkingLayer.Overlay.values().length);
                    oMooshroom.setOverlayVariant(randomOverlayVariant);

                    int randomHorns = event.getLevel().getRandom().nextInt(OMooshroomHornLayer.HornOverlay.values().length);
                    oMooshroom.setHornVariant(randomHorns);

                    int randomMushrooms = event.getLevel().getRandom().nextInt(OMooshroomMushroomLayer.Overlay.values().length);
                    oMooshroom.setMushroomVariant(randomMushrooms);

                    int randomGender = event.getLevel().getRandom().nextInt(OMooshroom.Gender.values().length);
                    oMooshroom.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillamooshroom.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oMooshroom);
                    vanillamooshroom.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla mooshroom with an O-Mooshroom!");

                    event.setCanceled(true);
                }
            }
        }

        //Camel
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_CAMELS.get() && event.getEntity() instanceof Camel) {

            if (event.getEntity().getClass() == Camel.class) {
                Camel vanillacamel = (Camel) event.getEntity();

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

                    int randomVariant = event.getLevel().getRandom().nextInt(OCamelModel.Variant.values().length);
                    oCamel.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OCamelMarkingLayer.Overlay.values().length);
                    oCamel.setOverlayVariant(randomOverlayVariant);

                    int randomGender = event.getLevel().getRandom().nextInt(AbstractOMount.Gender.values().length);
                    oCamel.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillacamel.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oCamel);
                    vanillacamel.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla camel with an O-Camel!");

                    event.setCanceled(true);
                }
            }
        }

        //Goat
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_GOATS.get() && event.getEntity() instanceof Goat) {

            if (event.getEntity().getClass() == Goat.class) {
                Goat vanillagoat = (Goat) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OGoat oGoat = EntityTypes.O_GOAT_ENTITY.get().create(event.getLevel());
                if (oGoat != null) {
                    oGoat.copyPosition(vanillagoat);

                    oGoat.setCustomName(vanillagoat.getCustomName());
                    oGoat.setAge(vanillagoat.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OGoatModel.Variant.values().length);
                    oGoat.setVariant(randomVariant);

                    int randomHorns = event.getLevel().getRandom().nextInt(AbstractOMount.Gender.values().length);
                    oGoat.setGender(randomHorns);

                    if (event.getLevel().isClientSide) {
                        vanillagoat.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oGoat);
                    vanillagoat.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla goat with an O-Goat!");

                    event.setCanceled(true);
                }
            }
        }

        //Undead Horse
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && event.getEntity() instanceof SkeletonHorse) {

            if (event.getEntity().getClass() == SkeletonHorse.class) {
                SkeletonHorse skeletonHorse = (SkeletonHorse) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OUndeadHorse oUndeadHorse = EntityTypes.O_UNDEAD_HORSE_ENTITY.get().create(event.getLevel());
                if (oUndeadHorse != null) {
                    oUndeadHorse.copyPosition(skeletonHorse);

                    oUndeadHorse.setCustomName(skeletonHorse.getCustomName());
                    oUndeadHorse.setOwnerUUID(skeletonHorse.getOwnerUUID());
                    oUndeadHorse.setAge(skeletonHorse.getAge());
                    oUndeadHorse.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oUndeadHorse.generateRandomMaxHealth());
                    oUndeadHorse.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oUndeadHorse.generateRandomSpeed());
                    oUndeadHorse.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oUndeadHorse.generateRandomJumpStrength());

                    int randomVariant = event.getLevel().getRandom().nextInt(OUndeadHorseModel.Variant.values().length);
                    oUndeadHorse.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OUndeadHorseSkeletalLayer.Overlay.values().length);
                    oUndeadHorse.setOverlayVariant(randomOverlayVariant);

                    if (event.getLevel().isClientSide) {
                        skeletonHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oUndeadHorse);
                    skeletonHorse.remove(Entity.RemovalReason.DISCARDED);

                    event.setCanceled(true);
                }
            }
        }

        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && event.getEntity() instanceof ZombieHorse) {

            if (event.getEntity().getClass() == ZombieHorse.class) {
                ZombieHorse zombieHorse = (ZombieHorse) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OUndeadHorse oUndeadHorse = EntityTypes.O_UNDEAD_HORSE_ENTITY.get().create(event.getLevel());
                if (oUndeadHorse != null) {
                    oUndeadHorse.copyPosition(zombieHorse);

                    oUndeadHorse.setCustomName(zombieHorse.getCustomName());
                    oUndeadHorse.setOwnerUUID(zombieHorse.getOwnerUUID());
                    oUndeadHorse.setAge(zombieHorse.getAge());
                    oUndeadHorse.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oUndeadHorse.generateRandomMaxHealth());
                    oUndeadHorse.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oUndeadHorse.generateRandomSpeed());
                    oUndeadHorse.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oUndeadHorse.generateRandomJumpStrength());

                    int randomVariant = event.getLevel().getRandom().nextInt(OUndeadHorseModel.Variant.values().length);
                    oUndeadHorse.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OUndeadHorseSkeletalLayer.Overlay.values().length);
                    oUndeadHorse.setOverlayVariant(randomOverlayVariant);

                    if (event.getLevel().isClientSide) {
                        zombieHorse.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oUndeadHorse);
                    zombieHorse.remove(Entity.RemovalReason.DISCARDED);

                    event.setCanceled(true);
                }
            }
        }

        //Frog
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_FROGS.get() && event.getEntity() instanceof Frog) {

            if (event.getEntity().getClass() == Frog.class) {
                Frog frog = (Frog) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OFrog oFrog = EntityTypes.O_FROG_ENTITY.get().create(event.getLevel());
                if (oFrog != null) {
                    oFrog.copyPosition(frog);

                    oFrog.setCustomName(frog.getCustomName());
                    oFrog.setAge(frog.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(OFrogModel.Variant.values().length);
                    oFrog.setVariant(randomVariant);

                    int randomOverlayVariant = event.getLevel().getRandom().nextInt(OFrogMarkingLayer.Overlay.values().length);
                    oFrog.setOverlayVariant(randomOverlayVariant);

                    int randomEyeVariant = event.getLevel().getRandom().nextInt(OFrogEyeLayer.Overlay.values().length);
                    oFrog.setEyesVariant(randomEyeVariant);

                    if (event.getLevel().isClientSide) {
                        frog.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oFrog);
                    frog.remove(Entity.RemovalReason.DISCARDED);

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
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_GOATS.get() && event.getEntity() instanceof OGoat) {
            OGoat oGoat = (OGoat) event.getEntity();

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


    }

}