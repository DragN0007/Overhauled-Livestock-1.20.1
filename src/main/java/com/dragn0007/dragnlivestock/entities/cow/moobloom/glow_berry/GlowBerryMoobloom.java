package com.dragn0007.dragnlivestock.entities.cow.moobloom.glow_berry;

import com.dragn0007.dragnlivestock.entities.cow.OCowHornLayer;
import com.dragn0007.dragnlivestock.entities.cow.OCowMarkingLayer;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.AbstractMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.wheat.WheatMoobloomModel;
import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class GlowBerryMoobloom extends AbstractMoobloom implements GeoEntity {
    public GlowBerryMoobloom(EntityType<? extends AbstractMoobloom> type, Level level) {
        super(type, level);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.is(Items.SHEARS) && (!isPlantsSheared() || regrowPlantsTickCounter >= 4800)) {
            this.setPlantsSheared(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(Items.GLOW_BERRIES);
            this.spawnAtLocation(Items.GLOW_BERRIES);
            this.spawnAtLocation(Items.GLOW_BERRIES);
            this.spawnAtLocation(Items.GLOW_BERRIES);
            this.spawnAtLocation(Items.GLOW_BERRIES);
            regrowPlantsTickCounter = 0;

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemStack.is(Items.BOWL) && !this.isBaby()) {
            player.playSound(SoundEvents.MOOSHROOM_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, LOItems.GLOW_BERRY_SOUP.get().getDefaultInstance());
            player.setItemInHand(hand, itemstack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    // Generates the base texture
    public ResourceLocation getTextureLocation() {
        return GlowBerryMoobloomModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }

    public ResourceLocation getOverlayLocation() {
        return OCowMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
    }

    public ResourceLocation getHornsLocation() {
        return OCowHornLayer.HornOverlay.hornOverlayFromOrdinal(getHornVariant()).resourceLocation;
    }

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(GlowBerryMoobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(GlowBerryMoobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HORNS = SynchedEntityData.defineId(GlowBerryMoobloom.class, EntityDataSerializers.INT);

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
    public int getOverlayVariant() {
        return this.entityData.get(OVERLAY);
    }
    public int getHornVariant() {
        return this.entityData.get(HORNS);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }
    public void setOverlayVariant(int overlayVariant) {
        this.entityData.set(OVERLAY, overlayVariant);
    }
    public void setHornVariant(int hornVariant) {
        this.entityData.set(HORNS, hornVariant);
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

        if (tag.contains("Horns")) {
            setHornVariant(tag.getInt("Horns"));
        }

        this.updateInventory();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", getVariant());

        tag.putInt("Overlay", getOverlayVariant());

        tag.putInt("Horns", getHornVariant());
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        if (data == null) {
            data = new AgeableMobGroupData(0.2F);
        }
        Random random = new Random();
        setVariant(random.nextInt(GlowBerryMoobloomModel.Variant.values().length));
        setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
        setHornVariant(random.nextInt(OCowHornLayer.HornOverlay.values().length));

        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(OVERLAY, 0);
        this.entityData.define(HORNS, 0);
    }

}
