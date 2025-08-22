package com.dragn0007.dragnlivestock.common.entities;

import com.dragn0007.dragnlivestock.common.entities.bee.OBee;
import com.dragn0007.dragnlivestock.common.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.common.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.common.entities.cow.OCow;
import com.dragn0007.dragnlivestock.common.entities.cow.AzaleaMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.BeetrootMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.CarrotMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.FloweringMoobloom;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.GlowBerryMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.MelonMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.PeachMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.PotatoMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.PumpkinMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.SweetBerryMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.WheatMoobloom;
import com.dragn0007.dragnlivestock.common.entities.cow.OMooshroom;
import com.dragn0007.dragnlivestock.common.entities.frog.OFrog;
import com.dragn0007.dragnlivestock.common.entities.frog.Grub;
import com.dragn0007.dragnlivestock.common.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.common.entities.horse.HeadlessHorseman;
import com.dragn0007.dragnlivestock.common.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.common.entities.mule.OMule;
import com.dragn0007.dragnlivestock.common.entities.pig.OPig;
import com.dragn0007.dragnlivestock.common.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.common.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.common.entities.unicorn.Unicorn;
import com.dragn0007.dragnlivestock.common.entities.wagon.CoveredWagon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dragn0007.dragnlivestock.common.LivestockOverhaul.MODID;

