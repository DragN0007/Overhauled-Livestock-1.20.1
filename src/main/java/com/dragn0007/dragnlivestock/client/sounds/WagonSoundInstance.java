package com.dragn0007.dragnlivestock.client.sounds;

import com.dragn0007.dragnlivestock.LOSoundEvents;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;

public class WagonSoundInstance extends AbstractTickableSoundInstance {

    private final AbstractWagon wagon;

    public WagonSoundInstance(AbstractWagon wagon) {
        super(LOSoundEvents.WAGON.get(), SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.wagon = wagon;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.0F;
        this.x = wagon.getX();
        this.y = wagon.getY();
        this.z = wagon.getZ();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if(wagon.isRemoved()) {
            stop();
        } else {
            x = wagon.getX();
            y = wagon.getY();
            z = wagon.getZ();

            double speed = wagon.getDeltaMovement().horizontalDistance();
            if(speed >= 0.01F) {
                float f = (float)(speed / wagon.getMaxSpeed());
                pitch = Mth.lerp(f, 0.75F, 1.0F);
                volume = Mth.lerp(f, 0.0F, 1F);
            } else {
                pitch = 0.0F;
                volume = 0.0F;
            }
        }
    }

}
