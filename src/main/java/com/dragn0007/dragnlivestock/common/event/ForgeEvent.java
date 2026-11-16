package com.dragn0007.dragnlivestock.common.event;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.farm_goat.FarmGoat;
import com.dragn0007.dragnlivestock.entities.frog.OFrog;
import com.dragn0007.dragnlivestock.entities.goat.OGoat;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.AnimalCrateItem;
import com.dragn0007.dragnlivestock.items.custom.MountRegistryItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LOUtils;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.event.ContainerEvent;
import java.util.List;
import java.util.Random;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvent {

    @SubscribeEvent
    public static void onResourceReload(AddReloadListenerEvent event) {
        event.addListener((ResourceManagerReloadListener) resourceManager -> {
            LivestockOverhaul.LOGGER.info("placeholder");
        });
    }

    @SubscribeEvent
    public static void preventCrateItemDespawn(ItemExpireEvent event) {
        if (event.getEntity().getItem().is(LOItems.ANIMAL_CRATE.get())) event.setCanceled(true);
    }

    @SubscribeEvent
    public static void preventFilledCratePickup(EntityItemPickupEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (event.getItem().getItem().is(LOItems.ANIMAL_CRATE.get())) {
                if (AnimalCrateItem.getFilledCrateAmount(player) >= LivestockOverhaulCommonConfig.MAX_CRATES.get()) {
                    player.displayClientMessage(Component.translatable("tooltip.dragnlivestock.too_many_crates.tooltip").withStyle(ChatFormatting.RED), true);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void dropOnDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Entity source = event.getSource().getEntity();
        Random random = new Random();
        if (entity instanceof OPig && ModList.get().isLoaded("kaleidoscope_cookery")) {
            if (source instanceof Player player && player.getMainHandItem().is(LOTags.Items.OIL_KNIVES))
                entity.spawnAtLocation(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kaleidoscope_cookery", "oil")), random.nextInt(2)), 0F);
        }
        if (entity instanceof OPig && ModList.get().isLoaded("farmersdelight")) {
            if (source instanceof Player player && player.getMainHandItem().is(LOTags.Items.HAM_KNIVES))
                entity.spawnAtLocation(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("farmersdelight", "ham")), 1), 0F);
        }
        if (entity instanceof OPig || entity instanceof OSheep || entity instanceof OCow && ModList.get().isLoaded("hexerei"))
            entity.spawnAtLocation(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("hexerei", "animal_fat")), random.nextInt(3)), 0F);
    }

    @SubscribeEvent
    public static void leashHandler(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (event.getTarget() instanceof Mob mob) {
            if (stack.is(Items.LEAD) && player.isShiftKeyDown()) {
                List<Mob> currentlyLeading = mob.level().getEntitiesOfClass(Mob.class,
                        player.getBoundingBox().inflate(10.0D),
                        (animal) -> animal.getLeashHolder() == player
                );

                if (!currentlyLeading.isEmpty()) {
                    for (Mob sourceMob : currentlyLeading) {mob.setLeashedTo(sourceMob, true);}
                } else {
                    mob.setLeashedTo(player, true);
                }

                if (!player.getAbilities().instabuild) {stack.shrink(1);}
            }

            if (mob.isLeashed() && !(mob.getLeashHolder() instanceof Player) && stack.is(Items.SHEARS)) {
                mob.dropLeash(true, !player.isCreative());
                mob.setLeashedTo(null, true);
            }
        }
    }

    @SubscribeEvent
    public static void onUseMountRegistry(PlayerInteractEvent.EntityInteract event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof MountRegistryItem && event.getTarget() instanceof AbstractOMount entity) {
            Player player = event.getEntity();
            CompoundTag tag = stack.getOrCreateTag();
            if (entity.getOwnerUUID() != player.getUUID()) {
                String ownerUUID = stack.getTag().getString("ownerUUID");
                if (!ownerUUID.isEmpty()) {
                    String name = stack.getTag().getString("mount_name");
                    tag.putString("mount_name", entity.getName().getString());
                    tag.putBoolean("has_mount", true);
                    tag.putString("ownerUUID", player.getUUID().toString());
                    tag.putString("owner_name", player.getName().getString());
                    entity.setOwnerUUID(player.getUUID());
                    player.displayClientMessage(Component.translatable(name + " has been transferred to you!").withStyle(ChatFormatting.GOLD), true);
                    if (entity.getOwner() instanceof Player ownerPlayer)
                        ownerPlayer.displayClientMessage(Component.translatable(name + " has been transferred!").withStyle(ChatFormatting.GOLD), true);
                }
            } else {
                tag.putString("mount_name", entity.getName().getString());
                tag.putBoolean("has_mount", true);
                tag.putString("ownerUUID", player.getUUID().toString());
                tag.putString("owner_name", player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onUseMagnifyingGlass(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof LivingEntity entity) {
            Player player = event.getEntity();
            ItemStack stack = event.getItemStack();

            if (stack.is(LOItems.MAGNIFYING_GLASS.get())) {
                if (entity instanceof OCamel animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+camel/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+camel/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OChicken animal) {
                    String breedText;
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+chicken/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    if (animal.getBreed() == 0) {
                        breedText = "Breed: LEGHORN";
                    } else if (animal.getBreed() == 6) {
                        breedText = "Breed: AYAM CEMANI";
                    } else {
                        breedText = "Breed: " + noPNGTextBreed.toUpperCase();
                    }

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+chicken/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OCow animal) {
                    String breedText = LOUtils.getCowBreeds(animal.getBreed()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+cow/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    String hornText = LOUtils.getCowHorns(animal.getHornVariant()).toUpperCase();

                    player.displayClientMessage(Component.translatable("Breed: " + breedText + " | " + coatText + " | " + markingText + " | " + "Horns: " + hornText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof ODonkey animal) {
                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+donkey/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof FarmGoat animal) {
                    String breedText = LOUtils.getGoatBreeds(animal.getBreed()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+farm_goat/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    String facemarking = animal.getFaceOverlayLocation().toString();
                    String noFillerTextFaceMarking = facemarking.replaceAll(".+face_overlay/", "");
                    String noUnderscoresFaceTextMarking = noFillerTextFaceMarking.replaceAll("_", " ");
                    String noPNGTextFaceMarking = noUnderscoresFaceTextMarking.replace(".png", "");
                    String facemarkingText = "Face Marking: " + noPNGTextFaceMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable("Breed: " + breedText + " | " + coatText + " | " + facemarkingText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OFrog animal) {
                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+frog/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Morph Color: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OGoat animal) {
                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+goat/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OHorse animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+horse/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+horse/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OLlama animal) {
                    String breedText = LOUtils.getLlamaBreeds(animal.getWooly()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+llama/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable("Breed: " + breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OMule animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+mule/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+mule/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OPig animal) {
                    String breedText = LOUtils.getPigBreeds(animal.getBreed()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+pig/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable("Breed: " + breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof ORabbit animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+rabbit/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+rabbit/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OSheep animal) {
                    String breedText = LOUtils.getSheepBreeds(animal.getBreed()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+sheep/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable("Breed: " + breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof Unicorn animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+unicorn/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource();
                    String noFillerTextCoat = coat.replaceAll(".+unicorn/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation();
                    String noFillerTextMarking = marking.replaceAll(".+unicorn/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }
            }
        }
    }
}