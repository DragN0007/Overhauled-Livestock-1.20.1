package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.cod.OCod;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroom;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorseman;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmon;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oHorse.setVariant(randomVariant);

                int randomOverlayVariant = event.getLevel().getRandom().nextInt(31);
                oHorse.setOverlayVariant(randomOverlayVariant);

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

        //Donkey
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && event.getEntity() instanceof Donkey) {
            Donkey vanillaDonkey = (Donkey) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            ODonkey oDonkey = EntityTypes.O_DONKEY_ENTITY.get().create(event.getLevel());
            if (oDonkey != null) {
                oDonkey.copyPosition(vanillaDonkey);
                Entity entity = event.getEntity();

                oDonkey.setCustomName(vanillaDonkey.getCustomName());
                oDonkey.setOwnerUUID(vanillaDonkey.getOwnerUUID());
                oDonkey.setAge(vanillaDonkey.getAge());
                oDonkey.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oDonkey.generateRandomMaxHealth());
                oDonkey.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oDonkey.generateRandomSpeed());
                oDonkey.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oDonkey.generateRandomJumpStrength());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oDonkey.setVariant(randomVariant);

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

        //Mule
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_DONKEYS.get() && event.getEntity() instanceof Mule) {
            Mule vanillaMule = (Mule) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OMule oMule = EntityTypes.O_MULE_ENTITY.get().create(event.getLevel());
            if (oMule != null) {
                oMule.copyPosition(vanillaMule);
                Entity entity = event.getEntity();

                oMule.setCustomName(vanillaMule.getCustomName());
                oMule.setOwnerUUID(vanillaMule.getOwnerUUID());
                oMule.setAge(vanillaMule.getAge());
                oMule.getAttribute(Attributes.MAX_HEALTH).setBaseValue(oMule.generateRandomMaxHealth());
                oMule.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(oMule.generateRandomSpeed());
                oMule.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(oMule.generateRandomJumpStrength());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oMule.setVariant(randomVariant);

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

        //Cow
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof Cow) {
            Cow vanillacow = (Cow) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OCow oCow = EntityTypes.O_COW_ENTITY.get().create(event.getLevel());
            if (oCow != null) {
                oCow.copyPosition(vanillacow);
                Entity entity = event.getEntity();

                oCow.setCustomName(vanillacow.getCustomName());
                oCow.setAge(vanillacow.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oCow.setVariant(randomVariant);

                int randomOverlayVariant = event.getLevel().getRandom().nextInt(31);
                oCow.setOverlayVariant(randomOverlayVariant);

                int randomHorns = event.getLevel().getRandom().nextInt(31);
                oCow.setHornVariant(randomHorns);

                int randomGender = event.getLevel().getRandom().nextInt(31);
                oCow.setUdderVariant(randomGender);

                if (event.getLevel().isClientSide) {
                    vanillacow.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oCow);
                vanillacow.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla cow with an O-Cow!");

                event.setCanceled(true);
            }
        }

        //Chicken
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_CHICKENS.get() && event.getEntity() instanceof Chicken) {
            Chicken vanillachicken = (Chicken) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OChicken oChicken = EntityTypes.O_CHICKEN_ENTITY.get().create(event.getLevel());
            if (oChicken != null) {
                oChicken.copyPosition(vanillachicken);
                Entity entity = event.getEntity();

                oChicken.setCustomName(vanillachicken.getCustomName());
                oChicken.setAge(vanillachicken.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oChicken.setVariant(randomVariant);

                int randomOverlayVariant = event.getLevel().getRandom().nextInt(31);
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

        //Salmon
        OSalmon oSalmon = EntityTypes.O_SALMON_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_SALMON.get() && event.getEntity() instanceof Salmon) {
            Salmon vanillasalmon = (Salmon) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oSalmon != null) {
                oSalmon.copyPosition(vanillasalmon);
                Entity entity = event.getEntity();

                oSalmon.setCustomName(vanillasalmon.getCustomName());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
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

        //Cod
        OCod oCod = EntityTypes.O_COD_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COD.get() && event.getEntity() instanceof Cod) {
            Cod vanillacod = (Cod) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oCod != null) {
                oCod.copyPosition(vanillacod);
                Entity entity = event.getEntity();

                oCod.setCustomName(vanillacod.getCustomName());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oCod.setVariant(randomVariant);

                if (event.getLevel().isClientSide) {
                    vanillacod.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oCod);
                vanillacod.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla cod with an O-Cod!");

                event.setCanceled(true);
            }
        }

        //Bee
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_BEES.get() &&
                (event.getEntity() instanceof Bee bee && !(bee instanceof OBee))) {

            OBee oBee = EntityTypes.O_BEE_ENTITY.get().create(event.getLevel());

            bee.remove(Entity.RemovalReason.DISCARDED);
            event.getLevel().addFreshEntity(oBee);
            oBee.copyPosition(bee);
            oBee.setCustomName(bee.getCustomName());

            int randomVariant = event.getLevel().getRandom().nextInt(23);
            oBee.setVariant(randomVariant);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla bee with an O-Bee!");

            event.setCanceled(true);
        }

        //Rabbit
        ORabbit oRabbit = EntityTypes.O_RABBIT_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_RABBITS.get() && event.getEntity() instanceof Rabbit) {
            Rabbit vanillarabbit = (Rabbit) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oRabbit != null) {
                oRabbit.copyPosition(vanillarabbit);
                Entity entity = event.getEntity();

                oRabbit.setCustomName(vanillarabbit.getCustomName());
                oRabbit.setAge(vanillarabbit.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oRabbit.setVariant(randomVariant);

                int randomOverlay = event.getLevel().getRandom().nextInt(23);
                oRabbit.setOverlayVariant(randomOverlay);

                if (event.getLevel().isClientSide) {
                    vanillarabbit.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oRabbit);
                vanillarabbit.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla rabbit with an O-Rabbit!");

                event.setCanceled(true);
            }
        }

        //Sheep
        OSheep oSheep = EntityTypes.O_SHEEP_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_SHEEP.get() && event.getEntity() instanceof Sheep) {
            Sheep vanillasheep = (Sheep) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oSheep != null) {
                oSheep.copyPosition(vanillasheep);

                oSheep.setCustomName(vanillasheep.getCustomName());
                oSheep.setAge(vanillasheep.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oSheep.setVariant(randomVariant);

                int randomGender = event.getLevel().getRandom().nextInt(31);
                oSheep.setHornVariant(randomGender);

                if (event.getLevel().isClientSide) {
                    vanillasheep.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oSheep);
                vanillasheep.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla sheep with an O-Sheep!");

                event.setCanceled(true);
            }
        }

        //Llama
        OLlama oLlama = EntityTypes.O_LLAMA_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_LLAMAS.get() && event.getEntity() instanceof Llama) {
            Llama vanillallama = (Llama) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oLlama != null) {
                oLlama.copyPosition(vanillallama);
                Entity entity = event.getEntity();

                oLlama.setCustomName(vanillallama.getCustomName());
                oLlama.setOwnerUUID(vanillallama.getOwnerUUID());
                oLlama.setAge(vanillallama.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oLlama.setVariant(randomVariant);

                int randomOverlay = event.getLevel().getRandom().nextInt(23);
                oLlama.setOverlayVariant(randomOverlay);

                if (event.getLevel().isClientSide) {
                    vanillallama.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oLlama);
                vanillallama.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla llama with an O-Llama!");

                event.setCanceled(true);
            }
        }

        //Pig
        OPig oPig = EntityTypes.O_PIG_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_PIGS.get() && event.getEntity() instanceof Pig) {
            Pig vanillapig = (Pig) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oPig != null) {
                oPig.copyPosition(vanillapig);
                Entity entity = event.getEntity();

                oPig.setCustomName(vanillapig.getCustomName());
                oPig.setAge(vanillapig.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oPig.setVariant(randomVariant);

                int randomOverlay = event.getLevel().getRandom().nextInt(23);
                oPig.setOverlayVariant(randomOverlay);

                int randomTusksVariant = event.getLevel().getRandom().nextInt(23);
                oPig.setTusksVariant(randomTusksVariant);

                if (event.getLevel().isClientSide) {
                    vanillapig.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oPig);
                vanillapig.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla pig with an O-Llama!");

                event.setCanceled(true);
            }
        }

        //Mooshroom
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_COWS.get() && event.getEntity() instanceof MushroomCow) {
            MushroomCow vanillamooshroom = (MushroomCow) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OMooshroom oMooshroom = EntityTypes.O_MOOSHROOM_ENTITY.get().create(event.getLevel());
            if (oMooshroom != null) {
                oMooshroom.copyPosition(vanillamooshroom);

                oMooshroom.setCustomName(vanillamooshroom.getCustomName());
                oMooshroom.setAge(vanillamooshroom.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oMooshroom.setVariant(randomVariant);

                int randomOverlayVariant = event.getLevel().getRandom().nextInt(31);
                oMooshroom.setOverlayVariant(randomOverlayVariant);

                int randomHorns = event.getLevel().getRandom().nextInt(31);
                oMooshroom.setHornVariant(randomHorns);

                int randomMushrooms = event.getLevel().getRandom().nextInt(31);
                oMooshroom.setMushroomVariant(randomMushrooms);

                int randomGender = event.getLevel().getRandom().nextInt(31);
                oMooshroom.setUdderVariant(randomGender);

                if (event.getLevel().isClientSide) {
                    vanillamooshroom.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oMooshroom);
                vanillamooshroom.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla mooshroom with an O-Mooshroom!");

                event.setCanceled(true);
            }
        }

        //Camel
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && LivestockOverhaulCommonConfig.REPLACE_CAMELS.get() && event.getEntity() instanceof Camel) {
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

                int randomVariant = event.getLevel().getRandom().nextInt(23);
                oCamel.setVariant(randomVariant);

                int randomOverlayVariant = event.getLevel().getRandom().nextInt(31);
                oCamel.setOverlayVariant(randomOverlayVariant);

                if (event.getLevel().isClientSide) {
                    vanillacamel.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oCamel);
                vanillacamel.remove(Entity.RemovalReason.DISCARDED);

//                    System.out.println("[Livestock Overhaul]: Replaced a vanilla camel with an O-Camel!");

                event.setCanceled(true);
            }
        }






        //FAILSAFE O-to-Vanilla Converter
        //Horse
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_HORSES.get() && event.getEntity() instanceof OHorse) {
            OHorse oHorse1 = (OHorse) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Horse horse = EntityType.HORSE.create(event.getLevel());
            if (horse != null) {
                horse.copyPosition(oHorse1);
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
        if (LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && !LivestockOverhaulCommonConfig.REPLACE_BEES.get() && event.getEntity() instanceof OBee oBee) {
            Bee bee = EntityType.BEE.create(event.getLevel());
            oBee.remove(Entity.RemovalReason.DISCARDED);
            event.getLevel().addFreshEntity(bee);
            bee.copyPosition(oBee);
            bee.setCustomName(oBee.getCustomName());
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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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
                Entity entity = event.getEntity();

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

    }
}