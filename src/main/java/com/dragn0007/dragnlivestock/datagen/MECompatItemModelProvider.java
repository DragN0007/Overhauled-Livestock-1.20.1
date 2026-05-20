package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
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

        simpleMedievalItem(MECompatItems.LEATHER_RUMP_STRAPS);
        simpleMedievalItem(MECompatItems.BLACK_RUMP_STRAPS);
        simpleMedievalItem(MECompatItems.WHITE_RUMP_STRAPS);

        for (DyeColor color : DyeColor.values()) {
            simpleMedievalItem(MECompatItems.SOLID_CAPARISON_CAPES.get(color));
            simpleMedievalItem(MECompatItems.SOLID_CAPARISON_FULLS.get(color));
            simpleMedievalItem(MECompatItems.SOLID_CAPARISON_HALFS.get(color));
            simpleMedievalItem(MECompatItems.SOLID_CAPARISON_SHOULDERS.get(color));
        }

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            simpleMedievalItem(MECompatItems.BLACK_CHECKER_CAPARISON_CAPES.get(color));
            simpleMedievalItem(MECompatItems.BLACK_CHECKER_CAPARISON_FULLS.get(color));
            simpleMedievalItem(MECompatItems.BLACK_CHECKER_CAPARISON_HALFS.get(color));
            simpleMedievalItem(MECompatItems.BLACK_CHECKER_CAPARISON_SHOULDERS.get(color));
        }

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            simpleMedievalItem(MECompatItems.WHITE_CHECKER_CAPARISON_CAPES.get(color));
            simpleMedievalItem(MECompatItems.WHITE_CHECKER_CAPARISON_FULLS.get(color));
            simpleMedievalItem(MECompatItems.WHITE_CHECKER_CAPARISON_HALFS.get(color));
            simpleMedievalItem(MECompatItems.WHITE_CHECKER_CAPARISON_SHOULDERS.get(color));
        }
    }

    private ItemModelBuilder simpleMedievalItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("medievalembroidery","item/" + item.getId().getPath()));
    }
}