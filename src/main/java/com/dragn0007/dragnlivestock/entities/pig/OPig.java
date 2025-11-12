package com.dragn0007.dragnlivestock.entities.pig;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.util.Taggable;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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
import java.util.Random;

public class OPig extends Animal implements GeoEntity, Taggable {

	public OPig(EntityType<? extends OPig> type, Level level) {
		super(type, level);
	}

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_pig");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/pig");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/pig");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (ModList.get().isLoaded("tfc")) {
			return TFC_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public boolean isMeatBreed() {
		return this.getBreed() == 4 || this.getBreed() == 0;
	}

	public boolean isNormalBreed() {
		return this.getBreed() == 1 || this.getBreed() == 2 || this.getBreed() == 3 || this.getBreed() == 5;
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 0.9F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_PIG_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	@Override
	public float getStepHeight() {
		return 1F;
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(2.0);
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
			}
		} else {
			controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
		}

		return PlayState.CONTINUE;
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		Item item = itemstack.getItem();

		if (item instanceof BrandTagItem) {
			setTagged(true);
			this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
			BrandTagItem tagItem = (BrandTagItem)item;
			DyeColor color = tagItem.getColor();
			if (color != this.getBrandTagColor()) {
				this.setBrandTagColor(color);
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			}
		}

		if (itemstack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getVariant() > 0) {
					this.setVariant(this.getVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			this.setVariant(this.getVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(LOItems.MARKING_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getOverlayVariant() > 0) {
					this.setOverlayVariant(this.getOverlayVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			}
			this.setOverlayVariant(this.getOverlayVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(LOItems.BREED_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getBreed() > 0) {
					this.setBreed(this.getBreed() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			PigBreed.Breed currentBreed = PigBreed.Breed.values()[this.getBreed()];
			PigBreed.Breed nextBreed = currentBreed.next();
			this.setBreed(nextBreed.ordinal());
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(Items.SHEARS) && player.isShiftKeyDown()) {
			if (this.isTagged()) {
				this.setTagged(false);
				this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		} else if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.PIG_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.PIG_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.PIG_HURT;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}


	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.INT);
	public int getBreedLocation() {
		return PigBreed.Breed.values().length;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.INT);
	public ResourceLocation getTextureLocation() {
		return OPigModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.INT);
	public ResourceLocation getOverlayLocation() {return OPigMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}

	protected static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.BOOLEAN);
	public DyeColor getBrandTagColor() {
		return DyeColor.byId(this.entityData.get(BRAND_TAG_COLOR));
	}
	public void setBrandTagColor(DyeColor color) {
		this.entityData.set(BRAND_TAG_COLOR, color.getId());
	}
	@Override
	public boolean isTaggable() {
		return this.isAlive() && !this.isBaby();
	}
	@Override
	public boolean isTagged() {
		return this.entityData.get(TAGGED);
	}
	public void setTagged(boolean tagged) {
		this.entityData.set(TAGGED, tagged);
	}

	public static final EntityDataAccessor<Integer> QUALITY = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.INT);
	public int getQuality() {
		return this.entityData.get(QUALITY);
	}
	public void setQuality(int i) {
		this.entityData.set(QUALITY, i);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if(tag.contains("Quality")) {
			this.setQuality(tag.getInt("Quality"));
		}

		if (tag.contains("Breed")) {
			this.setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Gender")) {
			setGender(tag.getInt("Gender"));
		}

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Quality", this.getQuality());
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putInt("Gender", getGender());
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OPigModel.Variant.values().length));
		setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
		setBreed(random.nextInt(PigBreed.Breed.values().length));
		setGender(random.nextInt(Gender.values().length));
		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			this.setQuality(random.nextInt(30));
		}

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(QUALITY, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
	}

	@Override
	public void equipTag(@javax.annotation.Nullable SoundSource soundSource) {
		if(soundSource != null) {
			this.level().playSound(null, this, SoundEvents.BOOK_PAGE_TURN, soundSource, 0.5f, 1f);
		}
	}

	public enum Gender {
		FEMALE,
		MALE
	}
	public boolean isFemale() {
		return this.getGender() == 0;
	}
	public boolean isMale() {
		return this.getGender() == 1;
	}
	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OPig.class, EntityDataSerializers.INT);
	public int getGender() {
		return this.entityData.get(GENDER);
	}
	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OPig)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OPig) animal).canParent();
			} else {
				OPig partner = (OPig) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}

	public int maxBabyAmount = LivestockOverhaulCommonConfig.MAX_PIG_BABIES.get();
	public int babiesBirthed = 0;
	public int babyCooldown = 0;

	public void tick() {
		super.tick();

		if (babiesBirthed > 0) {
			babyCooldown++;
		}

		if (babiesBirthed >= maxBabyAmount && babyCooldown >= 20) {
			babiesBirthed = 0;
			babyCooldown = 0;
		}

		if (babyCooldown >= 20) {
			babyCooldown = 0;
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OPig piglet;
		OPig partner = (OPig) ageableMob;
		piglet = EntityTypes.O_PIG_ENTITY.get().create(serverLevel);

		int breedChance = this.random.nextInt(5);
		int breed;
		if (breedChance == 0) {
			breed = this.random.nextInt(PigBreed.Breed.values().length);
		} else {
			breed = (this.random.nextInt(2) == 0) ? this.getBreed() : partner.getBreed();
		}
		piglet.setBreed(breed);

		if (!(breedChance == 0)) {
			int variantChance = this.random.nextInt(14);
			int variant;
			if (variantChance < 6) {
				variant = this.getVariant();
			} else if (variantChance < 12) {
				variant = partner.getVariant();
			} else {
				variant = this.random.nextInt(OPigModel.Variant.values().length);
			}
			piglet.setVariant(variant);
		} else if (random.nextDouble() < 0.5) {
			piglet.setColorByBreed();
		}

		if (!(breedChance == 0)) {
			int overlayChance = this.random.nextInt(10);
			int overlay;
			if (overlayChance < 4) {
				overlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				overlay = partner.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(OPigMarkingLayer.Overlay.values().length);
			}
			piglet.setOverlayVariant(overlay);
		} else if (random.nextDouble() < 0.5) {
			piglet.setMarkingByBreed();
		}

		piglet.setGender(random.nextInt(Gender.values().length));

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			int qual_avg = (this.getQuality() + partner.getQuality()) / 2;
			if (random.nextDouble() <= 0.05) {
				piglet.setQuality(qual_avg + random.nextInt(50));
			} else if (random.nextDouble() >= 0.05 && random.nextDouble() <= 0.25) {
				piglet.setQuality(qual_avg + random.nextInt(25));
			} else if (random.nextDouble() >= 0.25 && random.nextDouble() <= 0.60) {
				piglet.setQuality(qual_avg + random.nextInt(10));
			} else {
				piglet.setQuality(qual_avg + random.nextInt(5));
			}
		}

		babiesBirthed++;

		if (babiesBirthed < maxBabyAmount && this.isInLove()) {
			if (random.nextDouble() <= 0.25) {
				spawnChildFromBreeding(serverLevel, partner);
				babiesBirthed++;
			} else if (random.nextDouble() <= 0.50 && babiesBirthed < (maxBabyAmount * 0.50)) {
				spawnChildFromBreeding(serverLevel, partner);
				babiesBirthed++;
			}
		}

		return piglet;
	}

	public void setColorByBreed() {

		if (this.getBreed() == 0) { //yorkshires are pink or sometimes white
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OPigModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.25) {
				this.setVariant(7);
			} else if (random.nextDouble() > 0.25) {
				this.setVariant(5);
			}
		}

		if (this.getBreed() == 1) { //norfolk are pink, brown, red or black
			if (random.nextDouble() < 0.15) {
				this.setVariant(random.nextInt(OPigModel.Variant.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {0, 1, 5, 6};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) { //guinea hogs are black or blue
			if (random.nextDouble() < 0.15) {
				this.setVariant(random.nextInt(OPigModel.Variant.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {0, 2};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) { //kunekunes are black, white or red
			if (random.nextDouble() < 0.15) {
				this.setVariant(random.nextInt(OPigModel.Variant.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {0, 1, 6};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //poland chinas are primarily black
			if (random.nextDouble() < 0.15) {
				this.setVariant(random.nextInt(OPigModel.Variant.values().length));
			} else if (random.nextDouble() > 0.15) {
				this.setVariant(0);
			}
		}

		if (this.getBreed() == 5) { //berkshires are primarily black
			if (random.nextDouble() < 0.30) {
				this.setVariant(random.nextInt(OPigModel.Variant.values().length));
			} else if (random.nextDouble() > 0.30) {
				this.setVariant(0);
			}
		}


	}

	public void setMarkingByBreed() {

		if (this.getBreed() == 0) { //yorkshires dont usually come in markings but can
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 1) { //norfolk can come in all sorts of markings
			if (random.nextDouble() < 0.50) {
				this.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.50) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 2) { //guinea hogs dont usually come in markings but can
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 3) { //kunekunes have spots or splotches
			if (random.nextDouble() < 0.15) {
				this.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {5, 6, 7, 8, 9, 10, 11, 12};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //poland chinas have socks or stripes
			if (random.nextDouble() < 0.15) {
				this.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {0, 1, 2, 3, 4, 15};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) { //berkshires can come in all sorts of markings
			if (random.nextDouble() < 0.50) {
				this.setOverlayVariant(random.nextInt(OPigMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.50) {
				this.setOverlayVariant(0);
			}
		}

	}

	public boolean isFineQuality() {
		return this.getQuality() <= 25;
	}

	public boolean isGreatQuality() {
		return this.getQuality() > 25 && this.getQuality() <= 50;
	}

	public boolean isFantasticQuality() {
		return this.getQuality() > 50 && this.getQuality() <= 75;
	}

	public boolean isExquisiteQuality() {
		return this.getQuality() > 75 && this.getQuality() <= 100;
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {
			if (this.isMeatBreed()) {
				if (random.nextDouble() < 0.40) {
					this.spawnAtLocation(Items.PORKCHOP, 2);
					this.spawnAtLocation(LOItems.PORK_RIB_CHOP.get(), 2);
					this.spawnAtLocation(LOItems.PORK_TENDERLOIN.get(), 2);
					this.spawnAtLocation(Items.LEATHER, 2);
				} else if (random.nextDouble() > 0.40) {
					this.spawnAtLocation(Items.PORKCHOP);
					this.spawnAtLocation(LOItems.PORK_RIB_CHOP.get());
					this.spawnAtLocation(LOItems.PORK_TENDERLOIN.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

			if (this.isNormalBreed()) {
				if (random.nextDouble() < 0.15) {
					this.spawnAtLocation(Items.PORKCHOP);
					this.spawnAtLocation(LOItems.PORK_RIB_CHOP.get());
					this.spawnAtLocation(LOItems.PORK_TENDERLOIN.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

			if (LivestockOverhaulCommonConfig.QUALITY.get()) {
				if (this.isExquisiteQuality()) {
					this.spawnAtLocation(Items.PORKCHOP, 3);
					this.spawnAtLocation(LOItems.PORK_RIB_CHOP.get(), 3);
					this.spawnAtLocation(LOItems.PORK_TENDERLOIN.get(), 3);
					this.spawnAtLocation(Items.LEATHER, 3);
				} else if (this.isFantasticQuality()) {
					this.spawnAtLocation(Items.PORKCHOP, 2);
					this.spawnAtLocation(LOItems.PORK_RIB_CHOP.get(), 2);
					this.spawnAtLocation(LOItems.PORK_TENDERLOIN.get(), 2);
					this.spawnAtLocation(Items.LEATHER, 2);
				} else if (this.isGreatQuality()) {
					this.spawnAtLocation(Items.PORKCHOP);
					this.spawnAtLocation(LOItems.PORK_RIB_CHOP.get());
					this.spawnAtLocation(LOItems.PORK_TENDERLOIN.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

		}
	}

}
