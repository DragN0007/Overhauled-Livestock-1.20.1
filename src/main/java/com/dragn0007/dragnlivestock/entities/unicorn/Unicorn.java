package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.UnicornMenu;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleMenuProvider;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
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
		if (this.getSpecies() == 0) {
			return O_LOOT_TABLE;
		}
		if (this.getSpecies() == 1) {
			return N_LOOT_TABLE;
		}
		if (this.getSpecies() == 2) {
			return E_LOOT_TABLE;
		} else {
			return VANILLA_LOOT_TABLE;
		}
	}

	@Override
	public boolean handleEating(Player player, ItemStack stack) {
		int i = 0;
		int j = 0;
		float f = 0.0F;
		boolean flag = false;
		if (isFood(stack)) {
			i = 90;
			j = 6;
			f = 10.0F;
			if (this.isTamed() && this.getAge() == 0 && this.canFallInLove()) {
				flag = true;
				this.setInLove(player);
			}
		}

		if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
			this.heal(f);
			flag = true;
		}

		if (this.isBaby() && i > 0) {
			this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
			if (!this.level().isClientSide) {
				this.ageUp(i);
			}

			flag = true;
		}

		if (j > 0 && (flag || !this.isTamed()) && this.getTemper() < this.getMaxTemper()) {
			flag = true;
			if (!this.level().isClientSide) {
				this.modifyTemper(j);
			}
		}

		if (flag) {
			this.gameEvent(GameEvent.ENTITY_INTERACT);
			if (!this.isSilent()) {
				SoundEvent soundevent = this.getEatingSound();
				if (soundevent != null) {
					this.level().playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(), this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
				}
			}
		}

		return flag;
	}

	@Override
	public boolean hasGrowableHair() {
		return true;
	}

	public void openInventory(Player player) {
		if(player instanceof ServerPlayer serverPlayer && this.isTamed()) {
			NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
				return new UnicornMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1F, (double)(this.getBbWidth() * 1.4F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static final Ingredient OVERWORLD_FOOD_ITEMS = Ingredient.of(Items.HEART_OF_THE_SEA, Items.AMETHYST_CLUSTER, Items.EMERALD);
	public static final Ingredient NETHER_FOOD_ITEMS = Ingredient.of(Items.NETHERITE_SCRAP, Items.QUARTZ_BLOCK, Items.GOLD_BLOCK);
	public static final Ingredient END_FOOD_ITEMS = Ingredient.of(Items.DRAGON_BREATH, Items.END_CRYSTAL, Items.ENDER_PEARL);
	public boolean isFood(ItemStack stack) {
		if (this.getSpecies() == 0) {
			return OVERWORLD_FOOD_ITEMS.test(stack);
		} else if (this.getSpecies() == 1) {
			return NETHER_FOOD_ITEMS.test(stack);
		} else if (this.getSpecies() == 2) {
			return END_FOOD_ITEMS.test(stack);
		} else {
			return AbstractOMount.FOOD_ITEMS.test(stack);
		}
	}

	public static AttributeSupplier.Builder createUnicornAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.JUMP_STRENGTH)
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.250F)
				.add(Attributes.ATTACK_DAMAGE, 4D)
				.add(Attributes.ATTACK_KNOCKBACK, 2F);
	}

	public void randomizeUnicornAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateRandomMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomJumpStrength());
	}

	public float generateRandomMaxHealth() {
		return 60.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
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
	}

	@Override
	public boolean canStandOnFluid(FluidState fluidState) {
		if (this.getSpecies() == 1) {
			return fluidState.is(FluidTags.LAVA);
		}
		return false;
	}

	@Override
	public boolean hurt(DamageSource damageSource, float v) {
		if (this.getSpecies() == 1 && (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.EXPLOSION))) {
			return false;
		}

		if ((this.getSpecies() == 0 || this.getSpecies() == 2) && (damageSource.is(DamageTypes.MAGIC) || damageSource.is(DamageTypes.EXPLOSION))) {
			return false;
		}

		return super.hurt(damageSource, v);
	}

	public int pearlTime = this.random.nextInt(6000) + 6000;

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.getSpecies() == 2 && !this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.pearlTime <= 0) {
			this.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(Items.ENDER_PEARL);
			this.pearlTime = this.random.nextInt(6000) + 6000;
		}

		if (this.getSpecies() == 0) {
			this.level().addParticle(ParticleTypes.MYCELIUM, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}

		if (this.getSpecies() == 1) {
			this.level().addParticle(ParticleTypes.WARPED_SPORE, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}

		if (this.getSpecies() == 2) {
			this.level().addParticle(ParticleTypes.ENCHANT, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean causeFallDamage(float f1, float f2, DamageSource damageSource) {
		return false;
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX = 0;
			double offsetY = 1.4;
			double offsetZ = -0.1;

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

	public static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getSpeciesLocation() {
		return UnicornSpecies.Breed.values().length;
	}
	public int getSpecies() {
		return this.entityData.get(SPECIES);
	}
	public void setSpecies(int breed) {
		this.entityData.set(SPECIES, breed);
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
		return EquineEyeColorOverlay.eyesFromOrdinal(getEyeVariant()).resourceLocation;
	}
	public int getEyeVariant() {
		return this.entityData.get(EYES);
	}
	public void setEyeVariant(int eyeVariant) {
		this.entityData.set(EYES, eyeVariant);
	}


	public static final EntityDataAccessor<Integer> HORN = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public ResourceLocation getHornTextureResource() {
		return UnicornHornLayer.Overlay.overlayFromOrdinal(getHornVariant()).resourceLocation;
	}
	public int getHornVariant() {
		return this.entityData.get(HORN);
	}
	public void setHornVariant(int eyeVariant) {
		this.entityData.set(HORN, eyeVariant);
	}


	public static final EntityDataAccessor<Integer> FEATHERING = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getFeathering() {
		return this.entityData.get(FEATHERING);
	}
	public void setFeathering(int feathering) {
		this.entityData.set(FEATHERING, feathering);
	}

	public static final EntityDataAccessor<ItemStack> FLOWER_ITEM = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.ITEM_STACK);
	public ItemStack getFlowerItem() {
		return this.entityData.get(FLOWER_ITEM);
	}
	public void setFlowerItem(ItemStack decorItem) {
		this.entityData.set(FLOWER_ITEM, decorItem);
	}

	public static final EntityDataAccessor<Integer> FLOWER_TYPE = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getFlowerType() {
		return this.entityData.get(FLOWER_TYPE);
	}
	public void setFlowerType(int decompVariant) {
		this.entityData.set(FLOWER_TYPE, decompVariant);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("Species")) {
			this.setSpecies(tag.getInt("Species"));
		}

		if (tag.contains("Variant")) {
			this.setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			this.setOverlayVariant(tag.getInt("Overlay"));
		}

//		if (LivestockOverhaulCommonConfig.DYNAMIC_RESOURCES.get()) {
//			if (tag.contains("Variant_Texture")) {
//				this.setVariantTexture(tag.getString("Variant_Texture"));
//			}
//
//			if (tag.contains("Overlay_Texture")) {
//				this.setOverlayVariantTexture(tag.getString("Overlay_Texture"));
//			}
//		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Mane")) {
			this.setManeType(tag.getInt("Mane"));
		}

		if (tag.contains("Feathering")) {
			this.setFeathering(tag.getInt("Feathering"));
		}

		if (tag.contains("Eyes")) {
			this.setEyeVariant(tag.getInt("Eyes"));
		}

		if (tag.contains("Horn")) {
			this.setHornVariant(tag.getInt("Horn"));
		}

		if (tag.contains("SprintTime")) {
			this.sprintTick = tag.getInt("SprintTime");
		}

		if (tag.contains("ManeGrowthTime")) {
			this.maneGrowthTick = tag.getInt("ManeGrowthTime");
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

		if (tag.contains("Flower_Type")) {
			this.setFlowerType(tag.getInt("Flower_Type"));
		}

		if(tag.contains("FlowerItem")) {
			ItemStack decorItem = ItemStack.of(tag.getCompound("FlowerItem"));
			this.setFlowerItem(decorItem);
		}

		this.updateContainerEquipment();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Species", this.getSpecies());
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
//		if (LivestockOverhaulCommonConfig.DYNAMIC_RESOURCES.get()) {
//			tag.putString("Variant_Texture", this.getTextureResource().toString());
//			tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
//		}
		tag.putInt("Gender", this.getGender());
		tag.putInt("Mane", this.getManeType());
		tag.putInt("Feathering", this.getFeathering());
		tag.putInt("Eyes", this.getEyeVariant());
		tag.putInt("Horn", this.getHornVariant());
		tag.putInt("SprintTime", this.sprintTick);
		tag.putInt("ManeGrowthTime", this.maneGrowthTick);
		tag.putInt("Flower_Type", this.getFlowerType());
		if(!this.getFlowerItem().isEmpty()) {
			tag.put("FlowerItem", this.getFlowerItem().save(new CompoundTag()));
		}

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
			data = new AgeableMob.AgeableMobGroupData(0.2F);
		}
		Random random = new Random();

		this.setGender(random.nextInt(Gender.values().length));
		this.setSpecies(random.nextInt(UnicornSpecies.Breed.values().length));

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByBreed();
			this.setMarkingByBreed();
			this.setHornByBreed();
			this.setFeatheringByBreed();
		} else {
			this.setVariant(random.nextInt(UnicornModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(UnicornMarkingLayer.Overlay.values().length));
			this.setHornVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
			this.setFeathering(random.nextInt(Feathering.values().length));
		}

		if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
			this.setEyeColorByChance();
		} else {
			this.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
		}

		int randomMane = 1 + this.getRandom().nextInt(3);
		this.setManeType(randomMane);

		this.randomizeUnicornAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SPECIES, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(VARIANT_TEXTURE, UnicornModel.Variant.BAY.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, UnicornMarkingLayer.Overlay.NONE.resourceLocation);
		this.entityData.define(MANE_TYPE, 0);
		this.entityData.define(FEATHERING, 0);
		this.entityData.define(EYES, 0);
		this.entityData.define(HORN, 0);
		this.entityData.define(FLOWER_ITEM, ItemStack.EMPTY);
		this.entityData.define(FLOWER_TYPE, 0);
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof Unicorn) && !(animal instanceof OHorse)) {
			return false;
		} else {
			if (LivestockOverhaulCommonConfig.UNICORN_BREEDING.get()) {
				if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
					return this.canParent() && ((AbstractOMount) animal).canParent();
				} else {
					AbstractOMount partner = (AbstractOMount) animal;
					if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
						return this.isFemale();
					}
				}
			}
		}
		return false;
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

	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getGender() {
		return this.entityData.get(GENDER);
	}
	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		AbstractOMount foal;
		if (ageableMob instanceof OHorse partnerHorse && ageableMob.getClass() == OHorse.class) {

			if (random.nextDouble() > 0.05) {
				foal = EntityTypes.O_HORSE_ENTITY.get().create(serverLevel);
			} else {
				foal = EntityTypes.UNICORN_ENTITY.get().create(serverLevel);
			}

			int overlayChance = this.random.nextInt(10);
			int overlay;
			if (overlayChance < 4) {
				overlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				overlay = partnerHorse.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(UnicornMarkingLayer.Overlay.values().length);
			}
			(foal).setVariant(overlay);

			((OHorse) foal).setManeType(3);
			((OHorse) foal).setTailType(3);
			(foal).setOverlayVariant(overlay);
			(foal).setVariant(random.nextInt(OHorseModel.Variant.values().length));

		} else {
			Unicorn partner = (Unicorn) ageableMob;
			foal = EntityTypes.UNICORN_ENTITY.get().create(serverLevel);

			int breed;
			breed = (this.random.nextInt(2) == 0) ? this.getSpecies() : partner.getSpecies();
			((Unicorn) foal).setSpecies(breed);


			int variantChance = this.random.nextInt(14);
			int variant;
			if (variantChance < 6) {
				variant = this.getVariant();
			} else if (variantChance < 12) {
				variant = partner.getVariant();
			} else {
				variant = this.random.nextInt(UnicornModel.Variant.values().length);
			}
			(foal).setVariant(variant);

			int overlayChance = this.random.nextInt(10);
			int overlay;
			if (overlayChance < 4) {
				overlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				overlay = partner.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(UnicornMarkingLayer.Overlay.values().length);
			}
			(foal).setOverlayVariant(overlay);

			int eyeColorChance = this.random.nextInt(11);
			int eyes;
			if (eyeColorChance < 5) {
				eyes = this.getEyeVariant();
			} else if (eyeColorChance < 10) {
				eyes = partner.getEyeVariant();
			} else {
				eyes = this.random.nextInt(EquineEyeColorOverlay.values().length);
			}
			((Unicorn) foal).setEyeVariant(eyes);

			int hornColorChance = this.random.nextInt(11);
			int horn;
			if (hornColorChance < 5) {
				horn = this.getHornVariant();
			} else if (hornColorChance < 10) {
				horn = partner.getHornVariant();
			} else {
				horn = this.random.nextInt(EquineEyeColorOverlay.values().length);
			}
			((Unicorn) foal).setHornVariant(horn);

			foal.setGender(random.nextInt(Gender.values().length));

			((Unicorn) foal).setFeatheringByBreed();
			((Unicorn) foal).setManeType(3);

			if (this.random.nextInt(3) >= 1) {
				((Unicorn) foal).generateRandomOHorseJumpStrength();

				int betterSpeed = (int) Math.max(partner.getSpeed(), this.random.nextInt(10) + 20);
				foal.setSpeed(betterSpeed);

				int betterHealth = (int) Math.max(partner.getHealth(), this.random.nextInt(20) + 40);
				foal.setHealth(betterHealth);
			}
		}

		this.setOffspringAttributes(ageableMob, foal);
		return foal;
	}

	public enum Mane {
		BUTTONS,
		LONG,
		ROACHED,
		SHORT,
		NONE;

		public Unicorn.Mane next() {
			return Unicorn.Mane.values()[(this.ordinal() + 1) % Unicorn.Mane.values().length];
		}
	}

	public static final EntityDataAccessor<Integer> MANE_TYPE = SynchedEntityData.defineId(Unicorn.class, EntityDataSerializers.INT);
	public int getManeType() {
		return this.entityData.get(MANE_TYPE);
	}
	public void setManeType(int mane) {
		this.entityData.set(MANE_TYPE, mane);
	}

	public void setFeatheringByBreed() {

		//overworlds are more likely to have half or full feathering, but have a small chance of having none.
		if (this.getSpecies() == 0) {
			if (random.nextDouble() < 0.15) {
				this.setFeathering(0);
			} else if (random.nextDouble() < 0.50 && random.nextDouble() > 0.15) {
				this.setFeathering(2);
			} else if (random.nextDouble() > 0.50) {
				this.setFeathering(1);
			}
		}

		//nethers are more likely to have no feathering but can have half or full
		if (this.getSpecies() == 1) {
			if (random.nextDouble() < 0.15) {
				this.setFeathering(2);
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.15) {
				this.setFeathering(1);
			} else if (random.nextDouble() > 0.30) {
				this.setFeathering(0);
			}
		}

		//ends are more likely to have half feathering
		if (this.getSpecies() == 2) {
			if (random.nextDouble() < 0.15) {
				this.setFeathering(2);
			} else if (random.nextDouble() < 0.50 && random.nextDouble() > 0.15) {
				this.setFeathering(0);
			} else if (random.nextDouble() > 0.50) {
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

	public void setColorByBreed() {

		if (this.getSpecies() == 0) {
			if (random.nextDouble() < 0.05) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.05) {
				int[] variants = {4, 8, 9, 11, 16, 18, 19, 24, 25, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.30) {
				this.setVariant(24);
			}
		}

		if (this.getSpecies() == 1) {
			if (random.nextDouble() < 0.05) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.05) {
				int[] variants = {0, 1, 2, 3, 5, 6, 10, 12, 13, 17, 20, 21, 26, 32};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.30) {
				this.setVariant(2);
			}
		}

		if (this.getSpecies() == 2) {
			if (random.nextDouble() < 0.05) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.05) {
				int[] variants = {7, 8, 11, 15, 22, 23, 28, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.30) {
				this.setVariant(31);
			}
		}

	}

	public void setMarkingByBreed() {

			if (random.nextDouble() < 0.30) {
				this.setOverlayVariant(random.nextInt(UnicornMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.30) {
				int[] variants = {0, 2, 4, 5, 6, 7, 11, 12, 14, 18, 19, 21, 22, 23, 29, 30, 32, 33, 35, 39, 41, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}

	}

	public void setHornByBreed() {

		if (this.getSpecies() == 0) {
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.30) {
				int[] variants = {0, 1, 2, 3, 4, 5, 6, 7};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getSpecies() == 1) {
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.30) {
				int[] variants = {8, 9, 10, 11, 12, 13, 14, 15};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getSpecies() == 2) {
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(UnicornHornLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.30) {
				int[] variants = {16, 17, 18, 19, 20, 21, 22, 23};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

	}


}
