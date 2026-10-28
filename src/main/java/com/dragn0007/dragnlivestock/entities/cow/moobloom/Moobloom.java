package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomModel;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.BovineMarkingOverlay;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class Moobloom extends OCow implements GeoEntity {
    public Moobloom(EntityType<? extends OCow> type, Level level) {
        super(type, level);
    }

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_cow");
    public static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/cow");
    public static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/cow");
    @Override
    public @NotNull ResourceLocation getDefaultLootTable() {
        if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) return VANILLA_LOOT_TABLE;
        if (ModList.get().isLoaded("tfc")) return TFC_LOOT_TABLE;
        return LOOT_TABLE;
    }

    public boolean removeWhenFarAway(double v) {return false;}

    @Override
    public boolean hurt(DamageSource damageSource, float v) {
        if (damageSource.is(DamageTypes.DROWN)) return false;
        return super.hurt(damageSource, v);
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

        if (itemStack.is(LOTags.Items.SHEARS) && player.isShiftKeyDown()) {
            if (this.isTagged()) {
                this.setTagged(false);
                this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }

        if (itemStack.is(LOTags.Items.SHEARS) && (!isSheared() || regrowPlantsCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && !player.isShiftKeyDown()) {
            this.setSheared(true);
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(new ItemStack(this.getShearItem(), random.nextInt(1, 4)), 0F);
            regrowPlantsCounter = 0;
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemStack.is(Items.BOWL) && !this.isBaby()) {
            if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.DAIRY_MILKING_COOLDOWN.get() && this.isDairyBreed() &&
                    (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
                            (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {
                player.playSound(SoundEvents.MOOSHROOM_MILK, 1.0F, 1.0F);
                ItemStack milkStack = ItemUtils.createFilledResult(itemStack, player, this.getMilkItem().getDefaultInstance());
                player.setItemInHand(hand, milkStack);
                replenishMilkCounter = 0;
                setMilked(true);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    public Item getShearItem() {
        return switch (this.getPlantType()) {
            default -> Items.ALLIUM;
            case 1 -> Items.AZURE_BLUET;
            case 2 -> Items.BLUE_ORCHID;
            case 3 -> Items.CORNFLOWER;
            case 4 -> Items.DANDELION;
            case 5 -> Items.LILAC;
            case 6 -> Items.LILY_OF_THE_VALLEY;
            case 7 -> Items.OXEYE_DAISY;
            case 8 -> Items.PEONY;
            case 9 -> Items.POPPY;
            case 10 -> Items.ROSE_BUSH;
            case 11 -> Items.SUNFLOWER;
            case 12 -> Items.PINK_TULIP;
            case 13 -> Items.BEETROOT;
            case 14 -> Items.CARROT;
            case 15 -> Items.MELON_SLICE;
            case 16 -> Items.POTATO;
            case 17 -> Items.PUMPKIN;
            case 18 -> Items.WHEAT;
            case 19 -> Items.AZALEA;
            case 20 -> Items.SWEET_BERRIES;
            case 21 -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("thatsjustpeachy", "peach"));
        };
    }

    public Item getMilkItem() {
        return switch (this.getPlantType()) {
            default -> Items.AIR;
            case 13 -> Items.BEETROOT_SOUP;
            case 14 -> LOItems.CARROT_SOUP.get();
            case 15 -> LOItems.MELON_SOUP.get();
            case 16 -> LOItems.POTATO_SOUP.get();
            case 17 -> LOItems.PUMPKIN_SOUP.get();
            case 18 -> LOItems.GRAIN_SOUP.get();
            case 20 -> LOItems.SWEET_BERRY_SOUP.get();
            case 21 -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("thatsjustpeachy", "peach_cocktail"));
        };
    }

    public Block getPlantBlock() {
        return switch (this.getPlantType()) {
            default -> Blocks.ALLIUM;
            case 1 -> Blocks.AZURE_BLUET;
            case 2 -> Blocks.BLUE_ORCHID;
            case 3 -> Blocks.CORNFLOWER;
            case 4 -> Blocks.DANDELION;
            case 5 -> Blocks.LILAC;
            case 6 -> Blocks.LILY_OF_THE_VALLEY;
            case 7 -> Blocks.OXEYE_DAISY;
            case 8 -> Blocks.PEONY;
            case 9 -> Blocks.POPPY;
            case 10 -> Blocks.ROSE_BUSH;
            case 11 -> Blocks.SUNFLOWER;
            case 12 -> Blocks.PINK_TULIP;
            case 13 -> Blocks.BEETROOTS;
            case 14 -> Blocks.CARROTS;
            case 15 -> Blocks.ATTACHED_MELON_STEM;
            case 16 -> Blocks.POTATOES;
            case 17 -> Blocks.ATTACHED_PUMPKIN_STEM;
            case 18 -> Blocks.WHEAT;
            case 19 -> Blocks.AZALEA;
            case 20 -> Blocks.SWEET_BERRY_BUSH;
            case 21 -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("thatsjustpeachy", "peach_leaves"));
        };
    }

    public int regrowPlantsCounter = 0;
    public int tickCounter = 0;

    @Override
    public void tick() {
        super.tick();
        regrowPlantsCounter++;
        if (regrowPlantsCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) this.setSheared(false);

        tickCounter++;

        if (tickCounter >= 600) {
            tickCounter = 0;
            for (int x = -5; x <= 5; x++) {
                for (int y = -5; y <= 5; y++) {
                    for (int z = -5; z <= 5; z++) {
                        BlockPos blockpos = Moobloom.this.blockPosition().offset(x, y, z);
                        BlockState blockstate = Moobloom.this.level().getBlockState(blockpos);
                        Block block = blockstate.getBlock();
                        if (blockstate.is(this.getPlantBlock())) {
                            if (block instanceof CropBlock cropblock) {
                                if (cropblock.isMaxAge(blockstate)) continue;
                                int currentAge = cropblock.getAge(blockstate);
                                BlockState newState = cropblock.getStateForAge(currentAge + 1);
                                if (!blockstate.equals(newState))
                                    Moobloom.this.level().setBlockAndUpdate(blockpos, newState);
                                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.7D, 0.7D, 0.7D);
                            } else if (block instanceof SweetBerryBushBlock) {
                                int age = blockstate.getValue(SweetBerryBushBlock.AGE);
                                if (age == SweetBerryBushBlock.MAX_AGE) continue;
                                BlockState newState = blockstate.setValue(SweetBerryBushBlock.AGE, age + 1);
                                if (!blockstate.equals(newState)) Moobloom.this.level().setBlockAndUpdate(blockpos, newState);
                                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.7D, 0.7D, 0.7D);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean canParent() {return false;}
    public boolean canMate(Animal animal) {return false;}

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {return null;}

    public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HORN_TYPE = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> HARNESSED = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> BELLED = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.INT);
    public ResourceLocation getTextureLocation() {return OMooshroomModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;}
    public int getVariant() {return this.entityData.get(VARIANT);}
    public void setVariant(int variant) {this.entityData.set(VARIANT, variant);}

    public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.BOOLEAN);
    public boolean isSheared() {return this.entityData.get(SHEARED);}
    public void setSheared(boolean sheared) {this.entityData.set(SHEARED, sheared);}

    public static final EntityDataAccessor<Integer> PLANT_TYPE = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.INT);
    public ResourceLocation getModelResource() {return MoobloomType.breedFromOrdinal(getBreed()).resourceLocation;}
    public int getPlantType() {return this.entityData.get(PLANT_TYPE);}
    public void setPlantType(int variant) {this.entityData.set(PLANT_TYPE, variant);}

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("PlantType")) {setPlantType(tag.getInt("PlantType"));}
        if (tag.contains("Variant")) {setVariant(tag.getInt("Variant"));}
        if (tag.contains("Overlay")) {setOverlayVariant(tag.getInt("Overlay"));}
        if (tag.contains("HornType")) {setHornVariant(tag.getInt("HornType"));}
        if (tag.contains("Gender")) {this.setGender(tag.getInt("Gender"));}
        if (tag.contains("MilkedTime")) {this.replenishMilkCounter = tag.getInt("MilkedTime");}
        if (tag.contains("Milked")) {setMilked(tag.getBoolean("Milked"));}
        if(tag.contains("Tagged")) {this.setTagged(tag.getBoolean("Tagged"));}
        this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));
        if(tag.contains("Harnessed")) {this.setHarnessed(tag.getBoolean("Harnessed"));}
        if(tag.contains("Belled")) {this.setBelled(tag.getBoolean("Belled"));}
        if (tag.contains("Sheared")) {this.setSheared(tag.getBoolean("Sheared"));}
        if (tag.contains("ShearedTime")) {this.regrowPlantsCounter = tag.getInt("ShearedTime");}
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("PlantType", getPlantType());
        tag.putInt("PlantType", getVariant());
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
        if (data == null) data = new AgeableMob.AgeableMobGroupData(0.2F);
        Random random = new Random();

        if (ModList.get().isLoaded("thatsjustpeachy")) this.setPlantType(random.nextInt(MoobloomType.values().length));
        else do this.setPlantType(random.nextInt(MoobloomType.values().length)); while (this.getPlantType() == 21);

        this.setVariant(this.getPlantType());
        this.setOverlayVariant(random.nextInt(BovineMarkingOverlay.values().length));
        this.setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
        this.setGender(0);
        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PLANT_TYPE, 0);
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