public class EntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<OHorse>> O_HORSE_ENTITY = ENTITY_TYPES.register("o_horse",
            () -> EntityType.Builder.of(OHorse::new,
                    MobCategory.CREATURE)
                    .sized(1.5f,2f)
                    .build(new ResourceLocation(MODID,"o_horse").toString()));

    public static final RegistryObject<EntityType<ODonkey>> O_DONKEY_ENTITY = ENTITY_TYPES.register("o_donkey",
            () -> EntityType.Builder.of(ODonkey::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.1f)
                    .build(new ResourceLocation(MODID,"o_donkey").toString()));

    public static final RegistryObject<EntityType<OMule>> O_MULE_ENTITY = ENTITY_TYPES.register("o_mule",
            () -> EntityType.Builder.of(OMule::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,2f)
                    .build(new ResourceLocation(MODID,"o_mule").toString()));

    public static final RegistryObject<EntityType<OCow>> O_COW_ENTITY = ENTITY_TYPES.register("o_cow",
            () -> EntityType.Builder.of(OCow::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"o_cow").toString()));

    public static final RegistryObject<EntityType<OChicken>> O_CHICKEN_ENTITY = ENTITY_TYPES.register("o_chicken",
            () -> EntityType.Builder.of(OChicken::new,
                            MobCategory.CREATURE)
                    .sized(0.7f,0.7f)
                    .build(new ResourceLocation(MODID,"o_chicken").toString()));

    public static final RegistryObject<EntityType<OSalmon>> O_SALMON_ENTITY = ENTITY_TYPES.register("o_salmon",
            () -> EntityType.Builder.of(OSalmon::new,
                            MobCategory.WATER_AMBIENT)
                    .sized(0.7f, 0.4f)
                    .build(new ResourceLocation(MODID,"o_salmon").toString()));

    public static final RegistryObject<EntityType<OCod>> O_COD_ENTITY = ENTITY_TYPES.register("o_cod",
            () -> EntityType.Builder.of(OCod::new,
                            MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.3f)
                    .build(new ResourceLocation(MODID,"o_cod").toString()));

    public static final RegistryObject<EntityType<OBee>> O_BEE_ENTITY = ENTITY_TYPES.register("o_bee",
            () -> EntityType.Builder.of(OBee::new,
                            MobCategory.CREATURE)
                    .sized(0.3f, 0.3f)
                    .build(new ResourceLocation(MODID,"o_bee").toString()));

    public static final RegistryObject<EntityType<ORabbit>> O_RABBIT_ENTITY = ENTITY_TYPES.register("o_rabbit",
            () -> EntityType.Builder.of(ORabbit::new,
                            MobCategory.CREATURE)
                    .sized(0.7f, 0.7f)
                    .build(new ResourceLocation(MODID,"o_rabbit").toString()));

    public static final RegistryObject<EntityType<OLlama>> O_LLAMA_ENTITY = ENTITY_TYPES.register("o_llama",
            () -> EntityType.Builder.of(OLlama::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"o_llama").toString()));

    public static final RegistryObject<EntityType<OSheep>> O_SHEEP_ENTITY = ENTITY_TYPES.register("o_sheep",
            () -> EntityType.Builder.of(OSheep::new,
                            MobCategory.CREATURE)
                    .sized(0.8f,0.8f)
                    .build(new ResourceLocation(MODID,"o_sheep").toString()));

    public static final RegistryObject<EntityType<OPig>> O_PIG_ENTITY = ENTITY_TYPES.register("o_pig",
            () -> EntityType.Builder.of(OPig::new,
                            MobCategory.CREATURE)
                    .sized(1f,1f)
                    .build(new ResourceLocation(MODID,"o_pig").toString()));

    public static final RegistryObject<EntityType<OMooshroom>> O_MOOSHROOM_ENTITY = ENTITY_TYPES.register("o_mooshroom",
            () -> EntityType.Builder.of(OMooshroom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"o_mooshroom").toString()));

    public static final RegistryObject<EntityType<OCamel>> O_CAMEL_ENTITY = ENTITY_TYPES.register("o_camel",
            () -> EntityType.Builder.of(OCamel::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,2.5f)
                    .build(new ResourceLocation(MODID,"o_camel").toString()));

    public static final RegistryObject<EntityType<OGoat>> O_GOAT_ENTITY = ENTITY_TYPES.register("o_goat",
            () -> EntityType.Builder.of(OGoat::new,
                            MobCategory.CREATURE)
                    .sized(0.9f,1f)
                    .build(new ResourceLocation(MODID,"o_goat").toString()));

    public static final RegistryObject<EntityType<FarmGoat>> FARM_GOAT_ENTITY = ENTITY_TYPES.register("farm_goat",
            () -> EntityType.Builder.of(FarmGoat::new,
                            MobCategory.CREATURE)
                    .sized(0.9f,1f)
                    .build(new ResourceLocation(MODID,"farm_goat").toString()));

    public static final RegistryObject<EntityType<OFrog>> O_FROG_ENTITY = ENTITY_TYPES.register("o_frog",
            () -> EntityType.Builder.of(OFrog::new,
                            MobCategory.CREATURE)
                    .sized(0.3f, 0.3f)
                    .build(new ResourceLocation(MODID,"o_frog").toString()));

    public static final RegistryObject<EntityType<Grub>> GRUB_ENTITY = ENTITY_TYPES.register("grub",
            () -> EntityType.Builder.of(Grub::new,
                            MobCategory.CREATURE)
                    .sized(0.3f, 0.3f)
                    .build(new ResourceLocation(MODID,"grub").toString()));


    public static final RegistryObject<EntityType<Unicorn>> UNICORN_ENTITY = ENTITY_TYPES.register("unicorn",
            () -> EntityType.Builder.of(Unicorn::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,2f)
                    .build(new ResourceLocation(MODID,"unicorn").toString()));

    public static final RegistryObject<EntityType<HeadlessHorseman>> HEADLESS_HORSEMAN_ENTITY = ENTITY_TYPES.register("headless_horseman",
            () -> EntityType.Builder.of(HeadlessHorseman::new,
                            MobCategory.MONSTER)
                    .sized(1.5f,2f)
                    .build(new ResourceLocation(MODID,"headless_horseman").toString()));


