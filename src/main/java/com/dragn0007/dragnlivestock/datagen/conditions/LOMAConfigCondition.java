package com.dragn0007.dragnlivestock.datagen.conditions;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.ModList;

public class LOMAConfigCondition implements ICondition {
    private final ResourceLocation conditionId;

    public LOMAConfigCondition(ResourceLocation id) {
        this.conditionId = id;
    }

    @Override
    public ResourceLocation getID() {
        return this.conditionId;
    }

    @Override
    public boolean test(IContext context) {
        return !ModList.get().isLoaded("dragnloequestrian") ||
                (LivestockOverhaulCommonConfig.ALLOW_ACCENTED_SADDLES.get() && ModList.get().isLoaded("dragnloequestrian"));
    }

    public static class Serializer implements IConditionSerializer<LOMAConfigCondition> {
        private final ResourceLocation conditionId;

        public Serializer(ResourceLocation id) {
            this.conditionId = id;
        }

        @Override
        public void write(JsonObject json, LOMAConfigCondition condition) {
        }

        @Override
        public LOMAConfigCondition read(JsonObject json) {
            return new LOMAConfigCondition(this.conditionId);
        }

        @Override
        public ResourceLocation getID() {
            return this.conditionId;
        }
    }
}