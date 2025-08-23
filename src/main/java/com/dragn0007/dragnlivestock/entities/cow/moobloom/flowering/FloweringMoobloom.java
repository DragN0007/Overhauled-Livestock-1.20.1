package com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering;

import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.AbstractMoobloom;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.BovineMarkingOverlay;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class FloweringMoobloom extends AbstractMoobloom implements GeoEntity {
    public FloweringMoobloom(EntityType<? extends AbstractMoobloom> type, Level level) {
        super(type, level);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (item instanceof BrandTagItem) {
            setTagged(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
            BrandTagItem tagItem = (BrandTagItem)item;
            DyeColor color = tagItem.getColor();
            if (color != this.getBrandTagColor()) {
                this.setBrandTagColor(color);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }
        }

        if (itemStack.is(Items.SHEARS) && player.isShiftKeyDown()) {
            if (this.isTagged()) {
                this.setTagged(false);
                this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }

        if (itemStack.is(Items.SHEARS) && (!isSheared() || regrowPlantsCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && !player.isShiftKeyDown()) {
            this.setSheared(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            if (this.getVariant() == 0) {
                this.spawnAtLocation(Items.ALLIUM);
                this.spawnAtLocation(Items.ALLIUM);
                this.spawnAtLocation(Items.ALLIUM);
            }

            if (this.getVariant() == 1) {
                this.spawnAtLocation(Items.AZURE_BLUET);
                this.spawnAtLocation(Items.AZURE_BLUET);
                this.spawnAtLocation(Items.AZURE_BLUET);
            }

            if (this.getVariant() == 2) {
                this.spawnAtLocation(Items.BLUE_ORCHID);
                this.spawnAtLocation(Items.BLUE_ORCHID);
                this.spawnAtLocation(Items.BLUE_ORCHID);
            }

            if (this.getVariant() == 3) {
                this.spawnAtLocation(Items.CORNFLOWER);
                this.spawnAtLocation(Items.CORNFLOWER);
                this.spawnAtLocation(Items.CORNFLOWER);
            }

            if (this.getVariant() == 4) {
                this.spawnAtLocation(Items.DANDELION);
                this.spawnAtLocation(Items.DANDELION);
                this.spawnAtLocation(Items.DANDELION);
            }

            if (this.getVariant() == 5) {
                this.spawnAtLocation(Items.LILY_OF_THE_VALLEY);
                this.spawnAtLocation(Items.LILY_OF_THE_VALLEY);
                this.spawnAtLocation(Items.LILY_OF_THE_VALLEY);
            }

            if (this.getVariant() == 6) {
                this.spawnAtLocation(Items.OXEYE_DAISY);
                this.spawnAtLocation(Items.OXEYE_DAISY);
                this.spawnAtLocation(Items.OXEYE_DAISY);
            }

            if (this.getVariant() == 7) {
                this.spawnAtLocation(Items.POPPY);
                this.spawnAtLocation(Items.POPPY);
                this.spawnAtLocation(Items.POPPY);
            }

            if (this.getVariant() == 8) {
                this.spawnAtLocation(Items.ORANGE_TULIP);
                this.spawnAtLocation(Items.PINK_TULIP);
                this.spawnAtLocation(Items.RED_TULIP);
                this.spawnAtLocation(Items.WHITE_TULIP);
            }

            if (this.getVariant() == 9) {
                this.spawnAtLocation(Items.LILAC);
                this.spawnAtLocation(Items.LILAC);
                this.spawnAtLocation(Items.LILAC);
            }

            if (this.getVariant() == 10) {
                this.spawnAtLocation(Items.PEONY);
                this.spawnAtLocation(Items.PEONY);
                this.spawnAtLocation(Items.PEONY);
            }

            if (this.getVariant() == 11) {
                this.spawnAtLocation(Items.ROSE_BUSH);
                this.spawnAtLocation(Items.ROSE_BUSH);
                this.spawnAtLocation(Items.ROSE_BUSH);
            }

            if (this.getVariant() == 12) {
                this.spawnAtLocation(Items.SUNFLOWER);
                this.spawnAtLocation(Items.SUNFLOWER);
                this.spawnAtLocation(Items.SUNFLOWER);
            }

            regrowPlantsCounter = 0;

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    // Generates the base texture
    public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HORN_TYPE = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> HARNESSED = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> BELLED = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.INT);
    public ResourceLocation getTextureLocation() {
        return FloweringMoobloomModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(FloweringMoobloom.class, EntityDataSerializers.BOOLEAN);
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
            data = new AgeableMob.AgeableMobGroupData(0.2F);
        }
        Random random = new Random();
        setVariant(random.nextInt(FloweringMoobloomModel.Variant.values().length));
        setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
        setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
        setGender(0);

        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(OVERLAY, 0);
        this.entityData.define(HORN_TYPE, 0);
        this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
        this.entityData.define(TAGGED, false);
        this.entityData.define(MILKED, false);
        this.entityData.define(HARNESSED, false);
        this.entityData.define(BELLED, false);
        this.entityData.define(SHEARED, false);
    }

}
