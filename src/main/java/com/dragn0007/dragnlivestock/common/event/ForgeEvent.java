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
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.MountRegistryItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvent {

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
                    for (Mob sourceMob : currentlyLeading) {
                        mob.setLeashedTo(sourceMob, true);
                    }
                } else {
                    mob.setLeashedTo(player, true);
                }

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
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

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof FarmGoat animal) {
                    String breedText = getGoatBreeds(animal.getBreed()).toUpperCase();

                    String coat = animal.getTextureLocation().toString();
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
        return switch (breed) {
            case 0 -> "Angus";
            case 1 -> "Longhorn";
            case 2 -> "Brahman";
            case 3 -> "Mini";
            case 4 -> "Watusi";
            case 5 -> "Corriente";
            case 6 -> "Holstein";
            case 7 -> "Jersey";
            case 8 -> "Hereford";
            case 9 -> "Highland";
            case 10 -> "Ox";
            case 11 -> "Spanish Fighting";
            default -> "Unknown";
        };
    }

    private static String getCowHorns(int horns) {
        return switch (horns) {
            case 0 -> "Polled";
            case 1, 8 -> "Medium";
            case 2, 4, 3 -> "Long";
            case 5, 6 -> "Thick";
            case 7, 10 -> "Small";
            case 9 -> "Zebu";
            default -> "Unknown";
        };
    }

    private static String getPigBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Yorkshire";
            case 1 -> "Pot-Belly";
            case 2 -> "Guinea Hog";
            case 3 -> "Kunekune";
            case 4 -> "Poland-China";
            case 5 -> "Berkshire";
            default -> "Unknown";
        };
    }

    private static String getSheepBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Gulf Coast";
            case 1 -> "Norfolk";
            case 2 -> "Dorset";
            case 3 -> "Jacob";
            case 4 -> "Racka";
            case 5 -> "California Red";
            case 6 -> "Hair";
            case 7 -> "Border Leicester";
            case 8 -> "Fat-Tailed";
            default -> "Unknown";
        };
    }

    private static String getLlamaBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Flocculent";
            case 1 -> "Woolly";
            default -> "Unknown";
        };
    }

    private static String getGoatBreeds(int breed) {
        switch (breed) {
            case 0: return "Classic";
            case 1: return "Meat";
            case 2: return "Nubian";
            case 3: return "Warm";
            case 4: return "Fibrous";
            case 5: return "Dairy";
            default: return "Unknown";
        }
    }
}