package com.dragn0007.dragnlivestock.entities.cow.mooshroom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.marking_layer.BovineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.cow.CowBreed;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class OMooshroom extends OCow implements GeoEntity {
    public OMooshroom(EntityType<? extends OCow> type, Level level) {
        super(type, level);
    }

    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_mooshroom");
    private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/mooshroom");
    private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/cow");
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
    public float getWalkTargetValue(BlockPos p_28933_, LevelReader p_28934_) {
        return p_28934_.getBlockState(p_28933_.below()).is(Blocks.MYCELIUM) ? 10.0F : p_28934_.getPathfindingCostFromLightLevels(p_28933_);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (itemStack.is(Items.SHEARS) && (!isSheared() || regrowPlantsCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && !player.isShiftKeyDown()) {
            this.setSheared(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            if (this.getMushroomVariant() == 0) {
                if (random.nextDouble() < 0.40) {
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                } else {
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                    this.spawnAtLocation(Items.BROWN_MUSHROOM);
                }
            } else if (this.getMushroomVariant() == 1) {
                if (random.nextDouble() < 0.40) {
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                } else {
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                    this.spawnAtLocation(Items.RED_MUSHROOM);
                }
            }
            regrowPlantsCounter = 0;

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemStack.is(Items.BOWL) && !this.isBaby()) {
            if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get() && !this.isDairyBreed() &&
                    (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
                            (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {
                player.playSound(SoundEvents.MOOSHROOM_MILK, 1.0F, 1.0F);
                ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, Items.MUSHROOM_STEW.getDefaultInstance());
                player.setItemInHand(hand, itemstack1);
                replenishMilkCounter = 0;
                setMilked(true);
            } else if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.DAIRY_MILKING_COOLDOWN.get() && this.isDairyBreed() &&
                    (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
                            (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {
                player.playSound(SoundEvents.MOOSHROOM_MILK, 1.0F, 1.0F);
                ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, Items.MUSHROOM_STEW.getDefaultInstance());
                player.setItemInHand(hand, itemstack1);
                replenishMilkCounter = 0;
                setMilked(true);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public int regrowPlantsCounter = 0;

    @Override
    public void tick() {
        super.tick();

        regrowPlantsCounter++;

        if (regrowPlantsCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
            this.setSheared(false);
        }
    }

    // Generates the base texture
    public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HORN_TYPE = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> HARNESSED = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> BELLED = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);
    public ResourceLocation getTextureLocation() {
        return OMooshroomModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public static final EntityDataAccessor<Integer> MUSHROOMS = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);
    public ResourceLocation getMushroomLocation() {
        return OMooshroomMushroomLayer.MushroomOverlay.overlayFromOrdinal(getMushroomVariant()).resourceLocation;
    }
    public int getMushroomVariant() {
        return this.entityData.get(MUSHROOMS);
    }
    public void setMushroomVariant(int mushroomVariant) {
        this.entityData.set(MUSHROOMS, mushroomVariant);
    }

    public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.BOOLEAN);
    public boolean isSheared() {
        return this.entityData.get(SHEARED);
    }
    public void setSheared(boolean sheared) {
        this.entityData.set(SHEARED, sheared);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("Breed")) {
            setBreed(tag.getInt("Breed"));
        }

        if (tag.contains("Variant")) {
            setVariant(tag.getInt("Variant"));
        }

        if (tag.contains("Overlay")) {
            setOverlayVariant(tag.getInt("Overlay"));
        }

        if (tag.contains("Mushrooms")) {
            setMushroomVariant(tag.getInt("Mushrooms"));
        }

        if (tag.contains("HornType")) {
            setHornVariant(tag.getInt("HornType"));
        }

        if (tag.contains("Gender")) {
            this.setGender(tag.getInt("Gender"));
        }

        if (tag.contains("MilkedTime")) {
            this.replenishMilkCounter = tag.getInt("MilkedTime");
        }

        if (tag.contains("Milked")) {
            setMilked(tag.getBoolean("Milked"));
        }

        if(tag.contains("Tagged")) {
            this.setTagged(tag.getBoolean("Tagged"));
        }

        this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));

        if(tag.contains("Harnessed")) {
            this.setHarnessed(tag.getBoolean("Harnessed"));
        }

        if(tag.contains("Belled")) {
            this.setBelled(tag.getBoolean("Belled"));
        }

        if (tag.contains("Sheared")) {
            this.setSheared(tag.getBoolean("Sheared"));
        }

        if (tag.contains("ShearedTime")) {
            this.regrowPlantsCounter = tag.getInt("ShearedTime");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Breed", getBreed());
        tag.putInt("Variant", getVariant());
        tag.putInt("Overlay", getOverlayVariant());
        tag.putInt("Mushrooms", getMushroomVariant());
        tag.putInt("HornType", getHornVariant());
        tag.putInt("Gender", this.getGender());
        tag.putBoolean("Milked", this.wasMilked());
        tag.putInt("MilkedTime", this.replenishMilkCounter);
        tag.putBoolean("Tagged", this.isTagged());
        tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
        tag.putBoolean("Harnessed", this.isHarnessed());
        tag.putBoolean("Belled", this.isBelled());
        tag.putBoolean("Sheared", this.isSheared());
        tag.putInt("ShearedTime", this.regrowPlantsCounter);
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        if (data == null) {
            data = new AgeableMobGroupData(0.2F);
        }
        Random random = new Random();
        setBreed(random.nextInt(CowBreed.Breed.values().length));
        setGender(random.nextInt(Gender.values().length));
        setMushroomVariant(OMooshroomMushroomLayer.MushroomOverlay.values().length);

        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
            this.setColorByBreed();
            this.setMarkingByBreed();
            this.setHornsByBreed();
        } else {
            this.setVariant(random.nextInt(OMooshroomModel.Variant.values().length));
            this.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
            this.setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
        }

        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }

    public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OMooshroom.class, EntityDataSerializers.INT);

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BREED, 0);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(OVERLAY, 0);
        this.entityData.define(MUSHROOMS, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(HORN_TYPE, 0);
        this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
        this.entityData.define(TAGGED, false);
        this.entityData.define(MILKED, false);
        this.entityData.define(HARNESSED, false);
        this.entityData.define(BELLED, false);
        this.entityData.define(SHEARED, false);
    }

    public boolean canParent() {
        return !this.isBaby() && this.isInLove();
    }

    public boolean canMate(Animal animal) {
        if (animal == this) {
            return false;
        } else if (!(animal instanceof OMooshroom)) {
            return false;
        } else {
            if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
                return this.canParent() && ((OMooshroom) animal).canParent();
            } else {
                OMooshroom partner = (OMooshroom) animal;
                if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
                    return isFemale();
                }
            }
        }
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        OMooshroom calf;
        OMooshroom partner = (OMooshroom) ageableMob;
        calf = EntityTypes.O_MOOSHROOM_ENTITY.get().create(serverLevel);

        int breedChance = this.random.nextInt(5);
        int breed;
        if (breedChance == 0) {
            breed = this.random.nextInt(CowBreed.Breed.values().length);
        } else {
            breed = (this.random.nextInt(2) == 0) ? this.getBreed() : partner.getBreed();
        }
        calf.setBreed(breed);

        int variantChance = this.random.nextInt(14);
        int variant;
        if (variantChance < 6) {
            variant = this.getVariant();
        } else if (variantChance < 12) {
            variant = partner.getVariant();
        } else {
            variant = this.random.nextInt(OMooshroomModel.Variant.values().length);
        }
        calf.setVariant(variant);

        if (!(breedChance == 0) && random.nextDouble() > 0.5) {
            int overlayChance = this.random.nextInt(10);
            int overlay;
            if (overlayChance < 4) {
                overlay = this.getOverlayVariant();
            } else if (overlayChance < 8) {
                overlay = partner.getOverlayVariant();
            } else {
                overlay = this.random.nextInt(BovineMarkingOverlay.values().length);
            }
            calf.setOverlayVariant(overlay);
        } else if (breedChance == 0 && random.nextDouble() < 0.5) {
            calf.setMarkingByBreed();
        }

        int mushroomChance = this.random.nextInt(10);
        int mushrooms;
        if (mushroomChance < 4) {
            mushrooms = this.getMushroomVariant();
        } else if (mushroomChance < 8) {
            mushrooms = partner.getMushroomVariant();
        } else {
            mushrooms = this.random.nextInt(OMooshroomMushroomLayer.MushroomOverlay.values().length);
        }
        calf.setMushroomVariant(mushrooms);

        if (!(breedChance == 0) && random.nextDouble() > 0.5) {
            int hornsChance = this.random.nextInt(10);
            int hornType;
            if (hornsChance < 4) {
                hornType = this.getHornVariant();
            } else if (hornsChance < 8) {
                hornType = partner.getHornVariant();
            } else {
                hornType = this.random.nextInt(OCow.BreedHorns.values().length);
            }
            calf.setHornVariant(hornType);
        } else if (breedChance == 0 && random.nextDouble() < 0.5) {
            calf.setHornsByBreed();
        }

        int gender;
        gender = this.random.nextInt(OCow.Gender.values().length);
        calf.setGender(gender);

        return calf;
    }

    public void setColorByBreed() {

        if (random.nextDouble() < 0.05) {
            this.setVariant(random.nextInt(OMooshroomModel.Variant.values().length));
        } else if (random.nextDouble() > 0.05) {
            int[] variants = {0, 7, 9, 10, 11};
            int randomIndex = new Random().nextInt(variants.length);
            this.setVariant(variants[randomIndex]);
        }
    }

}
