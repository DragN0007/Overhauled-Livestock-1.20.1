package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.util.LOUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class LlamaBreedTooltip implements IEntityComponentProvider {
    public LlamaBreedTooltip() {}

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OLlama animal && entityAccessor.getPlayer().isShiftKeyDown()) {
            String breeds = LOUtils.getLlamaBreeds(animal.getWooly());
            tooltip.add(Component.translatable(breeds));
        }
    }

    @Override
    public ResourceLocation getUid() {return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");}
}
