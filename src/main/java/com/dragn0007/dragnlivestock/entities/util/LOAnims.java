package com.dragn0007.dragnlivestock.entities.util;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class LOAnims {
    public static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("attack");
    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> genericAttackAnimation(T animatable, RawAnimation attackAnimation) {
        return new AnimationController<>(animatable, "attack", 1, state -> {
            if (animatable.swinging) return state.setAndContinue(attackAnimation);
            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }

    public static final RawAnimation IDLE = RawAnimation.begin().then("idle", Animation.LoopType.LOOP);
    public static final RawAnimation GROUND_TIE = RawAnimation.begin().then("ground_tie", Animation.LoopType.LOOP);
    public static final RawAnimation WALK = RawAnimation.begin().then("walk", Animation.LoopType.LOOP);
    public static final RawAnimation TROT = RawAnimation.begin().then("trot", Animation.LoopType.LOOP);
    public static final RawAnimation RUN = RawAnimation.begin().then("run", Animation.LoopType.LOOP);
    public static final RawAnimation SPRINT = RawAnimation.begin().then("sprint", Animation.LoopType.LOOP);
    public static final RawAnimation BACKWARDS_WALK = RawAnimation.begin().then("walk_back", Animation.LoopType.LOOP);
    public static final RawAnimation LEFT_TROT_STRAFE = RawAnimation.begin().then("left_trot_strafe", Animation.LoopType.LOOP);
    public static final RawAnimation RIGHT_TROT_STRAFE = RawAnimation.begin().then("right_trot_strafe", Animation.LoopType.LOOP);
    public static final RawAnimation LEFT_WALK_STRAFE = RawAnimation.begin().then("left_walk_strafe", Animation.LoopType.LOOP);
    public static final RawAnimation RIGHT_WALK_STRAFE = RawAnimation.begin().then("right_walk_strafe", Animation.LoopType.LOOP);
    public static final RawAnimation JUMP = RawAnimation.begin().then("jump", Animation.LoopType.PLAY_ONCE);
    public static final RawAnimation BUCK = RawAnimation.begin().then("buck", Animation.LoopType.LOOP);
}