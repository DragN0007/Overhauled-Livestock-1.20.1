package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.horse.*;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class Unicorn extends OHorse implements GeoEntity {

	public Unicorn(EntityType<? extends Unicorn> type, Level level) {
		super(type, level);
		this.xpReward = 50;
	}

	private static final ResourceLocation O_LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/overworld_unicorn");
	private static final ResourceLocation N_LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/nether_unicorn");
	private static final ResourceLocation E_LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/end_unicorn");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/horse");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (this.getBreed() == 0) {
			return O_LOOT_TABLE;
		}
		if (this.getBreed() == 1) {
			return N_LOOT_TABLE;
		}
		if (this.getBreed() == 2) {
			return E_LOOT_TABLE;
		} else {
			return VANILLA_LOOT_TABLE;
		}
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1F, (double)(this.getBbWidth() * 1.4F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createBaseHorseAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.JUMP_STRENGTH)
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.250F)
				.add(Attributes.ATTACK_DAMAGE, 4D)
				.add(Attributes.ATTACK_KNOCKBACK, 2F);
	}

	@Override
	public void randomizeOHorseAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateRandomMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomJumpStrength());
	}

	public float generateRandomMaxHealth() {
		return 24.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
	}

	public double generateRandomJumpStrength() {
		return (double)0.8F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D;
	}

	public double generateRandomSpeed() {
		return ((double)0.65F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new GroundTieGoal(this));

		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 0.0F));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity
				-> livingEntity instanceof Wolf
		));
	}

	@Override
	public boolean canStandOnFluid(FluidState fluidState) {
		if (this.getBreed() == 1) {
			return fluidState.is(FluidTags.LAVA);
		}
		return false;
	}

	@Override
	public boolean hurt(DamageSource damageSource, float v) {
		if (this.getBreed() == 1 && (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.EXPLOSION))) {
			return false;
		}

		if ((this.getBreed() == 0 || this.getBreed() == 2) && (damageSource.is(DamageTypes.MAGIC) || damageSource.is(DamageTypes.EXPLOSION))) {
			return false;
		}

		return super.hurt(damageSource, v);
	}

	public int pearlTime = this.random.nextInt(6000) + 6000;

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.getBreed() == 2 && !this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.pearlTime <= 0) {
			this.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(Items.ENDER_PEARL);
			this.pearlTime = this.random.nextInt(6000) + 6000;
		}

		if (this.getBreed() == 0) {
			this.level().addParticle(ParticleTypes.MYCELIUM, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}

		if (this.getBreed() == 1) {
			this.level().addParticle(ParticleTypes.WARPED_SPORE, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}

		if (this.getBreed() == 2) {
			this.level().addParticle(ParticleTypes.ENCHANT, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean causeFallDamage(float f1, float f2, DamageSource damageSource) {
		return false;
	}

	@Override
	public void positionRider(Entity entity, MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX = 0;
			double offsetY = 1.4;
			double offsetZ = -0.2;

			if (this.isJumping()) {
				offsetY = 1.7;
				offsetZ = -0.9;
			}

			double radYaw = Math.toRadians(this.getYRot());

			double offsetXRotated = offsetX * Math.cos(radYaw) - offsetZ * Math.sin(radYaw);
			double offsetYRotated = offsetY;
			double offsetZRotated = offsetX * Math.sin(radYaw) + offsetZ * Math.cos(radYaw);

			double x = this.getX() + offsetXRotated;
			double y = this.getY() + offsetYRotated;
			double z = this.getZ() + offsetZRotated;

			entity.setPos(x, y, z);
		}
	}

	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public ResourceLocation getModelResource() {
		return HorseBreed.breedFromOrdinal(getBreed()).resourceLocation;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}


	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
		this.entityData.set(VARIANT_TEXTURE, UnicornModel.Variant.variantFromOrdinal(variant).resourceLocation);
	}
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(Unicorn.class, LivestockOverhaul.RESOURCE_LOCATION);
	public ResourceLocation getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}
	public void setVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = UnicornModel.Variant.BAY.resourceLocation;
		}
		this.entityData.set(VARIANT_TEXTURE, resourceLocation);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
		this.entityData.set(OVERLAY_TEXTURE, UnicornMarkingLayer.Overlay.overlayFromOrdinal(variant).resourceLocation);
	}
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(Unicorn.class, LivestockOverhaul.RESOURCE_LOCATION);
	public ResourceLocation getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}
	public void setOverlayVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = UnicornMarkingLayer.Overlay.NONE.resourceLocation;
		}
		this.entityData.set(OVERLAY_TEXTURE, resourceLocation);
	}


	public static final EntityDataAccessor<Integer> EYES = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public ResourceLocation getEyeTextureResource() {
		return UnicornEyeLayer.EyeOverlay.eyesFromOrdinal(getEyeVariant()).resourceLocation;
	}
	public int getEyeVariant() {
		return this.entityData.get(EYES);
	}
	public void setEyeVariant(int eyeVariant) {
		this.entityData.set(EYES, eyeVariant);
	}


	public static final EntityDataAccessor<Integer> FEATHERING = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getFeathering() {
		return this.entityData.get(FEATHERING);
	}
	public void setFeathering(int feathering) {
		this.entityData.set(FEATHERING, feathering);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("Breed")) {
			this.setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Variant")) {
			this.setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			this.setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Variant_Texture")) {
			this.setVariantTexture(tag.getString("Variant_Texture"));
		}

		if (tag.contains("Overlay_Texture")) {
			this.setOverlayVariantTexture(tag.getString("Overlay_Texture"));
		}

		if (tag.contains("Reindeer_Variant")) {
			this.setReindeerVariant(tag.getInt("Reindeer_Variant"));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Mane")) {
			this.setManeType(tag.getInt("Mane"));
		}

		if (tag.contains("Tail")) {
			this.setTailType(tag.getInt("Tail"));
		}

		if (tag.contains("Feathering")) {
			this.setFeathering(tag.getInt("Feathering"));
		}

		if (tag.contains("Eyes")) {
			this.setEyeVariant(tag.getInt("Eyes"));
		}

		if (tag.contains("SprintTime")) {
			this.sprintTick = tag.getInt("SprintTime");
		}

		if (tag.contains("ManeGrowthTime")) {
			this.maneGrowthTick = tag.getInt("ManeGrowthTime");
		}

		if (tag.contains("TailGrowthTime")) {
			this.tailGrowthTick = tag.getInt("TailGrowthTime");
		}

		this.createInventory();
		if (this.hasChest()) {
			ListTag listtag = tag.getList("Items", 10);

			for(int i = 0; i < listtag.size(); ++i) {
				CompoundTag compoundtag = listtag.getCompound(i);
				int j = compoundtag.getByte("Slot") & 255;
				if (j >= 2 && j < this.inventory.getContainerSize()) {
					this.inventory.setItem(j, ItemStack.of(compoundtag));
				}
			}
		}

		this.updateContainerEquipment();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
		tag.putString("Variant_Texture", this.getTextureResource().toString());
		tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
		tag.putInt("Reindeer_Variant", this.getReindeerVariant());
		tag.putInt("Gender", this.getGender());
		tag.putInt("Mane", this.getManeType());
		tag.putInt("Tail", this.getTailType());
		tag.putInt("Feathering", this.getFeathering());
		tag.putInt("Eyes", this.getEyeVariant());
		tag.putInt("SprintTime", this.sprintTick);
		tag.putInt("ManeGrowthTime", this.maneGrowthTick);
		tag.putInt("TailGrowthTime", this.tailGrowthTick);

		if (this.hasChest()) {
			ListTag listtag = new ListTag();

			for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
				ItemStack itemstack = this.inventory.getItem(i);
				if (!itemstack.isEmpty()) {
					CompoundTag compoundtag = new CompoundTag();
					compoundtag.putByte("Slot", (byte)i);
					itemstack.save(compoundtag);
					listtag.add(compoundtag);
				}
			}

			tag.put("Items", listtag);
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();

		this.setGender(random.nextInt(Gender.values().length));
		this.setColorByBreed();
		this.setMarkingByBreed();
		this.setFeatheringByBreed();
		this.setEyeColorByChance();

		int randomMane = 1 + this.getRandom().nextInt(3);
		this.setManeType(randomMane);

		if (spawnType == MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
			this.setBreed(random.nextInt(UnicornSpecies.Breed.values().length));
			this.setBreed(random.nextInt(4));
		}

		this.randomizeOHorseAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(VARIANT_TEXTURE, UnicornModel.Variant.BAY.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, UnicornMarkingLayer.Overlay.NONE.resourceLocation);
		this.entityData.define(MANE_TYPE, 0);
		this.entityData.define(TAIL_TYPE, 0);
		this.entityData.define(FEATHERING, 0);
		this.entityData.define(EYES, 0);
	}

	@Override
	public boolean canMate(Animal animal) {
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return null;
	}

	public void setFeatheringByBreed() {

		//unicorns are more likely to have half or full feathering, but have a small chance of having none.
		if (this.isPonyBreed()) {
			if (random.nextDouble() < 0.15) {
				this.setFeathering(0);
			} else if (random.nextDouble() < 0.50 && random.nextDouble() > 0.15) {
				this.setFeathering(2);
			} else if (random.nextDouble() > 0.50) {
				this.setFeathering(1);
			} else {
				this.setFeathering(1);
			}
		}

	}

	public void setEyeColorByChance() {

		//white, cream and mostly-white or bald horses have a better chance of gaining blue or green eyes
		if (this.getVariant() == 24 || this.getVariant() == 25 || this.getOverlayVariant() == 2 || this.getOverlayVariant() == 8
				|| this.getOverlayVariant() == 9 || this.getOverlayVariant() == 10 || this.getOverlayVariant() == 15
				|| this.getOverlayVariant() == 17 || this.getOverlayVariant() == 20 || this.getOverlayVariant() == 24
				|| this.getOverlayVariant() == 26 || this.getOverlayVariant() == 32 || this.getOverlayVariant() == 34
				|| this.getOverlayVariant() == 36 || this.getOverlayVariant() == 37 || this.getOverlayVariant() == 38
				|| this.getOverlayVariant() == 39) {
			if (random.nextDouble() < 0.005) {
				this.setEyeVariant(7 + this.getRandom().nextInt(9)); //heterochromic
			} else if (random.nextDouble() < 0.10 && random.nextDouble() > 0.005) {
				this.setEyeVariant(6); //green
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.10) {
				this.setEyeVariant(5); //blue
			} else if (random.nextDouble() > 0.30) {
				this.setEyeVariant(this.getRandom().nextInt(4)); //random (between dark brown and dark blue)
			} else {
				this.setEyeVariant(0);
			}
		} else {
			if (random.nextDouble() < 0.005) {
				this.setEyeVariant(7 + this.getRandom().nextInt(9));
			} else if (random.nextDouble() < 0.03 && random.nextDouble() > 0.005) {
				this.setEyeVariant(6);
			} else if (random.nextDouble() < 0.10 && random.nextDouble() > 0.03) {
				this.setEyeVariant(5);
			} else if (random.nextDouble() > 0.10) {
				this.setEyeVariant(this.getRandom().nextInt(4));
			} else {
				this.setEyeVariant(0);
			}
		}

	}

}
