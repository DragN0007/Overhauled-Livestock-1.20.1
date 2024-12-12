package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.OCowHornLayer;
import com.dragn0007.dragnlivestock.entities.cow.OCowMarkingLayer;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloomModel;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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
    @Override
    public @NotNull ResourceLocation getDefaultLootTable() {
        if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
            return VANILLA_LOOT_TABLE;
        }
        return LOOT_TABLE;
    }

    public int regrowPlantsTickCounter = 0;

    private boolean plantsSheared = false;

    public boolean isPlantsSheared() {
        return this.plantsSheared;
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

        if (itemStack.is(Items.SHEARS) && (!isPlantsSheared() || regrowPlantsTickCounter >= 4800)) {
            this.setPlantsSheared(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(Items.GRASS);
            regrowPlantsTickCounter = 0;

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void tick() {
        super.tick();

        regrowPlantsTickCounter++;

        if (regrowPlantsTickCounter >= 1200) {
            this.setPlantsSheared(false);
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

    public boolean getPlantsSheared() {
        return this.plantsSheared;
    }

    public void setPlantsSheared(boolean sheared) {
        this.plantsSheared = sheared;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("Sheared")) {
            setPlantsSheared(tag.getBoolean("Sheared"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putBoolean("Sheared", getPlantsSheared());
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        if (data == null) {
            data = new AgeableMobGroupData(0.2F);
        }
        Random random = new Random();
        setVariant(random.nextInt(SweetBerryMoobloomModel.Variant.values().length));
        setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
        setHornVariant(random.nextInt(OCowHornLayer.HornOverlay.values().length));

        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }
}
