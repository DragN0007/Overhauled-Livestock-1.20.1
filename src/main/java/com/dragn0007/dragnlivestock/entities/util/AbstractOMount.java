package com.dragn0007.dragnlivestock.entities.util;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.OMountMenu;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.camel.CamelBreed;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.horse.HorseBreed;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import com.dragn0007.dragnlivestock.entities.mule.MuleBreed;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.HorseShoeItem;
import com.dragn0007.dragnlivestock.items.custom.LightHorseArmorItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractOMount extends AbstractChestedHorse {

    public SimpleContainer getInventory() {
        return inventory;
    }

    public net.minecraftforge.common.util.LazyOptional<?> itemHandler = null;

    public static final float MAX_HEALTH = generateMaxHealth((p_272504_) -> {
        return p_272504_ - 1;
    });

    public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_HORSE_EATS);
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    public int getMaxHeadXRot() {
        return 40;
    }

    @Override
    public int getMaxHeadYRot() {
        return 60;
    }

    public int getTemper() {
        return this.temper;
    }

    public void setTemper(int temper) {
            this.temper = temper;
    }

    public int modifyTemper(int p_30654_) {
        int i = Mth.clamp(this.getTemper() + p_30654_, 0, this.getMaxTemper());
        this.setTemper(i);
        return i;
    }

    public boolean isWearingHarness() {
        return this.isWearingPullingHarness() || this.isWearingRodeoHarness();
    }

    public boolean isWearingRodeoHarness() {
        return (this.getItemBySlot(EquipmentSlot.CHEST).is(LOItems.RODEO_HARNESS.get()) && !this.getItemBySlot(EquipmentSlot.CHEST).isEmpty());
    }

    public boolean isWearingPullingHarness() {
        return this.getItemBySlot(EquipmentSlot.CHEST).is(LOItems.WAGON_HARNESS.get()) && !this.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
    }

    public boolean isWearingMartingale() {
        return this.getItemBySlot(EquipmentSlot.CHEST).is(LOItems.MARTINGALE_HARNESS.get()) && !this.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
    }

    public void tickRidden(Player player, Vec3 vec3) {
        Vec2 vec2 = this.getRiddenRotation(player);

        //bringing back the old horse head turning feature, because who thought getting rid of it was a good idea

        float degrees = Mth.wrapDegrees(vec2.y - this.getYRot());
        float playerXRot = vec2.x;
        this.setXRot(vec2.x);
        this.xRotO = this.getXRot();
        float yRot = this.getYRot();
        float maxHeadYRot = 25.0f;
        this.yHeadRot = yRot + Mth.clamp(degrees, -maxHeadYRot, maxHeadYRot);
        this.setXRot(playerXRot);
        this.xRotO = this.getXRot();

        if (LivestockOverhaulCommonConfig.OLD_HORSE_TURNING.get()) {
            if (Math.abs(degrees) > maxHeadYRot) {
                float turnSpeed = 8.0f;
                float yaw = yRot + Mth.clamp(degrees, -turnSpeed, turnSpeed);
                this.setYRot(yaw);
                this.yRotO = yaw;
                this.yBodyRot = yaw;
            }
        } else {
            this.setRot(vec2.y, vec2.x);
            this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        }

        if (this.isControlledByLocalInstance()) {
            if (vec3.z <= 0.0D) {
                this.gallopSoundCounter = 0;
            }

            if (this.onGround()) {
                this.setIsJumping(false);
                if (this.playerJumpPendingScale > 0.0F && !this.isJumping()) {
                    this.executeRidersJump(this.playerJumpPendingScale, vec3);
                }

                this.playerJumpPendingScale = 0.0F;
            }
        }
    }

    public Vec3 getRiddenInput(Player player, Vec3 vec3) {
        if (this.onGround() && this.playerJumpPendingScale == 0.0F && this.isStanding() && !this.allowStandSliding) {
            return Vec3.ZERO;
        } else {
            float strafe = player.xxa * 0.5F;
            float forward = player.zza;
            if (forward <= 0.0F) {
                forward *= 0.25F;
            }

            return new Vec3((double)strafe, 0.0D, (double)forward);
        }
    }

    public static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("3c50e848-b2e3-404a-9879-7550b12dd09b");
    public static final UUID SHOE_MODIFIER_UUID = UUID.fromString("d9b2d63d-5baf-4f2d-9e24-d80b02e6d17c");
    public static final UUID SPRINT_SPEED_MOD_UUID = UUID.fromString("c9379664-01b5-4e19-a7e9-11264453bdce");
    public static final UUID WALK_SPEED_MOD_UUID = UUID.fromString("59b55c98-e39b-45e2-846c-f91f3e9ea861");

    public static final AttributeModifier SPRINT_SPEED_MOD = new AttributeModifier(SPRINT_SPEED_MOD_UUID, "Sprint speed mod", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final AttributeModifier WALK_SPEED_MOD = new AttributeModifier(WALK_SPEED_MOD_UUID, "Walk speed mod", -0.7D, AttributeModifier.Operation.MULTIPLY_TOTAL); // KEEP THIS NEGATIVE. It is calculated by adding 1. So -0.1 actually means 0.9

    public static final EntityDataAccessor<ItemStack> DECOR_ITEM = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.ITEM_STACK);
    public static final EntityDataAccessor<ItemStack> SADDLE_ITEM = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.ITEM_STACK);

    public boolean shouldEmote;

    public boolean isGroundTied() {
        return this.isSaddled() && !this.isVehicle() && LivestockOverhaulCommonConfig.GROUND_TIE.get();
    }

    public boolean isOnSand() {
        BlockState blockState = this.level().getBlockState(this.blockPosition().below());
        return blockState.is(LOTags.Blocks.SAND);
    }

    public boolean isOnSnow() {
        BlockState blockState = this.level().getBlockState(this.blockPosition().below());
        return blockState.is(Blocks.SNOW) || blockState.is(Blocks.SNOW_BLOCK) || blockState.is(Blocks.POWDER_SNOW);
    }

    public void applySlownessEffect() {
        MobEffectInstance slownessEffectInstance = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, false);
        this.addEffect(slownessEffectInstance);
    }

    public boolean hasSlownessEffect() {
        return this.hasEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }

    public void removeSlownessEffect() {
        this.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }

    public AbstractOMount(EntityType<? extends AbstractOMount> entityType, Level level) {
        super(entityType, level);
    }

    public abstract void playEmote(String emoteName, String loopType);

    public void openInventory(Player player) {
        if(player instanceof ServerPlayer serverPlayer && this.isTamed() && ((this.isOwnedBy(player) && this.isLocked()) || !this.isLocked())) {
            NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
                return new OMountMenu(containerId, inventory, this.inventory, this);
            }, this.getDisplayName()), (data) -> {
                data.writeInt(this.getInventorySize());
                data.writeInt(this.getId());
            });
        }
    }

    public void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.updateContainerEquipment();
        this.itemHandler = net.minecraftforge.common.util.LazyOptional.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this.inventory));
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        this.openInventory(player);
    }

    @Override
    public boolean canParent() {
        return !this.isVehicle() && !this.isPassenger() && this.isTamed() && !this.isBaby() && this.isInLove();
    }

    public boolean isShoe(ItemStack itemStack) {
        return itemStack.getItem() instanceof HorseShoeItem;
    }

    public boolean canWearShoes() {
        return false;
    }

    public ItemStack getShoes() {
        return this.getItemBySlot(EquipmentSlot.CHEST);
    }

    public void setShoes(ItemStack itemStack) {
        this.setItemSlot(EquipmentSlot.CHEST, itemStack);
        this.setDropChance(EquipmentSlot.CHEST, 0f);
    }

    public void setShoeEquipment(ItemStack itemStack) {
        this.setShoes(itemStack);
        if (!this.level().isClientSide) {
            this.getAttribute(Attributes.ARMOR).removeModifier(SHOE_MODIFIER_UUID);

            if (itemStack.getItem() instanceof HorseShoeItem horseShoeItem) {
                int protection = horseShoeItem.getProtection();
                if (protection > 0) {
                    this.getAttribute(Attributes.ARMOR).addTransientModifier(
                            new AttributeModifier(SHOE_MODIFIER_UUID, "Horse shoe armor bonus", (double) protection, AttributeModifier.Operation.ADDITION)
                    );
                }
            }
        }
    }

    public int sleepCounter = this.random.nextInt(300) + 300;
    public int staySleepingCounter = 0;
    private boolean sleeping = false;
    public boolean isSleeping() {
        return this.sleeping;
    }
    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }
    public boolean shouldSleep() {
        return ((this.level().isNight() && !this.isVehicle()) || (this.isGroundTied() && !this.isVehicle()));
    }

    public boolean isOwnedBy(LivingEntity entity) {
        return entity == this.getOwner();
    }

    public boolean isWearingShoes() {
        return !this.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby() && this.isTamed();
    }

    @Override
    public boolean isSaddled() {
        return !this.entityData.get(SADDLE_ITEM).isEmpty();
    }

    public boolean isSaddle(ItemStack itemStack) {
        return itemStack.getItem() instanceof SaddleItem;
    }

    @Override
    public void equipSaddle(@Nullable SoundSource soundSource) {
        // NOTE(EVNGLX): Handled in the interact method instead
    }

    public void setSaddleItem(ItemStack itemStack) {
        this.entityData.set(SADDLE_ITEM, itemStack);
    }

    public ItemStack getSaddleItem() {
        return this.entityData.get(SADDLE_ITEM);
    }

    @Override
    public boolean isArmor(ItemStack itemStack) {
        return itemStack.getItem() instanceof HorseArmorItem || itemStack.is(LOTags.Items.ARMOR_FOR_O_MOUNTS) || itemStack.getItem() instanceof LightHorseArmorItem;
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() - 0.25D;
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

    public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.INT);
    public int getGender() {
        return this.entityData.get(GENDER);
    }
    public void setGender(int gender) {
        this.entityData.set(GENDER, gender);
    }

    public boolean hasGrowableHair() {
        return false;
    }

    @Override
    protected boolean canAddPassenger(Entity p_20354_) {
        return super.canAddPassenger(p_20354_);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.is(Items.SHEARS) && player.isShiftKeyDown() && this.isOwnedBy(player)) {
            if (this.isEquine(this)) {
                AbstractOMount equine = this;
                equine.setFlowerType(0);
                equine.setFlowerItem(Items.AIR.getDefaultInstance());
            }

            if (this.hasChest() && this.isOwnedBy(player)) {
                this.dropEquipment();
                this.inventory.removeAllItems();
                this.setChest(false);
                this.playChestEquipsSound();

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }

        if (itemStack.is(LOItems.MOUNT_KEY.get()) && player.isShiftKeyDown() && this.isOwnedBy(player) && this.isTamed()) {
            if (!this.isLocked()) {
                this.setLocked(true);
                if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                    player.displayClientMessage(Component.translatable("Mount Locked, [DEBUG]Owned By: " + this.getOwner()).withStyle(ChatFormatting.GOLD), true);
                } else {
                    player.displayClientMessage(Component.translatable("Mount Locked").withStyle(ChatFormatting.GOLD), true);
                }
            } else {
                this.setLocked(false);
                if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
                    player.displayClientMessage(Component.translatable("Mount Unlocked, [DEBUG]Owned By: " + this.getOwner()).withStyle(ChatFormatting.GOLD), true);
                } else {
                    player.displayClientMessage(Component.translatable("Mount Unlocked").withStyle(ChatFormatting.GOLD), true);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemStack.is(LOItems.MANE_SCISSORS.get()) && this.hasGrowableHair() && (this.isOwnedBy(player) || !this.isTamed())) {
            OHorse oHorse = (OHorse) this;
            if (player.isShiftKeyDown() && LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH.get()) {
                if (oHorse.getManeType() == 3 || oHorse.getManeType() == 2) {
                    oHorse.setManeType(0);
                } else if (oHorse.getManeType() == 0) {
                    oHorse.setManeType(3);
                }
            } else if ((!player.isShiftKeyDown() && oHorse.getManeType() < 4) || !LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH.get()) {
                OHorse.Mane currentMane = OHorse.Mane.values()[oHorse.getManeType()];
                OHorse.Mane nextMane = currentMane.next();
                oHorse.maneGrowthTick = 0;
                oHorse.setManeType(nextMane.ordinal());
            }
            this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemStack.is(LOItems.TAIL_SCISSORS.get()) && this.hasGrowableHair() && !this.isUnicorn(this) && (this.isOwnedBy(player) || !this.isTamed())) {
            OHorse oHorse = (OHorse) this;
            if (player.isShiftKeyDown() && LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH.get()) {
                if (oHorse.getTailType() == 3 || oHorse.getTailType() == 2) {
                    oHorse.setTailType(0);
                } else if (oHorse.getTailType() == 0) {
                    oHorse.setTailType(3);
                }
            } else if ((!player.isShiftKeyDown() && oHorse.getTailType() < 4) || !LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH.get()) {
                OHorse.Tail currentTail = OHorse.Tail.values()[oHorse.getTailType()];
                OHorse.Tail nextTail = currentTail.next();
                oHorse.tailGrowthTick = 0;
                oHorse.setTailType(nextTail.ordinal());
            }
            this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemStack.is(Items.PACKED_ICE) && this.isHorse(this) && ((player.getAbilities().instabuild && LivestockOverhaulCommonConfig.CREATIVE_BRANDING.get()) || !LivestockOverhaulCommonConfig.CREATIVE_BRANDING.get())) {
            OHorse oHorse = (OHorse) this;
            if (!oHorse.isBranded() && (!oHorse.isTamed() || player.getAbilities().instabuild)) {
                oHorse.setIsBranded(true);
                this.playSound(SoundEvents.LAVA_EXTINGUISH, 0.5f, 1f);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }

        if (itemStack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
            if (player.isShiftKeyDown()) {
                if (this.getVariant() > 0) {
                    this.setVariant(this.getVariant() - 1);
                    this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
                    return InteractionResult.SUCCESS;
                }
            }
            this.setVariant(this.getVariant() + 1);
            this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(LOItems.MARKING_OSCILLATOR.get()) && player.getAbilities().instabuild) {
            if (player.isShiftKeyDown()) {
                if (this.getOverlayVariant() > 0) {
                    this.setOverlayVariant(this.getOverlayVariant() - 1);
                    this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
                    return InteractionResult.SUCCESS;
                }
            }
            this.setOverlayVariant(this.getOverlayVariant() + 1);
            this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(LOItems.BREED_OSCILLATOR.get()) && player.getAbilities().instabuild) {
            if (this.isHorse(this) || this.isMule(this) || this.isCamel(this)) {
                if (player.isShiftKeyDown()) {
                    if (this.getBreed() > 0) {
                        this.setBreed(this.getBreed() - 1);
                        this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
                        return InteractionResult.SUCCESS;
                    }
                }
                if (this.isHorse(this)) {
                    OHorse oHorse = (OHorse) this;
                    HorseBreed currentBreed = HorseBreed.values()[oHorse.getBreed()];
                    HorseBreed nextBreed = currentBreed.next();
                    oHorse.setBreed(nextBreed.ordinal());
                }
                if (this.isMule(this)) {
                    OMule mule = (OMule) this;
                    MuleBreed currentBreed = MuleBreed.values()[mule.getBreed()];
                    MuleBreed nextBreed = currentBreed.next();
                    mule.setBreed(nextBreed.ordinal());
                }
                if (this.isCamel(this)) {
                    OCamel camel = (OCamel) this;
                    CamelBreed.Breed currentBreed = CamelBreed.Breed.values()[camel.getBreed()];
                    CamelBreed.Breed nextBreed = currentBreed.next();
                    camel.setBreed(nextBreed.ordinal());
                }
                this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
                return InteractionResult.SUCCESS;
            }
        }

        if (this.isEquine(this) && this.isOwnedBy(player)) {
            AbstractOMount mount = this;
            if (this.isEquine(this) && itemStack.is(LOTags.Items.HAIR_FLOWERS)) {
                if (mount.getFlowerType() == 0) {
                    mount.setFlowerType(1);
                } else if (mount.getFlowerType() == 1) {
                    mount.setFlowerType(2);
                } else if (mount.getFlowerType() == 2) {
                    mount.setFlowerType(0);
                }
                mount.setFlowerItem(itemStack);
                this.playSound(SoundEvents.FLOWERING_AZALEA_PLACE, 0.5f, 1f);
                return InteractionResult.SUCCESS;
            }
        }

        if (this.isHorse(this) && itemStack.is(Items.HEART_OF_THE_SEA) && this.isOwnedBy(player)) {
            OHorse oHorse = (OHorse) this;
            if (oHorse.isUndead()) {
                oHorse.setDecompVariant(0);
                oHorse.setUndead(false);
                this.level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
                this.level().playSound(null, this, SoundEvents.TOTEM_USE, SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(this.level().random, 0.8F, 1.2F));
            }
            return InteractionResult.SUCCESS;
        }

        if (this.isHorse(this) && itemStack.is(Items.COAL) && this.isOwnedBy(player)) {
            OHorse oHorse = (OHorse) this;
            if (oHorse.isUndead()) {
                itemStack.finishUsingItem(level(), player);
                this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);

                if (oHorse.canDecompose()) {
                    oHorse.setCanDecompose(false);
                } else if (!oHorse.canDecompose()) {
                    oHorse.setCanDecompose(true);
                }
            }
            return InteractionResult.SUCCESS;
        }

        if (!this.isBaby()) {
            if (this.isTamed() && player.isSecondaryUseActive()) {
                this.openInventory(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            if(this.isVehicle()) {
                if (this.canAddPassenger(this)) {
                    this.doPlayerRide(player);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
                return super.mobInteract(player, hand);
            }
        }

        if (itemStack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
            player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
            player.setItemInHand(hand, itemstack1);
            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
            player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
            player.setItemInHand(hand, itemstack1);
            return InteractionResult.SUCCESS;
        }

        if (this.isSaddle(itemStack) && this.isSaddleable() && !this.isSaddled()) {
            // item is a saddle, entity can be saddled, and isn't already wearing a saddle
            if (!this.level().isClientSide) {
                this.level().gameEvent(this, GameEvent.EQUIP, this.position());
                ItemStack saddleItem = itemStack.copy();
                this.inventory.setItem(this.saddleSlot(), saddleItem);
                itemStack.shrink(1);

                this.setSaddleItem(saddleItem);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (!itemStack.isEmpty()) {
            if (this.isFood(itemStack)) {
                return this.fedFood(player, itemStack);
            }

            InteractionResult interactionResult = itemStack.interactLivingEntity(player, this, hand);
            if (interactionResult.consumesAction()) {
                return interactionResult;
            }

            if (!this.isTamed()) {
                this.makeMad();
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            if (!this.hasChest() && itemStack.is(Blocks.CHEST.asItem()) && this.isOwnedBy(player)) {
                this.setChest(true);
                this.playChestEquipsSound();
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                this.createInventory();
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            boolean canSaddle = !this.isBaby() && !this.isSaddled() && this.isSaddle(itemStack);
            if (this.isArmor(itemStack) || canSaddle) {
                this.openInventory(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }

        //star worm equestrian horse compat (only spawns the base variant. i dont know, sorry)
        if (itemStack.is(LOTags.Items.SWEM_CANTAZARITE_POTION) && this.isHorse(this) && this.isOwnedBy(player)) {
            if (!player.level().isClientSide) {
                Entity entity = this;

                ResourceLocation swemHorseId = new ResourceLocation("swem", "swem_horse");

                EntityType<?> swemHorseType = EntityType.byString(swemHorseId.toString()).orElse(null);

                if (swemHorseType != null) {
                    Entity newEntity = swemHorseType.create(entity.level());
                    if (newEntity != null) {
                        newEntity.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                        entity.level().addFreshEntity(newEntity);
                        entity.discard();
                    }
                } else {
                    return InteractionResult.PASS;
                }
            }
            return InteractionResult.SUCCESS;
        }

        if(this.isBaby()) {
            return super.mobInteract(player, hand);
        } else {
            this.doPlayerRide(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
    }

    protected void doPlayerRide(Player player) {
        this.setEating(false);
        this.setStanding(false);
        if (!this.level().isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            if ((this.isLocked() && this.isOwnedBy(player)) || !this.isLocked() || !this.isTamed()) {
                player.startRiding(this);
            }
        }

    }

    @Override
    public boolean canWearArmor() {
        return true;
    }

    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> LOCKED = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.BOOLEAN);
    public boolean isLocked() {
        return this.entityData.get(LOCKED);
    }
    public void setLocked(boolean locked) {
        this.entityData.set(LOCKED, locked);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_CHEST, false);
        this.entityData.define(DECOR_ITEM, ItemStack.EMPTY);
        this.entityData.define(SADDLE_ITEM, ItemStack.EMPTY);
        this.entityData.define(LOCKED, false);
    }

    public ItemStack getDecorItem() {
        return this.entityData.get(DECOR_ITEM);
    }

    public void setDecorItem(ItemStack decorItem) {
        this.entityData.set(DECOR_ITEM, decorItem);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if(!this.getSaddleItem().isEmpty()) {
            compoundTag.put("SaddleItem", this.getSaddleItem().save(new CompoundTag()));
        }

        if(!this.getArmor().isEmpty()) {
            compoundTag.put("ArmorItem", this.getArmor().save(new CompoundTag()));
        }

        if(!this.getDecorItem().isEmpty()) {
            compoundTag.put("DecorItem", this.getDecorItem().save(new CompoundTag()));
        }

        compoundTag.putBoolean("Locked", this.isLocked());

        if (this.hasChest()) {
            ListTag listtag = new ListTag();

            for(int i = 2; i < this.inventory.getContainerSize(); i++) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte)i);
                    itemstack.save(compoundtag);
                    listtag.add(compoundtag);
                }
            }

            compoundTag.put("Items", listtag);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if(compoundTag.contains("SaddleItem")) {
            ItemStack saddleItem = ItemStack.of(compoundTag.getCompound("SaddleItem"));
            this.setSaddleItem(saddleItem);
            this.inventory.setItem(this.saddleSlot(), saddleItem);
        }

        if(compoundTag.contains("ArmorItem")) {
            ItemStack armorItem = ItemStack.of(compoundTag.getCompound("ArmorItem"));
            this.setArmorEquipment(armorItem);
            this.inventory.setItem(this.armorSlot(), armorItem);
        }

        if(compoundTag.contains("DecorItem")) {
            ItemStack decorItem = ItemStack.of(compoundTag.getCompound("DecorItem"));
            this.setDecorItem(decorItem);
            this.inventory.setItem(this.decorSlot(), decorItem);
        }

        if (compoundTag.contains("Locked")) {
            this.setLocked(compoundTag.getBoolean("Locked"));
        }

        if (this.hasChest()) {
            ListTag listtag = compoundTag.getList("Items", 10);

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
    public void playGallopSound(SoundType soundType) {
        super.playGallopSound(soundType);
        if(this.random.nextInt(10) == 0) {
            this.playSound(SoundEvents.HORSE_BREATHE, soundType.getVolume() * 0.6f, soundType.getPitch());
        }

        ItemStack itemStack = this.inventory.getItem(1);
        if(this.isArmor(itemStack)) {
            itemStack.onHorseArmorTick(this.level(), this);
        }
    }

    public ItemStack getArmor() {
        return this.getItemBySlot(EquipmentSlot.CHEST);
    }

    public void setArmor(ItemStack itemStack) {
        this.setItemSlot(EquipmentSlot.CHEST, itemStack);
        this.setDropChance(EquipmentSlot.CHEST, 0f);
    }

    public void setArmorEquipment(ItemStack itemStack) {
        this.setArmor(itemStack);
        if (!this.level().isClientSide) {
            this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);

            if (itemStack.getItem() instanceof HorseArmorItem horseArmorItem) {
                int protection = horseArmorItem.getProtection();
                if (protection > 0) {
                    this.getAttribute(Attributes.ARMOR).addTransientModifier(
                            new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double) protection, AttributeModifier.Operation.ADDITION)
                    );
                }
            }
            if (itemStack.getItem() instanceof LightHorseArmorItem horseArmorItem) {
                int protection = horseArmorItem.getProtection();
                if (protection > 0) {
                    this.getAttribute(Attributes.ARMOR).addTransientModifier(
                            new AttributeModifier(ARMOR_MODIFIER_UUID, "Light armor bonus", (double) protection, AttributeModifier.Operation.ADDITION)
                    );
                }
            }
        }
    }

    @Override
    public void updateContainerEquipment() {
       if(!this.level().isClientSide) {
           this.setSaddleItem(this.inventory.getItem(this.saddleSlot()));
           this.setArmorEquipment(this.inventory.getItem(this.armorSlot()));
           this.setDropChance(EquipmentSlot.CHEST, 0f);
           this.setDecorItem(this.inventory.getItem(this.decorSlot()));
       }
    }

    public int saddleSlot() {
        return 0;
    }

    public int armorSlot() {
        return 1;
    }

    public int decorSlot() {
        return 2;
    }

    @Override
    public void containerChanged(Container container) {
        ItemStack prevArmor = this.getArmor();
        super.containerChanged(container);
        ItemStack newArmor = this.getArmor();
        if(this.tickCount > 20 && this.isArmor(newArmor) && prevArmor != newArmor) {
            this.playSound(SoundEvents.HORSE_ARMOR, 0.5f, 1f);
        }
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
        this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPRINT_SPEED_MOD);
        this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(WALK_SPEED_MOD);
        return super.getDismountLocationForPassenger(livingEntity);
    }

    public boolean isOx(Entity entity) {
        return entity.getType() == EntityTypes.O_COW_ENTITY.get();
    }
    public boolean isHorse(Entity entity) {
        return entity.getType() == EntityTypes.O_HORSE_ENTITY.get();
    }
    public boolean isUnicorn(Entity entity) {
        return entity.getType() == EntityTypes.UNICORN_ENTITY.get();
    }
    public boolean isMule(Entity entity) {
        return entity.getType() == EntityTypes.O_MULE_ENTITY.get();
    }
    public boolean isDonkey(Entity entity) {
        return entity.getType() == EntityTypes.O_DONKEY_ENTITY.get();
    }
    public boolean isCamel(Entity entity) {
        return entity.getType() == EntityTypes.O_CAMEL_ENTITY.get();
    }
    public boolean isEquine(Entity entity) {
        return entity.getType() == EntityTypes.O_HORSE_ENTITY.get() ||
                entity.getType() == EntityTypes.O_DONKEY_ENTITY.get() ||
                entity.getType() == EntityTypes.O_MULE_ENTITY.get() ||
                entity.getType() == EntityTypes.UNICORN_ENTITY.get();
    }

    public void handleSpeedRequest(int speedMod) {
        AttributeInstance movementSpeed = this.getAttribute(Attributes.MOVEMENT_SPEED);

        if (speedMod == -1 && movementSpeed.hasModifier(SPRINT_SPEED_MOD)) {
            movementSpeed.removeModifier(SPRINT_SPEED_MOD);
        } else if (speedMod == -1 && !movementSpeed.hasModifier(WALK_SPEED_MOD)) {
            movementSpeed.addTransientModifier(WALK_SPEED_MOD);
        } else if (speedMod == 1 && movementSpeed.hasModifier(WALK_SPEED_MOD)) {
            movementSpeed.removeModifier(WALK_SPEED_MOD);
        } else if (speedMod == 1 && !movementSpeed.hasModifier(SPRINT_SPEED_MOD) && !this.isOx(this)) {
            movementSpeed.addTransientModifier(SPRINT_SPEED_MOD);
        }
    }

    double x = this.getX() - this.xo;
    double z = this.getZ() - this.zo;
    public boolean isMoving = (x * x + z * z) > 0.0001;

    @Override
    public boolean handleEating(Player player, ItemStack stack) {
        int i = 0;
        int j = 0;
        float f = 0.0F;
        boolean flag = false;
        if (stack.is(LOTags.Items.O_HORSE_EATS)) {
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

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.INT);
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
        this.entityData.set(VARIANT_TEXTURE, OHorseModel.Variant.variantFromOrdinal(variant).resourceLocation);
    }
    public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(AbstractOMount.class, LivestockOverhaul.RESOURCE_LOCATION);
    public ResourceLocation getTextureResource() {
        return this.entityData.get(VARIANT_TEXTURE);
    }
    public void setVariantTexture(String variant) {
        ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
        if (resourceLocation == null) {
            resourceLocation = OHorseModel.Variant.BAY.resourceLocation;
        }
        this.entityData.set(VARIANT_TEXTURE, resourceLocation);
    }


    public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.INT);
    public int getOverlayVariant() {
        return this.entityData.get(OVERLAY);
    }
    public void setOverlayVariant(int variant) {
        this.entityData.set(OVERLAY, variant);
        this.entityData.set(OVERLAY_TEXTURE, EquineMarkingOverlay.overlayFromOrdinal(variant).resourceLocation);
    }
    public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(AbstractOMount.class, LivestockOverhaul.RESOURCE_LOCATION);
    public ResourceLocation getOverlayLocation() {
        return this.entityData.get(OVERLAY_TEXTURE);
    }
    public void setOverlayVariantTexture(String variant) {
        ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
        if (resourceLocation == null) {
            resourceLocation = EquineMarkingOverlay.NONE.resourceLocation;
        }
        this.entityData.set(OVERLAY_TEXTURE, resourceLocation);
    }

    public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.INT);
    public ResourceLocation getModelResource() {
        return HorseBreed.breedFromOrdinal(getBreed()).resourceLocation;
    }
    public int getBreed() {
        return this.entityData.get(BREED);
    }
    public void setBreed(int breed) {
        this.entityData.set(BREED, breed);
    }

    public static final EntityDataAccessor<ItemStack> FLOWER_ITEM = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.ITEM_STACK);
    public ItemStack getFlowerItem() {
        return this.entityData.get(FLOWER_ITEM);
    }
    public void setFlowerItem(ItemStack decorItem) {
        this.entityData.set(FLOWER_ITEM, decorItem);
    }

    public static final EntityDataAccessor<Integer> FLOWER_TYPE = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.INT);
    public int getFlowerType() {
        return this.entityData.get(FLOWER_TYPE);
    }
    public void setFlowerType(int decompVariant) {
        this.entityData.set(FLOWER_TYPE, decompVariant);
    }

    public static final EntityDataAccessor<Boolean> BRANDED = SynchedEntityData.defineId(AbstractOMount.class, EntityDataSerializers.BOOLEAN);
    public boolean isBranded() {
        return this.entityData.get(BRANDED);
    }
    public void setIsBranded(boolean canBeBranded) {
        this.entityData.set(BRANDED, canBeBranded);
    }

}