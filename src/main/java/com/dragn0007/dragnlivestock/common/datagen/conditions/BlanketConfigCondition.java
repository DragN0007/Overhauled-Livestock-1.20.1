package com.dragn0007.dragnlivestock.common.datagen.conditions;

import com.dragn0007.dragnlivestock.common.LivestockOverhaulCommonConfig;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class BlanketConfigCondition implements ICondition {
    private final ResourceLocation conditionId;

    public BlanketConfigCondition(ResourceLocation id) {
        this.conditionId = id;
    }

    @Override
    public ResourceLocation getID() {
        return this.conditionId;
    }

    @Override
    public boolean test(IContext context) {
        return LivestockOverhaulCommonConfig.ALLOW_SPECIAL_BLANKET_CRAFTING.get();
    }

    public static class Serializer implements IConditionSerializer<BlanketConfigCondition> {
        private final ResourceLocation conditionId;

        public Serializer(ResourceLocation id) {
            this.conditionId = id;
        }

        @Override
        public void write(JsonObject json, BlanketConfigCondition condition) {
        }

        @Override
        public BlanketConfigCondition read(JsonObject json) {
            return new BlanketConfigCondition(this.conditionId);
        }

        @Override
        public ResourceLocation getID() {
            return this.conditionId;
        }
    }
}