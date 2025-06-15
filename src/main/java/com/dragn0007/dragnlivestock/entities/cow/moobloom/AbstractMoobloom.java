package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroom;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomModel;
import com.dragn0007.dragnlivestock.entities.marking_layer.BovineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloomModel;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.sun.jna.platform.win32.ShellAPI;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class AbstractMoobloom extends OCow implements GeoEntity {
    public AbstractMoobloom(EntityType<? extends OCow> type, Level level) {
        super(type, level);
    }

    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_cow");
    private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/cow");
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

    public boolean removeWhenFarAway(double v) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource damageSource, float v) {
        if (damageSource.is(DamageTypes.DROWN)) {
            return false;
        }
        return super.hurt(damageSource, v);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.is(Items.SHEARS) && (!isSheared() || regrowPlantsCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && !player.isShiftKeyDown()) {
            this.setSheared(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
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
            regrowPlantsCounter = 0;

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
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

    public boolean canParent() {
        return false;
    }

    public boolean canMate(Animal animal) {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HORN_TYPE = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> HARNESSED = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> BELLED = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.INT);
    public ResourceLocation getTextureLocation() {
        return OMooshroomModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(AbstractMoobloom.class, EntityDataSerializers.BOOLEAN);
    public boolean isSheared() {
        return this.entityData.get(SHEARED);
    }
    public void setSheared(boolean sheared) {
        this.entityData.set(SHEARED, sheared);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("Variant")) {
            setVariant(tag.getInt("Variant"));
        }

        if (tag.contains("Overlay")) {
            setOverlayVariant(tag.getInt("Overlay"));
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
        tag.putInt("Variant", getVariant());
        tag.putInt("Overlay", getOverlayVariant());
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
        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(OVERLAY, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(HORN_TYPE, 0);
        this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
        this.entityData.define(TAGGED, false);
        this.entityData.define(MILKED, false);
        this.entityData.define(HARNESSED, false);
        this.entityData.define(BELLED, false);
        this.entityData.define(SHEARED, false);
    }

}
