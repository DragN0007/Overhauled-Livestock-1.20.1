package com.dragn0007.dragnlivestock.blocks.client.render.item;

import com.dragn0007.dragnlivestock.common.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.common.entities.wagon.base.AbstractWagon.Type;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class WagonItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final EntityType<? extends AbstractWagon> type;
    private AbstractWagon entity = null;

    public WagonItemRenderer(EntityType<? extends AbstractWagon> type) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.type = type;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(entity == null)
            entity = type.create(Minecraft.getInstance().level);

        CompoundTag nbt = stack.getTag();
        entity.setWoodType(nbt != null && nbt.contains("type") ? Type.values()[nbt.getInt("type")] : Type.OAK);

        Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0, 0, 0, 0, 0, pose, buffer, packedLight);
    }

}
