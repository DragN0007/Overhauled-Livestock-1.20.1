package com.dragn0007.dragnlivestock.common.entities.util;

import net.minecraft.sounds.SoundSource;

import javax.annotation.Nullable;

public interface Taggable {
    boolean isTaggable();

    void equipTag(@Nullable SoundSource var1);

    boolean isTagged();
}
