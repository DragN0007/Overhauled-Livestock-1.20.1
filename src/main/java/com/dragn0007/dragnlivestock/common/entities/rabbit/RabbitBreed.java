package com.dragn0007.dragnlivestock.common.entities.rabbit;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class RabbitBreed {

    public enum Breed {
        DEFAULT(LivestockOverhaul.id("geo/rabbit/o_rabbit.geo.json")),
        GIANT(LivestockOverhaul.id("geo/rabbit/giant.geo.json")),
        DWARF(LivestockOverhaul.id("geo/rabbit/dwarf.geo.json")),
        LOP(LivestockOverhaul.id("geo/rabbit/lop.geo.json"));

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
