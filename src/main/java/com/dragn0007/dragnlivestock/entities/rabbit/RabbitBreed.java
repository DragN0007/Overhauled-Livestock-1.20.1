package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class RabbitBreed {

    public enum Breed {
        DEFAULT(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/o_rabbit.geo.json")),
        MEAT(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/meat.geo.json")),
        DWARF(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/dwarf.geo.json")),
        LOP(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/lop.geo.json")),
        ANGORA(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/angora.geo.json")),
        ARCH(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/arch.geo.json")),
        CHECKERED_GIANT(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/checkered_giant.geo.json")),
        LIONHEAD(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/lionhead.geo.json")),
        GIANT(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/giant.geo.json")),
        JACKELOPE(new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit/jackelope.geo.json")),
        ;

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
