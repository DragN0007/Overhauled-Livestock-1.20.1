package com.dragn0007.dragnlivestock.datagen.conditions;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.ModList;

public class TFCCondition implements ICondition {
    private final ResourceLocation conditionId;

    public TFCCondition(ResourceLocation id) {
        this.conditionId = id;
    }

    @Override
    public ResourceLocation getID() {
        return this.conditionId;
    }

    @Override
    public boolean test(IContext context) {
        return ModList.get().isLoaded("tfc");
    }

    public static class Serializer implements IConditionSerializer<TFCCondition> {
        private final ResourceLocation conditionId;

        public Serializer(ResourceLocation id) {
            this.conditionId = id;
        }

        @Override
        public void write(JsonObject json, TFCCondition condition) {
        }

        @Override
        public TFCCondition read(JsonObject json) {
            return new TFCCondition(this.conditionId);
        }

        @Override
        public ResourceLocation getID() {
            return this.conditionId;
        }
    }
}