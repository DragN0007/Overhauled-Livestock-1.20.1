package com.dragn0007.dragnlivestock.blocks.custom;

import com.dragn0007.dragnlivestock.blocks.LOBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AnimalCrateBlockEntity extends BlockEntity {
    public Tag animal;

    public AnimalCrateBlockEntity(BlockPos pos, BlockState state) {
        super(LOBlockEntities.ANIMAL_CRATE.get(), pos, state);
    }

    public void setAnimalNBT(Tag tag) {this.animal = tag;}
    public Tag getAnimalNBT() {return this.animal;}

    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Animal")) this.setAnimalNBT(tag.get("Animal"));
    }

    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.getAnimalNBT() != null) tag.put("Animal", this.animal);
    }
}
