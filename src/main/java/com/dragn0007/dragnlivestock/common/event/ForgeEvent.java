package com.dragn0007.dragnlivestock.common.event;

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
import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvent {

    @SubscribeEvent
    public static void onTryCureEntity(PlayerInteractEvent.EntityInteract event) {

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

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+camel/", "");
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

                if (entity instanceof OChicken animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+chicken/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

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
                    String breedText = getCowBreeds(animal.getBreed()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+cow/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    String hornText = getCowHorns(animal.getHornVariant()).toUpperCase();

                    player.displayClientMessage(Component.translatable("Breed: " + breedText + " | " + coatText + " | " + markingText + " | " + "Horns: " + hornText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof ODonkey animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+donkey/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+donkey/", "");
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

                if (entity instanceof FarmGoat animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+farm_goat/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+farm_goat/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    String facemarking = animal.getFaceOverlayLocation().toString();
                    String noFillerTextFaceMarking = facemarking.replaceAll(".+face_overlay/", "");
                    String noUnderscoresFaceTextMarking = noFillerTextFaceMarking.replaceAll("_", " ");
                    String noPNGTextFaceMarking = noUnderscoresFaceTextMarking.replace(".png", "");
                    String facemarkingText = "Face Marking: " + noPNGTextFaceMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + facemarkingText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OFrog animal) {
                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+frog/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OGoat animal) {
                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+goat/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
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

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+horse/", "");
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

                if (entity instanceof OLlama animal) {
                    String breedText = getLlamaBreeds(animal.getWooly()).toUpperCase();

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

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+mule/", "");
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

                if (entity instanceof OPig animal) {
                    String breedText = getPigBreeds(animal.getBreed()).toUpperCase();

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

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+rabbit/", "");
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

                if (entity instanceof OSheep animal) {
                    String breedText = getSheepBreeds(animal.getBreed()).toUpperCase();

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

                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+unicorn/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+unicorn/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }
            }
        }
    }

    private static String getCowBreeds(int breed) {
        switch (breed) {
            case 0: return "Angus";
            case 1: return "Longhorn";
            case 2: return "Brahman";
            case 3: return "Mini";
            case 4: return "Watusi";
            case 5: return "Corriente";
            case 6: return "Holstein";
            case 7: return "Jersey";
            case 8: return "Hereford";
            case 9: return "Highland";
            case 10: return "Ox";
            default: return "Unknown";
        }
    }

    private static String getCowHorns(int horns) {
        switch (horns) {
            case 0: return "Polled";
            case 1, 8: return "Medium";
            case 2, 4, 3: return "Long";
            case 5, 6: return "Thick";
            case 7, 10: return "Small";
            case 9: return "Zebu";
            default: return "Unknown";
        }
    }

    private static String getPigBreeds(int breed) {
        switch (breed) {
            case 0: return "Yorkshire";
            case 1: return "Pot-Belly";
            case 2: return "Guinea Hog";
            case 3: return "Kunekune";
            case 4: return "Poland-China";
            case 5: return "Berkshire";
            default: return "Unknown";
        }
    }

    private static String getSheepBreeds(int breed) {
        switch (breed) {
            case 0: return "Gulf Coast";
            case 1: return "Norfolk";
            case 2: return "Dorset";
            case 3: return "Jacob";
            case 4: return "Racka";
            case 5: return "California Red";
            case 6: return "Hair";
            case 7: return "Border Leicester";
            default: return "Unknown";
        }
    }

    private static String getLlamaBreeds(int breed) {
        switch (breed) {
            case 0: return "Flocculent";
            case 1: return "Woolly";
            default: return "Unknown";
        }
    }
}