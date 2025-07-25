package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class RabbitBreed {

    public enum Breed {
        DEFAULT(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/o_rabbit.geo.json")),
        GIANT(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/giant.geo.json")),
        DWARF(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/dwarf.geo.json")),
        LOP(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/lop.geo.json"));

        public final ResourceLocation resourceLocation;

        Breed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }

        public RabbitBreed.Breed next() {
            return RabbitBreed.Breed.values()[(this.ordinal() + 1) % RabbitBreed.Breed.values().length];
        }
    }

}
