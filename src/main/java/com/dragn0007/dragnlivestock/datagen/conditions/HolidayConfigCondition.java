package com.dragn0007.dragnlivestock.datagen.conditions;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class HolidayConfigCondition implements ICondition {
    private final ResourceLocation conditionId;

    public HolidayConfigCondition(ResourceLocation id) {
        this.conditionId = id;
    }

    @Override
    public ResourceLocation getID() {
        return this.conditionId;
    }

    @Override
    public boolean test(IContext context) {
        return LivestockOverhaulCommonConfig.ALLOW_HOLIDAY_EVENTS.get();
    }

    public static class Serializer implements IConditionSerializer<HolidayConfigCondition> {
        private final ResourceLocation conditionId;

        public Serializer(ResourceLocation id) {
            this.conditionId = id;
        }

        @Override
        public void write(JsonObject json, HolidayConfigCondition condition) {
        }

        @Override
        public HolidayConfigCondition read(JsonObject json) {
            return new HolidayConfigCondition(this.conditionId);
        }

        @Override
        public ResourceLocation getID() {
            return this.conditionId;
        }
    }
}