//    public static final RegistryObject<EntityType<CoveredWagon>> COVERED_WAGON_ENTITY = ENTITY_TYPES.register("covered_wagon",
//            () -> EntityType.Builder.of(CoveredWagon::new,
//                            MobCategory.CREATURE)
//                    .sized(3f,0.8f)
//                    .build(new ResourceLocation(MODID,"covered_wagon").toString()));

    public static final RegistryObject<EntityType<WheatMoobloom>> WHEAT_MOOBLOOM_ENTITY = ENTITY_TYPES.register("wheat_moobloom",
            () -> EntityType.Builder.of(WheatMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"wheat_moobloom").toString()));

    public static final RegistryObject<EntityType<SweetBerryMoobloom>> SWEET_BERRY_MOOBLOOM_ENTITY = ENTITY_TYPES.register("sweet_berry_moobloom",
            () -> EntityType.Builder.of(SweetBerryMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"sweet_berry_moobloom").toString()));

    public static final RegistryObject<EntityType<PumpkinMoobloom>> PUMPKIN_MOOBLOOM_ENTITY = ENTITY_TYPES.register("pumpkin_moobloom",
            () -> EntityType.Builder.of(PumpkinMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"pumpkin_moobloom").toString()));

    public static final RegistryObject<EntityType<PotatoMoobloom>> POTATO_MOOBLOOM_ENTITY = ENTITY_TYPES.register("potato_moobloom",
            () -> EntityType.Builder.of(PotatoMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"potato_moobloom").toString()));

    public static final RegistryObject<EntityType<MelonMoobloom>> MELON_MOOBLOOM_ENTITY = ENTITY_TYPES.register("melon_moobloom",
            () -> EntityType.Builder.of(MelonMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"melon_moobloom").toString()));

    public static final RegistryObject<EntityType<GlowBerryMoobloom>> GLOW_BERRY_MOOBLOOM_ENTITY = ENTITY_TYPES.register("glow_berry_moobloom",
            () -> EntityType.Builder.of(GlowBerryMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"glow_berry_moobloom").toString()));

    public static final RegistryObject<EntityType<FloweringMoobloom>> FLOWERING_MOOBLOOM_ENTITY = ENTITY_TYPES.register("flowering_moobloom",
            () -> EntityType.Builder.of(FloweringMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"flowering_moobloom").toString()));

    public static final RegistryObject<EntityType<CarrotMoobloom>> CARROT_MOOBLOOM_ENTITY = ENTITY_TYPES.register("carrot_moobloom",
            () -> EntityType.Builder.of(CarrotMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"carrot_moobloom").toString()));

    public static final RegistryObject<EntityType<BeetrootMoobloom>> BEETROOT_MOOBLOOM_ENTITY = ENTITY_TYPES.register("beetroot_moobloom",
            () -> EntityType.Builder.of(BeetrootMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"beetroot_moobloom").toString()));

    public static final RegistryObject<EntityType<AzaleaMoobloom>> AZALEA_MOOBLOOM_ENTITY = ENTITY_TYPES.register("azalea_moobloom",
            () -> EntityType.Builder.of(AzaleaMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"azalea_moobloom").toString()));

    public static final RegistryObject<EntityType<Caribou>> CARIBOU_ENTITY = ENTITY_TYPES.register("caribou",
            () -> EntityType.Builder.of(Caribou::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,2f)
                    .build(new ResourceLocation(MODID,"caribou").toString()));

    public static final RegistryObject<EntityType<PeachMoobloom>> PEACH_MOOBLOOM_ENTITY = ENTITY_TYPES.register("peach_moobloom",
            () -> EntityType.Builder.of(PeachMoobloom::new,
                            MobCategory.CREATURE)
                    .sized(1.5f,1.5f)
                    .build(new ResourceLocation(MODID,"peach_moobloom").toString()));

    public static final RegistryObject<EntityType<CoveredWagon>> COVERED_WAGON = ENTITY_TYPES.register("covered_wagon",
            () -> EntityType.Builder.of(CoveredWagon::new,
                    MobCategory.MISC)
                    .sized(3.0F, 3.0F)
                    .build(new ResourceLocation(MODID, "covered_wagon").toString()));
}

