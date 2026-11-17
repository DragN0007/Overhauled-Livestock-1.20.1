package com.dragn0007.dragnlivestock.blocks.custom;

import com.dragn0007.dragnlivestock.blocks.LOBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class AnimalCrateBlockEntity extends BlockEntity {
    public static final String ANIMAL = "Animal";
    public static final String ENTITY_TYPE = "Entity_Type";
    private String animal;
    private String type;

    public AnimalCrateBlockEntity(BlockPos pos, BlockState state) {
        super(LOBlockEntities.ANIMAL_CRATE.get(), pos, state);
    }

    public void setAnimalNBT(@Nullable String animalNBT) {this.animal = animalNBT;}
    public String getAnimalNBT() {return this.animal == null ? "" : this.animal;}

    public void setEntityTypeNBT(@Nullable String entityTypeNBT) {this.type = entityTypeNBT;}
    public String getEntityTypeNBT() {return this.type == null ? "" : this.type;}

    public void load(CompoundTag tag) {
        super.load(tag);
        this.setAnimalNBT(tag.getString(ANIMAL));
        this.setEntityTypeNBT(tag.getString(ENTITY_TYPE));
    }

    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString(ANIMAL, this.getAnimalNBT());
        tag.putString(ENTITY_TYPE, this.getEntityTypeNBT());
    }
}
