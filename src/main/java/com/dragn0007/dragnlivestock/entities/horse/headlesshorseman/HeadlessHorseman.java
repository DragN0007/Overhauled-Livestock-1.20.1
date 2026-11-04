package com.dragn0007.dragnlivestock.entities.horse.headlesshorseman;

import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.util.LOAnims;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class HeadlessHorseman extends Monster implements GeoEntity {
	public HeadlessHorseman(EntityType<? extends HeadlessHorseman> type, Level level) {
		super(type, level);
		this.noCulling = true;
		this.xpReward = 80;
	}

	public static AttributeSupplier.Builder createBaseHorseAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.JUMP_STRENGTH)
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25F)
				.add(Attributes.ATTACK_DAMAGE, 5D)
				.add(Attributes.ATTACK_KNOCKBACK, 1F);
	}

	public boolean shouldDespawnInPeaceful() {return true;}

	@Override
	public boolean canBeLeashed(Player p_21418_) {return false;}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
        AnimationController<T> controller = tAnimationState.getController();
        double x = this.getX() - this.xo;
        double z = this.getZ() - this.zo;
        boolean isMoving = (x * x + z * z) > 0.0001;

		double mountx = 0;
		double mountz = 0;
		if (this.isPassenger()) {
			mountx = this.getVehicle().getX() - this.getVehicle().xo;
			mountz = this.getVehicle().getZ() - this.getVehicle().zo;
		}
        boolean mountIsMoving = (mountx * mountx + mountz * mountz) > 0.0001;

        if (isMoving || (this.isPassenger() && mountIsMoving)) {
            if (this.isAggressive()) {
                if (this.isPassenger()) {
                    controller.setAnimation(RawAnimation.begin().then("ride_sprint", Animation.LoopType.LOOP));
                    controller.setAnimationSpeed(1.0);
				} else {
					controller.setAnimation(LOAnims.WALK);
					controller.setAnimationSpeed(1.5);
				}
            } else {
                if (this.isPassenger()) {
                    controller.setAnimation(RawAnimation.begin().then("ride_run", Animation.LoopType.LOOP));
                    controller.setAnimationSpeed(1.0);
                } else {
					controller.setAnimation(LOAnims.WALK);
					controller.setAnimationSpeed(1.0);
				}
            }
        } else {
			if (this.isPassenger()) {
				controller.setAnimation(RawAnimation.begin().then("ride_idle", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.0);
			} else {
				controller.setAnimation(LOAnims.IDLE);
				controller.setAnimationSpeed(1.0);
			}
		}
        return PlayState.CONTINUE;
    }

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
		controllers.add(LOAnims.genericAttackAnimation(this, LOAnims.ATTACK));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {return this.geoCache;}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5D, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 0.0F));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
	}

	public MobType getMobType() {return MobType.UNDEAD;}

	@Override
	public boolean hurt(DamageSource damageSource, float amount) {
		if (damageSource.is(DamageTypes.MAGIC) || damageSource.is(DamageTypes.EXPLOSION)) {return false;}
		return super.hurt(damageSource, amount);
	}

	@Override
	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.ZOMBIE_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {return SoundEvents.ZOMBIE_DEATH;}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		super.getHurtSound(damageSource);
		return SoundEvents.ZOMBIE_HURT;
	}

	@Override
	public boolean causeFallDamage(float f1, float f2, DamageSource damageSource) {return false;}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {return InteractionResult.FAIL;}

	@Override
	public boolean canRiderInteract() {return false;}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		Random random = new Random();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();
		LocalDate date = LocalDate.now();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();

		if ((month == Month.OCTOBER && (day == 31)) || (month == Month.NOVEMBER && (day == 1 || day == 2)) && LivestockOverhaulCommonConfig.ALLOW_HOLIDAY_EVENTS.get()) {
			this.spawnAtLocation(LOItems.HALLOW_HEART.get());
		}

		this.spawnAtLocation(Items.BLACK_CANDLE);
		this.spawnAtLocation(Items.JACK_O_LANTERN);
	}
}
