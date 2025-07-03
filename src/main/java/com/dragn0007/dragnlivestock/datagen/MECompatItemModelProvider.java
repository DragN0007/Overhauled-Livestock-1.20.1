package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.compat.MECompatItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MECompatItemModelProvider extends ItemModelProvider {
    public MECompatItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "medievalembroidery", existingFileHelper);
    }

    @Override
    public void registerModels() {

        simpleMedievalItem(MECompatItems.BLACK_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_BLACK_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_BLACK_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_BLACK_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_BLACK_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_BLUE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_BLUE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_BLUE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_BLUE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.BLUE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLUE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLUE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLUE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_BLUE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_BLUE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_BLUE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_BLUE_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_BROWN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_BROWN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_BROWN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_BROWN_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.BROWN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BROWN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BROWN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BROWN_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_BROWN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_BROWN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_BROWN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_BROWN_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_CYAN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_CYAN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_CYAN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_CYAN_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.CYAN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.CYAN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.CYAN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.CYAN_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_CYAN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_CYAN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_CYAN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_CYAN_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_GREEN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_GREEN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_GREEN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_GREEN_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.GREEN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.GREEN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.GREEN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.GREEN_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_GREEN_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_GREEN_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_GREEN_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_GREEN_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_GREY_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_GREY_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_GREY_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_GREY_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.GREY_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.GREY_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.GREY_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.GREY_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_GREY_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_GREY_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_GREY_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_GREY_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.LIGHT_BLUE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.LIGHT_BLUE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.LIGHT_BLUE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.LIGHT_BLUE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.LIGHT_GREY_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.LIGHT_GREY_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.LIGHT_GREY_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.LIGHT_GREY_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_LIME_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_LIME_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_LIME_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_LIME_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.LIME_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.LIME_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.LIME_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.LIME_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_LIME_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_LIME_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_LIME_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_LIME_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_MAGENTA_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_MAGENTA_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_MAGENTA_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_MAGENTA_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.MAGENTA_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.MAGENTA_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.MAGENTA_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.MAGENTA_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_MAGENTA_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_MAGENTA_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_MAGENTA_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_MAGENTA_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_ORANGE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_ORANGE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_ORANGE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_ORANGE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.ORANGE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.ORANGE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.ORANGE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.ORANGE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_ORANGE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_ORANGE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_ORANGE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_ORANGE_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_PINK_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_PINK_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_PINK_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_PINK_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.PINK_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.PINK_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.PINK_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.PINK_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_PINK_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_PINK_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_PINK_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_PINK_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_PURPLE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_PURPLE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_PURPLE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_PURPLE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.PURPLE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.PURPLE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.PURPLE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.PURPLE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_PURPLE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_PURPLE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_PURPLE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_PURPLE_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_RED_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_RED_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_RED_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_RED_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.RED_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.RED_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.RED_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.RED_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_RED_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_RED_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_RED_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_RED_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_WHITE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_WHITE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_WHITE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_WHITE_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_CAPARISON_SHOULDER);

        simpleMedievalItem(MECompatItems.BLACK_YELLOW_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.BLACK_YELLOW_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.BLACK_YELLOW_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.BLACK_YELLOW_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.YELLOW_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.YELLOW_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.YELLOW_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.YELLOW_CAPARISON_SHOULDER);
        simpleMedievalItem(MECompatItems.WHITE_YELLOW_CAPARISON_CAPE);
        simpleMedievalItem(MECompatItems.WHITE_YELLOW_CAPARISON_FULL);
        simpleMedievalItem(MECompatItems.WHITE_YELLOW_CAPARISON_HALF);
        simpleMedievalItem(MECompatItems.WHITE_YELLOW_CAPARISON_SHOULDER);
    }

    private ItemModelBuilder simpleMedievalItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("medievalembroidery","item/" + item.getId().getPath()));
    }
}