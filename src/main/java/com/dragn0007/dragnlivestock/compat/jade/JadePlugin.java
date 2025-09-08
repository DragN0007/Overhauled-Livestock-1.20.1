package com.dragn0007.dragnlivestock.compat.jade;

import com.dragn0007.dragnlivestock.blocks.custom.CheeseBase;
import com.dragn0007.dragnlivestock.blocks.custom.JerkyBase;
import com.dragn0007.dragnlivestock.compat.jade.block.CheeseTooltipProvider;
import com.dragn0007.dragnlivestock.compat.jade.block.JerkyTooltipProvider;
import com.dragn0007.dragnlivestock.compat.jade.breed.*;
import com.dragn0007.dragnlivestock.compat.jade.gender.*;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.farm_goat.FarmGoat;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(new MountGenderTooltip(), AbstractOMount.class);
        registration.registerEntityComponent(new ChickenGenderTooltip(), OChicken.class);
        registration.registerEntityComponent(new LlamaGenderTooltip(), OLlama.class);
        registration.registerEntityComponent(new PigGenderTooltip(), OPig.class);
        registration.registerEntityComponent(new RabbitGenderTooltip(), ORabbit.class);
        registration.registerEntityComponent(new SheepGenderTooltip(), OSheep.class);

        registration.registerEntityComponent(new BeeSpeciesTooltip(), OBee.class);
        registration.registerEntityComponent(new CamelBreedTooltip(), OCamel.class);
        registration.registerEntityComponent(new ChickenBreedTooltip(), OChicken.class);
        registration.registerEntityComponent(new CowBreedTooltip(), OCow.class);
        registration.registerEntityComponent(new HorseBreedTooltip(), OHorse.class);
        registration.registerEntityComponent(new LlamaBreedTooltip(), OLlama.class);
        registration.registerEntityComponent(new MuleBreedTooltip(), OMule.class);
        registration.registerEntityComponent(new PigBreedTooltip(), OPig.class);
        registration.registerEntityComponent(new RabbitBreedTooltip(), ORabbit.class);
        registration.registerEntityComponent(new SheepBreedTooltip(), OSheep.class);
        registration.registerEntityComponent(new UnicornSpeciesTooltip(), Unicorn.class);
        registration.registerEntityComponent(new GoatBreedTooltip(), FarmGoat.class);

        registration.registerBlockComponent(new CheeseTooltipProvider(), CheeseBase.class);
        registration.registerBlockComponent(new JerkyTooltipProvider(), JerkyBase.class);
    }
}
